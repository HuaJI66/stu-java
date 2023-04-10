package com.pika.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aop嵌套切面优先级，数字越低，优先级越高,执行顺序类似过滤链
 *
 * @author pikachu
 * @since 2023/4/2 17:21
 */
//@Aspect
//@Component
//@Order(1)
public class TestOrderAspect {
    @Pointcut("com.pika.aspect.TestAspect.pointcut()")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("TestOrderAspect.before()");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("TestOrderAspect before around");
        Object proceed = joinPoint.proceed();
        System.out.println("TestOrderAspect after around");
        return proceed;
    }
}
