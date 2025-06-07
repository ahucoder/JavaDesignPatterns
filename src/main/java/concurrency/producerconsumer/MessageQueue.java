package concurrency.producerconsumer;

import com.google.common.collect.Lists;

import java.util.List;

public class MessageQueue {
    public int capacity = 10;

    private final List<Message> queue = Lists.newArrayList();

    public Message take() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (queue) {
            while (queue.size() >= capacity) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(message);
            queue.notifyAll();
        }
    }

    public int size() {
        return queue.size();
    }
}
