package com.simontran.collections.disjointset;

public interface DisjointSet<E> {
    void union(int x, int y);

    int find(int x);
}
