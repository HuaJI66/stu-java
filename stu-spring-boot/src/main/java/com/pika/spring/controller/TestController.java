package com.pika.spring.controller;

import com.pika.spring.service.AService;
import com.pika.spring.service.LoginService;
import com.pika.spring.service.impl.BServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/25 17:18
 */
@RestController
public class TestController {
    @Resource
    AService aService;
    @Autowired
    BServiceImpl bService;
    @Resource
    LoginService loginService;

    @GetMapping("hello/a")
    public String test1() {
        return aService.hello();
    }

    @GetMapping("hello/b")
    public String test3() {
        return bService.hello();
    }

}
