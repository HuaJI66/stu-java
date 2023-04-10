package lock.pessimism;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pikachu
 * @since 2023/4/9 11:03
 */
public class ReentrantLockTest {
    volatile int data = 0;
    ReentrantLock lock = new ReentrantLock();

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        int nThreads = 20;
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        CompletableFuture[] futures = new CompletableFuture[nThreads];
        for (int i = 0; i < nThreads; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    add();
//                    addOnLock();
                } catch (InterruptedException ignored) {
                }
            }, pool);
            futures[i] = future;
        }
        try {
            CompletableFuture.allOf(futures).get();
        } catch (Exception ignored) {

        }
        System.out.println("final data = " + data);
    }

    public void addOnLock() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "   data = " + data);
        Thread.sleep(1000);
        data++;
        lock.unlock();
    }

    public void add() throws InterruptedException {
//        System.out.println(Thread.currentThread().getId() + "  before data = " + data);
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getId() + " after  data = " + ++data);
    }
}
