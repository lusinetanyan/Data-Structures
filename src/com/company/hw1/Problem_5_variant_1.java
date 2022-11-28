package com.company.hw1;

public class Problem_5_variant_1 {

    public static void main(String[] args) {
        drawNestedPatterns(3);
    }

    public static void drawNestedPatterns(int depth) {
        drawPattern(depth, 1);
    }

    private static void drawPattern(int depth, int level) {
        if (level <= depth) {
            patternUp(depth, level);
            drawPattern(depth, level + 1);
            patternDown(depth, level);
        }
    }

    private static void patternUp(int depth, int level) {
        int a = depth - level;
        int b = depth * 3 + depth - 1 - 4 * level;
        int c1 = level - 1;
        int c2 = level - 1;
        int d2 = level;
        int d3 = level;

        if(level != 1) {
            while (c1 != 0) {
                System.out.print("| ");
                c1--;
            }
        }

        while (a != 0) {
            System.out.print("/^\\-");
            a--;
        }
        System.out.print("/^\\");

        if(level != 1) {
            while (c2 != 0) {
                System.out.print(" |");
                c2--;
            }
        }

        System.out.println();

        if(level != depth) {
            while (d2 != 0) {
                System.out.print("| ");
                d2--;
            }
            while (b != 0) {
                System.out.print(" ");
                b--;
            }
            while (d3 != 0) {
                System.out.print(" |");
                d3--;
            }
            System.out.println();
        }
    }

    private static void patternDown(int depth, int level) {
        int a = depth - level;
        int b = depth * 3 + depth - 4 * level + 3;
        int c1 = level - 1;
        int c2 = level - 1;
        int d2 = level - 1;
        int d3 = level - 1;

        if(level != 1) {
            while (c1 != 0) {
                System.out.print("| ");
                c1--;
            }
        }

        while (a != 0) {
            System.out.print("\\-/^");
            a--;
        }
        System.out.print("\\-/");

        if(level != 1) {
            while (c2 != 0) {
                System.out.print(" |");
                c2--;
            }
        }

        System.out.println();

        if(level != 1) {
            while (d2 != 0) {
                System.out.print("| ");
                d2--;
            }
            while (b != 0) {
                System.out.print(" ");
                b--;
            }
            while (d3 != 0) {
                System.out.print(" |");
                d3--;
            }
            System.out.println();
        }
    }
}
