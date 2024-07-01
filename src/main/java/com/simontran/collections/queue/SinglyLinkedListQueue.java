package com.simontran.collections.queue;

public class SinglyLinkedListQueue<T> implements Queue<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public T peek() throws EmptyQueueException {
        if (this.head == null) {
            throw new EmptyQueueException();
        }
        return this.head.data;
    }

    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size += 1;
    }

    public T dequeue() throws EmptyQueueException {
        if (this.size == 0) {
            throw new EmptyQueueException();
        }
        Node<T> removeNode = this.head;
        this.head = removeNode.next;
        T removedElement = removeNode.data;
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
