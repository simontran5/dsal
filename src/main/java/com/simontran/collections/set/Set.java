package com.simontran.collections.set;

public interface Set<K> {
    boolean contains(K key);

    void insert(K key);

    void remove(K key);
}
