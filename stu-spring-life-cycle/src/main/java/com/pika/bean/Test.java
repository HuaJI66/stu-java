package com.pika.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pikachu
 * @since 2023/4/4 20:39
 */
public class Test {
    @org.junit.Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        User bean = context.getBean(User.class);
        System.out.println("bean = " + bean);
    }

    @org.junit.Test
    public void test2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        context.close();
    }
}
