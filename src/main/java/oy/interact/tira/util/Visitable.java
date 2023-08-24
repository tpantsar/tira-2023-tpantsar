package oy.interact.tira.util;

public interface Visitable<K extends Comparable<K>,V> {
	void accept(Visitor<K,V> visitor) throws Exception;
}
