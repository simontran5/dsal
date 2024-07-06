package com.simontran.collections.map;

public class TrieMap<K, V> implements Map<K, V> {
    private static class Node<K> {
        private K key;
        private V value;
        private ArrayList<Node<K, V>> children;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.children =
        }
    }

    private AVLTreeSet.Node<K> root;

    public AVLTreeSet() {
        this.root = null;
    }

    public V get(K key) {
        return null;
    }

    public void insert(K key, V value) {

    }

    public void remove(K key) {

    }
}
