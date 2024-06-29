package com.simontran.collections.set;

public class AVLTreeSet<K extends Comparable<K>> implements SortedSet<K> {
    private static class Node<K> {
        K key;
        Node<K> left;
        Node<K> right;
        int height;

        Node(K key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private Node<K> root;

    public AVLTreeSet() {
        this.root = null;
    }

    public boolean contains(K key) {
        Node<K> current = this.root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public K getFirst() {
        Node<K> current = this.root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    public K getLast() {
        Node<K> current = this.root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public boolean add(K key) {
        if (contains(key)) {
            return false;
        }
        this.root = add(this.root, key);
        return true;
    }

    private Node<K> add(Node<K> root, K key) {
        if (root == null) {
            return new Node<>(key);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = add(root.left, key);
        } else if (cmp > 0) {
            root.right = add(root.right, key);
        }
        updateHeight(root);
        return balance(root);
    }

    public boolean remove(K key) {
        if (!contains(key)) {
            return false;
        }
        this.root = remove(this.root, key);
        return true;
    }

    private Node<K> remove(Node<K> root, K key) {
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
                Node<K> successor = getSuccessor(root.right);
                root.key = successor.key;
                root.right = remove(root.right, root.key);
            }
        }
        if (root == null) {
            return null;
        }
        updateHeight(root);
        return balance(root);
    }

    private Node<K> getSuccessor(Node<K> root) {
        Node<K> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void updateHeight(Node<K> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private Node<K> balance(Node<K> node) {
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

    private int balanceFactor(Node<K> node) {
        return height(node.right) - height(node.left);
    }

    private Node<K> rotateRight(Node<K> root) {
        Node<K> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<K> rotateLeft(Node<K> root) {
        Node<K> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }
}
