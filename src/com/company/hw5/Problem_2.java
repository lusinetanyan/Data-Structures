package com.company.hw5;

import com.company.hw5.utilityClasses.LinkedBinaryTree;
import com.company.hw5.utilityInterfaces.Position;

public class Problem_2 {
    public static int evaluate(LinkedBinaryTree<Character> tree, Position<Character> root) {
        if(tree.isExternal(root)) return root.getElement() - 48;
        int left = evaluate(tree, tree.left(root));
        int right = evaluate(tree, tree.right(root));
        return switch (root.getElement()) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '/' -> left / right;
            case '*' -> left * right;
            default -> 0;
        };
    } // Time complexity - O(n), because there is a recursive call for every n positions in the tree
    // and in each call constant amount of work is done.
}
