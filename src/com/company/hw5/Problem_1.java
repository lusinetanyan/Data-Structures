package com.company.hw5;

import com.company.hw5.utilityClasses.LinkedBinaryTree;
import com.company.hw5.utilityClasses.LinkedQueue;
import com.company.hw5.utilityInterfaces.Position;
import com.company.hw5.utilityInterfaces.Queue;
import com.company.hw5.utilityInterfaces.Tree;

public class Problem_1 {

    public static double preorderAverage(Tree<Double> tree) {
        double sum = preorderAverage(tree, tree.root(), 0);
        return sum / tree.size();
    }

    public static double breadthFirstAverage(Tree<Double> tree) {
        double sum = 0;
        if (!tree.isEmpty()) {
            Queue<Position<Double>> fringe = new LinkedQueue<>();
            fringe.enqueue(tree.root());
            while (!fringe.isEmpty()) {
                Position<Double> p = fringe.dequeue();
                sum += p.getElement();
                for (Position<Double> c : tree.children(p))
                    fringe.enqueue(c);
            }
        }
        return sum / tree.size();
    } //time complexity - O(n), overall the while loop and for loop together do linear amount
    // of work, because all the positions of the tree are enqueued once and dequeued once,
    // also all other operations in the loops do constant amount of work.

    private static double preorderAverage(Tree<Double> tree, Position<Double> root, double sum) {
        sum = root.getElement();
        for(Position<Double> child: tree.children(root)) {
            sum += preorderAverage(tree, child, sum);
        }
        return sum;
    } //time complexity - O(n) as there are n recursive calls linear to the number of
    // nodes in the tree, and in every call there is done only constant amoount of work.


}
