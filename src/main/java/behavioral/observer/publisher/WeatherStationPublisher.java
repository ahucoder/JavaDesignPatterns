package behavioral.observer.publisher;

import behavioral.observer.event.WeatherUpdateEvent;
import behavioral.observer.eventbus.EventBus;

import java.util.Random;

public class WeatherStationPublisher extends Publisher<WeatherUpdateEvent> {
    private final Random random = new Random();

    public WeatherStationPublisher(EventBus eventBus) {
        super(eventBus, 3000);
    }

    @Override
    protected WeatherUpdateEvent getEventInfo() {
        String info = random.nextBoolean() ? "Sunny Day" : "Rain Day";
        return new WeatherUpdateEvent(info);
    }
}
