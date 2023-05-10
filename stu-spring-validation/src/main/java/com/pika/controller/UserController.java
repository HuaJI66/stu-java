package com.pika.controller;

import com.pika.entity.User;
import com.pika.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>{@link Validated} 启用参数校验</p>
 * <p>{@link BindingResult} 接收参数校验结果</p>
 *
 * @author pikachu
 * @since 2023/5/5 9:44
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    @PostMapping("add")
    public R addUser(@Validated @RequestBody User user) {
        return R.ok().put("data", user);
    }
}
