package com.simontran.collections.set;

public interface OrderedSet<K> extends Set<K> {
    K min();

    K max();

    K successor(K key);

    K predecessor(K key);
}
