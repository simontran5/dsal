package com.simontran.collections.queue;

public class SinglyLinkedListQueue<E> implements Queue<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public E peek() throws EmptyQueueException {
        if (this.head == null) {
            throw new EmptyQueueException();
        }
        return this.head.data;
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size += 1;
    }

    public E dequeue() throws EmptyQueueException {
        if (this.size == 0) {
            throw new EmptyQueueException();
        }
        Node<E> removeNode = this.head;
        this.head = removeNode.next;
        E removedElement = removeNode.data;
        removeNode.next = null; // Help garbage collection
        this.size -= 1;
        if (this.size == 0) {
            this.tail = null; // Queue is now empty
        }
        return removedElement;
    }

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {
            super("Queue is empty");
        }
    }
}
