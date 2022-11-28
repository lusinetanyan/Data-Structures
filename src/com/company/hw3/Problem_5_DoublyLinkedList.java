package com.company.hw3;

public class Problem_5_DoublyLinkedList {
    //Time complexity - O(n)
    //Memory complexity - O(1)
    public static <E> void reverse(DoublyLinkedList<E> list) {
        int n = list.size();
        if(n <= 1) return;
        else {
            E first = list.removeFirst();
            E last = list.removeLast();
            reverse(list); /* The recursive call is done n/2 times, where n = list.size(),
                            * each recursive call only contains primitive operations |=>
                            * time complexity = O(n) */
            list.addLast(first);
            list.addFirst(last);
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        print(list);
        reverse(list);
        print(list);
    }

    private static <E> void print(DoublyLinkedList<E> list) {
        int n = list.size();
        while (n != 0) {
            System.out.print(list.first() + " ");
            list.addLast(list.removeFirst());
            n--;
        }
        System.out.println();
    }
}

