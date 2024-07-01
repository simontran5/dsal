package com.simontran.collections.list;

public interface List<T> {
    T get(int index);

    T front();

    T back();

    T set(int index, T element);

    void insert(int index, T element);

    void pushFront(T element);

    void pushBack(T element);

    T remove(int index);

    T removeFront();

    T removeBack();
}
