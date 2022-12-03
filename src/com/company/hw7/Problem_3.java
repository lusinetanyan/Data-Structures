package com.company.hw7;

import com.company.hw7.UtilityInterfaces.Position;
import com.company.hw7.UtilityClasses.LinkedBinaryTree;

public class Problem_3 {

    public static LinkedBinaryTree<Integer> convertToAVL(int[] keys) {
        LinkedBinaryTree<Integer> AVLTree = new LinkedBinaryTree<>();

        if (keys.length == 0) {
            AVLTree.addRoot(null);
        } else {
            convertToAVL(AVLTree, keys, null, 0, keys.length - 1, false);
        }

        return AVLTree;
    }

    private static void convertToAVL(LinkedBinaryTree<Integer> AVLTree, int[] keys, Position<Integer> parent, int low, int high, boolean toLeft) {
        if (low > high) return;

        int mid = (low + high) / 2;
        int key = keys[mid];
        Position<Integer> current;

        if (parent != null) {
            if (toLeft) {
                AVLTree.addLeft(parent, key);
                current = AVLTree.left(parent);
            } else {
                AVLTree.addRight(parent, key);
                current = AVLTree.right(parent);
            }
        } else {
            AVLTree.addRoot(key);
            current = AVLTree.root();
        }

        convertToAVL(AVLTree, keys, current, low, mid - 1, true);
        convertToAVL(AVLTree, keys, current, mid + 1, high, true);
    }


    public static void main(String[] args) {

    }
}
