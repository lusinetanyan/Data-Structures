package com.company.hw1;

import java.util.Scanner;

//n = 0, for up to 1 second
//n = 15, for up to 1 minute
//n = 17, for up to 10 minutes, 18 is about 17 minutes

public class Problem_4_variant_1 {
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
    //Overall time O(n)

    //printTernaryNumbers(n) = O(n)
    public static void printTernaryNumbers(int n) {
        if(n > 0) {
            printTernaryNumbers(n, 0, "", "", n);//O(n)
            printTernaryNumbers(n, 1, "", "", n);//O(n)
            printTernaryNumbers(n, 2, "", "", n);//O(n)
        }
    }

    //printTernaryNumbers(n) = O(n)
    private static void printTernaryNumbers(int n, int first, String ternary, String previous, int length) {
        if(n == 0) {
            if(length == 1 || ternary.charAt(0) != '0') {
                System.out.println(ternary);
            }
            return;
        }
        //constant amount of time

        printTernaryNumbers(n - 1, 0, "" + ternary + first, previous, length);
        previous = ternary + first;
        if(previous.length() == length && ("" + ternary + first).equals(previous)) return;
        printTernaryNumbers(n - 1, 1, "" + ternary + first, previous, length);
        previous = ternary + first;
        if(previous.length() == length && ("" + ternary + first).equals(previous)) return;
        printTernaryNumbers(n - 1, 2, "" + ternary + first, previous, length);
    }
    //T(n) = c + 3 * T(n - 1) = ... = (n - 1) * c + T(0) = c * n = O(n)
}
