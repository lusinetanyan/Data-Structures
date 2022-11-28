package com.company.hw2;

public class Student {
    private String fullName;
    private String program;

    public Student(String fullName, String program) {
        this.fullName = fullName;
        this.program = program;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return fullName + " " + program;
    }
}
