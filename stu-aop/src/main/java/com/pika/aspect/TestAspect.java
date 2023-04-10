package com.pika.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 切点表达式：execution([访问控制权限修饰符] 返回值类型 [全限定类名]方法名(形式参数列表) [异常])
 *
 * @author pikachu
 * @since 2023/4/1 21:56
 */
@Component
@Aspect
public class TestAspect {
    /**
     * 定义切点
     */
    @Pointcut("execution(public String com.pika.service.UserService.login(..))")
    public void pointcut() {
    }

    /**
     * 前置通知：目标方法执行前
     */
    @Before("pointcut()")
    public void beforeLogin(JoinPoint joinPoint) {
        System.out.println("before advice..");
    }

    /**
     * 环绕通知：调用方法前后
     */
    @Around("execution(* com.pika.service.UserService.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("pjp.getTarget() = " + pjp.getTarget());
        System.out.println("before around advice");
        Object proceed = pjp.proceed();
        System.out.println("after around advice");
        return proceed;
    }

    /**
     * 最终通知 finally：目标方法执行完成(无论shi后发生异常)
     */
    @After("pointcut()")
    public void after() {
        System.out.println("after advice");
    }

    /**
     * 后置：目标方法返回结果后
     */
    @AfterReturning("pointcut()")
    public void afterReturn() {
        System.out.println("afterReturn advice");
    }

    /**
     * 异常通知：目标方法抛出异常后
     */
    @AfterThrowing("pointcut()")
    public void afterThrow() {
        System.out.println("afterThrow advice");
    }
}
