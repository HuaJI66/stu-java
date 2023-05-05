package com.pika.controller;

import com.pika.scope.PrototypeEntity;
import com.pika.scope.RequestEntity;
import com.pika.scope.SessionEntity;
import com.pika.scope.SingletonEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pikachu
 * @since 2023/5/3 17:39
 */
@RestController
@RequestMapping("test")
public class TestController implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @GetMapping("scope/singleton")
    public String test1() {
        SingletonEntity singletonEntity = applicationContext.getBean(SingletonEntity.class);
        return singletonEntity.toString();
    }

    @GetMapping("scope/prototype")
    public String test2() {
        PrototypeEntity prototypeEntity = applicationContext.getBean(PrototypeEntity.class);
        return prototypeEntity.toString();
    }

    @GetMapping("scope/session")
    public String test3() {
        SessionEntity sessionEntity = applicationContext.getBean(SessionEntity.class);
        return sessionEntity.toString();
    }

    @GetMapping("scope/request")
    public String test4() {
        RequestEntity requestEntity = applicationContext.getBean(RequestEntity.class);
        return requestEntity.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
