package com.pika.string;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/19 10:51
 */
public class Test {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();

        p1.name = "Tom";
        p2.name = "Tom";

        System.out.println("p1.name==p2.name = " + p1.name == p2.name);
        System.out.println("p1.name.equals(p2.name) = " + p1.name.equals(p2.name));
        System.out.println("p1.name==\"Tom\" = " + p1.name == "Tom");
    }

}
