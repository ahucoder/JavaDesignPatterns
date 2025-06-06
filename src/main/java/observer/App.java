package observer;

import observer.event.WeatherUpdateEvent;
import observer.eventbus.EventBus;
import observer.listener.User;
import observer.publisher.WeatherStationPublisher;

public class App {

    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new EventBus();
        WeatherStationPublisher weatherStation = new WeatherStationPublisher(eventBus);

        // create users
        User user1 = new User("Tom", System.out::println);
        User user2 = new User("Jerry", System.out::println);

        // subscribe to weather update events
        eventBus.subscribe(WeatherUpdateEvent.class, user1);
        eventBus.subscribe(WeatherUpdateEvent.class, user2);

        // activate the weather station
        weatherStation.start();
        System.out.println("The weather broadcast system has been activated");

        // stop after running for a period of time
        Thread.sleep(6100);
        eventBus.unsubscribe(WeatherUpdateEvent.class, user1);
        System.out.println("user1 has been unsubscribed");
        Thread.sleep(6100);
        weatherStation.stop();

        System.out.println("The weather broadcast system has been turned off");
    }

}
