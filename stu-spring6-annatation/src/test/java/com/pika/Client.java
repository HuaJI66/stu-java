package com.pika;

import com.pika.bean.UserController;
import com.pika.bean.UserDao;
import com.pika.bean.impl.UserServiceImplA;
import com.pika.config.MyConfig;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        UserServiceImplA userService = null;
        UserController userController = null;
        try {
            userController = context.getBeanFactory().getBean(UserController.class);
            userService = context.getBean(UserServiceImplA.class);
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

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserServiceImplA bean = context.getBean(UserServiceImplA.class);
    }

    @Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        UserDao userDao1 = context.getBean(UserDao.class);
        System.out.println("userDao = " + userDao);
        System.out.println("userDao1 = " + userDao1);
    }

    @Test
    public void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        UserDao userDao1 = context.getBean(UserDao.class);
        System.out.println("userDao = " + userDao);
        System.out.println("userDao1 = " + userDao1);
    }

}
