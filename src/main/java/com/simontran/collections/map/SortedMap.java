package com.simontran.collections.map;

public interface SortedMap<K, V> extends Map<K, V> {
    V getFirst();

    V getLast();
}
