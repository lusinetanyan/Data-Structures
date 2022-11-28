package com.company.hw5.utilityClasses;

import com.company.hw5.utilityInterfaces.Position;
import com.company.hw5.utilityInterfaces.Queue;
import com.company.hw5.utilityInterfaces.Tree;

import java.util.*;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
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

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() { return preorder(); }

    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p); // for postorder, we add position p after exploring subtrees
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root()); // start with the root
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue(); // remove from front of the queue
                snapshot.add(p); // report this position
                for (Position<E> c : children(p))
                    fringe.enqueue(c); // add children to back of queue
            }
        }
        return snapshot;
    }
}
