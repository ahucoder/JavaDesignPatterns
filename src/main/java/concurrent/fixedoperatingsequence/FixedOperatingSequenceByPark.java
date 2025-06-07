package concurrent.fixedoperatingsequence;

import java.util.concurrent.locks.LockSupport;

/**
 * Require always to run the t2 thread first before running other threads.
 */
public class FixedOperatingSequenceByPark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("t1 run...");
        }, "t1");
        t1.start();

        new Thread(() -> {
            System.out.println("t2 run...");
            LockSupport.unpark(t1);
        }, "t2").start();
    }
}
