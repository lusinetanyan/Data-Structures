package com.company.hw3;

//Memory consumption of the data structure is O(n) - n - number of elements in the list
public class CircularlyDoublyLinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        //Time complexity O(1)
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; } //Time complexity O(1)
        public Node<E> getPrev() { return prev; } //Time complexity O(1)
        public Node<E> getNext() { return next; } //Time complexity O(1)
        public void setPrev(Node<E> p) { prev = p; } //Time complexity O(1)
        public void setNext(Node<E> n) { next = n; } //Time complexity O(1)
    }

    private Node<E> sentinel;
    private int size;

    //Time complexity O(1)
    public CircularlyDoublyLinkedList() {
        this.sentinel = new Node<>(null, null, null); //Time complexity O(1)
        sentinel.setPrev(sentinel); //Time complexity O(1)
        sentinel.setNext(sentinel); //Time complexity O(1)
        this.size = 0;
    }

    //Time complexity O(1)
    public  int size() { return this.size; }

    //Time complexity O(1)
    public  boolean isEmpty() { return size == 0; }

    //Time complexity O(1)
    public E first() {
        if(isEmpty()) return null; //isEmpty() - time complexity O(1)
        else return this.sentinel.getNext().getElement(); //Time complexity O(1)
    }

    //Time complexity O(1)
    public E last() {
        if(isEmpty()) return null; //isEmpty() - time complexity O(1)
        else return this.sentinel.getPrev().getElement(); //Time complexity O(1)
    }

    //Time complexity O(1)
    public  void addFirst(E e) {
        addBetween(e, sentinel, sentinel.getNext()); //addBetween() - time complexity O(1)
    }

    //Time complexity O(1)
    public  void addLast(E e) {
        addBetween(e, sentinel.getPrev(), sentinel); //addBetween() - time complexity O(1)
    }

    //Time complexity O(1)
    public  E removeFirst() {
        if(isEmpty()) return null; //isEmpty() - time complexity O(1)
        return remove(sentinel.getNext()); //Time complexity O(1)
    }

    //Time complexity O(1)
    public  E removeLast() {
        if(isEmpty()) return null; //isEmpty() - time complexity O(1)
        return remove(sentinel.getPrev()); //Time complexity O(1)
    }

    public void rotate() {
        if (!isEmpty()) { //isEmpty() - time complexity O(1)
            this.addLast(this.removeFirst()); //Time complexity O(1)
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = sentinel.getNext();
        while (walk != sentinel) {
            sb.append(walk.getElement());
            if (walk != sentinel.getPrev())
                sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    //Time complexity O(1)
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor); //Time complexity O(1)
        predecessor.setNext(newest); //Time complexity O(1)
        successor.setPrev(newest); //Time complexity O(1)
        size++;
    }

    //Time complexity O(1)
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev(); //Time complexity O(1)
        Node<E> successor = node.getNext(); //Time complexity O(1)
        predecessor.setNext(successor); //Time complexity O(1)
        successor.setPrev(predecessor); //Time complexity O(1)
        size--;
        return node.getElement(); //Time complexity O(1)
    }

    public static void main(String[] args) {
        CircularlyDoublyLinkedList<Integer> numbers = new CircularlyDoublyLinkedList<>();
        numbers.addFirst(1);
        numbers.addLast(2);
        numbers.addLast(3);
        System.out.println(numbers);
        numbers.rotate();
        System.out.println(numbers);
        numbers.removeFirst();
        System.out.println(numbers);
        numbers.removeLast();
        System.out.println(numbers);
        System.out.println(numbers.isEmpty());
        System.out.println(numbers.size());
        numbers.removeLast();
        System.out.println(numbers);
    }
}
