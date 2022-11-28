package com.company.hw1;

import java.util.Scanner;
//Overall, time O(n), memory O(n)

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of integers");
        int n = sc.nextInt();
        if(n > 0) {
            System.out.println("Enter the integers");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            } // O(n)
            System.out.println(isNonDecreasing(arr, n - 1)); // O(n-1) = O(n)
        }
    }
    //time O(n), memory O(n)

    //isNonDecreasing(n) - time O(n), memory O(n)
    public static boolean isNonDecreasing(int[] arr, int lastIndex) {
        if(lastIndex == 1)  // 1 primitive operation
            return (arr[0] <= arr[1]); // 4 primitive operation
        else return (arr[lastIndex] >= arr[lastIndex - 1] && isNonDecreasing(arr, lastIndex - 1));
        // 6 primitive operations && recursive call, which is performed (lastIndex - 1) times.
    }
    //memory consumption - O(n)
    //n = lastIndex
    //T(n) = 1 + 6 + T(n - 1) = 7 + 7 + T(n - 2) = ... = (n - 1) * 7 + T(1) = (n - 1) * 7 + 5 = 7n - 1
}
