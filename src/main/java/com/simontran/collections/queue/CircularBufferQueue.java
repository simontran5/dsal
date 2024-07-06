package com.simontran.collections.queue;

public class CircularBufferQueue<T> implements Queue<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private T[] data;
    private int front;
    private int back;
    private int size;

    public CircularBufferQueue() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    public T peek() {
        if (this.size == 0) return null;
        return this.data[this.front];
    }

    public void enqueue(T element) {
        if (this.size == this.data.length) this.resize(this.data.length * GROWTH_FACTOR);
        this.data[this.back] = element;
        this.back = (this.back + 1) % this.data.length;
        this.size += 1;
    }

    public T dequeue() {
        if (this.size == 0) throw new EmptyQueueException();
        T oldElement = this.data[this.front];
        this.data[this.front] = null; // Help garbage collection
        this.front = (this.front + 1) % this.data.length;
        this.size -= 1;
        return oldElement;
    }

    private void resize(int newCapacity) {
        T[] biggerArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i += 1) {
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
