package com.company.hw5;


import com.company.hw5.utilityClasses.AbstractPriorityQueue;
import com.company.hw5.utilityInterfaces.Entry;

import java.util.Comparator;
import java.util.Stack;

// ArrayStack is based on a fixed-capacity array.
// It can actually be very simple and efficient if
// the user knows the maximum possible capacity of the
// underlying array. Otherwise, maybe our given default
// capacity will be much larger than the required capacity
// which will cause a waste of memory. Or if the default
// capacity is less than required and array reaches its
// capacity, the method will just throw an exception.

// LinkedStack is much preferred when the maximal capacity
// of the stack is unknown. So it allows us with limitless
// capacity, and it is always proportional to the number of
// elements in the stack. Nevertheless, if the maximal
// capacity of stack is known than in that case using LinkedStack
// will result in additional memory waste because of the node
// creation.

public class StackPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private Stack<Entry<K, V>> list = new Stack<>();

    public StackPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    public StackPriorityQueue() {
        super();
    }

    @Override
    public int size() {
        return list.size();
    } //time complexity - O(1)

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        if(list.isEmpty()) list.push(newest);
        else {
            Stack<Entry<K, V>> newList = new Stack<>();
            while (!list.isEmpty() && compare(newest, list.peek()) > 0) {
                newList.push(list.pop());
            }
            list.push(newest);
            while (!newList.isEmpty()) {
                list.push(newList.pop());
            }
        }
        return newest;
    } // worst case time complexity - O(n)

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return list.peek();
    } // time complexity - O(1)

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.pop();
    } //time complexity - O(1)

}
