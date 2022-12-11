package com.company.hw7_2.UtilityClasses;

import java.util.Objects;

public class Rectangle implements Comparable<Rectangle> {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Rectangle rectangle) {
        if (this.width - rectangle.width == 0) return (int) (this.height - rectangle.height);
        else return (int) (this.width - rectangle.width);
    }

//    @Override
//    public int hashCode() {
//        return (int) width;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
