package com.pika.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/13 20:51
 */
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable =  () -> {
            return 1;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        futureTask.get();
    }
}
