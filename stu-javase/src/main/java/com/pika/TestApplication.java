package com.pika;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/25 16:33
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println(beanFactory);
    }
}
