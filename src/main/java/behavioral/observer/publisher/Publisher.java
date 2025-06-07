package behavioral.observer.publisher;

import behavioral.observer.event.Event;
import behavioral.observer.eventbus.EventBus;

import java.util.Objects;

public abstract class Publisher<E extends Event<?>> {
    private final EventBus eventBus;
    private final int intervalMillis;
    private volatile boolean running;
    private Thread workerThread;

    public Publisher(EventBus eventBus, int intervalMillis) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.intervalMillis = intervalMillis;
    }

    protected abstract E getEventInfo();

    public final void start() {
        if (running) {
            return;
        }
        running = true;
        workerThread = new Thread(() -> {
            try {
                while (running) {
                    E event = getEventInfo();
                    if (event != null) {
                        eventBus.publish(event);
                        Thread.sleep(intervalMillis);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        workerThread.start();
    }

    public final void stop() {
        running = false;
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }

    public final boolean isRunning() {
        return running;
    }
}
