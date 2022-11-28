package com.company.hw6;

import com.company.hw6.UtilityClasses.HeapPriorityQueue;
import com.company.hw6.UtilityClasses.SortedPriorityQueue;
import com.company.hw6.UtilityClasses.UnsortedPriorityQueue;
import com.company.hw6.UtilityInterfaces.PriorityQueue;

import java.util.Random;

import static com.company.hw6.Problem3.count;
import static com.company.hw6.Problem3.printTokensInOrder;

public class Problem_3_Test {
    public static void main(String[] args) {
        PriorityQueue<Integer, String> tokens1 = new UnsortedPriorityQueue<>();
        PriorityQueue<Integer, String> tokens2 = new SortedPriorityQueue<>();
        PriorityQueue<Integer, String> tokens3 = new HeapPriorityQueue<>();
        int i = 0;
        while (i != 1000) {
            String token = generateRandomAlphanumericString();
            int count = count(token);
            tokens1.insert(count, token);
            tokens2.insert(count, token);
            tokens3.insert(count, token);
            i++;
        }

        long startTime1 = System.nanoTime();
        printTokensInOrder(tokens1);
        long elapsedTime1 = System.nanoTime() - startTime1;
        System.out.println("Total execution time with UnsortedPriorityQueue in millis: "
                + elapsedTime1/1000000);
        System.out.println();

        long startTime2 = System.nanoTime();
        printTokensInOrder(tokens2);
        long elapsedTime2 = System.nanoTime() - startTime2;
        System.out.println("Total execution time with SortedPriorityQueue in millis: "
                + elapsedTime2/1000000);
        System.out.println();

        long startTime3 = System.nanoTime();
        printTokensInOrder(tokens3);
        long elapsedTime3 = System.nanoTime() - startTime3;
        System.out.println("Total execution time with HeapPriorityQueue in millis: "
                + elapsedTime3/1000000);
    }

    public static String generateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    } //https://www.baeldung.com/java-random-string
}
