package oy.interact.tira.task_06_fastsort;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import oy.interact.tira.model.Coder;
import oy.interact.tira.student.Algorithms;

@DisplayName("Testing fastSort algorithm with Comparators")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CodersFastSortBasicTests extends CoderTestBase {
	
	@BeforeAll
	static void printTest() {
		System.out.println("Testing FAST sorting with Coders and Comparator");
		System.out.println("Testing sort correctness with 100 coders");
	}

	@BeforeEach
	void loadTestPhoneBook() {
		try {
			if (currentFileIndex < coderFiles.length) {
				coderArray = loadCoders(new File(coderFiles[currentFileIndex]));
				assertNotNull(coderArray, "Coders array cannot be null");
				assertEquals(coderCounts[currentFileIndex], coderArray.length, "Array size of coders is not correct");
			}
		} catch (Exception e) {
			fail("Could not read coders to memory for the test");
		}
	}

	@Test
	@Order(1)
	void testDefaultSortOrder() {
		System.out.println("--\nSorting by DEFAULT sort order, printing first 10 coders, verify order:");
		Algorithms.fastSort(coderArray);
		printCoders(0, coderArray.length);
		assertDoesNotThrow(() -> isInOrder(coderArray), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(2)
	void testCoderNameAscendingComparator() {
		System.out.println("--\nSorting with ASCENDING comparator by name, printing first 10 coders, verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return first.getFullName().compareTo(second.getFullName());
			}
		};
		Algorithms.fastSort(coderArray, comparator);
		printCoders(0, coderArray.length);
		assertDoesNotThrow(() -> isInOrder(coderArray, comparator), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(3)
	void testCoderNameDescendingComparator() {
		System.out.println("--\nSorting DESCENDING comparator by name, printing first 10 coders, verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return second.getFullName().compareTo(first.getFullName());
			}
		};
		Algorithms.fastSort(coderArray, comparator);
		printCoders(0, coderArray.length);
		assertDoesNotThrow(() -> isInOrder(coderArray, comparator), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(4)
	void testLeastKnownLanguagesComparator() {
		System.out.println("--\nPrinting first 10 coders who know the least number of languages verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return first.getLanguages().size() - second.getLanguages().size();				
			}
		};
		Algorithms.fastSort(coderArray, comparator);
		printCoders(0, coderArray.length);
		assertDoesNotThrow(() -> isInOrder(coderArray, comparator), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(5)
	void testMostKnownLanguagesComparator() {
		System.out.println("--\nPrinting first 10 coders who know the most number of languages, verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return second.getLanguages().size() - first.getLanguages().size();				
			}
		};
		Algorithms.fastSort(coderArray, comparator);
		printCoders(0, coderArray.length);
		assertDoesNotThrow(() -> isInOrder(coderArray, comparator), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(6)
	void testCoderNameRangeAscendingComparator() {
		System.out.println("--\nSorting a range of 8 Coders with ASCENDING comparator by name, printing the range ofs coders, verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return first.getFullName().compareTo(second.getFullName());
			}
		};
		Algorithms.fastSort(coderArray, 42, 50, comparator);
		printCoders(42, 50);
		assertDoesNotThrow(() -> isInOrder(coderArray, 42, 50, comparator), "Array contained nulls, or sort order was wrong");
	}

	@Test
	@Order(7)
	void testCoderNameRangeDescendingComparator() {
		System.out.println("--\nSorting a range of 8 Coders with DESCENDING comparator by name, printing the range ofs coders, verify order:");
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return second.getFullName().compareTo(first.getFullName());
			}
		};
		Algorithms.fastSort(coderArray, 42, 50, comparator);
		printCoders(42, 50);
		assertDoesNotThrow(() -> isInOrder(coderArray, 42, 50, comparator), "Array contained nulls, or sort order was wrong");
	}



	private void printCoders(int from, int to) {
		System.out.format("%3s %3s  %s%n", "ind", "Langs", "Coder name -- programming language skills");
		for (int index = from; index < to; index++) {
			System.out.format("%3d. %3d  %s -- %s %n", index, coderArray[index].getLanguages().size(), coderArray[index], coderArray[index].getLanguagesString());
			if (index == 10) {
				break;
			}
		}
		System.out.println("...");
	}

}
