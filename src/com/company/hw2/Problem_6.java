package com.company.hw2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] students = new Student[n];
        int BCS = 0;
        int BDS = 0;
        int BES = 0;
        for(int i = 0; i < n; i++) {
            String fullName = sc.next() + " " + sc.next();
            String program = sc.next();
            switch (program) {
                case "BCS" -> BCS++;
                case "BDS" -> BDS++;
                case "BES" -> BES++;
            }
            Student student = new Student(fullName, program);
            students[i] = student;
        }
        printProgramStudents(students, BCS, BDS, BES);
    }

    public static void printProgramStudents(Student[] students, int BCS, int BDS, int BES) {
        boolean program = false;
        for(int i = 0, k = 0; i < students.length && k < 3; i++) {
            if(k == 0) {
                if(!program) {
                    System.out.println("BCS");
                    program = true;
                }
                if(students[i].getProgram().equals("BCS")) {
                    System.out.println(students[i].getFullName());
                }
            }
            if(k == 1) {
                if(!program) {
                    System.out.println("BDS");
                    program = true;
                }
                if(students[i].getProgram().equals("BDS")) {
                    System.out.println(students[i].getFullName());
                }
            }
            if(k == 2) {
                if(!program) {
                    System.out.println("BES");
                    program = true;
                }
                if(students[i].getProgram().equals("BES")) {
                    System.out.println(students[i].getFullName());
                }
            }
            if(i == students.length - 1) {
                i = 0;
                k++;
                program = false;
            }
        }
    }
}
