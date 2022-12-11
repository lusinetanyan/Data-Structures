package com.company.hw7_2;

import com.company.hw7_2.UtilityClasses.Rectangle;

import java.util.HashMap;

public class Problem_2 {
    public static void main(String[] args) {
        Rectangle[] rectangles = new Rectangle[]{new Rectangle(2, 3), new Rectangle(4, 1),
                new Rectangle(3, 4), new Rectangle(2, 2)};
        HashMap<Rectangle, Double> rectanglesAreas = new HashMap<>();
        for (Rectangle rectangle: rectangles) {
            rectanglesAreas.put(rectangle, rectangle.getHeight() * rectangle.getWidth());
        }

        for (Double areas: rectanglesAreas.values()) {
            System.out.println(areas);
        }
    }
}
