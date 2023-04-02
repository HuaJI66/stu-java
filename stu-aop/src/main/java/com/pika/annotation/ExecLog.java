package com.pika.annotation;

import java.lang.annotation.*;

/**
 * @author pikachu
 * @since 2023/4/2 9:50
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExecLog {
    String value() default "执行耗时";
}
