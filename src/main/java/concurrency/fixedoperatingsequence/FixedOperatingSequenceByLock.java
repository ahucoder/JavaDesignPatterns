package concurrency.fixedoperatingsequence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Require always to run the t2 thread first before running other threads.
 */
public class FixedOperatingSequenceByLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition cond = lock.newCondition();
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                lock.lock();
                while (!ready) {
                    try {
                        cond.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } finally {
                lock.unlock();
            }
            System.out.println("t1 run...");
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t2 run...");
                ready = true;
                cond.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
