package com.pika.spring.bean;


import lombok.Getter;
import lombok.Setter;

/**
 * @author pikachu
 * @since 2023/3/30 21:59
 */
@Getter
@Setter
public class B {

    private C c;

    public A selectUserByName(String userName) {
        return new A(userName);
    }

    @Override
    public String toString() {
        return "B@" + hashCode() + "{" +
                "C=" + c.hashCode() +
                '}';
    }
}
