package com.pika.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author pikachu
 * @since 2023/5/10 9:12
 */
public class MyApplicationEvent extends ApplicationEvent {
    @Getter
    private String message;

    public MyApplicationEvent(Object source, String msg) {
        super(source);
        this.message = msg;
    }
}
