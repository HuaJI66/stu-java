package com.pika.controller;

import com.pika.entity.Balance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pikachu
 * @since 2023/5/4 15:26
 */
@RestController
@RequestMapping("test")
public class MessageConverterTestController {
    @PostMapping("/msg/post")
    public Balance test1(@RequestBody Balance balance) {
        return balance;
    }
}
