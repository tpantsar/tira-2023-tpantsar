package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import java.util.Comparator;
import java.util.function.Predicate;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    //TreeNode treeNode; // Root node of the tree, your private little helper class.
    private Node root = null;
    private int size; // Number of elements currently in the tree.
    private int currentIndex = 0; // Keep track of the index

    // The comparator used to determine if new node will go to left or right subtree.
    private Comparator<K> comparator;

    // Constructor
    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.root = null;
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
            this.leftChild = null;
            this.rightChild = null;
        }

        // Utilize Comparable.compareTo method for comparing objects
        private void add(Node node) {
            // If added value is identical in BST, update the node and remove duplicate values
            if (this.value.equals(node.value)) {
                this.key = node.key;
                this.value = node.value;
            } else {
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
        }

        private Node bstSearch(Node node, K key) {
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
        // Check if one or both of the parameters are null
        if (key == null || value == null) {
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
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node result = root.bstSearch(root, key);

        if (result != null) {
            return result.value;
        } else {
            return null; // Key not found
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        // Haetaan predikaatin mukaisen hakukriteerin täyttävä arvo puusta
        return findValueInOrder(root, searcher);
    }

    // Helper method for in-order traversal searching a value conforming to the predicate
    private V findValueInOrder(Node node, Predicate<V> searcher) {
        if (node == null) {
            return null; // Value not found
        }

        // Traverse the left subtree
        V leftResult = findValueInOrder(node.leftChild, searcher);

        // Check if the current node's value matches with predicate
        if (searcher.test(node.value)) {
            return node.value; // Value found
        }

        if (leftResult != null) {
            return leftResult;
        }

        // Traverse the right subtree
        return findValueInOrder(node.rightChild, searcher);
    }

    @Override
    public int size() {
        size = getTreeSize(root);
        return size;
    }

    // Calculates size of the tree beginning from the node parameter
    private int getTreeSize(Node node) {
        if (node == null) {
            return 0; // An empty tree has size 0
        }

        return getTreeSize(node.leftChild) + getTreeSize(node.rightChild) + 1;
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
        root = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() {
        Pair<K, V>[] resultArray = (Pair<K, V>[]) new Pair[size];
        int[] index = {0};
        toArrayInOrder(root, resultArray, index);
        return resultArray;
    }

    // Helper method for in-order traversal and filling the array
    private void toArrayInOrder(Node node, Pair<K, V>[] array, int[] index) {
        if (node == null) {
            return;
        }
        toArrayInOrder(node.leftChild, array, index); // Traverse the left subtree
        array[index[0]++] = new Pair<>(node.key, node.value);
        toArrayInOrder(node.rightChild, array, index); // Traverse the right subtree
    }

    @Override
    public int indexOf(K itemKey) {
        currentIndex = 0;
        return indexOfInOrder(root, itemKey);
    }

    // Helper method for finding the index of node's key
    private int indexOfInOrder(Node node, K itemKey) {
        if (node == null) {
            return -1; // Key not found
        }

        // Traverse the left subtree
        int leftIndex = indexOfInOrder(node.leftChild, itemKey);

        if (leftIndex != -1) {
            return leftIndex; // Key found in the left subtree
        }

        // If the current node's key matches the target key, return the currentIndex
        if (node.key.equals(itemKey)) {
            return currentIndex;
        }

        currentIndex++;

        // Traverse the right subtree
        return indexOfInOrder(node.rightChild, itemKey);
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        // Haetaan tietyssä indeksissä oleva avain-arvo -pari
        if (index < 0) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        currentIndex = 0;
        return getKeyValueAtIndex(root, index);
    }

    // Helper method for finding key-value pair at the index
    private Pair<K, V> getKeyValueAtIndex(Node node, int index) {
        if (node == null) {
            return null;
        }

        // Traverse the left subtree
        Pair<K, V> keyValuePair = getKeyValueAtIndex(node.leftChild, index);

        if (keyValuePair != null) {
            return keyValuePair;
        }

        if (currentIndex == index) {
            return new Pair<>(node.key, node.value);
        }

        currentIndex++;

        // Traverse the right subtree
        return getKeyValueAtIndex(node.rightChild, index);
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        // Haetaan predikaatin mukaisen hakukriteerin täyttävä arvon indeksi eli järjestysnumero puusta.
        currentIndex = 0;
        return findIndexInOrder(root, searcher);
    }

    // Helper method for finding index
    private int findIndexInOrder(Node node, Predicate<V> searcher) {
        if (node == null) {
            return -1; // Index not found
        }

        // Traverse the left subtree
        int leftIndex = findIndexInOrder(node.leftChild, searcher);

        if (leftIndex != -1) {
            return leftIndex; // Key found in the left subtree
        }

        // Check if the current node's index matches with predicate
        if (searcher.test(node.value)) {
            return currentIndex; // Index for the value found
        }

        currentIndex++;

        // Traverse the right subtree
        return findIndexInOrder(node.rightChild, searcher);
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {

    }
}
