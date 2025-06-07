package concurrency.threadalternaterunning;

import java.util.concurrent.locks.LockSupport;

public class ThreadAlternateRunningByPark {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnparkObj = new ParkUnpark(5);
        t1 = new Thread(() -> {
            parkUnparkObj.process("a", t2);
        }, "t1");
        t2 = new Thread(() -> {
            parkUnparkObj.process("b", t3);
        }, "t2");
        t3 = new Thread(() -> {
            parkUnparkObj.process("c", t1);
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnpark {
    private final int loopNumber;
    ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void process(String str, Thread t) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(t);
        }
    }
}
