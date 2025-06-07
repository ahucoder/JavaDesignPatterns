package behavioral.observer.event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class BaseEvent<T> implements Event<T> {
    private final long timestamp;
    private final T source;
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    protected BaseEvent(T source) {
        this.timestamp = System.currentTimeMillis();
        this.source = Objects.requireNonNull(source);
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

    @Override
    public T source() {
        return source;
    }

    public String formattedTimestamp() {
        return DATE_FORMAT.format(new Date(timestamp));
    }
}
