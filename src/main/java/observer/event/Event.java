package observer.event;

public interface Event<T> {
    long timestamp();

    T source();
}
