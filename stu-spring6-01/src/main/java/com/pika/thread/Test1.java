package com.pika.thread;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/12 23:44
 */
public class Test1 {
    public static int i = 0;

    public static void main(String[] args) {
//        ipp();
//        ipp();
        new Thread(() -> {
            ipp();
        }).start();
        new Thread(() -> {
            ipp();
        }).start();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "---- i = " + i);
    }

    public static void ipp(){
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "---- i = " + i);
        for (int j = 0; j < 100; j++) {
            i++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "---- i = " + i);
    }
}
