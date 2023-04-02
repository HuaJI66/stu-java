package com.pika.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/4/2 11:26
 */
@Aspect
@Component
public class TestNoAnnotationPointcutAspect {
    //    @Pointcut("within(com.pika.service.PayService)")
//    @Pointcut("within(com.pika.service.impl.*)")
//    @Pointcut("this(com.pika.service.impl.PayServiceImpl1)")
//    @Pointcut("this(com.pika.service.PayService)")
//    @Pointcut("target(com.pika.service.PayService)")
//    @Pointcut("args()")
    @Pointcut("bean(userController)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("className = " + className);
        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
