package com.company.hw6;

import com.company.hw5.utilityClasses.LinkedQueue;
import com.company.hw5.utilityInterfaces.Queue;
import com.company.hw6.UtilityClasses.AbstractPriorityQueue;
import com.company.hw6.UtilityInterfaces.Entry;

import java.util.Comparator;

public class LinkedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private static class Node<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        private Node<K, V> parent;
        private Node<K, V> right;
        private Node<K, V> left;

        public Node(K key, V value, Node<K, V> parent, Node<K, V> right, Node<K, V> left) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.right = right;
            this.left = left;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public Node<K, V> getParent() {
            return parent;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }

    private Node<K, V> root;
    private int size;

    public LinkedHeapPriorityQueue() {
        super();
    }

    public LinkedHeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public int size() {
        return this.size;
    } //Time complexity - O(1)

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        if (isEmpty()) {
            root = new Node<>(key, value, null, null, null);
            size++;
            return root;
        }
        Node<K, V> parent = findParent(root);
        Node<K, V> entry = new Node<>(key, value, parent, null, null);
        if (!this.hasLeft(parent)) {
            parent.setLeft(entry);
        } else if (!this.hasRight(parent)) {
            parent.setRight(entry);
        }
        this.upHeap(entry);
        size++;
        return entry;
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> min() {
        if (isEmpty()) return null;
        return root;
    } //Time complexity - O(1)

    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) return null;
        Node<K, V> last = findLast(this.root); //Time complexity - O(logn)
        Node<K, V> returnEntry = root;
        size--;
        if(last == root) {
            root = null;
            return returnEntry;
        }
        Node<K, V> newRoot = new Node<>(last.getKey(), last.getValue(), null, root.getRight(), root.getLeft());
        if (this.hasRight(root)) root.getRight().setParent(newRoot);
        if (this.hasLeft(root)) root.getLeft().setParent(newRoot);
        root = newRoot;
        if (last.getParent().getLeft() == last) last.getParent().setLeft(null);
        else last.getParent().setRight(null);
        last.setParent(null);
        last.setKey(null);
        last.setValue(null);
        downHeap(root);
        return returnEntry;
    } //Time complexity - O(logn)

    private boolean hasRight(Node<K, V> p) {
        return (p.getRight() != null);
    } //Time complexity - O(1)

    private boolean hasLeft(Node<K, V> p) {
        return (p.getLeft() != null);
    } //Time complexity - O(1)

    private int height(Node<K, V> p, boolean right) {
        int height = 0;
        Node<K, V> walk = p;
        if(!right) {
            while (walk != null) {
                walk = walk.getLeft();
                height++;
            }
        } else {
            while (walk != null) {
                walk = walk.getRight();
                height++;
            }
        }
        return height;
    } //Time complexity - O(logn)

    private Node<K, V> findLast(Node<K, V> p) {
        int leftHeight = height(p, false) - 1;
        int rightHeight = height(p, true) - 1 ;

        if(leftHeight == 0) return p;
        if(rightHeight == 0) return p.getLeft();
        if(rightHeight == 1) return p.getRight();
        if(leftHeight == 1) return p.getLeft();
        if(rightHeight < leftHeight) {
            int leftOfRight = 1 + height(p.getRight(), false);
            if(leftOfRight == leftHeight) return findLast(p.getRight());
            else return findLast(p.getLeft());
        }
       else return findLast(p.getRight());
    } //Time complexity - O(logn)

    private Node<K, V> findParent(Node<K, V> p) {
        if (!hasRight(p)) return p;
        else {
            Node<K, V> left = findParent(p.getLeft());
            Node<K, V> right = findParent(p.getRight());
            if (left.getParent().getParent() == right.getParent()) return right;
            else return left;
        }
    } //Time complexity - worst case O(logn)

    private void upHeap(Node<K, V> p) {
        Node<K, V> parent = p.getParent();
        if (parent != null && compare(p, parent) < 0) {
            swap(parent, p);
            upHeap(p);
        }
        if (parent == null) root = p;
    } //Time complexity - worst case O(logn)

    private void downHeap(Node<K, V> p) {
        if (!hasLeft(p) && !hasRight(p)) return;
        if (!hasRight(p)) {
            if (hasLeft(p)) {
                if(compare(p.getLeft(), p) < 0) {
                    swap(p, p.getLeft());
                    if (p == root) root = p.getLeft();
                    downHeap(p);
                }
            }
        } else {
            Node<K, V> leftChild = p.getLeft();
            Node<K, V> rightChild = p.getRight();
            if (compare(leftChild, rightChild) < 0) {
                if(compare(leftChild, p) < 0) {
                    swap(p, leftChild);
                    if (p == root) root = leftChild;
                }
            } else {
                if(compare(rightChild, p) < 0) {
                    swap(p, rightChild);
                    if (p == root) root = rightChild;
                }
            }
            downHeap(p);
        }
    } //Time complexity - worst case O(logn)

    private void swap(Node<K, V> parent, Node<K, V> child) {
        Node<K, V> leftChild = child.getLeft();
        Node<K, V> rightChild = child.getRight();
        Node<K, V> parentOfParent = parent.getParent();
        if (parent.getLeft() == child) {
            child.setLeft(parent);
            child.setRight(parent.getRight());
            if (parent.getRight() != null)
                parent.getRight().setParent(child);
        } else {
            child.setRight(parent);
            child.setLeft(parent.getLeft());
            if (parent.getLeft() != null)
                parent.getLeft().setParent(child);
        }
        child.setParent(parentOfParent);
        if(parentOfParent == null) root = child;
        else {
            if (parentOfParent.getLeft() == parent) parentOfParent.setLeft(child);
            else parentOfParent.setRight(child);
        }
        parent.setParent(child);
        parent.setLeft(leftChild);
        if (leftChild != null) leftChild.setParent(parent);
        parent.setRight(rightChild);
        if (rightChild != null) rightChild.setParent(parent);
    } //Time complexity - O(1)
}
