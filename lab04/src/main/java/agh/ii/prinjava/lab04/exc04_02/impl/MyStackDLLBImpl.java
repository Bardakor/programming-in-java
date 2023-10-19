package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;

public class MyStackDLLBImpl<E> implements MyStack<E> {
    private DLinkList<E> elems = new DLinkList<>(); // Create a doubly linked list to store elements

    @Override
    public E pop() {
        // Remove and return the element from the top of the stack (first element in the
        // linked list)
        return elems.removeFirst();
    }

    @Override
    public void push(E x) {
        // Push (add) an element to the top of the stack (at the beginning of the linked
        // list)
        elems.addFirst(x);
    }

    @Override
    public int numOfElems() {
        // Get the number of elements in the stack (size of the linked list)
        return elems.getSize();
    }

    @Override
    public E peek() {
        // Peek at the element on the top of the stack (first element in the linked
        // list)
        return elems.getFirstElem();
    }
}
