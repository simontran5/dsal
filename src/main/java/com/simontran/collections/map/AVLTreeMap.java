package com.simontran.collections.map;

public class AVLTreeMap<K extends Comparable<K>, V> implements SortedMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private Node<K, V> root;

    public AVLTreeMap() {
        this.root = null;
    }

    public V get(K key) {
        Node<K, V> current = this.root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public V getFirst() {
        Node<K, V> current = this.root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public V getLast() {
        Node<K, V> current = this.root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The key can't be null");
        }
        V previousValue = get(key);
        this.root = put(this.root, key, value);
        return previousValue;
    }

    public V remove(K key) {
        V previousValue = get(key);
        this.root = remove(this.root, key);
        return previousValue;
    }

    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }
        updateHeight(root);
        return balance(root);
    }

    private Node<K, V> remove(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = remove(root.left, key);
        } else if (cmp > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                Node<K, V> successor = getSuccessor(root.right);
                root.key = successor.key;
                root.value = successor.value;
                root.right = remove(root.right, root.key);
            }
        }
        if (root == null) {
            return null;
        }
        updateHeight(root);
        return balance(root);
    }

    private Node<K, V> getSuccessor(Node<K, V> root) {
        Node<K, V> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private Node<K, V> balance(Node<K, V> node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor < -1) { // left heavy
            if (balanceFactor(node.left) > 0) { // left triangle
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balanceFactor > 1) { // right heavy
            if (balanceFactor(node.right) < 0) { // right triangle
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
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
