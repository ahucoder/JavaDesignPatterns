package concurrent.twophasestop;

public class TwoPhaseStopModByInterrupt {
    public static void main(String[] args) throws InterruptedException {
        MonitorVersion1 demo = new MonitorVersion1();
        demo.start();
        Thread.sleep(3500);
        demo.stop();
    }
}

class MonitorVersion1 {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    System.out.println("Thread interrupted......");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("Simulate the execution of business logic......");
                } catch (InterruptedException e) {
                    //Because the sleep thread is marked as false by interrupt, i.e. isInterrupted returns false
                    //At this point, it is necessary to interrupt again and mark the interrupt as true so that it will exit in the next round of while judgment
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
