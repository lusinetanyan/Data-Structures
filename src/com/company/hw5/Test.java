package com.company.hw5;

import com.company.hw5.utilityClasses.LinkedBinaryTree;
import com.company.hw5.utilityInterfaces.Position;

import static com.company.hw5.Problem_1.breadthFirstAverage;
import static com.company.hw5.Problem_1.preorderAverage;
import static com.company.hw5.Problem_2.evaluate;
import static com.company.hw5.Problem_4.constructBinaryTree;

public class Test {
    public static void main(String[] args) {
//-----------------------Problem 1-------------------------------------
        LinkedBinaryTree<Double> tree1 = new LinkedBinaryTree<>();
        tree1.addRoot(5d);
        tree1.addLeft(tree1.root(), 4d);
        tree1.addRight(tree1.root(), 6d);
        tree1.addRight(tree1.left(tree1.root()), 3d);
        tree1.addLeft(tree1.left(tree1.root()), 7d);
        tree1.addRight(tree1.right(tree1.root()), 8d);
        tree1.addLeft(tree1.right(tree1.root()), 9d);
        tree1.addRight(tree1.left(tree1.left(tree1.root())), 10d);
        tree1.addLeft(tree1.left(tree1.left(tree1.root())), 0d);
        tree1.addRight(tree1.left(tree1.right(tree1.root())), 1d);
        tree1.addLeft(tree1.left(tree1.right(tree1.root())), 2d);
        System.out.println(preorderAverage(tree1));
        System.out.println(breadthFirstAverage(tree1));

//-----------------------Problem 2-------------------------------------

        LinkedBinaryTree<Character> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot('+');
        tree2.addLeft(tree2.root(), '+');
        tree2.addRight(tree2.root(), '-');
        tree2.addRight(tree2.left(tree2.root()), '3');
        tree2.addLeft(tree2.left(tree2.root()), '*');
        tree2.addRight(tree2.right(tree2.root()), '8');
        tree2.addLeft(tree2.right(tree2.root()), '/');
        tree2.addRight(tree2.left(tree2.left(tree2.root())), '7');
        tree2.addLeft(tree2.left(tree2.left(tree2.root())), '0');
        tree2.addRight(tree2.left(tree2.right(tree2.root())), '1');
        tree2.addLeft(tree2.left(tree2.right(tree2.root())), '9');

        System.out.println(evaluate(tree2, tree2.root()));

//-----------------------Problem 3-------------------------------------

        StackPriorityQueue<Integer, Character> list = new StackPriorityQueue<>();
        list.insert(2, 'a');
        list.insert(4, 'b');
        list.insert(1, 'c');
        list.insert(3, 'd');
        System.out.println(list.min().getKey());
        list.removeMin();
        System.out.println(list.min().getKey());

//-----------------------Problem 4-------------------------------------

        Character[] arr = new Character[]{'/', '*', '+', '+', '4', '-', '2', '3', '1', null, null, '9', '5', null, null};
        LinkedBinaryTree<Character> tree3 = constructBinaryTree(arr);
        for (Position<Character> p : tree3.inorder()) {
            System.out.println(p.getElement());
        }
    }
}
