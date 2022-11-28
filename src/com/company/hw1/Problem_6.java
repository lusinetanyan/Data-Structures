package com.company.hw1;

import java.util.Scanner;
//Overall, time O(log(n)), memory O(log(n))
public class Problem_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number:");
        int n = sc.nextInt();
        //constant amount of time
        System.out.println(numberOfOddDigits(n));
        //O(log(n))
    }
    //time O(log(n))
    //memory O(1)

    // numberOfOddDigits(n) - time O(log(n)), memory O(log10(n) + 2) = O(log10(n))
    private static int numberOfOddDigits(int n) {
        if (n / 10 == 0) // 1 primitive operation
            return n % 2 == 0 ? 0 : 1; // 5 primitive operations
        return ((n % 10) % 2 == 0 ? 0 : 1) + numberOfOddDigits(n / 10);
        // 6 primitive operations and recursive call, which is performed log10(n) + 1 times.
    }
    //T(n) = 1 + 6 + T(n / 10) = 7 + 7 + T(n / 100) = ... = (log10(n)) * 7 + T(0) = log10(n)) * 7 + 6
}
