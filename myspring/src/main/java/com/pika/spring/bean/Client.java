package com.pika.spring.bean;

import org.junit.Test;
import org.myspringframework.core.ClassPathXmlApplicationContext;

/**
 * @author pikachu
 * @since 2023/3/30 22:41
 */
public class Client {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        A a = context.getBean("a", A.class);
        A pikachu = a.getB().selectUserByName("pikachu");
        pikachu.setB(a.getB());
        System.out.println("pikachu = " + pikachu);
    }
}
