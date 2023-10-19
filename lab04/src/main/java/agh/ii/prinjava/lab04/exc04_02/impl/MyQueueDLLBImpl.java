package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyQueue;

public class MyQueueDLLBImpl<E> implements MyQueue<E> {
    private DLinkList<E> elems = new DLinkList<>(); // Create a doubly linked list to store elements

    @Override
    public void enqueue(E x) {
        // Enqueue (add) an element to the rear of the queue (at the end of the linked
        // list)
        elems.addLast(x);
    }

    @Override
    public E dequeue() {
        // Dequeue (remove and return) the element from the front of the queue (first
        // element in the linked list)
        return elems.removeFirst();
    }

    @Override
    public int numOfElems() {
        // Get the number of elements in the queue (size of the linked list)
        return elems.getSize();
    }

    @Override
    public E peek() {
        // Peek at the element at the front of the queue (first element in the linked
        // list)
        return elems.getFirstElem();
    }
}
