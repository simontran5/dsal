package com.simontran.collections.queue;

public class CircularBufferQueue<E> implements Queue<E> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private E[] data;
    private int front;
    private int back;
    private int size;

    public CircularBufferQueue() {
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    public E peek() throws EmptyQueueException {
        if (this.size == 0) {
            throw new EmptyQueueException();
        }
        return this.data[this.front];
    }

    public void enqueue(E element) {
        if (this.size == this.data.length) {
            this.resize(this.data.length * GROWTH_FACTOR);
        }
        this.data[this.back] = element;
        this.back = (this.back + 1) % this.data.length;
        this.size += 1;
    }

    public E dequeue() throws EmptyQueueException {
        if (this.size == 0) {
            throw new RuntimeException();
        }
        E oldElement = this.data[this.front];
        this.data[this.front] = null; // Help garbage collection
        this.front = (this.front + 1) % this.data.length;
        this.size -= 1;
        return oldElement;
    }

    private void resize(int newCapacity) {
        E[] biggerArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            biggerArray[i] = this.data[(this.front + i) % this.data.length];
        }
        this.data = biggerArray;
        this.front = 0;
        this.back = this.size;
    }

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {
            super("Queue is empty");
        }
    }
}
