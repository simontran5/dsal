package com.simontran.collections.list;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private T[] data;
    private int size;

    public ArrayList() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size) throw new IndexOutOfBoundsException();
        return this.data[index];
    }

    public T front() throws IndexOutOfBoundsException {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return this.data[0];
    }

    public T back() throws IndexOutOfBoundsException {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return this.data[this.size - 1];
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        if (index >= this.size) throw new IndexOutOfBoundsException();
        T oldElement = this.data[index];
        this.data[index] = element;
        return oldElement;
    }

    public void insert(int index, T element) {
        if (index > this.size) throw new IndexOutOfBoundsException();
        if (this.size == this.data.length) resize(this.data.length * GROWTH_FACTOR);
        if (index < this.size) System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = element;
        this.size += 1;
    }

    public void pushFront(T element) {
        insert(0, element);
    }

    public void pushBack(T element) {
        insert(this.size, element);
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        if (index >= size) throw new IndexOutOfBoundsException();
        T oldElement = this.data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.data[this.size - 1] = null;
        this.size -= 1;
        return oldElement;
    }

    public T removeFront() throws IndexOutOfBoundsException {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return remove(0);
    }

    public T removeBack() throws IndexOutOfBoundsException {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return remove(this.size - 1);
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.size);
        this.data = newData;
    }
}
