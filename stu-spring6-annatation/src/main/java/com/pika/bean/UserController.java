package com.pika.bean;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 * @author pikachu
 * @since 2023/3/31 22:32
 */
@Controller
public class UserController {
    @Resource
    private UserService userServiceImplA;
}
