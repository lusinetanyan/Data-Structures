package com.company.hw6;

import com.company.hw6.UtilityClasses.LinkedBinaryTree;

public class Problem_5 {
    private static <E> int heightDifference(LinkedBinaryTree.Node<E> root) {
        if (root.getRight() == null && root.getLeft() == null) return 0;
        if (root.getLeft() != null && root.getRight() == null) {
            return 1 + heightDifference(root.getLeft());
        } else if (root.getRight() != null && root.getLeft() == null) {
            return 1 + heightDifference(root.getRight());
        } else {
            int leftHeight = heightDifference(root.getLeft());
            int rightHeight = heightDifference(root.getRight());
            return Math.max(leftHeight, rightHeight);
        }
    } // Time complexity - O(n)

    public static <E> boolean isBalanced(LinkedBinaryTree.Node<E> root) {
        int difference = heightDifference(root);
        return difference <= 1;
    } // Time complexity - O(n)
}
