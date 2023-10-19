package agh.ii.prinjava.lab04.exc04_02.impl;

public class DLinkList<E> {
    private Node<E> first;  // Reference to the first node in the doubly linked list
    private Node<E> last;   // Reference to the last node in the doubly linked list
    private int size;       // The current size of the doubly linked list

    // Constructor
    // ...

    // Get the size of the linked list
    public int getSize() {
        return size;
    }

    // Get the element stored in the first node of the linked list
    public E getFirstElem() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return first.elem;
    }

    // Add an element to the beginning of the linked list
    public void addFirst(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    // Add an element to the end of the linked list
    public void addLast(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    // Remove and return the element from the beginning of the linked list
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        E removedElem = first.elem;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return removedElem;
    }

    // Remove and return the element from the end of the linked list
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        E removedElem = last.elem;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return removedElem;
    }

    // Override the toString() method to provide a string representation of the linked list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = first;
        while (current != null) {
            sb.append(current.elem).append(" <-> ");
            current = current.next;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 5); // Remove the last " <-> "
        }
        return sb.toString();
    }

    // Check if the linked list is empty
    private boolean isEmpty() {
        return size == 0;
    }

    // Private inner class representing a node in the doubly linked list
    private static class Node<T> {
        T elem;         // The element stored in the node
        Node<T> next;   // Reference to the next node
        Node<T> prev;   // Reference to the previous node

        Node(T elem) {
            this.elem = elem;
        }
    }
}
