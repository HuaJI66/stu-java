package com.pika;

import com.pika.bean.UserController;
import com.pika.bean.UserDao;
import com.pika.bean.UserService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pikachu
 * @since 2023/3/31 22:31
 */
public class Client {
    @Resource
    private ConfigurableListableBeanFactory factory;

    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = null;
        UserService userService = null;
        UserController userController = null;
        try {
            userController = context.getBeanFactory().getBean(UserController.class);
            userService = context.getBean(UserService.class);
            userDao = context.getBean(UserDao.class);
        } catch (NoSuchBeanDefinitionException ignored) {
        }
        System.out.println("userDao = " + userDao);
        System.out.println("userService = " + userService);
        System.out.println("userController = " + userController);
    }

    @Test
    public void test2() {
        System.out.println("factory = " + factory);
    }
}
