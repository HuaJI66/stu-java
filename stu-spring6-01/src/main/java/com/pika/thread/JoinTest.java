package com.pika.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/13 19:52
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Date date = new Date();
                System.out.println("1 = "+date + Thread.currentThread().getName());
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Date date = new Date();
        System.out.println("2 = "+date + Thread.currentThread().getName());
        thread.join();
        Date date1 = new Date();
        System.out.println("3 = "+date1 + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().getPriority() = " + Thread.currentThread().getPriority());
    }
}
