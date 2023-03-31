package com.pika.spring6;

import cn.hutool.core.util.ReflectUtil;
import com.pika.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author pikachu
 * @since 2023/3/30 19:48
 */
public class Test01 {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean01.xml");
        User user = context.getBean("user", User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void test2() throws Exception {
        Class<?> userClass = Class.forName("com.pika.spring6.bean.User");
        Constructor<?> declaredConstructor = userClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Object instance = declaredConstructor.newInstance();
        System.out.println("instance = " + instance);
        Field nameField = userClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(instance, "pikachu");
        Field idField = userClass.getDeclaredField("id");
        idField.setAccessible(true);
        idField.setInt(instance, 22);
        System.out.println("instance = " + instance);
    }

    @Test
    public void test3() throws Exception {
        Class<?> userClass = Class.forName("com.pika.spring6.bean.User");
        Constructor<?> constructor = userClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        Method setName = userClass.getDeclaredMethod("setName", String.class);
        setName.invoke(instance, "pikachu");
        System.out.println("instance = " + instance);
        ReflectUtil.setFieldValue(instance, "id", 19);
        System.out.println("instance = " + instance);
    }

    @Test
    public void test4() throws Exception {
        Class<?> userClass = Class.forName("com.pika.spring6.bean.User");
        Constructor<?> constructor = userClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        String fieldName = "id";
        String methodName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
        Method setId = userClass.getDeclaredMethod(methodName, int.class);
        setId.invoke(instance, 99);
        System.out.println("instance = " + instance);
    }
}
