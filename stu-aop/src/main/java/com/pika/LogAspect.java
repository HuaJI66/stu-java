package com.pika;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/4/1 21:56
 */
@Component
@Aspect
public class LogAspect {
    /**
     * 前置通知：目标方法执行前
     */
    @Before("execution(public String com.pika.UserService.login(..))")
    public void beforeLogin() {
        System.out.println("before advice..");
    }

    /**
     * 环绕通知：调用方法前后
     */
    @Around("execution(public String com.pika.UserService.login(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("pjp.getTarget() = " + pjp.getTarget());
        System.out.println("around advice");
        Object proceed = pjp.proceed();
        System.out.println("around advice");
        return proceed;
    }

    /**
     * 后置通知：目标方法执行完成
     */
    @After("execution(public String com.pika.UserService.login(..))")
    public void after() {
        System.out.println("after advice");
    }

    /**
     * 最终通知：目标方法返回结果后
     */
    @AfterReturning("execution(public String com.pika.UserService.login(..))")
    public void afterReturn() {
        System.out.println("afterReturn advice");
    }

    @AfterThrowing("execution(public String com.pika.UserService.login(..))")
    public void afterThrow() {
        System.out.println("afterThrow advice");
    }
}
