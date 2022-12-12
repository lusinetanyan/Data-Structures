package com.company.hw7_2.UtilityClasses;

public class Rectangle3 extends Rectangle {

    public Rectangle3(double width, double height) {
        super(width, height);
    }

    @Override
    public int hashCode() {
        return (int) (getWidth()) ^ (int) (getHeight());
    }
}
