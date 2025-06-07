package producerconsumer;

import concurrency.producerconsumer.Message;
import concurrency.producerconsumer.MessageQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProducerConsumerModTest {

    /**
     * Test basic functions: Produce a message and then consume it
     */
    @Test
    public void testBasicFunctionality() {
        System.out.println("Test basic functions...");
        MessageQueue queue = new MessageQueue();
        queue.capacity = 10;

        // producer thread
        Thread producer = new Thread(() -> {
            Message msg = new Message();
            msg.setId(1);
            msg.setValue("Hello World");
            queue.put(msg);
            System.out.println(STR."Producer: send message - \{msg}");
        });

        // consumer thread
        Thread consumer = new Thread(() -> {
            Message msg = queue.take();
            System.out.println(STR."Consumer: receive message - \{msg}");
            if (msg.getId() != 1 || !"Hello World".equals(msg.getValue())) {
                throw new AssertionError("The message content is incorrect!");
            }
        });

        // Activate the consumer first and ensure that it is waiting for a message
        consumer.start();
        try {
            Thread.sleep(100); // Ensure that consumers initiate first
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Start the producer
        producer.start();

        // Waiting for thread completion
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Test multiple producers and a single consumer
     *
     * @throws InterruptedException
     */
    @Test
    public void testMultipleProducersSingleConsumer() throws InterruptedException {
        System.out.println("Test multiple producers and a single consumer...");
        MessageQueue queue = new MessageQueue();
        queue.capacity = 10;

        int producerCount = 5;
        int messageCount = 20;
        final CountDownLatch latch = new CountDownLatch(producerCount + 1);
        List<Integer> consumedMessages = new ArrayList<>();

        // create producers
        for (int i = 0; i < producerCount; i++) {
            final int producerId = i + 1;
            new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < messageCount / producerCount; j++) {
                    try {
                        Thread.sleep(random.nextInt(50)); // random delay

                        Message msg = new Message();
                        msg.setId(producerId * 100 + j);
                        msg.setValue(STR."Producer \{producerId} - Msg \{j}");
                        queue.put(msg);

                        System.out.println(STR."Producer \{producerId}: send message - \{msg.getId()}");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                latch.countDown();
            }).start();
        }

        // create consumer
        new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    Message msg = queue.take();
                    consumedMessages.add(msg.getId());
                    System.out.println(STR."Consumer: receive message - \{msg.getId()}");
                    Thread.sleep(50); // consumption delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            latch.countDown();
        }).start();

        // Waiting for all tasks to be completed
        latch.await(5, TimeUnit.SECONDS);

        System.out.println(STR."Number of messages consumed: \{consumedMessages.size()} (should be \{messageCount})");
        assertSame(consumedMessages.size(), messageCount);
    }


    /**
     * Test multiple consumers for a single producer
     *
     * @throws InterruptedException
     */
    @Test
    public void testSingleProducerMultipleConsumers() throws InterruptedException {
        System.out.println("Test multiple consumers for a single producer...");
        MessageQueue queue = new MessageQueue();
        queue.capacity = 10;

        int consumerCount = 3;
        int messageCount = 15;
        final CountDownLatch latch = new CountDownLatch(1 + consumerCount);
        List<Integer> consumedMessages = new ArrayList<>();

        // create producer
        new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    Message msg = new Message();
                    msg.setId(i + 1);
                    msg.setValue(STR."Message \{i + 1}");
                    queue.put(msg);
                    System.out.println(STR."producer: send message - \{msg.getId()}");
                    Thread.sleep(30); // production delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            latch.countDown();
        }).start();

        // create consumers
        for (int i = 0; i < consumerCount; i++) {
            final int consumerId = i + 1;
            new Thread(() -> {
                while (true) {
                    try {
                        Message msg = queue.take();
                        synchronized (consumedMessages) {
                            consumedMessages.add(msg.getId());
                        }
                        System.out.println(STR."Consumer \{consumerId}: receive message - \{msg.getId()}");
                    } catch (Exception e) {
                        break;
                    }
                }
            }).start();
        }

        // Waiting for the producer to complete
        latch.await(3, TimeUnit.SECONDS);

        // Give consumers time to process remaining messages
        Thread.sleep(500);

        System.out.println(STR."Number of messages consumed: \{consumedMessages.size()} (should be \{messageCount})");
        assertSame(consumedMessages.size(), messageCount);
    }

    /**
     * Test multiple producers and consumers
     *
     * @throws InterruptedException
     */
    @Test
    public void testMultipleProducersMultipleConsumers() throws InterruptedException {
        System.out.println("Test multiple producers and consumers...");
        MessageQueue queue = new MessageQueue();
        queue.capacity = 20;

        int producerCount = 4;
        int consumerCount = 4;
        int messagesPerProducer = 10;
        int totalMessages = producerCount * messagesPerProducer;
        final CountDownLatch latch = new CountDownLatch(producerCount + consumerCount);
        List<Integer> consumedMessages = new ArrayList<>();

        // create producers
        for (int i = 0; i < producerCount; i++) {
            final int producerId = i + 1;
            new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < messagesPerProducer; j++) {
                    try {
                        Thread.sleep(random.nextInt(50)); // random delay

                        Message msg = new Message();
                        msg.setId(producerId * 1000 + j);
                        msg.setValue(STR."P\{producerId}-Msg\{j}");
                        queue.put(msg);

                        System.out.println(STR."Producer \{producerId}: send message - \{msg.getId()}");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                latch.countDown();
            }).start();
        }

        // create consumers
        for (int i = 0; i < consumerCount; i++) {
            final int consumerId = i + 1;
            new Thread(() -> {
                for (int j = 0; j < totalMessages / consumerCount; j++) {
                    try {
                        Message msg = queue.take();
                        synchronized (consumedMessages) {
                            consumedMessages.add(msg.getId());
                        }
                        System.out.println(STR."Consumer \{consumerId}: receive message - \{msg.getId()}");
                        Thread.sleep(30); // consumption delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                latch.countDown();
            }).start();
        }

        // Waiting for all tasks to be completed
        boolean completed = latch.await(10, TimeUnit.SECONDS);

        System.out.println(STR."Number of messages consumed: \{consumedMessages.size()} (should be \{totalMessages})");
        assertSame(consumedMessages.size(), totalMessages);
        assertTrue(completed);
    }
}
