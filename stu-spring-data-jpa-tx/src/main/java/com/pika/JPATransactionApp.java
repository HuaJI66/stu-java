package com.pika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author pikachu
 * @since 2023/5/3 16:39
 */
@SpringBootApplication
@MapperScan
@EnableAspectJAutoProxy(exposeProxy = true)
public class JPATransactionApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JPATransactionApp.class, args);
        System.out.println();
    }
}
