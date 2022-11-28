package com.company.hw3;

import java.util.Deque;

public class ArrayDeque<E> {
    private E[] data;
    private int first = 0;
    private int size = 0;

    public ArrayDeque() { this(20); }
    public ArrayDeque(int capacity) { data = (E[]) new Object[capacity]; }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() {
        if(isEmpty()) return null;
        return data[first];
    }
    public E last() {
        if(isEmpty()) return null;
        return data[size - 1];
    }
    public void addFirst(E e) throws IllegalStateException{
        if (size == data.length) throw new IllegalStateException("Deque is full");
        data[first] = e;
        first = (first - 1 + data.length) % data.length;
        size++;
    }
    public void addLast(E e) throws IllegalStateException{
        if (size == data.length) throw new IllegalStateException("Deque is full");
        int insert = (first + size + 1) % data.length;
        data[insert] = e;
        size++;
    }
    public E removeFirst() {
        if(isEmpty()) return null;
        E answer = data[first];
        data[first] = null;
        first = (first + 1) % data.length;
        size--;
        return answer;
    }
    public  E removeLast() {
        if(isEmpty()) return null;
        int remove = (first + size) % data.length;
        E answer = data[remove];
        data[remove] = null;
        size--;
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("(");
        for(int k = 0, i = (first + 1) % data.length; k < size; k++, i = (i + 1) % data.length) {
            if(k != size - 1) {
                result.append(data[i]).append(", ");
            }
            else result.append(data[i]);
        }
        result.append(")");
        return result.toString();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(5);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        System.out.println(deque);
        deque.removeLast();
        System.out.println(deque);
        deque.addLast(6);
        System.out.println(deque);
        deque.removeFirst();
        System.out.println(deque);
        System.out.println(deque.size());
        deque.removeFirst();
        System.out.println(deque);
        deque.removeLast();
        System.out.println(deque);
        System.out.println(deque.isEmpty());
    }
}
