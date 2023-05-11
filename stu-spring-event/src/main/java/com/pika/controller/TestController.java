package com.pika.controller;

import com.pika.event.MyApplicationEventPublisher;
import com.pika.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pikachu
 * @since 2023/5/10 9:44
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
    @Resource
    MyApplicationEventPublisher myApplicationEventPublisher;

    @GetMapping("test1")
    public R test(@RequestParam("msg") String msg) {
        myApplicationEventPublisher.publish(msg);
        return R.ok();
    }
}
