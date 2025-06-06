package observer.event;

public class WeatherUpdateEvent extends BaseEvent<String> {
    public WeatherUpdateEvent(String source) {
        super(source);
    }
}
