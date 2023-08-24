package oy.interact.tira.task_07_bst;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.model.Coder;
import oy.interact.tira.util.JSONConverter;
import oy.interact.tira.util.SimpleContainer;
import oy.interact.tira.util.TIRAContainer;

public class SimpleContainerPerformanceTests {
	private static final String outputFileName = "compare-simplecontainer.csv";
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
			"1000000-global-coders.json" // This will take a  l o n g  time with this container. Orion Spur coders - no way...
		};

	private static int foundIndex = -1;
	private static Coder found = null;

	@BeforeAll
	static void openOutputFile() {
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.append("** Analysing SimpleContainer.add() and toArray (sorting included) speed, times in ms **");
			writer.newLine();
			writer.append("elements (n),add time,add time/item,To array+sort time,search time,search time/item,getIndex time,getIndex time/item,testfile");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not open test output file for writing");
		}
	}

	@Test
	@Order(1)
	// @Timeout(value = 600, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void handleReadTestFilesWithSimpleContainer() {
		if (null != writer) {
			try {
				currentIndex = 0;
				DecimalFormat df = new DecimalFormat("0.0000", DecimalFormatSymbols.getInstance(Locale.US));
				while (currentIndex < testFiles.length) {
					System.out.format(
						"%d/%d ==> Starting to analyse SimpleContainer with %s %s%n", 
						currentIndex + 1, 
						testFiles.length, 
						testFiles[currentIndex], 
						new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
					final TIRAContainer<Coder> simpleContainer = new SimpleContainer<Coder>(Coder.class);
					Coder [] coders = readCodersFromFile(testFiles[currentIndex]);
					// ...................
					// Measuring add time
					// ...................
					long start = System.currentTimeMillis();
					for (Coder coder : coders) {
						simpleContainer.add(coder);
					}
					long end = System.currentTimeMillis();
					long duration = end - start;
					assertEquals(coders.length, simpleContainer.size(), "Test array size and hashtable size must be the same");
					// Steps 1-2 are in readCodersFromFile
					System.out.format(" Step 3/7: Adding to SimpleContainer from Coders array it took %d ms%n", duration);
					writer.append(Long.toString(simpleContainer.size()));
					writer.append(separator);
					writer.append(Long.toString(duration));
					writer.append(separator);
					String perItem = df.format((double)duration/(double)coders.length);
					writer.append(perItem);
					writer.append(separator);
					// ...................
					// Measuring toArray time
					// ...................
					start = System.currentTimeMillis();
					coders = simpleContainer.toArray();
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 4/7: Exporting coders to array it took %d ms%n", duration);
					// .........................................
					// Sorting now, so that can use fast search
					// .........................................
					start = System.currentTimeMillis();
					Comparator<Coder> comparator = new Comparator<>() {
						@Override
						public int compare(Coder first, Coder second) {
							return first.getId().compareTo(second.getId());
						}
					};
					simpleContainer.sort(comparator);
					end = System.currentTimeMillis();
					long totalDuration = duration;
					duration = end - start;
					System.out.format(" Step 5/7: Sorting SimpleContainer took %d ms%n", duration);
					totalDuration += duration;					
					writer.append(Long.toString(totalDuration));
					writer.append(separator);
					start = System.currentTimeMillis();
					for (Coder coder : coders) {
						assertDoesNotThrow(() -> foundIndex = simpleContainer.indexOf(new Coder(coder.getId()), comparator), "hashTable.get(K) must not throw");
						assertTrue(foundIndex >= 0 && foundIndex < simpleContainer.size(), "Must get a valid index from simpleContainer.indexOf");
						assertDoesNotThrow(() -> found = simpleContainer.get(foundIndex), "Getting the Coder by index from SimpleContainer must not throw");
						assertEquals(coder, found, "Found coder must be equal to the searched coder");
					}
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 6/7: Searching SimpleContainer took %d ms%n", duration);
					writer.append(Long.toString(duration));
					writer.append(separator);
					perItem = df.format((double)duration/(double)coders.length);
					writer.append(perItem);
					writer.append(separator);

					// get(index) time
					start = System.currentTimeMillis();
					for (int index = 0; index < simpleContainer.size(); index++) {
						Coder coder = simpleContainer.get(index);
						if (null == coder) {
							assertNotNull(coder, "simpleContainer.get(index) must not return null");
						}
					}
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 7/7: Calling get(int index) for all items took %d ms%n", duration);
					writer.append(Long.toString(duration));
					writer.append(separator);
					perItem = df.format((double)duration/(double)simpleContainer.size());
					writer.append(perItem);
					writer.append(separator);

					writer.append(testFiles[currentIndex]);
					writer.newLine();
					writer.flush();
					currentIndex++;
				}
				System.out.format("<== Test finished, see file %s for statistics!", outputFileName);
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

	private Coder [] readCodersFromFile(String fileName) throws IOException {
		long start = System.currentTimeMillis();
		JSONTokener tokener = new JSONTokener(new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		System.out.format(" Step 1/7: JSON Parsing - from file %s to JSONArray it took %d ms%n", fileName, System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Coder [] coders = JSONConverter.codersFromJSONArray(array);
		System.out.format(" Step 2/7: From JSONArray to Coders array it took %d ms%n", System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		return coders;
	}
}
