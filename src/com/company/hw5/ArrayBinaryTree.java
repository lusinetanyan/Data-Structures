package com.company.hw5;

import com.company.hw5.utilityClasses.AbstractBinaryTree;
import com.company.hw5.utilityInterfaces.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    private static class ArrayPosition<E> implements Position<E> {
        private E element;
        private int index;

        public ArrayPosition(E e, int i) {
            element = e;
            index = i;
        }

        public E getElement() throws IllegalStateException {
            if (index == -1) throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public int getIndex() {
            return index;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setIndex(int i) {
            index = i;
        }
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        }

        public void remove() {
            posIterator.remove();
        }
    }

    public static final int DEFAULT_CAPACITY = 1;
    private ArrayPosition<E>[] tree;
    private int size = 0;

    public ArrayBinaryTree() {
        tree = (ArrayPosition<E>[]) new ArrayPosition[DEFAULT_CAPACITY];
    }

    public ArrayBinaryTree(int capacity) {
        tree = (ArrayPosition<E>[]) new ArrayPosition[capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    } // time complexity - O(1)

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        ArrayPosition<E> arrayPosition = validate(p);
        if (2 * arrayPosition.getIndex() + 1 > tree.length - 1) return null;
        else return tree[2 * arrayPosition.getIndex() + 1];
    }// time complexity - O(1)

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        ArrayPosition<E> arrayPosition = validate(p);
        if (2 * arrayPosition.getIndex() + 2 > tree.length - 1) return null;
        else return tree[2 * arrayPosition.getIndex() + 2];
    }// time complexity - O(1)

    @Override
    public Position<E> root() {
        return tree[0];
    }// time complexity - O(1)

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        ArrayPosition<E> arrayPosition = validate(p);
        if (arrayPosition.getIndex() == 0) return null;
        return tree[(arrayPosition.getIndex() - 1) / 2];
    }// time complexity - O(1)

    @Override
    public int size() {
        return size;
    } // time complexity - O(1)

    public int depth(Position<E> p) {
        ArrayPosition<E> arrayPosition = validate(p);
        return (int) Math.abs(Math.log(arrayPosition.getIndex() + 1) / Math.log(2));
    } // time complexity - O(1)

    public int height(Position<E> p) {
        return height() - depth(p);
    } // time complexity - O(n)

    public int height() {
        ArrayPosition<E> p = null;
        for (int i = size - 1; i > 0; i--) {
            if (tree[i] != null) {
                p = tree[i];
                break;
            }
        }
        return depth(p);
    } // worst case time complexity - O(n), it is linear to height of tree, which in worst case can be linear to the number of
    // positions in the tree, so overall O(n)

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        ArrayPosition<E> root = new ArrayPosition<>(e, 0);
        tree[0] = root;
        size = 1;
        return root;
    }// time complexity - O(1)

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        ArrayPosition<E> parent = validate(p);
        if (tree.length - 1 < parent.getIndex() * 2 + 1) {
            resize(2 * (parent.getIndex() * 2 + 1));
        }
        if (tree[parent.getIndex() * 2 + 1] != null) throw new IllegalArgumentException("p already has a left child");
        ArrayPosition<E> child = new ArrayPosition<>(e, parent.getIndex() * 2 + 1);
        tree[parent.getIndex() * 2 + 1] = child;
        size++;
        return child;
    }// worst case time complexity - O(2^n), when resizing

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        ArrayPosition<E> parent = validate(p);
        if (tree.length - 1 < parent.getIndex() * 2 + 2) {
            resize(2 * (parent.getIndex() * 2 + 2));
        }
        if (tree[parent.getIndex() * 2 + 2] != null) throw new IllegalArgumentException("p already has a right child");
        ArrayPosition<E> child = new ArrayPosition<>(e, parent.getIndex() * 2 + 2);
        tree[parent.getIndex() * 2 + 2] = child;
        size++;
        return child;
    }// worst case time complexity - O(2^n), when resizing

    public E remove(Position<E> p) throws IllegalArgumentException {
        ArrayPosition<E> arrayPosition = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        ArrayPosition<E> child = (left(p) != null ? (ArrayPosition<E>) left(p) : (ArrayPosition<E>) right(p));
        if (child != null) {
            tree[arrayPosition.getIndex()] = child;
            tree[child.getIndex()] = null;
        } else {
            tree[arrayPosition.getIndex()] = null;
        }
        size--;
        E temp = p.getElement();
        arrayPosition.setElement(null);
        arrayPosition.setIndex(-1);
        return temp;
    }// time complexity - O(1)


    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null) inorderSubtree(right(p), snapshot);
    }// Time complexity - O(n), because there is a recursive call for every n positions in the tree
    // and in each call constant amount of work is done.

    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) inorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }// time complexity O(n)

    public Position<E> inorderBefore(Position<E> p) {
        ArrayPosition<E> arrayPosition = validate(p);
        if (arrayPosition.getIndex() == Math.pow(2, height()) - 1) return null;
        if (isExternal(p)) {
            if (left(parent(p)) == p) {
                Position<E> current = p;
                while (left(parent(p)) == current) current = parent(p);
                return parent(current);
            }
        } else {
            if (left(p) != null && isExternal(left(p))) return left(p);
            if (left(p) != null && isInternal(left(p))) {
                Position<E> current = left(p);
                while (isInternal(right(current))) current = right(current);
                return right(current);
            }
        }
        return parent(p);
    }// worst case time complexity O(n)

    public Iterable<Position<E>> positions() {
        return postorder();
    }// time complexity O(n)

    private ArrayPosition<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof ArrayPosition<E>)) throw new IllegalArgumentException("Invalid p");
        ArrayPosition<E> arrayPosition = (ArrayPosition<E>) p;
        if (arrayPosition.getIndex() == -1) throw new IllegalArgumentException("p is no longer in the list");
        return arrayPosition;
    }// time complexity - O(1)

    private void resize(int capacity) {
        ArrayPosition<E>[] temp = new ArrayPosition[capacity];
        for (int k = 0; k < size; k++)
            temp[k] = tree[k];
        tree = temp;
    }// time complexity - O(capacity)

}
