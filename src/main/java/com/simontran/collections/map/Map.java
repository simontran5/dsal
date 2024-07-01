package com.simontran.collections.map;

public interface Map<K, V> {
    V get(K key);

    void insert(K key, V value);

    void remove(K key);
}
