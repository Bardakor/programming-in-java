package agh.ii.prinjava.lab04.exc04_02.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {
    private DLinkList<Integer> dLinkList;

    @BeforeEach
    void setUp() {
        dLinkList = new DLinkList<>();
    }

    @AfterEach
    void tearDown() {
        dLinkList = null;
    }

    @Test
    void addFirst() {
        dLinkList.addFirst(1);
        assertEquals("1", dLinkList.toString());

        dLinkList.addFirst(2);
        assertEquals("2 <-> 1", dLinkList.toString());

        dLinkList.addFirst(3);
        assertEquals("3 <-> 2 <-> 1", dLinkList.toString());
    }

    @Test
    void addLast() {
        dLinkList.addLast(1);
        assertEquals("1", dLinkList.toString());

        dLinkList.addLast(2);
        assertEquals("1 <-> 2", dLinkList.toString());

        dLinkList.addLast(3);
        assertEquals("1 <-> 2 <-> 3", dLinkList.toString());
    }

    @Test
    void removeFirst() {
        assertThrows(IllegalStateException.class, () -> dLinkList.removeFirst());

        dLinkList.addFirst(1);
        dLinkList.addFirst(2);
        dLinkList.addFirst(3);

        assertEquals(3, dLinkList.removeFirst());
        assertEquals("2 <-> 1", dLinkList.toString());

        assertEquals(2, dLinkList.removeFirst());
        assertEquals("1", dLinkList.toString());

        assertEquals(1, dLinkList.removeFirst());
        assertTrue(dLinkList.toString().isEmpty());
    }

    @Test
    void removeLast() {
        assertThrows(IllegalStateException.class, () -> dLinkList.removeLast());

        dLinkList.addLast(1);
        dLinkList.addLast(2);
        dLinkList.addLast(3);

        assertEquals(3, dLinkList.removeLast());
        assertEquals("1 <-> 2", dLinkList.toString());

        assertEquals(2, dLinkList.removeLast());
        assertEquals("1", dLinkList.toString());

        assertEquals(1, dLinkList.removeLast());
        assertTrue(dLinkList.toString().isEmpty());
    }

    @Test
    void testToString() {
        assertTrue(dLinkList.toString().isEmpty());

        dLinkList.addFirst(1);
        assertEquals("1", dLinkList.toString());

        dLinkList.addLast(2);
        assertEquals("1 <-> 2", dLinkList.toString());

        dLinkList.addFirst(3);
        assertEquals("3 <-> 1 <-> 2", dLinkList.toString());
    }
}
