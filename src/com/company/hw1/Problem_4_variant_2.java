package com.company.hw1;

import java.util.Scanner;

// n = 0, for up to 1 second
// n = 11, for up to 1 minute
// n = 12, for up to 10 minutes, 13 is about 15 minutes

public class Problem_4_variant_2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number:");
        int n = sc.nextInt();
        printTernaryNumbers(n);
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");
    }
    //Overall time O(n * m) = O(n * 3^n)

    //printTernaryNumbers(n) = O(n * m)
    public static void printTernaryNumbers(int n) {
        if (n > 0) {
            String[] numbers;
            if(n == 1) {
                numbers = new String[]{"0", "1", "2"};
            }
            //constant amount of time
            else {
                numbers = new String[(int) Math.pow(3, n - 1) * 2];
                addTernaryNumbers(n, 1, "", numbers, 0);//O(n * m)
                addTernaryNumbers(n, 2, "", numbers, (int) Math.pow(3, n - 1));//O(n * m)
            }

            for (String number : numbers) {
                System.out.println(number);
            } // worst case O(m)
        }
    }

    //addTernaryNumbers(n,m) = O(n * m)
    private static void addTernaryNumbers(int n, int first, String ternary, String[] arr, int index) {
        if (index != 0 && ternary.equals(arr[index - 1])) return;
        if (n == 0) {
            if(ternary.charAt(0) != '0') {
                arr[index] = ternary;
            }
            return;
        }
        //constant amount of time - c primitive operations
        addTernaryNumbers(n - 1, 0, "" + ternary + first, arr, getIndex(arr)); // 4 + m primitive operations
        addTernaryNumbers(n - 1, 1, "" + ternary + first, arr, getIndex(arr)); // 4 + m primitive operations
        addTernaryNumbers(n - 1, 2, "" + ternary + first, arr, getIndex(arr)); // 4 + m primitive operations
    }
    //T(n) = c + 3 * (4 + m) + 3 * T(n - 1) = 2 * (c + 3 * (4 + m)) + 3 * T(n - 2) = ... = (n - 1) * (c + 3 * (4 + m)) + 3 * T(0) = O(n * m) = O(n * 3^n)

    //m = arr.length = 2 * 3^(n - 1);
    //getIndex(m) = O(3^n)
    private static int getIndex(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return arr.length - 1;
    }
}
