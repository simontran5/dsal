package com.simontran.collections.queue;

public interface Queue<T> {
    T peek();

    void enqueue(T element);

    T dequeue();
}
