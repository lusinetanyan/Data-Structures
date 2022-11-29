package com.company.hw7;

import com.company.hw7.UtilityClasses.AVLTree;
import com.company.hw7.UtilityClasses.LinkedBinaryTree;
import com.company.hw7.UtilityInterfaces.Entry;

public class Problem_2 {

    public static <K, V> K getMedian(LinkedBinaryTree.Node<Entry<K, V>> root) {
        int leftNodes = subtreeNodesCount(root.getLeft());
        int rightNodes = subtreeNodesCount(root.getRight());
        int allNodes = 1 + leftNodes + rightNodes;

        if(leftNodes == rightNodes || leftNodes - rightNodes == 1 || rightNodes - leftNodes == 1) return root.getElement().getKey();
        else if(leftNodes < rightNodes) return getMedian(root.getLeft());
        else return getMedian(root.getRight());
    }

    private static <K, V> int subtreeNodesCount(LinkedBinaryTree.Node<Entry<K, V>> root) {
        if(root.getLeft() != null) {
            return 1 + subtreeNodesCount(root.getLeft());
        }
        if(root.getRight() != null) {
            return 1 + subtreeNodesCount(root.getRight());
        } else return 0;
    }

    private static <K, V> K inorderLast(LinkedBinaryTree.Node<Entry<K, V>> root) {
        LinkedBinaryTree.Node<Entry<K, V>> walk = root.getRight();
        while (walk != null && walk.getRight() != null) {
            walk = walk.getRight();
        }
        return walk.getElement().getKey();
    }
}
