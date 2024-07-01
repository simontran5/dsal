//package com.simontran.collections.map;
//
//public class RedBlackTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {
//    private static final boolean RED = false;
//    private static final boolean BLACK = true;
//
//    private static class Node<K, V> {
//        private K key;
//        private V value;
//        private Node<K, V> left;
//        private Node<K, V> right;
//        private Node<K, V> parent;
//        private boolean color;
//
//        public Node(K key, V value) {
//            this.key = key;
//            this.value = value;
//            this.left = null;
//            this.right = null;
//            this.parent = null;
//            this.color = RED;
//        }
//    }
//
//    private Node<K, V> root;
//
//    public RedBlackTreeMap() {
//        this.root = null;
//    }
//
//    public K min() {
//        Node<K, V> current = this.root;
//        while (current.left != null) {
//            current = current.left;
//        }
//        return current.key;
//    }
//
//    public K max() {
//        Node<K, V> current = this.root;
//        while (current.right != null) {
//            current = current.right;
//        }
//        return current.key;
//    }
//
//    public K successor(K key) {
//
//    }
//
//    public V get(K key) {
//        Node<K, V> current = this.root;
//        while (current != null) {
//            int cmp = key.compareTo(current.key);
//            if (cmp < 0) {
//                current = current.left;
//            } else if (cmp > 0) {
//                current = current.right;
//            } else {
//                return current.value;
//            }
//        }
//        return null;
//    }
//
//    public void insert(K key, V value) {
//        Node<K, V> newNode = new Node<>(key, value);
//        Node<K, V> parent = null;
//        Node<K, V> current = this.root;
//        while (current != null) {
//            parent = current;
//            int cmp = key.compareTo(current.key);
//            if (cmp < 0) {
//                current = current.left;
//            } else if (cmp > 0) {
//                current = current.right;
//            } else {
//                current.value = value;
//                return;
//            }
//        }
//        newNode.parent = parent;
//        if (parent == null) {
//            this.root = newNode;
//        } else {
//            int cmp = key.compareTo(parent.key);
//            if (cmp < 0) {
//                parent.left = newNode;
//            } else {
//                parent.right = newNode;
//            }
//        }
//        insertFixup(newNode);
//    }
//
//    public void remove(K key) {
//
//    }
//
//    private void insertFixup(Node<K, V> newNode) {
//        while (newNode.parent != null && newNode.parent.color == RED) {
//            if (newNode.parent == newNode.parent.parent.left) {
//                Node<K, V> uncle = newNode.parent.parent.right;
//                if (uncle != null && uncle.color == RED) { // Case 1: Uncle is red
//                    newNode.parent.color = BLACK;
//                    uncle.color = BLACK;
//                    newNode.parent.parent.color = RED;
//                    newNode = newNode.parent.parent;
//                } else {
//                    if (newNode == newNode.parent.right) {  // Case 2: Uncle is black and newNode is a right child
//                        newNode = newNode.parent;
//                        leftRotate(newNode);
//                    }
//                    newNode.parent.color = BLACK; // Case 3: Uncle is black and newNode is a left child
//                    newNode.parent.parent.color = RED;
//                    rightRotate(newNode.parent.parent);
//                }
//            } else { // Same as above with "left" and "right" exchanged
//                Node<K, V> uncle = newNode.parent.parent.left;
//                if (uncle != null && uncle.color == RED) {
//                    newNode.parent.color = BLACK;
//                    uncle.color = BLACK;
//                    newNode.parent.parent.color = RED;
//                    newNode = newNode.parent.parent;
//                } else {
//                    if (newNode == newNode.parent.left) {
//                        newNode = newNode.parent;
//                        rightRotate(newNode);
//                    }
//                    newNode.parent.color = BLACK;
//                    newNode.parent.parent.color = RED;
//                    leftRotate(newNode.parent.parent);
//                }
//            }
//        }
//        this.root.color = BLACK;
//    }
//
//    private void leftRotate(Node<K, V> pivotNode) {
//        Node<K, V> rightChild = pivotNode.right;
//        pivotNode.right = rightChild.left;
//        if (rightChild.left != null) {
//            rightChild.left.parent = pivotNode;
//        }
//        rightChild.parent = pivotNode.parent;
//        if (pivotNode.parent == null) {
//            this.root = rightChild;
//        } else if (pivotNode == pivotNode.parent.left) {
//            pivotNode.parent.left = rightChild;
//        } else {
//            pivotNode.parent.right = rightChild;
//        }
//        rightChild.left = pivotNode;
//        pivotNode.parent = rightChild;
//    }
//
//    private void rightRotate(Node<K, V> pivotNode) {
//        Node<K, V> leftChild = pivotNode.left;
//        pivotNode.left = leftChild.right;
//        if (leftChild.right != null) {
//            leftChild.right.parent = pivotNode;
//        }
//        leftChild.parent = pivotNode.parent;
//        if (pivotNode.parent == null) {
//            this.root = leftChild;
//        } else if (pivotNode == pivotNode.parent.right) {
//            pivotNode.parent.right = leftChild;
//        } else {
//            pivotNode.parent.left = leftChild;
//        }
//        leftChild.right = pivotNode;
//        pivotNode.parent = leftChild;
//    }
//}
