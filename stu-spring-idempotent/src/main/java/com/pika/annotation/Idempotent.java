package com.pika.annotation;

import java.lang.annotation.*;

/**
 * 接口幂等性注解
 * <p>一个幂等操作的特点是指其多次执行所产生的影响均与一次执行的影响相同。
 * 在业务中也就是指的，多次调用方法或者接口不会改变业务状态，可以保证重复调用的结果和单次调用的结果一致。</p>
 * <p>幂等性保证难点在于:</p>
 * <li>代码侵入与重用 —— AOP切面编程</li>
 * <li>如何区分是否为重复请求 —— token 防重令牌</li>
 * <li>请求是否已经成功执行过 —— 目标方法需要再次校验</li>
 * <p>该注解采用 token 令牌机制实现，因此需要客户端请求该接口前先请求获取 token</p>
 * <p>注意:</p>
 * <li>token 校验成功会先删除缓存中 token 再执行目标方法</li>
 * <li>token 校验失败直接返回错误信息</li>
 * <li>接口的幂等性仍需要业务代码操作的幂等性,token 机制作用是限流、防刷</li>
 *
 * @author pikachu
 * @see Token
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
