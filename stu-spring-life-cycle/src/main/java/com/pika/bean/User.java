package com.pika.bean;

import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author pikachu
 * @since 2023/4/4 20:36
 */
@ToString
public class User implements ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;

    public User() {
        System.out.println("实例化：执行构造方法...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("填充属性...");
    }

    private void initMethod() {
        System.out.println("初始化...");
    }

    private void destroyMethod() {
        System.out.println("销毁...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行实现 Aware接口的方法...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行实现 InitializingBean接口的 afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行实现 DisposableBean接口的 destroy方法");
    }
}
