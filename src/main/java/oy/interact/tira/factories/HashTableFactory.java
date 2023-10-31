package oy.interact.tira.factories;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.student.HashTableContainer;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableFactory {
	private HashTableFactory() {
		// empty
	}

	public static <K extends Comparable<K>, V> TIRAKeyedContainer<K,V> createHashTable() {
		return new HashTableContainer<>();
	}

}
