package com.pika.validation.annotation;

import com.pika.validation.IPv4ConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * <p>自定义参数校验注解</p>
 * <p>IPv4地址校验</p>
 *
 * @author pikachu
 * @since 2023/5/5 20:28
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPv4ConstraintValidator.class)
public @interface IPv4 {

    String message() default "{com.pika.validation.annotation.IPv4}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 额外允许的IP段
     */
    String[] allows() default {};

}
