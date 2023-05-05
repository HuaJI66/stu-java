package com.pika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pikachu
 * @since 2023/5/3 16:39
 */
@SpringBootApplication
@MapperScan
public class JPATestApp {
    public static void main(String[] args) {
        SpringApplication.run(JPATestApp.class, args);
    }
}
