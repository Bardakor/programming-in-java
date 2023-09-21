package agh.ii.prinjava.lab01.lst01_09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testGetI1() {
        HelloImmutable hi = new HelloImmutable(64, "hello");
        assertEquals(64, hi.getI1());
    }

}
