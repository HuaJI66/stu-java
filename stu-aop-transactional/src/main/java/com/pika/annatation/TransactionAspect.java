package com.pika.annatation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/4/9 20:29
 */
@Aspect
@Component
public class TransactionAspect {
    @Pointcut("@annotation(com.pika.annatation.Transactional)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            System.out.println("提交事务...");
        } catch (Exception e) {
            System.out.println("回滚事务...");
        }
        return proceed;
    }
}
