package Singleton;

import java.io.Serial;
import java.io.Serializable;

public final class DoubleCheckedLockingSingleton implements Serializable {
    private static volatile DoubleCheckedLockingSingleton instance; //use volatile to prevent instruction reordering
    private static boolean initialized = false; //defense signs

    private DoubleCheckedLockingSingleton() {
        synchronized (DoubleCheckedLockingSingleton.class) {
            if (initialized) {  //prevent reflection calls
                throw new RuntimeException("Prohibit creating instances through reflection");
            }
            initialized = true;
        }
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { //first check
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { //second check
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    //return existing instances during deserialization
    @Serial
    private Object readResolve() {
        return getInstance();
    }
}
