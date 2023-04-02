package com.pika;

import com.pika.aspect.TestNoAnnotationPointcutAspect;
import com.pika.controller.UserController;
import com.pika.service.LogService;
import com.pika.service.PayService;
import com.pika.service.UserService;
import com.pika.service.impl.PayServiceImpl1;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

/**
 * @author pikachu
 * @since 2023/4/1 22:03
 */
public class Test {
    @org.junit.Test
    public void test1() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println("userService = " + userService);
        userService.login();
        userService.logout();
        System.out.println("AopUtils.isAopProxy(userService) = " + AopUtils.isAopProxy(userService));
    }

    @org.junit.Test
    public void test2() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.logout("pikachu");
    }

    @org.junit.Test
    public void test3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserController userController = context.getBean("userController", UserController.class);
        userController.doLogout();
    }

    @org.junit.Test
    public void testWithin() throws InstantiationException, IllegalAccessException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        PayService payServiceImpl1 = context.getBean("payServiceImpl1", PayService.class);
        PayService payServiceImpl2 = context.getBean("payServiceImpl2", PayService.class);
        payServiceImpl1.test();
        payServiceImpl2.test();
        System.out.println("AopUtils.isAopProxy(payServiceImpl1) = " + AopUtils.isAopProxy(payServiceImpl1));
        System.out.println("AopUtils.isAopProxy(payServiceImpl2) = " + AopUtils.isAopProxy(payServiceImpl2));
    }

    @org.junit.Test
    public void test4() {
        PayService target = new PayServiceImpl1();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.setInterfaces(ClassUtils.getAllInterfaces(target));
        proxyFactory.addAspect(TestNoAnnotationPointcutAspect.class);
        PayService proxy = proxyFactory.getProxy();
        proxy.test();
        System.out.println("AopUtils.isAopProxy(proxy) = " + AopUtils.isAopProxy(proxy));
        System.out.println("AopUtils.isJdkDynamicProxy(proxy) = " + AopUtils.isJdkDynamicProxy(proxy));
    }

    @org.junit.Test
    public void test5() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserController userController = context.getBean("userController", UserController.class);
        userController.doLogin();
        userController.doLogout();
        userController.test(new LogService());
        System.out.println("AopUtils.isJdkDynamicProxy(userController) = " + AopUtils.isJdkDynamicProxy(userController));
        System.out.println("AopUtils.isAopProxy(userController) = " + AopUtils.isAopProxy(userController));
    }
}
