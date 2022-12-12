package com.company.hw7_2.UtilityClasses;

public class Rectangle2 extends Rectangle {

    public Rectangle2(double width, double height) {
        super(width, height);
    }

    @Override
    public int hashCode() {
        return (int) (getWidth() * 11 + getHeight());
    }
}
