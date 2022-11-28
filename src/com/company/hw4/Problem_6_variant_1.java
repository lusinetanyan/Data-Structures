package com.company.hw4;

public class Problem_6_variant_1 {
    public static void removeLongStrings(LinkedPositionalList<String> strings) {
        int size = strings.size();
        for(int i = 0; i < size; i++) {
            String s = strings.remove(strings.first()); // both remove() and first() have constant execution time
            if(s.length() <= 10) strings.addLast(s); // both length() and addLast() have constant execution time
        } // execution time - O(n)
    }

    private static void print(LinkedPositionalList<String> strings) {
        for(int i = 0; i < strings.size(); i++) {
            String s = strings.remove(strings.first());
            System.out.println(s);
            strings.addLast(s);
        }
    }

    public static void main(String[] args) {
        LinkedPositionalList<String> strings = new LinkedPositionalList<>();
        strings.addLast("dsfh");
        strings.addLast("ewhfuewhdjwijioj");
        strings.addLast("reregre");
        strings.addLast("efjefijerijfijerijf");
        strings.addLast("rf");
        strings.addLast("dfhcbehbjsernf");
        removeLongStrings(strings);
        print(strings);
    }
}
