package com.company.hw7_2;

public class SetTest {
    public static void main(String[] args) {
        MySet<Integer> set = new MySet<>();
        System.out.println(set.add(7));
        System.out.println(set.add(1));
        System.out.println(set.add(3));
        System.out.println(set.add(1));
        System.out.println(set.add(5));

        for (Integer i: set) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Size is" + set.size());
        System.out.println(set.remove(1));
        System.out.println(set.remove(1));
        System.out.println(set.remove(5));

        for (Integer i: set) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Size is " + set.size());
        System.out.println(set.remove(3));
        System.out.println(set.remove(7));
        System.out.println(set.isEmpty());
    }
}
