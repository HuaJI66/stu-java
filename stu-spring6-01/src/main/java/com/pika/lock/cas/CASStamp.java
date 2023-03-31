package com.pika.lock.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Desc:带版本号的CAS操作
 *
 * @since: 2022/10/18 10:10
 * @author: pikachu
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class CASStamp {

    public static void main(String[] args) {
        Book java = new Book(1, "java");
        Book mysql = new Book(2, "mysql");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(java, 1);

        new Thread(() -> {
            int stamp = stampedReference.getStamp();

            try {
                Thread.sleep(100);
                boolean result = stampedReference.compareAndSet(java, mysql, stamp, stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\tstamp = " + stamp + "\tresult = " + result);

                stamp = stampedReference.getStamp();
                result = stampedReference.compareAndSet(mysql, java, stamp, stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\tstamp = " + stamp + "\tresult = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            try {
                Thread.sleep(1000);//让t1先执行完成
                boolean result = stampedReference.compareAndSet(java, mysql, stamp, stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\tstamp = " + stamp + "\tresult = " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
