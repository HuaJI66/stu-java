package com.pika;

import org.springframework.stereotype.Service;

/**
 * @author pikachu
 * @since 2023/4/1 21:55
 */
@Service
public class UserService {
    public String login() {
        System.out.println("login");
        return "success";
    }
}
