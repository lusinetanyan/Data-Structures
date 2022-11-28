package com.company.hw6;

import com.company.hw6.UtilityClasses.AbstractSortedMap;
import com.company.hw6.UtilityClasses.LinkedBinaryTree;
import com.company.hw6.UtilityInterfaces.Entry;
import com.company.hw6.UtilityInterfaces.Position;

import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> extends AbstractSortedMap<K, V> {
    private LinkedBinaryTree<MapEntry<K, V>> bst = new LinkedBinaryTree<>();

    public BSTMap(Comparator<K> comp) {
        super(comp);
        bst.addRoot(new MapEntry<K, V>(null, null));
    }

    public BSTMap() {

        super();
        bst.addRoot(new MapEntry<K, V>(null, null));
    }

    @Override
    public int size() {
        return (bst.size() - 1) / 2;
    } //Time complexity - O(1)

    private void expandExternal(Position<MapEntry<K, V>> p, MapEntry<K, V> e) {
        bst.set(p, e);
        bst.addLeft(p, new MapEntry<>(null, null));
        bst.addRight(p, new MapEntry<>(null, null));
    } //Time complexity - O(1)

    private Position<MapEntry<K, V>> treeSearch(Position<MapEntry<K, V>> p, K k) {
        if (bst.isExternal(p)) return p;
        if (compare(k, p.getElement().getKey()) < 0)
            return treeSearch(bst.left(p), k);
        else if (compare(k, p.getElement().getKey()) == 0)
            return p;
        else return treeSearch(bst.right(p), k);
    } //Time complexity - worst case O(logn)

    @Override
    public V get(K key) {
        checkKey(key);
        return treeSearch(bst.root(), key).getElement().getValue();
    } //Time complexity - worst case O(logn)

    @Override
    public V put(K key, V value) {
        if (isEmpty()) {
            expandExternal(bst.root(), new MapEntry<>(key, value));
            return null;
        }
        checkKey(key);
        Position<MapEntry<K, V>> p = treeSearch(bst.root(), key);
        if (bst.isExternal(p)) {
            expandExternal(p, new MapEntry<>(key, value));
            return null;
        } else {
            V returnValue = p.getElement().getValue();
            bst.set(p, new MapEntry<>(key, value));
            return returnValue;
        }
    } //Time complexity - worst case O(logn)

    @Override
    public V remove(K key) {
        checkKey(key);
        Position<MapEntry<K, V>> p = treeSearch(bst.root(), key);
        if (bst.isExternal(p)) return null;
        if (compare(key, p.getElement().getKey()) == 0) {
            V returnValue = p.getElement().getValue();
            if (isExternal(p)) {
                expandExternal(p, new MapEntry<>(null, null));
            } else if (bst.isExternal(bst.left(p))) {
                bst.remove(bst.left(p));
                bst.remove(p);
            } else if (bst.isExternal(bst.right(p))) {
                bst.remove(bst.right(p));
                bst.remove(p);
            } else {
                Position<MapEntry<K, V>> inorderAfter = inorderAfter(p);
                bst.set(p, inorderAfter.getElement());
                bst.remove(bst.left(inorderAfter));
                bst.remove(inorderAfter);
            }
            return returnValue;
        } else return null;
    } //Time complexity - worst case O(logn)

    private boolean isExternal(Position<MapEntry<K, V>> p) {
        return bst.isExternal(bst.left(p)) && bst.isExternal(bst.right(p));
    } //Time complexity - O(1)

    private Position<MapEntry<K, V>> lastInorder() {
        if (isEmpty()) return null;
        Position<MapEntry<K, V>> p = bst.root();
        while (!bst.isExternal(bst.right(p))) {
            p = bst.right(p);
        }
        return p;
    } //Time complexity - worst case O(logn)

    private Position<MapEntry<K, V>> firstInorder() {
        if (isEmpty()) return null;
        Position<MapEntry<K, V>> p = bst.root();
        while (!bst.isExternal(bst.left(p))) {
            p = bst.left(p);
        }
        return p;
    } //Time complexity - worst case O(logn)

    private Position<MapEntry<K, V>> inorderAfter(Position<MapEntry<K, V>> p) {
        if (p == lastInorder()) return null;
        if (bst.isExternal(p) || isExternal(p)) {
            if (bst.left(bst.parent(p)) == p) return bst.parent(p);
            else {
                Position<MapEntry<K, V>> walk = bst.parent(p);
                while (walk != null && bst.right(bst.parent(walk)) == walk) {
                    walk = bst.parent(walk);
                }
                return walk;
            }
        } else {
            if (bst.isExternal(bst.right(p))) return bst.right(p);
            else {
                Position<MapEntry<K, V>> walk = bst.right(p);
                while (walk != null && !bst.isExternal(bst.left(walk))) {
                    walk = bst.left(walk);
                }
                return walk;
            }
        }
    } //Time complexity - worst case O(logn)

    private Position<MapEntry<K, V>> inorderBefore(Position<MapEntry<K, V>> p) {
        if (p == firstInorder()) return null;
        if (bst.isExternal(p) || isExternal(p)) {
            if (bst.right(bst.parent(p)) == p) return bst.parent(p);
            else {
                Position<MapEntry<K, V>> walk = bst.parent(p);
                while (walk != null && bst.left(bst.parent(walk)) == walk) walk = bst.parent(walk);
                return bst.parent(walk);
            }
        } else {
            if (bst.left(p) != null && bst.isExternal(bst.left(p))) return bst.left(p);
            if (bst.left(p) != null && !bst.isExternal(bst.left(p))) {
                Position<MapEntry<K, V>> walk = bst.left(p);
                while (walk != null && !bst.isExternal(bst.right(walk))) walk = bst.right(walk);
                return bst.right(walk);
            }
        }
        return bst.parent(p);
    } //Time complexity - worst case O(logn)

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> entrySet = new ArrayList<>(bst.size());
        Position<MapEntry<K, V>> walk = firstInorder();
        int size = 0;
        while (walk != null && size != bst.size()) {
            entrySet.add(walk.getElement());
            walk = inorderAfter(walk);
            size++;
        }
        return entrySet;
    } //Time complexity - O(n)

    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) return null;
        return firstInorder().getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) return null;
        return lastInorder().getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> ceilingEntry(K k) {
        Position<MapEntry<K, V>> p = treeSearch(bst.root(), k);
        if (p.getElement().getKey() == k) return p.getElement();
        else return inorderAfter(p).getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> floorEntry(K k) {
        Position<MapEntry<K, V>> p = treeSearch(bst.root(), k);
        if (p.getElement().getKey() == k) return p.getElement();
        else return inorderBefore(p).getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> lowerEntry(K k) {
        Position<MapEntry<K, V>> returnEntry = treeSearch(bst.root(), k);
        return inorderBefore(returnEntry).getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Entry<K, V> higherEntry(K k) {
        Position<MapEntry<K, V>> returnEntry = treeSearch(bst.root(), k);
        return inorderAfter(returnEntry).getElement();
    } //Time complexity - worst case O(logn)

    @Override
    public Iterable<Entry<K, V>> subMap(K k1, K k2) {
        Position<MapEntry<K, V>> p = treeSearch(bst.root(), k1);
        ArrayList<Entry<K, V>> subMap = new ArrayList<>();
        while (p.getElement().getKey() != k2) {
            subMap.add(p.getElement());
            p = inorderAfter(p);
        }
        return subMap;
    } //Time complexity - worst case O(s + logn), where s is the number of entries in the range
}