package com.company.hw6.UtilityInterfaces;

public interface Queue<E> {

    int size();

    boolean isEmpty();

    void enqueue(E e);

    E first();

    E dequeue();
}

