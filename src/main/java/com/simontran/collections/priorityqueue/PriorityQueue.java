package com.simontran.collections.priorityqueue;

public interface PriorityQueue<K extends Comparable<K>> {
    K peek();

    void insert(K key);

    K removeTop();
}
