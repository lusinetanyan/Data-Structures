package com.company.hw5;

import com.company.hw5.utilityClasses.LinkedBinaryTree;
import com.company.hw5.utilityInterfaces.Position;

public class Problem_4 {
    public static <E> LinkedBinaryTree<E> constructBinaryTree(E[] arr) throws IllegalArgumentException{
        isValid(arr);
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        constructBinaryTree(tree, tree.addRoot(arr[0]), arr, 0);
        return tree;
    }

    private static <E> void constructBinaryTree(LinkedBinaryTree<E> tree, Position<E> root, E[] arr, int index) {
        if (index * 2 + 1 < arr.length) {
            constructBinaryTree(tree, tree.addLeft(root, arr[2 * index + 1]), arr, 2 * index + 1);
        }
        if (index * 2 + 2 < arr.length) {
            constructBinaryTree(tree, tree.addRight(root, arr[2 * index + 2]), arr, 2 * index + 2);
        }
    } // time complexity - O(n), recursive call vor every position in the tree,
    // constant amount of work in each call

    public static <E> void isValid(E[] arr) throws IllegalArgumentException{
        for(int i = arr.length - 1; i > 0; i--) {
            if(arr[(i - 1) / 2] == null) throw new IllegalArgumentException("Invalid array representation of a binary tree");
        }
    } //time complexity - O(arr.length) = O(n + k), worst case - O(2^n), arr.length is always greater or equal to n,
      //worst case it can be equal n + 2^n - 1 - n, so m = 2^n - 1 - n = O(2^n);
      //          5
      //    n          6
      // n     n    n     7
      //n n   n n  n n   n  8

    //the time complexity overall would change if we did not the validity check,
    //because now the worst time complexity is O(n + 2^n) = O(2^n), without check it will be just O(n)
}