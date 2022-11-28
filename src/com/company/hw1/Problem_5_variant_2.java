package com.company.hw1;

public class Problem_5_variant_2 {
    public static void main(String[] args) {
        drawNestedPatterns(7);
    }

    public static void drawNestedPatterns(int depth) {
        if(depth <= 0) return;
        String[][] matrix = new String[2 + (depth - 1) * 4][depth * 3 + (depth - 1)];
        String[][] pattern = nestedPatterns(depth, depth, matrix);
        for (String[] strings : pattern) {
            for (int j = 0; j < pattern[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    private static String[][] nestedPatterns(int depth, int level, String[][] matrix) {
        for (int i = (depth - level) * 2; i <= 1 + (depth - 1) * 4 - (depth - level) * 2; i++) {
            for (int j = (depth - level) * 2; j <= depth - 2 + (depth * 3) - (depth - level) * 2; j++) {
                if (i == (depth - level) * 2) {
                    matrix[i][j] = "/";
                    matrix[i][++j] = "^";
                    matrix[i][++j] = "\\";

                    int n = level - 1;

                    while (n != 0) {
                        matrix[i][++j] = "-";
                        matrix[i][++j] = "/";
                        matrix[i][++j] = "^";
                        matrix[i][++j] = "\\";
                        n--;
                    }
                }

                else if ((level != 1) && (i != (depth - level) * 2 && i != 1 + (depth - 1) * 4 - (depth - level) * 2
                        && ((j == (depth - level) * 2 || (j == depth - 2 + (depth * 3) - (depth - level) * 2))))) {
                    matrix[i][j] = "|";
                }

                else if (i == 1 + (depth - 1) * 4 - (depth - level) * 2) {
                    matrix[i][j] = "\\";
                    matrix[i][++j] = "-";
                    matrix[i][++j] = "/";

                    int n = level - 1;

                    while (n != 0) {
                        matrix[i][++j] = "^";
                        matrix[i][++j] = "\\";
                        matrix[i][++j] = "-";
                        matrix[i][++j] = "/";
                        n--;
                    }
                }
                else matrix[i][j] = " ";
            }
        }

        if (level == 1) return matrix;
        return nestedPatterns(depth, level - 1, matrix);
    }
}
