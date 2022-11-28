package com.company.hw3;

public class ArrayQueue<E> {
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return (sz == 0);
    }

    public void enqueue(E e) throws IllegalStateException {
        if (sz == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f + sz) % data.length; // use modular arithmetic
        data[avail] = e;
        sz++;
    }

    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E answer = data[f];
        data[f] = null; // dereference to help garbage collection
        f = (f + 1) % data.length;
        sz = sz - 1;
        return answer;
    }

}
