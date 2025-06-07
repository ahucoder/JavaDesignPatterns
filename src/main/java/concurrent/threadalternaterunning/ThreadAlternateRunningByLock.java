package concurrent.threadalternaterunning;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAlternateRunningByLock {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignalObj = new AwaitSignal(5);
        Condition a = awaitSignalObj.newCondition();
        Condition b = awaitSignalObj.newCondition();
        Condition c = awaitSignalObj.newCondition();

        new Thread(() -> {
            awaitSignalObj.process("a", a, b);
        }, "t1").start();
        new Thread(() -> {
            awaitSignalObj.process("b", b, c);
        }, "t2").start();
        new Thread(() -> {
            awaitSignalObj.process("c", c, a);
        }, "t3").start();

        Thread.sleep(1000);
        try {
            awaitSignalObj.lock();
            a.signal();
        } finally {
            awaitSignalObj.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private final int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void process(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt(); // restore interrupted state
                throw new RuntimeException(e);
            } finally {
                unlock();
            }
        }
    }
}