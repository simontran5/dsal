package com.simontran.collections.map;

public class AVLTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node<K, V> root;

    public AVLTreeMap() {
        this.root = null;
    }

    public K min() {
        return minNode(this.root).key;
    }

    public K max() {
        return maxNode(this.root).key;
    }

    public K successor(K key) {
        Node<K, V> successor = successorNode(this.root, key);
        return successor != null ? successor.key : null;
    }

    public K predecessor(K key) {
        Node<K, V> predecessor = predecessorNode(this.root, key);
        return predecessor != null ? predecessor.key : null;
    }

    public V get(K key) {
        Node<K, V> node = getNode(this.root, key);
        return node != null ? node.value : null;
    }

    public void insert(K key, V value) {
        this.root = insertNode(this.root, key, value);
    }

    public void remove(K key) {
        this.root = removeNode(this.root, key);
    }

    private Node<K, V> minNode(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node<K, V> successorNode(Node<K, V> node, K key) {
        Node<K, V> successor = null;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    private Node<K, V> predecessorNode(Node<K, V> node, K key) {
        Node<K, V> predecessor = null;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                predecessor = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return predecessor;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node<K, V> insertNode(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertNode(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insertNode(node.right, key, value);
        } else {
            node.value = value;
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<K, V> removeNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node<K, V> successor = minNode(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = removeNode(node.right, successor.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private Node<K, V> balance(Node<K, V> node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) { // right heavy
            if (balanceFactor(node.right) < 0) { // right triangle
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (balanceFactor < -1) { // left heavy
            if (balanceFactor(node.left) > 0) { // left triangle
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }

    private int balanceFactor(Node<K, V> node) {
        return height(node.right) - height(node.left);
    }

    private Node<K, V> rotateRight(Node<K, V> root) {
        Node<K, V> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<K, V> rotateLeft(Node<K, V> root) {
        Node<K, V> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }
}
