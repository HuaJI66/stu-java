package com.pika.annotation;

import java.lang.annotation.*;

/**
 * 接口幂等性注解
 * <p>一个幂等操作的特点是指其多次执行所产生的影响均与一次执行的影响相同。
 * 在业务中也就是指的，多次调用方法或者接口不会改变业务状态，可以保证重复调用的结果和单次调用的结果一致。</p>
 *
 * @author pikachu
 * @since 2023/5/9 22:56
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Idempotent {
    /**
     * 请求携带的防重令牌名称
     */
    String value() default "token";

    /**
     * token 携带位置,默认为请求头 request_header
     */
    TokenPosition tokenPosition() default TokenPosition.REQUEST_HEADER;

    enum TokenPosition {
        /**
         * 请求头
         */
        REQUEST_HEADER,
        /**
         * 请求参数
         */
        REQUEST_PARAMETER,
        /**
         * 请求体
         */
        REQUEST_BODY
    }
}
