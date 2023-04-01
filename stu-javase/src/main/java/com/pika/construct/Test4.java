package com.pika.construct;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/14 14:36
 */
public class Test4 {
    public Test4() {
        System.out.println("执行构造函数...");
    }

    private int x = test();
    private int test() {
        System.out.println("初始化非静态变量x,执行方法 Test5()");
        return 1;
    }

    private static int y = test1();

    private static int test1() {
        System.out.println("初始化静态变量y,执行静态方法 test1()");
        return 4;
    }
    static {
        System.out.println("执行静态代码块...");
    }
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        Test4 test44 = new Test4();
    }
}
