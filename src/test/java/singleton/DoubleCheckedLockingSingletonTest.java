package singleton;

import common.utils.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleCheckedLockingSingletonTest {

    @Test
    public void testSingletonInstance() {
        //Test ordinary obtaining instances
        DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton instance2 = DoubleCheckedLockingSingleton.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "Two instances should be the same object");
    }

    @Test
    public void testReflectionAttack() {
        try {
            //Attempt to create an instance through reflection
            Constructor<DoubleCheckedLockingSingleton> constructor =
                    DoubleCheckedLockingSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            //First instance acquisition (normal method)
            DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();

            //Attempt to create a second instance through reflection
            assertThrows(InvocationTargetException.class, () -> {
                DoubleCheckedLockingSingleton instance2 = constructor.newInstance();
            }, "An exception should be thrown to prevent reflection from creating an instance");

        } catch (Exception e) {
            fail(STR."Reflection call failed: \{e.getMessage()}");
        }
    }

    @Test
    public void testSerialization() throws Exception {
        //Obtain a singleton instance
        DoubleCheckedLockingSingleton original = DoubleCheckedLockingSingleton.getInstance();

        //Serialize
        byte[] serialized = SerializationUtils.serialize(original);

        //Deserialize
        DoubleCheckedLockingSingleton deserialized =
                SerializationUtils.deserialize(serialized);

        //Verify that even after deserialization, it still remains the same instance
        assertSame(original, deserialized, "After deserialization, it should be the same instance");
    }
}
