package com.company.hw2;

public class Problem_4 {
    public static void radixSort(int[] arr) {//Overall time consumption - O(n) + O(9) * O(n) = O(n), memory consumption - O(1)
        int max = max(arr);//time consumption - O(n)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            bucketSort(arr, exp);//time consumption - O(n)
        }//time consumption max - O(9), memory consumption - O(1)
    }

    private static void bucketSort(int[] arr, int exp) { // Overall time consumption - 6 * O(n) + O(1) = O(n), memory consumption - O(n)
        int n = arr.length;
        int[] count = new int[8];//memory consumption - O(8)
        int[][] result = new int[8][];//memory consumption - O(n)
        int[] sorted = new int[n];//memory consumption - O(n)

        for (int number : arr) {
            if((number / exp) % 10 == 8 || (number / exp) % 10 == 9) continue;
            count[(number / exp) % 10]++;
        }//time consumption - O(n)

        for(int i = 0; i < 8; i++) {
            result[i] = new int[count[i]];
        }//time consumption - O(1), memory consumption - O(n)

        for (int number : arr) {
            int radix = (number / exp) % 10;
            if (radix == 8 || radix == 9) continue;
            result[radix][count[radix] - 1] = number;
            count[radix]--;
        }//time consumption - O(n)

        int index = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = result[i].length - 1; j >= 0; j--) {
                sorted[index] = result[i][j];
                index++;
            }
        }//Although there are 2 loops, they iterate over n elements only. So, time consumption - O(n)

        for (int number : arr) {
            if((number / exp) % 10 == 8) {
                sorted[index] = number;
                index++;
            }
        }//time consumption - O(n)

        for (int number : arr) {
            if((number / exp) % 10 == 9) {
                sorted[index] = number;
                index++;
            }
        }//time consumption - O(n)

        for(int i = 0; i < sorted.length; i++) {
            arr[i] = sorted[i];
        }//time consumption - O(n)
    }

    private static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }//time consumption - O(n), memory consumption - O(1), memory consumption - O(1)

    public static void main(String[] args) {//Overall time consumption - O(n), memory consumption - O(n)
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66, 48, 59, 58, 209};
        radixSort(arr);//time consumption - O(n), memory consumption - O(n)
        for (int number : arr) System.out.print(number + " ");//time consumption - O(n)
    }
}

