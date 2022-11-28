package com.company.hw4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListWithDDL<E> implements List<E> {
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; }

        public Node<E> getPrev() { return prev; }

        public Node<E> getNext() { return next; }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) { prev = p; }

        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public ListWithDDL() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    public int size() {
        return size;
    } // execution time - O(1)

    public boolean isEmpty() {
        return size == 0;
    } // execution time - O(1)

    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size); // checkIndex() execution time - O(1)
        Node<E> current = getNode(index); // getNode() execution time - O(n/2 + 1)
        return current.getElement();
    } // execution time - O(n/2 + 1)

    public E set(int index, E element) throws IndexOutOfBoundsException {
        checkIndex(index, size); // checkIndex() execution time - O(1)
        Node<E> current = getNode(index); // getNode() execution time - O(n/2 + 1)
        E oldElement = current.getElement();
        current.setElement(element); // setElement() execution time - O(1)
        return oldElement;
    } // execution time - O(n/2 + 1)

    public void add(int index, E element) throws IndexOutOfBoundsException {
        checkIndex(index, size + 1); // checkIndex() execution time - O(1)
        if(isEmpty()) {
            addBetween(element, header, trailer); // addBetween() execution time - O(1)
        }
        else {
            Node<E> current = getNode(index); // getNode() execution time - O(n/2 + 1)
            addBetween(element, current.getPrev(), current); // addBetween() execution time - O(1)
        }
    } // execution time - O(n/2 + 1)


    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size); // checkIndex() execution time - O(1)
        Node<E> current = getNode(index); // getNode() execution time - O(n/2 + 1)
        return remove(current);
    } // execution time - O(n/2 + 1)

    private Node<E> getNode(int index) {
        if(index == 0) return header.getNext(); // getNext() execution time - O(1)
        else if(index == size - 1) return trailer.getPrev(); // getPrev() execution time - O(1)
        else if(index == size) return trailer; // getPrev() execution time - O(1)
        else {
            int i;
            Node<E> current;
            if(index < size / 2) {
                i = 0;
                current = header.getNext();
                while (i != index) {
                    current = current.getNext();
                    i++;
                } // execution time worst case - O(n / 2), n - size of the list
            }
            else {
                i = size - 1;
                current = trailer.getPrev();
                while (i != index) {
                    current = current.getPrev();
                    i--;
                } // execution time worst case - O(n/2 + 1), n - size of the list
            }
            return current;
        }
    } // execution time - O(n/2 + 1)

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = this.header.getNext();
        while (walk != this.trailer) {
            sb.append(walk.getElement());
            if (walk != this.trailer.getPrev())
                sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        --size;
        return node.getElement();
    }

    private void checkIndex(int index, int size) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Illegal index: " + index);
    }

    private class ListIterator implements Iterator<E> {
        private int i = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return i < size / 2;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (i == size) throw new NoSuchElementException("No next element");
            removable = true;
            return get(i++);
        }

        @Override
        public void remove() {
            if (!removable) throw new IllegalStateException("Nothing to remove");
            ListWithDDL.this.remove(i - 1);
            i--;
            removable = false;
        }
    }

    public static void main(String[] args) {
        ListWithDDL<Integer> list = new ListWithDDL<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);
        System.out.println(list);
        System.out.println(list.get(2));
        list.set(2, 6);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.add(2, 7);
        for (int n : list) {
            System.out.print(n + " ");
        }
    }
}
