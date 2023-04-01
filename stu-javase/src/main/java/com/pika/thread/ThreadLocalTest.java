package com.pika.thread;

import java.util.stream.IntStream;

/**
 * @author pikachu
 * @since 2023/4/1 20:24
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        IntStream.range(0, 10).forEach(item -> {
            new Thread(() -> {
                threadLocal.set(item);
                Integer value = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
            }).start();
        });
    }
}
