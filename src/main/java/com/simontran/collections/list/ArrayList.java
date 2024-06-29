package com.simontran.collections.list;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private E[] data;
    private int size;

    public ArrayList() {
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[index];
    }

    public E getFirst() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[0];
    }

    public E getLast() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[this.size - 1];
    }

    public E set(int index, E element) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = this.data[index];
        this.data[index] = element;
        return oldElement;
    }

    public void add(int index, E element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == this.data.length) {
            resize(this.data.length * GROWTH_FACTOR);
        }
        if (index < this.size) {
            System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        }
        this.data[index] = element;
        this.size += 1;
    }

    public void addFirst(E element) {
        add(0, element);
    }

    public void addLast(E element) {
        add(this.size, element);
    }

    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = this.data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.data[this.size - 1] = null;
        this.size -= 1;
        return oldElement;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(0);
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(this.size - 1);
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.size);
        this.data = newData;
    }
}
