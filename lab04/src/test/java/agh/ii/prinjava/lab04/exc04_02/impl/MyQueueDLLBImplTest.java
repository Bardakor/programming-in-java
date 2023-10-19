package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MyQueueDLLBImplTest {
    private MyQueue<Integer> queueOfInts;

    @BeforeEach
    void setUp() {
        queueOfInts = MyQueue.create();
    }

    @AfterEach
    void tearDown() {
        queueOfInts = null;
    }

    @Test
    void testEnqueue() {
        assertTrue(queueOfInts.numOfElems() == 0);
        queueOfInts.enqueue(1);
        assertTrue(queueOfInts.numOfElems() == 1);
        assertEquals(1, queueOfInts.peek());
    }

    @Test
    void testDequeue() {
        queueOfInts.enqueue(1);
        queueOfInts.enqueue(2);
        queueOfInts.enqueue(3);
        assertEquals(1, queueOfInts.dequeue());
        assertEquals(2, queueOfInts.peek());
        assertTrue(queueOfInts.numOfElems() == 2);
    }

    @Test
    void testNumOfElems() {
        assertTrue(queueOfInts.numOfElems() == 0);
        queueOfInts.enqueue(1);
        queueOfInts.enqueue(2);
        assertTrue(queueOfInts.numOfElems() == 2);
    }

    @Test
    void testPeek() {
        queueOfInts.enqueue(1);
        queueOfInts.enqueue(2);
        assertEquals(1, queueOfInts.peek());
        assertTrue(queueOfInts.numOfElems() == 2); // Ensure peek doesn't remove the element
    }

    @Test
    void testDequeueFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queueOfInts.dequeue());
    }

    @Test
    void testPeekFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queueOfInts.peek());
    }
}
