package com.pika.annotation;

import java.lang.annotation.*;

/**
 * @author pikachu
 * @since 2023/3/31 20:09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
