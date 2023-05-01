package com.pika.service;

import com.pika.annotation.ExecLog;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author pikachu
 * @since 2023/4/1 21:55
 */
@Service
public class UserService {
    public String login() {
        Object currentProxy = AopContext.currentProxy();
        System.out.println("login");
        return "success";
    }


    public void logout() throws InterruptedException {
        Thread.sleep(200);
        System.out.println("logout");
    }

    @ExecLog("注销")
    public void logout(String userName) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("logout");
    }

}
