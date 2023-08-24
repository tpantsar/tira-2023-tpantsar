package oy.interact.tira.factories;

import java.io.IOException;
import java.util.Comparator;

import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;
import oy.interact.tira.NotYetImplementedException;

public class BSTFactory {
	private BSTFactory() {
		// empty
	}

	public static <K extends Comparable<K>, V> TIRAKeyedOrderedContainer<K,V> createBST(Comparator<K> comparator) {
		throw new NotYetImplementedException("Task 07-TASK about BST not yet implemented");
	}

	// You may create the functionality that analyses a BST using the Visitor pattern
	public static <K extends Comparable<K>, V> Visitor<K,V> createBSTAnalyzerVisitor() {
		throw new NotYetImplementedException("Task 07-TASK BST Analyzer visitor not yet implemented");
	}

	public static <K extends Comparable<K>, V> Visitor<K,V> createBSTToDotFileTreeVisitor(String graphName, String fileName) throws IOException {
		throw new NotYetImplementedException("Task 07-TASK BST Analyzer dot file creator visitor not yet implemented");
	}
}
