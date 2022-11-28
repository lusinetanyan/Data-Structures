package com.company.hw6;

import com.company.hw6.UtilityClasses.HeapPriorityQueue;
import com.company.hw6.UtilityClasses.SortedPriorityQueue;
import com.company.hw6.UtilityClasses.UnsortedPriorityQueue;
import com.company.hw6.UtilityInterfaces.PriorityQueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer, String> tokens = new UnsortedPriorityQueue<>();
        while (sc.hasNext()) {
            String token = sc.next();
            if(token.equals("exit")) break;
            tokens.insert(count(token), token);
        }
        printTokensInOrder(tokens);
    }

    public static void printTokensInOrder(PriorityQueue<Integer, String> tokens) {
        HashMap<Integer, String> tokensAndCounts = new HashMap<>();
        while (!tokens.isEmpty()) {
            String token = tokens.min().getValue();
            int count = tokens.removeMin().getKey();
            if(tokensAndCounts.containsKey(count)) continue;
            tokensAndCounts.put(count, token);
        }
        for (String token: tokensAndCounts.values()) {
            System.out.println(token);
        }
    }

    static int count(String token) {
        int count = 0;
        for (char c: token.toCharArray()) {
            if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                count++;
            }
        }
        return count;
    }
}
