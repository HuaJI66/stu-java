package com.pika.controller;

import com.pika.annotation.ExecLog;
import com.pika.service.LogService;
import org.springframework.stereotype.Controller;

/**
 * @author pikachu
 * @since 2023/4/1 21:56
 */
@Controller
@ExecLog
public class UserController {
    //    @ExecLog
    public String doLogin() {
        System.out.println("login...");
        return "success";
    }

    public void doLogout() {
        System.out.println("logout...");
    }

    public void test(LogService logService) {
        System.out.println("test...");
    }
}
