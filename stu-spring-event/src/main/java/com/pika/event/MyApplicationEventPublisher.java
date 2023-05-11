package com.pika.event;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/5/10 9:39
 */
@Component
public class MyApplicationEventPublisher {
    @Resource
    private ApplicationContext applicationContext;

    public void publish(String msg) {
        applicationContext.publishEvent(new MyApplicationEvent(this, msg));
    }
}
