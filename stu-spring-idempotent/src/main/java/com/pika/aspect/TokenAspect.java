package com.pika.aspect;

import com.pika.utils.R;
import com.pika.utils.TokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
 * @since 2023/5/11 10:47
 */
@Aspect
@Component
@Slf4j
public class TokenAspect {
    @Resource
    private TokenUtils tokenUtils;

    @Pointcut("@annotation(com.pika.annotation.Token) || @within(com.pika.annotation.Token)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof R) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            ((R) proceed).setToken(tokenUtils.generateToken(request, joinPoint));
        }
        return proceed;
    }
}
