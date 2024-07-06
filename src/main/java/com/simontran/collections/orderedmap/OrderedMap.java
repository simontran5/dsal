package com.simontran.collections.orderedmap;

import com.simontran.collections.map.Map;

public interface OrderedMap<K, V> extends Map<K, V> {
    K min();

    K max();

    K successor(K key);

    K predecessor(K key);
}
