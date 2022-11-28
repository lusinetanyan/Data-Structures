package com.company.hw2;

import java.util.Scanner;

public class Problem_2_variant_1 {
    //O(n) - n = s.length()
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(vowelConsonant(s));
    }

    public static int vowelConsonant(String s) {
        int[] vowelIndexes = new int[s.length()];
        int j = 0;
        int count = 0;

        for(int i = 0; i < s.length(); i++){
            char vowel = s.charAt(i);
            if(vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u') {
                vowelIndexes[j] = i;
                j++;
            }
        }

        for(int i = 0; i < j; i++) {
            count += s.length() - 1 - vowelIndexes[i] - (j - 1 - i);
        }

        return count;
    }
}



