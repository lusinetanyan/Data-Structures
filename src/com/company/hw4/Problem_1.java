package com.company.hw4;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {
    // The execution time of this program is linear to either the
    // absolute value of the maximum number in the list or the number
    // of element in the list, it depends on which one is greater.
    // Execution time - O(n + max)
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of integers to be sorted:");
        int n = sc.nextInt();
        System.out.println("Enter the maximum absolute value of the numbers:");
        int max = sc.nextInt();
        System.out.println("Enter the numbers:");
        while (n != 0) {
            int number = sc.nextInt();
            if(max < number) {
                System.out.println("Invalid input. The number is greater than maximum.");
                System.exit(0);
            }
            list.add(number);
            n--;
        } // execution time - O(n)
        bucketSortArrayLists(list, max); // execution time O(n + max)
        System.out.println(list);
    }

    public static void bucketSortArrayLists(ArrayList<Integer> list, int max) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < max + 1; i++) {
            buckets.add(null);
        } // execution time - O(max)
        for (Integer number : list) {
            if (buckets.get(Math.abs(number)) == null) {
                ArrayList<Integer> bucket = new ArrayList<>();
                bucket.add(number);
                buckets.set(Math.abs(number), bucket);
            } else {
                buckets.get(Math.abs(number)).add(number);
            }
        } // execution time - O(n)
        list.clear();
        for (ArrayList<Integer> bucket : buckets) {
            int index = 0;
            if (bucket != null) {
                while (index < bucket.size()) {
                    list.add(bucket.get(index));
                    index++;
                }
            }
        } // execution time - O(n + max)
    }
}
