package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackDLLBImplTest {
    private MyStack<Integer> stackOfInts;

    @BeforeEach
    void setUp() {
        stackOfInts = MyStack.create();
    }

    @AfterEach
    void tearDown() {
        stackOfInts = null;
    }

    @Test
    void testPush() {
        assertTrue(stackOfInts.numOfElems() == 0);
        stackOfInts.push(1);
        assertTrue(stackOfInts.numOfElems() == 1);
        assertEquals(1, stackOfInts.peek());
    }

    @Test
    void testPop() {
        stackOfInts.push(1);
        stackOfInts.push(2);
        stackOfInts.push(3);
        assertEquals(3, stackOfInts.pop());
        assertEquals(2, stackOfInts.peek());
        assertTrue(stackOfInts.numOfElems() == 2);
    }

    @Test
    void testNumOfElems() {
        assertTrue(stackOfInts.numOfElems() == 0);
        stackOfInts.push(1);
        stackOfInts.push(2);
        assertTrue(stackOfInts.numOfElems() == 2);
    }

    @Test
    void testPeek() {
        stackOfInts.push(1);
        stackOfInts.push(2);
        assertEquals(2, stackOfInts.peek());
        assertTrue(stackOfInts.numOfElems() == 2); // Ensure peek doesn't remove the element
    }

    @Test
    void testPopFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stackOfInts.pop());
    }

    @Test
    void testPeekFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stackOfInts.peek());
    }
}
