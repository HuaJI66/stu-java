package com.pika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class StuMybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuMybatisPlusApplication.class, args);
    }

}
