package com.company.hw7_2;

import com.company.hw7_2.UtilityClasses.Rectangle;
import com.company.hw7_2.UtilityClasses.Rectangle1;
import com.company.hw7_2.UtilityClasses.Rectangle2;
import com.company.hw7_2.UtilityClasses.Rectangle3;

import java.util.HashMap;
import java.util.Random;

// The hashcode() function in Object class is often
// the integer representation of the address where
// our object is stored. So, even if our rectangles
// has the same heights and widths, they will be
// considered different and, eventually, we will end up
// having two keys in our map which are the same from our
// perspective. Overriding the hashcode() function to use
// the widths of the rectangles will not solve the issue
// as even if the heights of the rectangles do not coincide
// they will be considered equal and the value corresponding
// to another rectangle with the same width will be
// overridden with another rectangle's area that possibly
// does not match with the key rectangle as we do not know the heights.

public class Problem_2 {
    public static void main(String[] args) {
        HashMap<Rectangle, Double> rectangles = new HashMap<>();
        rectangles.put(new Rectangle(2, 4), (double) 2 * 4);
        rectangles.put(new Rectangle(3, 2), (double) 3 * 2);
        rectangles.put(new Rectangle(1, 7), (double) 1 * 7);
        rectangles.put(new Rectangle(2, 4), (double) 2 * 4);

        for (Double area : rectangles.values()) {
            System.out.println(area);
        }

        // The hashcode() function in Object class is often
        // the integer representation of the address where
        // our object is stored. So, even if our rectangles
        // has the same heights and widths, they will be
        // considered different and, eventually, we will end up
        // having two keys in our map which are the same from our
        // perspective. Overriding the hashcode() function to use
        // the widths of the rectangles will not solve the issue
        // as even if the heights of the rectangles do not coincide
        // they will be considered equal and the value corresponding
        // to another rectangle with the same width will be
        // overridden with another rectangle's area that possibly
        // does not match with the key rectangle as we do not know the heights.

        executionTimeTest();
    }

    private static void executionTimeTest() {
        Rectangle1[] rectangles1 = generateRandomRectangles1();
        Rectangle2[] rectangles2 = generateRandomRectangles2();
        Rectangle3[] rectangles3 = generateRandomRectangles3();

        HashMap<Rectangle1, Double> map1 = new HashMap<>();
        HashMap<Rectangle2, Double> map2 = new HashMap<>();
        HashMap<Rectangle3, Double> map3 = new HashMap<>();

        long startTime1 = System.nanoTime();
        for (Rectangle1 rectangle1 : rectangles1) {
            map1.put(rectangle1, rectangle1.getHeight() * rectangle1.getWidth());
        }
        long elapsedTime1 = System.nanoTime() - startTime1;
        System.out.println("Total execution time with polynomial hash code on the width and the height using a = 101 in millis: "
                + elapsedTime1 / 1000000);
        System.out.println();

        long startTime2 = System.nanoTime();
        for (Rectangle2 rectangle2 : rectangles2) {
            map2.put(rectangle2, rectangle2.getHeight() * rectangle2.getWidth());
        }
        long elapsedTime2 = System.nanoTime() - startTime2;
        System.out.println("Total execution time with polynomial hash code on the width and the height using a = 11 in millis: "
                + elapsedTime2 / 1000000);
        System.out.println();

        long startTime3 = System.nanoTime();
        for (Rectangle3 rectangle3 : rectangles3) {
            map3.put(rectangle3, rectangle3.getHeight() * rectangle3.getWidth());
        }
        long elapsedTime3 = System.nanoTime() - startTime3;
        System.out.println("Total execution time the XOR of the width and the height in millis: "
                + elapsedTime3 / 1000000);
        System.out.println();
    }

    private static Rectangle1[] generateRandomRectangles1() {
        Rectangle1[] rectangles = new Rectangle1[1000000];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int height = random.nextInt(1, 101);
            int width = random.nextInt(1, 101);
            rectangles[i] = new Rectangle1(width, height);
        }
        return rectangles;
    }

    private static Rectangle2[] generateRandomRectangles2() {
        Rectangle2[] rectangles = new Rectangle2[1000000];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int height = random.nextInt(1, 101);
            int width = random.nextInt(1, 101);
            rectangles[i] = new Rectangle2(width, height);
        }
        return rectangles;
    }

    private static Rectangle3[] generateRandomRectangles3() {
        Rectangle3[] rectangles = new Rectangle3[1000000];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int height = random.nextInt(1, 101);
            int width = random.nextInt(1, 101);
            rectangles[i] = new Rectangle3(width, height);
        }
        return rectangles;
    }
}
