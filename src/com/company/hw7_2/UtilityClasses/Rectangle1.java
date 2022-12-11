package com.company.hw7_2.UtilityClasses;

public class Rectangle1 implements Comparable<Rectangle1> {
    private double width;
    private double height;

    public Rectangle1(double width, double height) {
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
    public int compareTo(Rectangle1 rectangle) {
        if (this.width - rectangle.width == 0) return (int) (this.height - rectangle.height);
        else return (int) (this.width - rectangle.width);
    }

    @Override
    public int hashCode() {
        return (int) (width * 101 + height);
    }


}
