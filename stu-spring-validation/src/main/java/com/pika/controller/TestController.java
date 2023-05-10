package com.pika.controller;

import com.pika.utils.R;
import com.pika.validation.annotation.IPv4;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author pikachu
 * @since 2023/5/5 17:17
 */
@RestController
@RequestMapping("test")
@Validated
public class TestController {
    @GetMapping("1")
    public R test1() {
        throw new RuntimeException("test1 error");
    }

    /**
     * <p>{@link ResponseStatusException} 将异常以状态码形式响应</p>
     */
    @GetMapping("2")
    public R test2() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "test2 error");
    }

    @GetMapping("3")
    public R test3(@Valid @IPv4 @RequestParam String ip) {
        return R.ok().put("ip", ip);
    }
}
