package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import javax.swing.tree.TreeNode;
import java.util.Comparator;
import java.util.function.Predicate;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    //TreeNode<K, V> root; // Root node of the tree, your private little helper class.
    Node root;
    int size; // Number of elements currently in the tree.

    private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

    // Constructor
    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    private class Node {
        private K key;
        private V value;
        private Node leftChild;
        private Node rightChild;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // Utilize Comparable.compareTo method for comparing objects
        private void add (Node node) {
            if (node.key.compareTo(this.key) < 0) {
                if (this.leftChild == null) {
                    this.leftChild = node;
                } else {
                    this.leftChild.add(node);
                }
            } else if (node.key.compareTo(this.key) > 0) {
                if (this.rightChild == null) {
                    this.rightChild = node;
                } else {
                    this.rightChild.add(node);
                }
            }
        }

        private Node bstSearch (Node node, K key) {
            if (node == null || node.key.compareTo(key) == 0) {
                return node;
            }
            if (key.compareTo(node.key) < 0) {
                return bstSearch(node.leftChild, key);
            }
            return bstSearch(node.rightChild, key);
        }
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (null == key || null == value) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        Node node = new Node(key, value);

        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        // Haetaan predikaatin mukaisen hakukriteerin täyttävä arvo puusta.
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {

    }

    @Override
    public void clear() {

    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        // käydään puu läpi in-order ja palautetaan taulukko jossa on kaikki puussa olevat avain-arvoparit
        // omassa Pair -oliossaan.
        return new Pair[0];
    }

    @Override
    public int indexOf(K itemKey) {
        // haetaan tietyn avaimen indeksi eli järjestysnumero puussa.
        return 0;
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        // haetaan tietyssä indeksissä oleva avain-arvo -pari.
        return null;
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        // haetaan predikaatin mukaisen hakukriteerin täyttävä arvon indeksi eli järjestysnumero puusta.
        return 0;
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {

    }
}

