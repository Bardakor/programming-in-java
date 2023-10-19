package agh.ii.prinjava.lab04.exc04_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    Pair<Integer, String> pairOfIntStr;

    @BeforeEach
    void setUp() {
        // Initialize a Pair<Integer, String> for testing
        pairOfIntStr = new Pair<>(42, "Hello");
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test (if needed)
    }

    @Test
    void testGetters() {
        assertEquals(42, pairOfIntStr.getFirst());
        assertEquals("Hello", pairOfIntStr.getSecond());
    }

    @Test
    void testSetters() {
        pairOfIntStr.setFirst(123);
        pairOfIntStr.setSecond("World");

        assertEquals(123, pairOfIntStr.getFirst());
        assertEquals("World", pairOfIntStr.getSecond());
    }

    @Test
    void testToString() {
        assertEquals("(42, Hello)", pairOfIntStr.toString());
    }

    @Test
    void testEquals() {
        Pair<Integer, String> samePair = new Pair<>(42, "Hello");
        Pair<Integer, String> differentPair = new Pair<>(123, "World");

        assertTrue(pairOfIntStr.equals(samePair));
        assertFalse(pairOfIntStr.equals(differentPair));
    }

    @Test
    void testHashCode() {
        Pair<Integer, String> samePair = new Pair<>(42, "Hello");
        Pair<Integer, String> differentPair = new Pair<>(123, "World");

        assertEquals(pairOfIntStr.hashCode(), samePair.hashCode());
        assertNotEquals(pairOfIntStr.hashCode(), differentPair.hashCode());
    }

    @Test
    void testClone() {
        Pair<Integer, String> clonedPair = pairOfIntStr.clone();

        assertNotSame(pairOfIntStr, clonedPair); // Ensure they are not the same object
        assertEquals(pairOfIntStr, clonedPair); // Ensure they are equal
    }
}
