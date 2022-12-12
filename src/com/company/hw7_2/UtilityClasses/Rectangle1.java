package com.company.hw7_2.UtilityClasses;

public class Rectangle1 extends Rectangle {

    public Rectangle1(double width, double height) {
        super(width, height);
    }

    @Override
    public int hashCode() {
        return (int) (getWidth() * 101 + getHeight());
    }

}
