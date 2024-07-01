package com.simontran.collections.map;

public interface OrderedMap<K, V> extends Map<K, V> {
    K min();

    K max();

    K successor(K key);

    K predecessor(K key);
}
