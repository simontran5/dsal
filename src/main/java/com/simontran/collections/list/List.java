package com.simontran.collections.list;

public interface List<E> {
    E get(int index);

    E getFirst();

    E getLast();

    E set(int index, E element);

    void add(int index, E element);

    void addFirst(E element);

    void addLast(E element);

    E remove(int index);

    E removeFirst();

    E removeLast();
}
