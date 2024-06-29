package com.simontran.collections.map;

public class RedBlackTreeMap<K, V> implements SortedMap<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = RED;
        }
    }

    private Node<K, V> root;

    public RedBlackTreeMap() {
        this.root = null;
    }

    public V getFirst() {
        return null;
    }

    public V getLast() {
        return null;
    }

    public V get(K key) {
        return null;
    }

    public V put(K key, V value) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

}
