package com.company.hw7_2;

import com.company.hw7_2.UtilityClasses.UnsortedTableMap;
import com.company.hw7_2.UtilityInterfaces.Set;

import java.util.Iterator;

public class MySet<E> implements Set<E>, Iterable<E> {

    private UnsortedTableMap<E, Integer> set = new UnsortedTableMap<>();

    @Override
    public boolean add(E e) {
        if (set.get(e) != null) return false;
        set.put(e, 0);
        return true;
    }

    @Override
    public boolean remove(E e) {
        Integer value = set.remove(e);
        return value != null;
    }

    @Override
    public boolean contains(E e) {
        Integer value = set.get(e);
        return value != null;
    }

    @Override
    public Iterator<E> iterator() {
        return set.keySet().iterator();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
