package com.company.hw2;

import java.util.Scanner;
//Overall O(n^2) time complexity, O(n) memory consumption
public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n]; //O(n) memory consumption
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        } //linear time complexity - O(n)
        rearrange(arr, n);// O(n^2) time complexity
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        } //linear time complexity - O(n)
    }

    public static void rearrange(int[] arr, int n) { //O(n^2) time complexity, O(1) memory consumption
        boolean flag = false;
        int min;
        int falseIndex = 0;
        int trueIndex = n - 1;
        /* The main for loop is entered only n/2 + n%2 times,
         * where n = arr.length. The inner for loop firstly is
         * done n times and after every outer for loop update
         * it is done 1 fewer times. n + (n - 1) + ... + (n/2 + n%2) = (n - n/2 + n%2 + 1)(n + n/2 + n%2)/2.
         * Overall, O(n^2) */
        for(int i = falseIndex; i <= trueIndex; i++) {
            min = falseIndex;
            for(int j = falseIndex; j <= trueIndex; j++) {
                if(arr[j] < arr[min]) min = j;
            }

            if(!flag) {
                int temp = arr[falseIndex];
                arr[falseIndex] = arr[min];
                arr[min] = temp;
                falseIndex++;
            }

            if(flag){
                int temp = arr[trueIndex];
                arr[trueIndex] = arr[min];
                arr[min] = temp;
                trueIndex--;
            }

            flag = !flag;
        }
    }
}

