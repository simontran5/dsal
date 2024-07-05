package com.simontran.collections.list;

public class DoublyLinkedList<T> implements List<T> {
    private static class Node<T> {
        private Node<T> prev;
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.prev = null;
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> dummyHead;
    private Node<T> dummyTail;
    private int size;

    public DoublyLinkedList() {
        this.dummyHead = new Node<>(null);
        this.dummyTail = new Node<>(null);
        this.dummyHead.next = this.dummyTail;
        this.dummyTail.prev = this.dummyHead;
        this.size = 0;
    }

    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.getNode(index).data;
    }

    public T front() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.dummyHead.next.data;
    }

    public T back() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.dummyTail.prev.data;
    }

    public T set(int index, T element) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = this.getNode(index);
        T oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    public void insert(int index, T element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(element);
        Node<T> prevNode;
        Node<T> nextNode;
        if (this.size == 0) {
            prevNode = this.dummyHead;
            nextNode = this.dummyTail;
        } else if (index == 0) {
            prevNode = this.dummyHead;
            nextNode = this.dummyHead.next;
        } else if (index == this.size) {
            prevNode = this.dummyTail.prev;
            nextNode = this.dummyTail;
        } else {
            prevNode = this.getNode(index - 1);
            nextNode = prevNode.next;
        }
        prevNode.next = newNode;
        nextNode.prev = newNode;
        newNode.next = nextNode;
        newNode.prev = prevNode;
        this.size += 1;
    }

    public void pushFront(T element) {
        this.insert(0, element);
    }

    public void pushBack(T element) {
        this.insert(this.size, element);
    }

    public T remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> removeNode;
        Node<T> prevNode;
        Node<T> nextNode;
        if (index == 0) {
            removeNode = this.dummyHead.next;
            prevNode = this.dummyHead;
            nextNode = removeNode.next;
        } else if (index == this.size - 1) {
            removeNode = this.dummyTail.prev;
            prevNode = removeNode.prev;
            nextNode = this.dummyTail;
        } else {
            prevNode = this.getNode(index - 1);
            removeNode = prevNode.next;
            nextNode = removeNode.next;
        }
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        T removedElement = removeNode.data;
        this.size -= 1;
        return removedElement;
    }

    public T removeFront() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.remove(0);
    }

    public T removeBack() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.remove(this.size - 1);
    }

    private Node<T> getNode(int index) {
        Node<T> current;
        if (index < this.size / 2) {
            current = this.dummyHead.next;
            for (int i = 0; i < index; i += 1) {
                current = current.next;
            }
        } else {
            current = this.dummyTail;
            for (int i = this.size; i > index; i -= 1) {
                current = current.prev;
            }
        }
        return current;
    }
}