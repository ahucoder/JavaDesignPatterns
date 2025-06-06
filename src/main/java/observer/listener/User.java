package observer.listener;

import observer.event.WeatherUpdateEvent;

import java.util.function.Consumer;

public class User implements EventListener<WeatherUpdateEvent> {
    private final String name;
    private final Consumer<String> consumer;

    public User(String name, Consumer<String> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    @Override
    public void onEvent(WeatherUpdateEvent event) {
        String message = String.format("[%s] received weather update event: %s, current time is %s", name, event.source(), event.formattedTimestamp());
        consumer.accept(message);
    }
}
