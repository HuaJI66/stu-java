package com.pika;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pikachu
 * @since 2023/4/1 22:03
 */
public class Test {
    @org.junit.Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println("userService = " + userService);
        String login = userService.login();
        System.out.println("login = " + login);
    }
}
