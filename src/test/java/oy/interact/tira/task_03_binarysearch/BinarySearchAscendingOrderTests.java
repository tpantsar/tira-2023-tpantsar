package oy.interact.tira.task_03_binarysearch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import oy.interact.tira.util.SimpleContainer;
import oy.interact.tira.util.TIRAContainer;

public class BinarySearchAscendingOrderTests {

	static String searchResult;
	static int foundIndex;
	static final String[] hardCodedElements = { "Programming, one those little joys in life",
			"SimpleContainer",
			"All the code words",
			"I prefer binary search trees to SimpleContainers",
			"I prefer debugging to mindless hacking",
			"Inter cactus",
			"TIRA course, the trip everybody wants to join",
			"Juunivaersitus of Olutensis",
			"Reverse that array!",
			"Environment variables, those pesky beasts",
			"Reading a programming book on the beach",
			"Unit testing, better than beer!",
			"Sorting is fun!",
			"Hello World!",
			"Those lovely arrays!",
			"My god, it's full of stars!" };

	@Test
	@DisplayName("Simple linear search test for SimpleContainer")
	@Order(1)
	void testBinarySearchWithStrings() {
		SimpleContainer<String> container = new SimpleContainer<>(String.class);
		assertDoesNotThrow(() -> fillContainer(container, 1000), () -> "Adding to SimpleContainer should not throw");
		assertDoesNotThrow(() -> container.add("Something something"),
				() -> "Adding to SimpleContainer should not throw");

		Comparator<String> comparator = new Comparator<>() {
			@Override
			public int compare(String first, String second) {
				return first.compareTo(second);
			}
		};
		assertDoesNotThrow(() -> container.sort(comparator),
				"Sorting the SimpleContainer with comparator must not throw");

		assertDoesNotThrow(() -> foundIndex = container.indexOf("Something something", comparator),
				"SimpleContainer.indexOf should now throw");
		assertNotEquals(-1, foundIndex, "Must find the test string put in the container");
		assertTrue((foundIndex >= 0 && foundIndex < container.size()),
				"The returned index must be >= 0 && < container.size()");

		assertEquals(-1, container.indexOf("A string surely not in the container", comparator),
				"The string to find is not in container");
	}

	@Test
	@Order(2)
	@DisplayName("Time performance of binary search test for SimpleContainer")
	void testTimePerformanceOfBinarySearch() {

		System.out.println("Time performance tests of filling and binary searching the SimpleContainer");
		System.out.println("Fill time is in milliseconds, and search time in microseconds");
		System.out.println("n\tFill\tSort\tSearch\tTotal");
		for (int size = 500; size <= 32_000; size += 500) {
			final int currentSize = size;
			SimpleContainer<String> container = new SimpleContainer<>(String.class);
			long start = System.currentTimeMillis();
			assertDoesNotThrow(() -> fillContainer(container, currentSize),
					() -> "Adding to SimpleContainer should not throw");
			long fillDuration = System.currentTimeMillis() - start;

			Comparator<String> comparator = new Comparator<>() {
				@Override
				public int compare(String first, String second) {
					return first.compareTo(second);
				}
			};
			start = System.currentTimeMillis();
			assertDoesNotThrow(() -> container.sort(comparator),
				"Sorting the SimpleContainer with comparator must not throw");
			long sortDuration = System.currentTimeMillis() - start;
			start = System.nanoTime();
			for (int index = 0; index < hardCodedElements.length; index++) {
				final int currentIndex = index;
				assertDoesNotThrow(() -> foundIndex = container.indexOf(hardCodedElements[currentIndex], comparator),
						"The string to find must be in container");
			}
			long searchDuration = (System.nanoTime() - start) / 1000;
			System.out.format("%d\t%d\t%d\t%d\t%d%n", currentSize, fillDuration, sortDuration, searchDuration, fillDuration+sortDuration+searchDuration);
		}
		System.out.println("\nDone!");
	}

	private void fillContainer(TIRAContainer<String> container, int size) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		final Random random = new Random();
		int hardCodedElementIndex = 0;

		for (int i = 0; i < size; i++) {
			String generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(number -> (number <= 57 || number >= 65) && (number <= 90 || number >= 97))
					.limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			container.add(generatedString);
			if (i > ((size / 3) * 2) && hardCodedElementIndex < hardCodedElements.length) {
				if (random.nextInt(42) % 5 == 0) {
					container.add(hardCodedElements[hardCodedElementIndex++]);
				}
			}
		}
		while (hardCodedElementIndex < hardCodedElements.length) {
			container.add(hardCodedElements[hardCodedElementIndex++]);
		}
	}

}
