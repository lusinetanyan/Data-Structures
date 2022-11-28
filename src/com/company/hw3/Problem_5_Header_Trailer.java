package com.company.hw3;

import com.company.hw3.DoublyLinkedList.Node;

public class Problem_5_Header_Trailer {
    //Time complexity O(n)
    //Memory complexity O(1)
    public static <E> void reverse(Node<E> header, Node<E> trailer) {
        Node<E> temp = null;
        Node<E> current = header;

        while (current != trailer) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        } /* the body of while loop is executed n + 1 times, where
           * n = list.size(), the body contains only primitive operations,
            and all the methods that are called also have O(1) time
            complexity |=> time complexity O(n) */

        if (temp != null) {
            trailer.setPrev(header.getPrev());
            header.getPrev().setNext(trailer);
            trailer.setNext(null);
            header.setPrev(null);
            header.setNext(temp.getPrev());
            temp.getPrev().setPrev(header);
        } // Time complexity O(1)
    }

    public static void main(String[] args) {
        Node<Integer> header = new Node<>(null, null, null);
        Node<Integer> first = new Node<>(1, header, null);
        header.setNext(first);
        Node<Integer> second = new Node<>(2, first, null);
        first.setNext(second);
        Node<Integer> third = new Node<>(3, second, null);
        second.setNext(third);
        Node<Integer> forth = new Node<>(4, third, null);
        third.setNext(forth);
        Node<Integer> trailer = new Node<>(null, forth, null);
        forth.setNext(trailer);

        print(header, trailer);

        reverse(header, trailer);

        print(header, trailer);
    }

    private static <E> void print(Node<E> header, Node<E> trailer) {
        Node<E> temp = header.getNext();
        while (temp != trailer) {
            System.out.print(temp.getElement() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }
}


