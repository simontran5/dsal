package com.simontran.collections.disjointset;

public class ForestDisjointSet<T> implements DisjointSet<T> {
    private int[] parent;
    private int[] rank;

    public ForestDisjointSet(int initialCapacity) {
        this.parent = new int[initialCapacity];
        this.rank = new int[initialCapacity];
        for (int i = 0; i < initialCapacity; i += 1) {
            this.parent[i] = i;
            this.rank[i] = 0;
        }
    }

    public void union(int x, int y) {
        int xRep = find(x);
        int yRep = find(y);
        // Apply union by rank
        if (this.rank[xRep] > this.rank[yRep]) {
            this.parent[yRep] = xRep;
        } else {
            this.parent[xRep] = yRep;
            if (this.rank[xRep] == this.rank[yRep]) {
                this.rank[yRep] += 1;
            }
        }
    }

    public int find(int x) {
        if (x != this.parent[x]) {
            this.parent[x] = find(this.parent[x]); // Apply path compression
        }
        return this.parent[x];
    }
}
