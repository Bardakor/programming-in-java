package agh.ii.prinjava.lab01.lst01_08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testInstance() {
        UnsafeSingleton singleton1 = UnsafeSingleton.getInstance();
        UnsafeSingleton singleton2 = UnsafeSingleton.getInstance();
        assertSame(singleton1, singleton2);
    }
}
