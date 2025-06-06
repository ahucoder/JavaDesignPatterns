package observer.listener;

import observer.event.Event;

@FunctionalInterface
public interface EventListener<T extends Event<?>> {
    void onEvent(T event);
}
