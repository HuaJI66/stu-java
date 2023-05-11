package com.pika.aspect;

import com.pika.utils.R;
import com.pika.utils.TokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author pikachu
 * @since 2023/5/9 22:58
 */
@Aspect
@Component
@Slf4j
public class IdempotentAspect {

    @Resource
    private TokenUtils tokenUtils;

    @Pointcut("@annotation(com.pika.annotation.Idempotent) || @within(com.pika.annotation.Idempotent)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接口幂等性校验
        boolean isValid = false;
        try {
            final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            isValid = tokenUtils.checkRepetitive(joinPoint, request);
        } catch (Exception e) {
            log.error("检查 token 出错:{}", e.getMessage());
        }
        if (isValid) {
            return joinPoint.proceed();
        }
        // token 校验失败，直接返回通用结果 R 对象
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        R.sendResponse(response, 403, "token 校验失败");
        return null;
    }

}
