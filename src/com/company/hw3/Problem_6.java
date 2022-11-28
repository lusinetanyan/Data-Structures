package com.company.hw3;

import java.util.ArrayList;
import java.util.List;

//Program has O(n^2) time complexity
public class Problem_6 {
    //Time complexity - O(n^2)
    public static void insertionSortArrayList(List<String> arr) {
        int n = arr.size(); // primitive operation
        for(int i = 1; i < n; i++) { //the body of for loop is executed n - 1 times
            String cur = arr.get(i); // arr.get(i) - O(1) time complexity
            int j = i; // primitive operation
            // arr.get(i), arr.length() - O(1) time complexity
            // the body of while loop is executed i times
            // i =      iterations of while loop(worst case)
            //     1              1
            //     2              2
            //    ...            ...
            //   n - 1          n - 1
            // 1 + 2 + ... + n - 1 = (n - 1)n/2 = O(n^2)
            while (j > 0 && arr.get(j - 1).length() > cur.length()) {
                arr.set(j, arr.get(j - 1));  // arr.set(i, e) - O(1) time complexity
                j--; // primitive operation
            }
            arr.set(j, cur); // O(1) time complexity
        }
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("Lusine");
        arr.add("Sona");
        arr.add("Eva");
        arr.add("Marieta");
        arr.add("Nelli");
        arr.add("Donara");
        arr.add("Shushanik");
        arr.add("Tamara");
        arr.add("Andranik");
        arr.add("Asatur");
        //O(1) time complexity all above statements
        insertionSortArrayList(arr);// O(n^2) time complexity
        System.out.println(arr);
    }
}
