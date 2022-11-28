package com.company.hw2;

import java.util.Scanner;
//time consumption - O(nlog(n))
public class Problem_2_variant_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(vowelConsonant(s));
    }

    public static int vowelConsonant(String s) { //time consumption - O(nlog(n))
        return vowelConsonant(s, 0, s.length() - 1, 0);
    }
    //time consumption - O(nlog(n))
    public static int vowelConsonant(String s, int low, int high, int count) {
        if (low > high) return count;
        char vowel = s.charAt(low);
        if (vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u') {
            for (int i = low + 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') continue;
                count++;
            }
        }
        if(high == low) return count;
        int mid = (low + high) / 2;
        count = vowelConsonant(s, low + 1, mid, count);
        count = vowelConsonant(s, mid + 1, high, count);
        return count;
    }
}


