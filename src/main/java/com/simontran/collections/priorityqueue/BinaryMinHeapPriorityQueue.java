package com.simontran.collections.priorityqueue;

import java.util.ArrayList;

public class BinaryMinHeapPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {
    private final ArrayList<K> data;

    public BinaryMinHeapPriorityQueue() {
        this.data = new ArrayList<>();
        this.data.add(null);
    }

    public BinaryMinHeapPriorityQueue(ArrayList<K> initialKeys) {
        this.data = new ArrayList<>();
        this.data.add(null);
        this.data.addAll(initialKeys);
        buildMinHeap();
    }

    private void buildMinHeap() {
        for (int i = parent(this.data.size() - 1); i >= 1; i--) {
            siftDown(i);
        }
    }

    public void insert(K key) {
        this.data.add(key);
        siftUp(this.data.size() - 1);
    }

    public K peek() {
        if (this.data.size() == 1) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return this.data.get(1);
    }

    public K removeTop() {
        K top = this.data.get(1);
        this.data.set(1, this.data.getLast());
        this.data.removeLast();
        siftDown(1);
        return top;
    }

    private void siftDown(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);
        if (left <= this.data.size() - 1 && this.data.get(left).compareTo(this.data.get(smallest)) < 0) {
            smallest = left;
        }
        if (right <= this.data.size() - 1 && this.data.get(right).compareTo(this.data.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    private void siftUp(int i) {
        if (i > 1 && this.data.get(i).compareTo(this.data.get(parent(i))) < 0) {
            swap(i, parent(i));
            siftUp(parent(i));
        }
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void swap(int i, int j) {
        K temp = this.data.get(i);
        this.data.set(i, this.data.get(j));
        this.data.set(j, temp);
    }
}
