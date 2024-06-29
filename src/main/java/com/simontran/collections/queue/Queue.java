package com.simontran.collections.queue;

public interface Queue<E> {
    E peek();

    void enqueue(E element);

    E dequeue();
}
