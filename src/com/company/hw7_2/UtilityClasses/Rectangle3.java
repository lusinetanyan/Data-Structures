package com.company.hw7_2.UtilityClasses;

public class Rectangle3 implements Comparable<Rectangle3> {
    private double width;
    private double height;

    public Rectangle3(double width, double height) {
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
    public int compareTo(Rectangle3 rectangle) {
        if (this.width - rectangle.width == 0) return (int) (this.height - rectangle.height);
        else return (int) (this.width - rectangle.width);
    }

    @Override
    public int hashCode() {
        return (int) (width) ^ (int) (height);
    }
}