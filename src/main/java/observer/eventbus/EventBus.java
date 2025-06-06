package observer.eventbus;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import observer.event.Event;
import observer.listener.EventListener;

import java.util.List;
import java.util.Map;

/**
 * Event bus, responsible for event publishing and subscription
 */
public class EventBus {

    private final Map<Class<?>, List<EventListener<?>>> listenerMap = Maps.newConcurrentMap();

    /**
     * subscribe event
     *
     * @param eventClass    event type
     * @param eventListener event listener
     * @param <E>           event generics
     */
    public <E extends Event<?>> void subscribe(Class<E> eventClass, EventListener<E> eventListener) {
        listenerMap.computeIfAbsent(eventClass, v -> Lists.newCopyOnWriteArrayList()).add(eventListener);
    }

    /**
     * cancel
     *
     * @param eventClass    event type
     * @param eventListener event listener
     * @param <E>           event generics
     */
    public <E extends Event<?>> void unsubscribe(Class<E> eventClass, EventListener<E> eventListener) {
        List<EventListener<?>> eventListeners = listenerMap.get(eventClass);
        if (eventListeners != null) {
            eventListeners.remove(eventListener);
        }
    }

    /**
     * publish event
     *
     * @param event event
     * @param <E>   event generics
     */
    @SuppressWarnings("unchecked")
    public <E extends Event<?>> void publish(E event) {
        List<EventListener<?>> eventListeners = listenerMap.get(event.getClass());
        if (eventListeners != null) {
            eventListeners.forEach(eventListener -> ((EventListener<E>) eventListener).onEvent(event));
        }
    }
}
