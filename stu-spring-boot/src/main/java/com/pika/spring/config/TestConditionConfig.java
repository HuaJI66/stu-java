package com.pika.spring.config;

import com.pika.spring.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pikachu
 * @since 2023/4/22 17:48
 */
@Configuration
@ConditionalOnBean(value = TestController.class)
@Slf4j
public class TestConditionConfig {
    public TestConditionConfig() {
        log.warn("+++++++++++++++");
    }
}
