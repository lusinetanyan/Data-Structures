package com.company.hw2;

import java.util.Arrays;
import java.util.Random;

public class Problem_3 {
    public static void main(String[] args) {
        int[] select = {5, 3, 6, 1, 7, 9, 3, 2};
        selectionSort(select);
        System.out.println("Selection sort");
        for (int n : select) {
            System.out.print(n + " ");
        }

        int[] quick = {5, 8, 3, 1, 4, 9, 7, 2, 6};
        /* Best case occurs, when the sequences L and G
         * have approximately the same number of elements
         * after each pivot choice (n/2 elements in both or
         * n/2 and n/2 - 1 elements). In that case, the quick
         * sort tree is evenly distributed.
         * In this example, the first pivot index should be 0.
         * L = {3, 1, 4, 2}, E = {5}, G = {8, 9, 7, 6}. Then for
         * L the pivot index should be either 0 or 3.
         * L = {1, 2}, E = {3}, G = {4} or L = {1}, E = {2}, G = {3, 4}.
         * For G = {8, 9, 7, 6}, pivot index should be either 0 or 2.
         * L = {7, 6}, E = {8}, G = {9} or L = {6}, E = {7}, G = {8, 9}.
         * Then for remaining 2-element sequences it does not matter
         * which one is pivot. Overall, this would be the best case scenario.
         * O(nlogn())*/
        quickSort(select);
        System.out.println("Quick sort");
        for (int n : quick) {
            System.out.print(n + " ");
        }

        int[] insertBest = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        /* This is the best case as the sequence is already sorted.
         * That is why, the condition data[j + 1] < cur is always false
         * and the program never enters the while loop. So the time
         * complexity is O(n) in best case.*/
        insertionSort(insertBest);
        System.out.println("Insertion sort best-case");
        for (int n : insertBest) {
            System.out.print(n + " ");
        }

        int[] insertWorst = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        /* This is the worst case as the sequence is in decreasing order.
         * That is why, the condition data[j + 1] < cur is always true
         * and the program enters the while loop for every element. In
         * general, it should shift all the numbers after the index to
         * the left in order to insert the element in that index in the
         * end of the sequence. So the time complexity is O(n^2) in worst case.*/
        insertionSort(insertWorst);
        System.out.println("Insertion sort worst-case");
        for (int n : insertWorst) {
            System.out.print(n + " ");
        }
    }

    public static void selectionSort(int[] data) {
        selectionSort(data, 0); //O(n^2)
    }

    private static void selectionSort(int[] data, int index) {
        int n = data.length;
        if (index == n - 1) return;
        int min = index;
        for (int j = index; j < n; j++) {
            if (data[j] < data[min]) {
                min = j;
            }
        }
        int temp = data[index];
        data[index] = data[min];
        data[min] = temp;
        //linear time complexity
        selectionSort(data, ++index);// recursive call n times.
    }//Overall n * O(n) = O(n^2)

    public static void quickSort(int[] S) {
        int n = S.length;
        if (n < 2) return;
        int index = new Random().nextInt(n);
        int pivot = S[index];
        int m = 0, k = n;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == index) continue;
            if (S[i] < pivot)
                temp[m++] = S[i];
            else if (S[i] > pivot)
                temp[--k] = S[i];
        }
        int[] L = Arrays.copyOfRange(temp, 0, m);
        int[] E = new int[k - m];
        Arrays.fill(E, pivot);
        int[] G = Arrays.copyOfRange(temp, k, n);
        quickSort(L);
        quickSort(G);
        System.arraycopy(L, 0, S, 0, m);
        System.arraycopy(E, 0, S, m, k - m);
        System.arraycopy(G, 0, S, k, n - k);
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int k = n - 2; k >= 0; k--) {
            int cur = data[k];
            int j = k;
            while (j < n - 1 && data[j + 1] < cur) {
                data[j] = data[j + 1];
                j++;
            }
            data[j] = cur;
        }
    }
}
