package com.simontran.collections.orderedset;

import com.simontran.collections.set.Set;

public interface OrderedSet<K> extends Set<K> {
    K min();

    K max();

    K successor(K key);

    K predecessor(K key);
}
