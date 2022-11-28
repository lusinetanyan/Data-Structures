package com.company.hw6;

import com.company.hw6.UtilityClasses.LinkedBinaryTree;
import com.company.hw6.UtilityClasses.Rectangle;
import com.company.hw6.UtilityInterfaces.Entry;

import static com.company.hw6.Problem_5.isBalanced;
import static com.company.hw6.RectanglesHeapSort.inPlaceHeapSort;

public class Test_Programs {
    public static void main(String[] args) {

        //-----------------LinkedHeapPriorityQueue-----------------
        LinkedHeapPriorityQueue<Integer, Character> pq = new LinkedHeapPriorityQueue<>();
        pq.insert(5, 'A');
        pq.insert(9, 'C');
        pq.insert(3, 'B');
        System.out.println(pq.min().getValue());
        System.out.println(pq.removeMin().getValue());
        pq.insert(7, 'D');
        System.out.println(pq.removeMin().getValue());
        System.out.println(pq.removeMin().getValue());
        System.out.println(pq.removeMin().getValue());
        System.out.println(pq.removeMin());
        System.out.println(pq.isEmpty());

        //-----------------inPlaceHeapSort-----------------
        Rectangle[] rectangles = new Rectangle[]{new Rectangle(2, 3), new Rectangle(1, 2), new Rectangle(2, 4.5), new Rectangle(2, 2), new Rectangle(5, 1), new Rectangle(3.5, 2)};
        inPlaceHeapSort(rectangles);

        //-----------------BSTMap-----------------
        BSTMap<Integer, Integer> map = new BSTMap<>();
        map.put(1, null);
        map.put(2, null);
        map.put(5, null);
        map.put(1, 2);
        map.put(16, null);
        map.put(9, null);
        System.out.println(map.remove(1));
        System.out.println(map.ceilingEntry(8).getKey());
        System.out.println(map.floorEntry(7).getKey());
        System.out.println(map.higherEntry(9).getKey());
        System.out.println(map.lowerEntry(9).getKey());
        for (Entry<Integer, Integer> entry : map.subMap(2, 16)) {
            System.out.println(entry.getKey());
        }
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

        //-----------------isBalanced-----------------
        LinkedBinaryTree<Integer> balancedTree = new LinkedBinaryTree<>();
        balancedTree.addRoot(1);
        balancedTree.addLeft(balancedTree.root(), 2);
        balancedTree.addRight(balancedTree.root(), 3);
        balancedTree.addLeft(balancedTree.left(balancedTree.root()), 4);
        balancedTree.addRight(balancedTree.left(balancedTree.root()), 5);
        balancedTree.addRight(balancedTree.right(balancedTree.root()), 6);
        balancedTree.addLeft(balancedTree.right(balancedTree.left(balancedTree.root())), 7);

        System.out.println(isBalanced(balancedTree.validate(balancedTree.root())));

        LinkedBinaryTree<Integer> unbalancedTree = new LinkedBinaryTree<>();
        unbalancedTree.addRoot(1);
        unbalancedTree.addLeft(unbalancedTree.root(), 2);
        unbalancedTree.addRight(unbalancedTree.root(), 3);
        unbalancedTree.addLeft(unbalancedTree.left(unbalancedTree.root()), 4);
        unbalancedTree.addRight(unbalancedTree.left(unbalancedTree.root()), 5);
        unbalancedTree.addRight(unbalancedTree.right(unbalancedTree.root()), 6);
        unbalancedTree.addLeft(unbalancedTree.right(unbalancedTree.left(unbalancedTree.root())), 7);
        unbalancedTree.addRight(unbalancedTree.left(unbalancedTree.right(unbalancedTree.left(unbalancedTree.root()))), 8);

        System.out.println(isBalanced(balancedTree.validate(unbalancedTree.root())));
    }
}
