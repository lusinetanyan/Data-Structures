package com.company.hw7;

import com.company.hw7.UtilityClasses.LinkedBinaryTree;

import static com.company.hw7.Problem_3.convertToAVL;

public class Problem_2_Version_1 {

    public static <E> E getMedian(LinkedBinaryTree.Node<E> root) {
        int leftNodes = subtreeNodesCount(root.getLeft()) + 1;
        int rightNodes = subtreeNodesCount(root.getRight()) + 1;

        if (leftNodes == rightNodes || leftNodes - rightNodes == 1 || rightNodes - leftNodes == 1)
            return root.getElement();
        else if (leftNodes < rightNodes) {
            return getMedian(root.getRight(), rightNodes - leftNodes - 2, false);
        } else {
            return getMedian(root.getLeft(), leftNodes - rightNodes - 2, true);
        }
    }

    private static <E> E getMedian(LinkedBinaryTree.Node<E> root, int count, boolean left) {
        if (left) {
            LinkedBinaryTree.Node<E> inorderLast = inorderLast(root);
            while (count != 0) {
                inorderLast = inorderBefore(inorderLast, root);
                count--;
            }
            return inorderLast.getElement();
        } else {
            LinkedBinaryTree.Node<E> inorderFirst = inorderFirst(root);
            while (count != 0) {
                inorderFirst = inorderAfter(inorderFirst, root);
                count--;
            }
            return inorderFirst.getElement();
        }
    }

    private static <E> int subtreeNodesCount(LinkedBinaryTree.Node<E> root) {
        int left = 0;
        int right = 0;
        if (root.getLeft() != null) {
            left = 1 + subtreeNodesCount(root.getLeft());
        }
        if (root.getRight() != null) {
            right = 1 + subtreeNodesCount(root.getRight());
        }
        return right + left;
    }

    private static <E> LinkedBinaryTree.Node<E> inorderLast(LinkedBinaryTree.Node<E> root) {
        LinkedBinaryTree.Node<E> walk = root.getRight();
        while (walk != null && walk.getRight() != null) {
            walk = walk.getRight();
        }
        return walk;
    }

    private static <E> LinkedBinaryTree.Node<E> inorderFirst(LinkedBinaryTree.Node<E> root) {
        LinkedBinaryTree.Node<E> walk = root.getLeft();
        while (walk != null && walk.getLeft() != null) {
            walk = walk.getLeft();
        }
        return walk;
    }


    private static <E> LinkedBinaryTree.Node<E> inorderAfter(LinkedBinaryTree.Node<E> p, LinkedBinaryTree.Node<E> root) {
        if (p == inorderLast(root)) return null;
        if (isExternal(p)) {
            if (p.getParent().getLeft() == p) return p.getParent();
            else {
                LinkedBinaryTree.Node<E> walk = p.getParent();
                while (walk != null && walk.getParent().getRight() == walk) {
                    walk = walk.getParent();
                }
                return walk;
            }
        } else {
            if (isExternal(p.getRight())) return p.getRight();
            else {
                LinkedBinaryTree.Node<E> walk = p.getRight();
                while (walk != null && !(isExternal(walk.getLeft()))) {
                    walk = walk.getLeft();
                }
                return walk;
            }
        }
    }

    private static <E> boolean isExternal(LinkedBinaryTree.Node<E> p) {
        return p.getLeft() == null && p.getRight() == null;
    }

    private static <E> LinkedBinaryTree.Node<E> inorderBefore(LinkedBinaryTree.Node<E> p, LinkedBinaryTree.Node<E> root) {
        if (p == inorderFirst(root)) return null;
        if (isExternal(p)) {
            if (p.getParent().getRight() == p) return p.getParent();
            else {
                LinkedBinaryTree.Node<E> walk = p.getParent();
                while (walk.getParent() != null && walk.getParent().getLeft() == walk) walk = walk.getParent();
                return walk.getParent();
            }
        } else {
            if (p.getLeft() != null && isExternal(p.getLeft())) return p.getLeft();
            if (p.getLeft() != null && !isExternal(p.getLeft())) {
                LinkedBinaryTree.Node<E> walk = p.getLeft();
                while (walk.getRight() != null && !isExternal(walk.getRight())) walk = walk.getRight();
                return walk.getRight();
            }
        }
        return p.getParent();
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree1 =  convertToAVL(new int[]{4, 5, 8, 11, 12, 17, 18});
        LinkedBinaryTree<Integer> tree2 =  convertToAVL(new int[]{1, 2, 3, 4, 5, 7, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
        LinkedBinaryTree<Integer> tree3 =  convertToAVL(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9, 12});
        LinkedBinaryTree<Integer> tree4 =  convertToAVL(new int[]{1, 2, 3, 5, 7, 8, 9, 11, 12, 13});
        System.out.println(getMedian((LinkedBinaryTree.Node<Integer>)tree1.root()));
        System.out.println(getMedian((LinkedBinaryTree.Node<Integer>)tree2.root()));
        System.out.println(getMedian((LinkedBinaryTree.Node<Integer>)tree3.root()));
        System.out.println(getMedian((LinkedBinaryTree.Node<Integer>)tree4.root()));

        LinkedBinaryTree<Integer> tree5 = new LinkedBinaryTree<>();
        tree5.addRoot(8);
        tree5.addRight(tree5.root(), 11);
        tree5.addLeft(tree5.root(), 5);
        tree5.addLeft(tree5.left(tree5.root()), 3);
        tree5.addRight(tree5.left(tree5.root()), 7);
        tree5.addLeft(tree5.right(tree5.root()), 10);
        tree5.addRight(tree5.right(tree5.root()), 12);
        tree5.addLeft(tree5.left(tree5.left(tree5.root())), 2);
        tree5.addRight(tree5.left(tree5.left(tree5.root())), 4);
        tree5.addLeft(tree5.right(tree5.left(tree5.root())), 6);
        tree5.addLeft(tree5.left(tree5.right(tree5.root())), 9);
        tree5.addLeft(tree5.left(tree5.left(tree5.left(tree5.root()))), 1);
        System.out.println(getMedian((LinkedBinaryTree.Node<Integer>)tree5.root()));
    }
}
