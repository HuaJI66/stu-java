package com.pika.spring.service.impl;

import com.pika.spring.service.AService;
import com.pika.spring.service.BService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/25 16:35
 */
@Service
public class BServiceImpl implements BService {
    @Resource
    private AService aService;
    private String name = "BService";
    //    public BServiceImpl(AService aService) {
//        System.out.println("Init BService...");
//        this.aService = aService;
//    }
    public BServiceImpl() {
        System.out.println("instantiate BService:"+ this.aService);
    }

    @PostConstruct
    public void init() {
        System.out.println("post construct: " + this.aService);
        //此时bService已注入aService,但是aService中未填充bService属性，抛出空指针异常
//        ((AServiceImpl) aService).getBService().hello();
    }




    @Override
    public String hello() {
        System.out.println(this);
        System.out.println(this.aService);
        String s = "BService -> AService";
        System.out.println(s);
        return s;
    }
}
