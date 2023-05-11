package com.pika.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/5/10 9:15
 */
@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        log.info("receive message:{}", event.getMessage());
    }
}
