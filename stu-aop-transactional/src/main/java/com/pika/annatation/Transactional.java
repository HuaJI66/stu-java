package com.pika.annatation;

import java.lang.annotation.*;

/**
 * @author pikachu
 * @since 2023/4/9 17:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Transactional {
    Class<? extends Throwable>[] value() default {Exception.class};
}
