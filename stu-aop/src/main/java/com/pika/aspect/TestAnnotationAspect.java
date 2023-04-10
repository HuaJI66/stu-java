package com.pika.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 匹配持有某注解
 * <p>@within(注解类型)：匹配指定的注解内定义的方法。调用目标方法的时候，通过java中`Method.getDeclaringClass()`获取当前的方法是哪个类中定义的，然后会看这个类上是否有指定的注解。</p>
 * <p>@target(注解类型)：判断目标对象target类型上是否有指定的注解；@target中注解类型也必须是全限定类型名。</p>
 * <p>@annotation(注解类型)：匹配被调用的方法上有指定的注解。</p>
 * <p>@args(注解类型)：方法参数所属的类上有指定的注解；注意不是参数上有指定的注解，而是参数类型的类上有指定的注解。</p>
 *
 * @author pikachu
 * @since 2023/4/2 15:55
 */
@Component
@Aspect
public class TestAnnotationAspect {
    //    @Pointcut("@within(com.pika.annotation.ExecLog)")
//    @Pointcut("@annotation(com.pika.annotation.ExecLog)")
//    @Pointcut("@target(com.pika.annotation.ExecLog)")
    @Pointcut("@args(org.springframework.stereotype.Service)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before...");
    }
}
