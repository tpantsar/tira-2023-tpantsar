package oy.interact.tira.util;

import java.util.function.Predicate;

/**
 * An interface for simple generic container class having key-value (K,V) pairs.
 * 
 * The container key elements must be unique. There must not be key
 * elements in the container that equal each other so that 
 * element.key.equals(another.key) returns true.
 * 
 * The key elements must implement the Comparable interface.
 * Note that in this project, the equals and Comparable differ
 * from the recommended. That is, the usual situation (x.compareTo(y)==0) == (x.equals(y))
 * does _not_ hold. See also the `Coder` class comments, which is used as K in this project.
 */
public interface TIRAKeyedContainer<K extends Comparable<K>, V> {
	/**
	 * Adds a key-value pair in the container. Key must be unique, so 
	 * if a key that equals new key already exists in the container, replace 
	 * the existing key-value pair with the parameters.
	 * 
	 * @param key The key to add or update.
	 * @param value The value to add or update.
	 * @throws OutOfMemoryError If memory runs out while adding elements.
	 * @throws IllegalArgumentException If one or both of the parameters are null.
	 */
	void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException;

	/**
	 * Retrieves a value assosicated with the key (key is equal to some key in the container), or null
	 * if key cannot be found.
	 * 
	 * @param key The key to use in searching.
	 * @return The value or null if one is not found.
	 * @throws IllegalArgumentException If the key is null.
	 */
	V get(K key) throws IllegalArgumentException;

	/**
	 * Removes and retrieves a value assosicated with the key (keys are equal), or null.
	 * 
	 * The key-value pair is removed from the container, if found.
	 * 
	 * @param key The key to use in searching.
	 * @return The value or null if one is not found.
	 * @throws IllegalArgumentException If the key is null.
	 */
	V remove(K key) throws IllegalArgumentException;

	/**
	 * Searchers for a value conforming to the predicate in the parameter.
	 * 
	 * Returns the first value that conforms to the predicate.
	 * 
	 * @param searcher The predicate that is used in the search.
	 * @return The value conforming to the predicate, or null if not found.
	 */
	V find(Predicate<V> searcher);

	/**
	 * The number of key-value pairs in the container.
	 * 
	 * @return The number of pairs in container.
	 */
	int size();

	/**
	 * The total capacity of the container, indicating how many elements
	 * the container can hold currently.
	 * 
	 * @return The capacity of the container.
	 */
	int capacity();

	/**
	 * Ensures that the container can hold the indicated number of elements.
	 * 
	 * Note that if the container already holds elements, they must also be
	 * in the reallocated container. Note also that in some implementations,
	 * this method can be a no-op (e.g. linked lists or trees), while table
	 * (array) based implementations need to make sure elements in the old
	 * array are copied to the new reallocated array.
	 * 
	 * Note that the new capacity can be smaller than the current capacity,
	 * when compressing the storage. However, the storage must be able to fit
	 * the possible already existing items in the container.
	 * 
	 * @param capacity The new capacity for the container.
	 * @throws OutOfMemoryError If memory runs out.
	 * @throws IllegalArgumentException If capacity <= 0 or capacity <= size().
	 */
	void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException;

	/**
	 * Clears the container of all elements. After the call, size is zero.
	 */
	void clear();

	/**
	 * Provides the elements of the container in an array. Note that
	 * the array must not be any internal array used by the container, but
	 * a temporary array allocated in the method. Caller can then manipulate
	 * that array without manipulating the container internal data.
	 * 
	 * @return The key-value pairs in the container in an array.
	 * @throws Exception If memory runs out or other faults happen in creating the array.
	 */
	Pair<K,V> [] toArray() throws Exception;
}
