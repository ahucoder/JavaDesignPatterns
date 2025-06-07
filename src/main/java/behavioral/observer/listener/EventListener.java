package behavioral.observer.listener;

import behavioral.observer.event.Event;

@FunctionalInterface
public interface EventListener<T extends Event<?>> {
    void onEvent(T event);
}
