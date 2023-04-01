package com.pika.enums;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/14 18:37
 */
public class EnumTest {
    public static void main(String[] args) {

    }
}

enum Color {
    Red("red",0),
    Blue("blue",1),
    Yellow("yellow",2),
    ;
    String name;
    int value;
    Color(String name,int value) {
        this.name = name;
        this.value = value;
    }

}
