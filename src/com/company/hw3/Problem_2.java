package com.company.hw3;

import java.util.Scanner;
import java.util.Stack;

public class Problem_2 {
    //n - the number of digits
    //Overall, time complexity is O(3^n), memory consumption - 2 * (2 * n + 1) - O(n)
    public static void printTernaryNumbers(int number, String string) {
        Stack<Integer> numbers = new Stack<>();
        numbers.push(number);
        Stack<String> strings = new Stack<>();
        strings.push(string);
        int count = 0;
        int maxSize = 0;
        //The stack constructor's time complexity, depending on the implementation, can be maximum O(n)
        while (numbers.size() > 0 && strings.size() > 0) {
            if (strings.size() > maxSize) maxSize = strings.size();
            count++;
            int n = numbers.pop();
            String s = strings.pop();
            if (n == 0) {
                if (number == 1) {
                    System.out.println(s);
                } else {
                    if (s.charAt(0) != '0') {
                        System.out.println(s);
                    }
                }
                continue;
            }
            numbers.push(n - 1);
            strings.push(s + "0");
            numbers.push(n - 1);
            strings.push(s + "1");
            numbers.push(n - 1);
            strings.push(s + "2");
        } // The body of the while loop does constant amount of work,
          // and the body is executed [3^n / 2] + 3^n times which is O(3^n)
        System.out.println("This is how many times the while loop was executed");
        System.out.println(count);
        System.out.println(Math.floor(Math.pow(3, number) / 2) + Math.pow(3, number));
        System.out.println("This is the maximum size that the stack reached");
        System.out.println(maxSize);
        System.out.println(2 * number + 1);
    }

    public static void printTernaryNumbers(int n) {
        if (n > 0) {
            printTernaryNumbers(n, "");//Time complexity - O(3^n)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printTernaryNumbers(n);//Time complexity - O(3^n)
    }
}
