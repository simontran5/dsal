package com.simontran.collections.list;

public class SinglyLinkedList<T> implements List<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        if (index == 0) return this.head.data;
        if (index == this.size - 1) return this.tail.data;
        return getNode(index).data;
    }

    public T front() {
        if (this.head == null) throw new IndexOutOfBoundsException();
        return this.head.data;
    }

    public T back() {
        if (this.tail == null) throw new IndexOutOfBoundsException();
        return this.tail.data;
    }

    public T set(int index, T element) {
        if (index >= this.size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            T oldElement = this.head.data;
            this.head.data = element;
            return oldElement;
        }
        if (index == size - 1) {
            T oldElement = this.tail.data;
            this.tail.data = element;
            return oldElement;
        }
        Node<T> node = getNode(index);
        T oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    public void insert(int index, T element) {
        if (index > this.size) throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else if (index == this.size) {
            this.tail.next = newNode;
            this.tail = newNode;
        } else {
            Node<T> prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        this.size += 1;
    }

    public void pushFront(T element) {
        insert(0, element);
    }

    public void pushBack(T element) {
        insert(this.size, element);
    }

    public T remove(int index) {
        if (index >= this.size) throw new IndexOutOfBoundsException();
        Node<T> removeNode;
        if (index == 0) {
            removeNode = this.head;
            this.head = removeNode.next;
            if (this.size == 1) this.tail = null;
        } else {
            Node<T> prevNode = getNode(index - 1);
            removeNode = prevNode.next;
            prevNode.next = removeNode.next;
            if (index == this.size - 1) this.tail = prevNode;
        }
        T removedElement = removeNode.data;
        this.size -= 1;
        return removedElement;
    }

    public T removeFront() {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return remove(0);
    }

    public T removeBack() {
        if (this.size == 0) throw new IndexOutOfBoundsException();
        return remove(this.size - 1);
    }

    private Node<T> getNode(int index) {
        Node<T> current = this.head;
        for (int i = 0; i < index; i += 1) {
            current = current.next;
        }
        return current;
    }
}