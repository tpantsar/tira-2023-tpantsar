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
import org.junit.jupiter.api.RepeatedTest;

import oy.interact.tira.model.Coder;
import oy.interact.tira.student.Algorithms;

@DisplayName("Testing slow sort algorithm with Comparators")
public class CodersSlowComparatorTests extends CoderTestBase {

	private static final int TEST_COUNT = 6;

	@BeforeAll
	static void printTest() {
		System.out.println("Testing SLOW sorting with Coders and Comparator");
		System.out.format("There will be %d tests executed%n", TEST_COUNT);
		System.out.format("%3s\t%8s\t%-8s\t%-8s%n", "Test#", "Count", "ms", "ms/element");
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

	@RepeatedTest(TEST_COUNT)
	void testCoderNameAscendingComparator() {
		// System.out.format("Sorting file %s with %d coders...%n", coderFiles[currentFileIndex], coderCounts[currentFileIndex]);
		long start = System.currentTimeMillis();
		Comparator<Coder> comparator = new Comparator<Coder>() {
			@Override
			public int compare(Coder first, Coder second) {
				return first.getFullName().compareTo(second.getFullName());
			}
		};
		Algorithms.insertionSort(coderArray, comparator);
		long duration = System.currentTimeMillis() - start;
		System.out.format("%3d\t%8d\t%8d\t%8.3f%n", currentFileIndex+1, coderCounts[currentFileIndex], duration, (double)duration/(double)coderCounts[currentFileIndex]);
		assertDoesNotThrow(() -> isInOrder(coderArray, comparator), "Order is not correct in array");
		currentFileIndex++;			
	}

}
