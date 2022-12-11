package com.company.hw7_2.UtilityInterfaces;

import java.util.Iterator;

public interface Set<E> {
    boolean add(E e);
    boolean remove(E e);
    boolean contains(E e);
    Iterator<E> iterator();
    int size();
    boolean isEmpty();
}
