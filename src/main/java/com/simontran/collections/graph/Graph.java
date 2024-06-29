package com.simontran.collections.graph;

import java.util.ArrayList;

//https://en.wikipedia.org/wiki/Graph_(abstract_data_type)
public interface Graph<V> {
    void addVertex(V vertex);

    void addEdge(V vertex1, V vertex2);

    void addEdge(V vertex1, V vertex2, double weight);

    ArrayList<V> getVertices();

    ArrayList<Edge<V>> getEdges();

    ArrayList<V> getNeighbors(V vertex);

    boolean hasVertex(V vertex);

    boolean hasEdge(V vertex1, V vertex2);

    void removeVertex(V vertex);

    void removeEdge(V vertex1, V vertex2);

    class Edge<V> {
        V source;
        V destination;
        double weight;

        public Edge(V source, V destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}