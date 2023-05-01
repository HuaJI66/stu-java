package com.pika.spring.annocation;


import java.lang.annotation.*;

/**
 * @author pi'ka'chu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Inherited
@Documented
public @interface Login {
}
