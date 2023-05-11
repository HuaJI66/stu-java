package com.pika.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 为通用返回结果 {@link com.pika.utils.R} 添加token字段，被标注的接口应为标识性接口，不处理其它业务逻辑
 * <p>默认需要从请求参数中获取 request_uri (需要幂等性的接口地址) 作为缓存 token 的 key</p>
 *
 * @author pikachu
 * @see Idempotent
 * @since 2023/5/10 21:47
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Token {
    String REQUEST_URI = "reqUri";

    /**
     * 请求参数中的幂等性接口 URI (不包含 http协议 和 主机名,只有路径)
     */
    String requestURI() default REQUEST_URI;

    /**
     * 为响应添加防重字段，默认名称为 token，需要响应类型为 application/json；
     * <p>下次请求（需要幂等性）接口时应将其携带该 token</p>
     */
    String value() default "token";

    /**
     * token 过期时间,默认为 30
     */
    long expire() default 30;

    /**
     * 过期时间单位,默认为分钟
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
