package com.company.hw2;

import com.company.hw5.utilityClasses.SinglyLinkedList;

public class Problem_5 {
    public static void main(String[] args) {
        SinglyLinkedList<String> strings = new SinglyLinkedList<>();
        strings.addLast("Armine");
        strings.addLast("Donara");
        strings.addLast("Eva");
        strings.addLast("Lusine");
        strings.addLast("Marieta");
        strings.addLast("Nelli");
        strings.addLast("Sona");
        strings.addLast("Tamara");
        System.out.println(contains("Sona", strings, 0, strings.size() - 1));
    }

    public static boolean contains(String target, SinglyLinkedList<String> strings, int low, int high) {
        if (low > high) return false;
        int mid = (low + high) / 2;
        String current = strings.at(mid);
        if (target.equals(current)) return true;
        else if (target.compareTo(current) > 0) {
            return contains(target, strings, mid + 1, high);
        } else {
            return contains(target, strings, low, mid - 1);
        }
    }
}

