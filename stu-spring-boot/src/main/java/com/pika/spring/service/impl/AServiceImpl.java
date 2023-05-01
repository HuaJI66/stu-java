package com.pika.spring.service.impl;

import com.pika.spring.service.AService;
import com.pika.spring.service.BService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/25 16:35
 */
@Service
public class AServiceImpl implements AService, InitializingBean {
    private String name = "AService";
    //    @Lazy
    @Getter
    @Resource(name = "BServiceImpl")
    private BService bServiceImpl;

    //    public AServiceImpl(BService bService) {
//        this.bService = bService;
//        System.out.println("Init AService....");
//    }
    public AServiceImpl() {
        System.out.println("instantiate AService....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct A");
    }


    @Override
    public String hello() {
        System.out.println(this);
        System.out.println(this.bServiceImpl);
        String s = "AService->BService";
        System.out.println(s);
        return s;
    }
}
