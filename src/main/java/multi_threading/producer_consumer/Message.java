package multi_threading.producer_consumer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private int id;
    private String value;

    @Override
    public String toString() {
        return STR."Message{id=\{id}, value='\{value}'}";
    }
}
