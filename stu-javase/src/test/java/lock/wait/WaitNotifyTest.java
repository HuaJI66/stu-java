package lock.wait;

import org.junit.Test;

/**
 * @author pikachu
 * @since 2023/5/8 9:56
 */
public class WaitNotifyTest {
    private Boolean sharedObject = Boolean.TRUE;

    @Test
    public void test1() throws InterruptedException {
        Thread workThread = new Thread(() -> {
            synchronized (sharedObject) {
                while (sharedObject) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " waiting...");
                        sharedObject.wait();
                        sharedObject = Boolean.FALSE;
                        System.out.println(Thread.currentThread().getName() + " resume running");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + " finished");
            }

        });

        Thread notifyThread = new Thread(() -> {
            synchronized (sharedObject) {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " begin notify...");
                    sharedObject.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        workThread.start();
        notifyThread.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
