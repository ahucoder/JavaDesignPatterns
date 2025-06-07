package concurrency.fixedoperatingsequence;

/**
 * Require always to run the t2 thread first before running other threads.
 */
public class FixedOperatingSequenceByWaitNotify {
    private static final Object obj = new Object();
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                while (!ready) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("t1 run...");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t2 run...");
                ready = true;
                obj.notify();
            }
        }, "t2").start();
    }
}
