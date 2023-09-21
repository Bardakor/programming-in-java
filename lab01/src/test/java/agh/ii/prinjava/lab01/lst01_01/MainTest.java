package agh.ii.prinjava.lab01.lst01_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testIsEncapsulated() {
        HelloEncapsulation enDemo = new HelloEncapsulation(42);
        assertTrue(enDemo.isEncapsulated());
    }
}
