package com.company.hw4;

import java.util.Iterator;

public class Problem_6_variant_2 {
    public static void removeLongStrings(LinkedPositionalList<String> strings) {
        Iterator<Position<String>> walk = strings.positions().iterator(); // constant execution time
        while (walk.hasNext()) { // hasNext() constant execution time
            if (walk.next().getElement().length() >= 10) // next(), getElement(), length() constant execution time
                walk.remove(); // remove() constant execution time
        } // O(n) execution time
    }

    private static void print(LinkedPositionalList<String> strings) {
        for (String s : strings) {
            System.out.println(s);
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
