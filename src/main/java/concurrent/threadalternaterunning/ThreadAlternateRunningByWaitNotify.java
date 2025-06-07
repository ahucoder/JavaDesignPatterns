package concurrent.threadalternaterunning;

public class ThreadAlternateRunningByWaitNotify {
    public static void main(String[] args) {
        WaitNotify waitNotifyObj = new WaitNotify(1, 5);
        new Thread(() -> {
            waitNotifyObj.process("a", 1, 2);
        }, "t1").start();
        new Thread(() -> {
            waitNotifyObj.process("b", 2, 3);
        }, "t2").start();
        new Thread(() -> {
            waitNotifyObj.process("c", 3, 1);
        }, "t3").start();
    }
}

class WaitNotify {
    private int flag;
    private final int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void process(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (waitFlag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}