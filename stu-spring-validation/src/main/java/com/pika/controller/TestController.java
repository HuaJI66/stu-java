package com.pika.controller;

import com.pika.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pikachu
 * @since 2023/5/5 9:44
 */
@RestController
@RequestMapping("api/user")
public class TestController {
    @PostMapping("add")
    public String addUser(@Validated @RequestBody User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
        }
        return user.toString();
    }
}
