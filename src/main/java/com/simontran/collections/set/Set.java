package com.simontran.collections.set;

public interface Set<K> {
    boolean contains(K key);

    boolean add(K key);

    boolean remove(K key);
}
