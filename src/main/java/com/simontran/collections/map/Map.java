package com.simontran.collections.map;

public interface Map<K, V> {
    V get(K key);

    V put(K key, V value);

    V remove(K key);
}
