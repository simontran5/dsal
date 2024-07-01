package com.simontran.collections.stack;

public class ArrayStack<T> implements Stack<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private T[] data;
    private int size;

    public ArrayStack() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public T peek() throws EmptyStackException {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        return this.data[this.size - 1];
    }

    public void push(T element) {
        if (this.size == this.data.length) {
            this.resize(this.data.length * GROWTH_FACTOR);
        }
        this.data[this.size] = element;
        this.size += 1;
    }

    public T pop() throws EmptyStackException {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        T oldElement = this.data[this.size - 1];
        this.data[this.size - 1] = null;
        this.size -= 1;
        return oldElement;
    }

    private void resize(int newCapacity) {
        T[] biggerArray = (T[]) new Object[newCapacity];
        System.arraycopy(this.data, 0, biggerArray, 0, this.size);
        this.data = biggerArray;
    }

    public static class EmptyStackException extends RuntimeException {
        public EmptyStackException() {
            super("Stack is empty");
        }
    }
}
