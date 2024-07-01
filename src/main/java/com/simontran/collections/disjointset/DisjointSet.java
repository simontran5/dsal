package com.simontran.collections.disjointset;

public interface DisjointSet<T> {
    void union(int x, int y);

    int find(int x);
}
