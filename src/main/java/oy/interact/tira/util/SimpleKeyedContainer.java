package oy.interact.tira.util;

import java.util.function.Predicate;

// This is a test class used in comparisons with hash table implementation. 
// Not to be touched by you, the student.
public class SimpleKeyedContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

	private Pair<K, V>[] array;
	private int lastElementIndex = -1;
	private int size = 0;
	private static final int DEFAULT_ARRAY_SIZE = 20;
	private static final int ARRAY_SIZE_MULTIPLICATION = 2;

	@SuppressWarnings("unchecked")
	public SimpleKeyedContainer() {
		array = (Pair<K, V>[]) new Pair[DEFAULT_ARRAY_SIZE];
	}

	@Override
	public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
		if (lastElementIndex >= array.length - 1) {
			reallocate(array.length * ARRAY_SIZE_MULTIPLICATION);
		}
		Pair<K, V> newPair = new Pair<>(key, value);
		lastElementIndex++;
		array[lastElementIndex] = newPair;
		size++;
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		if (null == key) {
			throw new IllegalArgumentException("Must not add nulls to container");
		}
		if (size < 1) {
			return null;
		}
		for (int index = 0; index <= lastElementIndex; index++) {
			if (null != array[index] && array[index].getKey().equals(key)) {
				return array[index].getValue();
			}
		}
		return null;
	}

	@Override
	public V remove(K key) throws IllegalArgumentException {
		if (size < 1) {
			return null;
		}
		for (int index = 0; index <= lastElementIndex; index++) {
			if (null != array[index] && array[index].getKey().equals(key)) {
				V value = array[index].getValue();
				array[index] = null;
				size--;
				if (index == lastElementIndex) {
					lastElementIndex--;
				}
				return value;
			}
		}
		return null;
	}

	@Override
	public V find(Predicate<V> searcher) {
		for (int index = 0; index <= lastElementIndex; index++) {
			if (null != array[index] && searcher.test(array[index].getValue())) {
				return array[index].getValue();
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return array.length;
	}

	@Override
	public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
		if (capacity > array.length) {
			reallocate(capacity);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		lastElementIndex = -1;
		size = 0;
		array = (Pair<K, V>[]) new Pair[DEFAULT_ARRAY_SIZE];
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pair<K, V>[] toArray() throws Exception {
		Pair<K, V>[] newArray = (Pair<K, V>[]) new Pair[size];
		int newIndex = 0;
		for (int index = 0; index <= lastElementIndex; index++) {
			if (null != array[index]) {
				newArray[newIndex++] = new Pair<>(array[index].getKey(), array[index].getValue());
			}
		}
		return newArray;
	}

	@SuppressWarnings("unchecked")
	private void reallocate(int newSize) {
		Pair<K, V>[] newArray = (Pair<K, V>[]) new Pair[newSize];
		int newIndex = 0;
		for (int index = 0; index <= lastElementIndex; index++) {
			if (null != array[index]) {
				newArray[newIndex++] = new Pair<>(array[index].getKey(), array[index].getValue());
			}
		}
		array = newArray;
		lastElementIndex = newIndex + 1;
	}
}