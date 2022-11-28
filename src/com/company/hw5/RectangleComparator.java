package com.company.hw5;

import java.util.Comparator;

public class RectangleComparator implements Comparator<Rectangle> {
    @Override
    public int compare(Rectangle rectangle1, Rectangle rectangle2) {
        return (int) (rectangle1.getWidth() * rectangle1.getHeight() - rectangle2.getWidth() * rectangle2.getHeight());
    }
}
