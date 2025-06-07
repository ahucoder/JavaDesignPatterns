package multi_threading.two_phase_stop;

public class TwoPhaseStopModByVolatile {
    public static void main(String[] args) throws InterruptedException {
        MonitorVersion2 demo = new MonitorVersion2();
        demo.start();
        Thread.sleep(3500);
        demo.stop();
    }
}

class MonitorVersion2 {
    private Thread monitor;
    private volatile boolean stop = false;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if (stop) {
                    System.out.println("Thread interrupted......");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("Simulate the execution of business logic......");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        stop = true;
    }
}