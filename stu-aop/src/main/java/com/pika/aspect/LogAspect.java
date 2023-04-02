package com.pika.aspect;

import com.pika.annotation.ExecLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author pikachu
 * @since 2023/4/2 9:53
 */
@Aspect
//@Component
public class LogAspect {
    @Pointcut("@annotation(com.pika.annotation.ExecLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long execTime = System.currentTimeMillis() - start;
        saveLog(joinPoint, execTime);
        return proceed;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long execTime) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ExecLog execLog = method.getDeclaredAnnotation(ExecLog.class);
        String value = execLog.value();
        System.out.println(method.getName() + value + "执行耗时: " + execTime + "ms");
    }
}
