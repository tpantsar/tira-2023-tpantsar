package oy.interact.tira.task_07_bst;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.model.Coder;
import oy.interact.tira.util.JSONConverter;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Performance tests for BST")
public class BSTPerformanceTests {

	private static final String outputFileName = "compare-bst.csv";
	private static final String separator = ",";
	private static int currentIndex = 0;
	private static BufferedWriter writer = null;
	private static final String[] testFiles = { 
			"100-city-coders.json",
			"1000-area-coders.json",
			"5000-town-coders.json",
			"10000-large-city-coders.json",
			"50000-country-coders.json",
			"100000-europe-coders.json",
			"1000000-global-coders.json",
			"2000000-milky-way-orion-spur-coders.json"
		};

	private static Coder found = null;

	@BeforeAll
	static void openOutputFile() {
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.append("** Analysing bst.add(key, value) and toArray speeds, all times are ms **");
			writer.newLine();
			writer.append("Elements (n),Add time,Add time/item,To sorted array time,Search time,Search time/item,get(index) time,get(index) time/item,Testfile");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not open test output file for writing");
		}
	}

	@Test
	@Order(1)
	// @Timeout(value = 600, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void handleReadTestFilesWithBST() {
		if (null != writer) {
			try {
				currentIndex = 0;
				Comparator<String> comparator = new Comparator<>() {
					@Override
					public int compare(String first, String second) {
						return first.compareTo(second);
					}
				};
				DecimalFormat df = new DecimalFormat("0.0000", DecimalFormatSymbols.getInstance(Locale.US));
				while (currentIndex < testFiles.length) {
					System.out.format(
						"%d/%d ==> Starting to analyse BST with %s %s%n", 
						currentIndex + 1, 
						testFiles.length, 
						testFiles[currentIndex], 
						new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
					final TIRAKeyedOrderedContainer<String, Coder> fastBST = BSTFactory.createBST(comparator);
					assertNotNull(fastBST,
							() -> "BSTFactory.createBST() returns null, not yet implemented?");
					Coder [] coders = readCodersFromFile(testFiles[currentIndex]);
					long start = System.currentTimeMillis();
					for (Coder coder : coders) {
						fastBST.add(coder.getId(), coder);
					}
					long end = System.currentTimeMillis();
					long duration = end - start;
					System.out.format(" Step 3/6: Adding to BST from Coders array it took %d ms%n", duration);
					assertEquals(coders.length, fastBST.size(), "Coder array length and BST.size do not match");
					/*
					 * testfile,bytes,elements,duration
					 */
					writer.append(Long.toString(fastBST.size()));
					writer.append(separator);
					writer.append(Long.toString(duration));
					writer.append(separator);
					String perItem = df.format((double)duration/(double)coders.length);
					writer.append(perItem);
					writer.append(separator);
					
					// Also test if all coders are in the tree, this is find
					start = System.currentTimeMillis();
					for (Coder coder : coders) {
						assertDoesNotThrow(() -> found = fastBST.get(coder.getId()), "bst.get(K) must not throw");
						assertEquals(coder, found, "Found coder must be equal to the searched coder");
					}
					end = System.currentTimeMillis();
					long findDuration = end - start;
					System.out.format(" Step 4/6: Searching BST took %d ms%n", findDuration);
					int codersLength = coders.length;
					coders = null;
					
					// BST.ToArray. No need to sort since items are in order in BST already.
					start = System.currentTimeMillis();
					Pair<String,Coder> [] coders2 = fastBST.toArray();
					end = System.currentTimeMillis();
					duration = end - start;
					writer.append(Long.toString(duration));
					writer.append(separator);
					
					writer.append(Long.toString(findDuration));
					writer.append(separator);
					perItem = df.format((double)findDuration/(double)codersLength);
					writer.append(perItem);
					writer.append(separator);
					assertEquals(codersLength, coders2.length, "Count of coders from JSON and from BST.toArray should be equal");
					// Steps 1-2 are in readCodersFromFile
					System.out.format(" Step 5/6: Exporting an array from BST to Coders array it took %d ms%n", duration);
					for (Pair<String,Coder> coderPair : coders2) {
						assertNotNull(coderPair, "Array of pairs of coders and keys must not contain nulls");
						assertEquals(coderPair.getKey(), coderPair.getValue().getId(), "Key must be coder's id");
					}
					// get(index) time
					start = System.currentTimeMillis();
					for (int index = 0; index < fastBST.size(); index++) {
						Pair<String,Coder> pair = fastBST.getIndex(index);
						if (null == pair) {
							assertNotNull(pair, "fastBST.get(index) must not return null");
						}
					}
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 6/6: Calling get(int index) for all items took %d ms%n", duration);
					writer.append(Long.toString(duration));
					writer.append(separator);
					perItem = df.format((double)duration/(double)fastBST.size());
					writer.append(perItem);
					writer.append(separator);
					
					writer.append(testFiles[currentIndex]);
					writer.newLine();
					writer.flush();
					currentIndex++;
				}
				System.out.format("<== Test finished, see file %s for statistics!%n", outputFileName);
			} catch (OutOfMemoryError oom) {
				System.out.println("\n*** ERROR: Run out of memory while handling test json files\n");
				System.out.println("*** If testing from IDE; try to run the test from command line:");
				System.out.println("*** mvn -Dtest=oy.interact.tira.task_07_bst.BSTPerformanceTests test");
				System.out.format("<== Test finished, see file %s for statistics!%n", outputFileName);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Could not write test output file: " + e.getMessage());
			}
		} else {
			fail("Cannot run tests since opening output file writer failed.");
		}
	}

	@AfterAll
	static void closeOutputFile() {
		try {
			writer.close();
		} catch (IOException e) {
			fail("Could not close test output file");
		}
	}

	private Coder [] readCodersFromFile(String fileName)
			throws IOException {
		long start = System.currentTimeMillis();
		JSONTokener tokener = new JSONTokener(new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		System.out.format(" Step 1/6: JSON Parsing - from file %s to JSONArray it took %d ms%n", fileName, System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Coder [] coders = JSONConverter.codersFromJSONArray(array);
		System.out.format(" Step 2/6: From JSONArray to Coders array it took %d ms%n", System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		return coders;
	}
}
