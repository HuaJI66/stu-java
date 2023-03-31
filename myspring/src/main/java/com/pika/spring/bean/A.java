package com.pika.spring.bean;

import lombok.*;

/**
 * @author pikachu
 * @since 2023/3/30 21:58
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class A {
    private String name;
    private int age;
    private B b;

    public A(String userName) {
        this.name = userName;
    }

    @Override
    public String toString() {
        return "A@" +hashCode()+"{"+
                "name='" + name + '\'' +
                ", age=" + age +
                ", b=" + b.hashCode() +
                '}';
    }
}
