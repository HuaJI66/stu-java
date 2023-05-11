package com.pika.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.pika.annotation.Idempotent;
import com.pika.annotation.Token;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author pikachu
 * @since 2023/5/11 8:55
 */
@Component
@Slf4j
public class TokenUtils {
    /**
     * 存放在 redis 中 token 的 key
     * <p>key -> {module}:token:{requestURI}:{userId}</p>
     * <p>value -> token</p>
     */
    @Setter
    public Supplier<String> tokenKeyFormat = () -> "%s:token:%s:%s";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 模块名称
     */
    @Setter
    private Supplier<String> moduleName = () -> "TEST";
    /**
     * 用户标识
     */
    @Setter
    private Supplier<String> userId = () -> "SYSTEM";

    @SuppressWarnings("all")
    private String getCheckToken(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws IOException {
        String token = null;
        Idempotent idempotent = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //先尝试从类中获取 Idempotent 注解，在从方法中获取
        idempotent = joinPoint.getTarget().getClass().getAnnotation(Idempotent.class);
        if (idempotent == null) {
            idempotent = method.getAnnotation(Idempotent.class);
        }
        String tokenName = idempotent.value();
        switch (idempotent.tokenPosition()) {
            case REQUEST_HEADER -> {
                return request.getHeader(tokenName);
            }
            case REQUEST_PARAMETER -> {
                return request.getParameter(tokenName);
            }
            case REQUEST_BODY -> {
                // 判断当前 request_body 是否被读取过
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                // @RequestBody 注解的参数索引
                Integer argIndex = null;
                for (int i = 0; i < parameterAnnotations.length; i++) {
                    for (Annotation annotation : parameterAnnotations[i]) {
                        if (annotation.annotationType().isAssignableFrom(RequestBody.class)) {
                            argIndex = i;
                            //从方法形参(@RequestBody 标注的参数)中获取
                            return (String) JSONUtil.parseObj(joinPoint.getArgs()[argIndex]).get(tokenName);
                        }
                    }
                }
                //从 request 读取
                String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                return (String) JSONUtil.parseObj(requestBody).get(tokenName);
            }
        }
        return token;
    }

    public boolean checkRepetitive(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws IOException {
        String token = getCheckToken(joinPoint, request);
        if (!StringUtils.hasText(token)) {
            return false;
        }
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = stringRedisTemplate.execute(
                new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList(buildTokenKey(request.getRequestURI())),
                token);
        return result != null && result == 1;
    }

    public String buildTokenKey(String requestUri) {
        return tokenKeyFormat.get().formatted(moduleName.get(), requestUri, userId.get());
    }

    public String generateToken(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Token tokenAnno = method.getAnnotation(Token.class);
        String requestUri = request.getParameter(tokenAnno.requestURI());
        String tokenKey = buildTokenKey(requestUri);
        String token = IdUtil.fastSimpleUUID();
        stringRedisTemplate.opsForValue().set(tokenKey, token, tokenAnno.expire(), tokenAnno.timeUnit());
        return token;
    }
}
