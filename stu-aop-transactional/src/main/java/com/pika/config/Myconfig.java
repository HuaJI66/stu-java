package com.pika.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author pikachu
 * @since 2023/4/10 10:43
 */
@Configuration
@ComponentScan("com.pika")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class Myconfig {
}
