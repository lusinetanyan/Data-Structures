package com.company.hw4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPositionalListOfIntegers implements PositionalList<Integer> {
    private PositionInteger[] list;
    private int size = 0;

    public ArrayPositionalListOfIntegers() {
        list = new PositionInteger[1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<Integer> first() {
        return list[0];
    }

    public Position<Integer> last() {
        return list[size - 1];
    }

    public Position<Integer> after(Position<Integer> p) throws IllegalArgumentException {
        PositionInteger positionInteger = validate(p);
        return list[positionInteger.getIndex() + 1];
    }

    public Position<Integer> before(Position<Integer> p) throws IllegalArgumentException {
        PositionInteger positionInteger = validate(p);
        return list[positionInteger.getIndex() - 1];
    }

    public Position<Integer> addFirst(Integer integer) {
        return add(0, integer);
    }

    public Position<Integer> addLast(Integer integer) {
        return add(size - 1, integer);
    }

    public Position<Integer> addBefore(Position<Integer> p, Integer integer) throws IllegalArgumentException {
        int index = validate(p).getIndex();
        return add(index, integer);
    }

    public Position<Integer> addAfter(Position<Integer> p, Integer integer) throws IllegalArgumentException {
        int index = validate(p).getIndex() + 1;
        return add(index, integer);
    }

    public Integer set(Position<Integer> p, Integer integer) throws IllegalArgumentException {
        PositionInteger positionInteger = validate(p);
        Integer temp = positionInteger.getElement();
        positionInteger.setElement(integer);
        return temp;
    }

    public Integer remove(Position<Integer> p) throws IllegalArgumentException {
        PositionInteger positionInteger = validate(p);
        Integer temp = positionInteger.getElement();
        for (int i = positionInteger.getIndex(); i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        positionInteger.setIndex(-1);
        return temp;
    }

    private PositionInteger validate(Position<Integer> p) throws IllegalArgumentException {
        if (!(p instanceof PositionInteger)) throw new IllegalArgumentException("Invalid p");
        PositionInteger positionInteger = (PositionInteger) p;
        if (positionInteger.getIndex() == -1)
            throw new IllegalArgumentException("p is no longer in the list");
        return positionInteger;
    }

    private void resize(int capacity) {
        PositionInteger[] temp = new PositionInteger[capacity];
        for (int k = 0; k < size; k++)
            temp[k] = list[k];
        list = temp;
    }

    private Position<Integer> add(int index, Integer integer) {
        if (size == list.length) resize(2 * size);
        PositionInteger p = new PositionInteger(integer, index);
        for (int i = size - 1; i >= index; i--) {
            PositionInteger current = list[i];
            current.setIndex(current.getIndex() + 1);
            list[i + 1] = current;
        }
        list[index] = p;
        size++;
        return p;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ElementIterator();
    }

    //space complexity of my iterator class is O(n) as I create an array with size n.
    private class PositionIterator implements Iterator<PositionInteger> {
        private int i;
        private PositionInteger[] copyList;

        public PositionIterator() {
            i = 0;
            copyList = new PositionInteger[size];
            for (int j = 0; j < size; j++) {
                copyList[j] = list[j];
            } // execution time - O(n), linear to the number of the elements in the list.
            mergeSort(copyList); // execution time - O(nlogn)
        } // Execution time of the constructor - O(nlogn)

        @Override
        public boolean hasNext() {
            return i < size;
        } // execution time - O(1)

        @Override
        public PositionInteger next() throws NoSuchElementException {
            if (i == size) throw new NoSuchElementException("No next element");
            return copyList[i++];
        } // execution time - O(1)

        public void remove() throws IllegalStateException {
            if (i >= size) throw new IllegalStateException("Nothing to remove");
            ArrayPositionalListOfIntegers.this.remove(copyList[i]); // the execution time of removal is O(n) in the worst case.
        } // execution time - O(n)

        private static void mergeSort(PositionInteger[] S) {
            int n = S.length;
            if (n < 2) return;
            int mid = n / 2;
            PositionInteger[] S1 = Arrays.copyOfRange(S, 0, mid);
            PositionInteger[] S2 = Arrays.copyOfRange(S, mid, n);
            mergeSort(S1);
            mergeSort(S2);
            merge(S1, S2, S);
        }

        private static void merge(PositionInteger[] S1, PositionInteger[] S2, PositionInteger[] S) {
            int i = 0, j = 0;
            while (i + j < S.length) {
                if (j == S2.length || (i < S1.length && S1[i].getElement() > S2[j].getElement()))
                    S[i + j] = S1[i++];
                else
                    S[i + j] = S2[j++];
            }
        }
    }

    private class PositionIterable implements Iterable<PositionInteger> {
        @Override
        public Iterator<PositionInteger> iterator() {
            return new PositionIterator();
        }
    }

    public Iterable<PositionInteger> positions() {
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<Integer> {
        Iterator<PositionInteger> positionIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public Integer next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    private static class PositionInteger implements Position<Integer> {
        private int element;
        private int index;

        public PositionInteger(int e, int i) {
            element = e;
            index = i;
        }

        public Integer getElement() throws IllegalStateException {
            if (index == -1) throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public int getIndex() {
            return index;
        }

        public void setElement(int e) {
            element = e;
        }

        public void setIndex(int i) {
            index = i;
        }
    }

    public static void main(String[] args) {
        ArrayPositionalListOfIntegers list = new ArrayPositionalListOfIntegers();
        list.addFirst(1);
        list.addLast(4);
        list.addBefore(list.last(), 3);
        list.addAfter(list.first(), 2);
        list.addLast(0);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }
}
