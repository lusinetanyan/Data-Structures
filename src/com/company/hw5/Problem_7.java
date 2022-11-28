package com.company.hw5;

import java.util.Arrays;

public class Problem_7 {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(4, 5);
        Rectangle rectangle2 = new Rectangle(4, 4);
        Rectangle rectangle3 = new Rectangle(5, 2);
        Rectangle rectangle4 = new Rectangle(6, 1);
        Rectangle rectangle5 = new Rectangle(3, 7);

        Rectangle[] rectanglesComparable = {rectangle1, rectangle2, rectangle3, rectangle4, rectangle5};
        Arrays.sort(rectanglesComparable);
        System.out.println(Arrays.toString(rectanglesComparable));

        Rectangle[] rectanglesComparator = {rectangle1, rectangle2, rectangle3, rectangle4, rectangle5};
        Arrays.sort(rectanglesComparator, new RectangleComparator());
        System.out.println(Arrays.toString(rectanglesComparator));
    }
}
