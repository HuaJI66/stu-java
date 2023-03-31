package com.pika.spring.bean;


import lombok.Getter;
import lombok.Setter;

/**
 * @author pikachu
 * @since 2023/3/30 22:00
 */
@Getter
@Setter
public class C {

    private A a;


    @Override
    public String toString() {
        return "C@" + hashCode() + "{" +
                "A=" + a.hashCode() +
                '}';
    }
}
