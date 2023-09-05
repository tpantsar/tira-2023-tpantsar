package oy.interact.tira.task_06_fastsort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONTokener;

import oy.interact.tira.model.Coder;
import oy.interact.tira.util.JSONConverter;

public abstract class CoderTestBase {

	Coder[] coderArray;

	static final int[] coderCounts = {
			100,
			1000,
			5000,
			10000,
			50000,
			100000,
			1000000,
			2000000
	};
	static final String[] coderFiles = {
			"100-city-coders.json",
			"1000-area-coders.json",
			"5000-town-coders.json",
			"10000-large-city-coders.json",
			"50000-country-coders.json",
			"100000-europe-coders.json",
			"1000000-global-coders.json",
			"2000000-milky-way-orion-spur-coders.json"
	};

	static int currentFileIndex = 0;

	Coder[] loadCoders(File fromFile) throws FileNotFoundException {
		JSONTokener tokener = new JSONTokener(
				new BufferedReader(new InputStreamReader(new FileInputStream(new File(coderFiles[currentFileIndex])),
						StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		return JSONConverter.codersFromJSONArray(array);
	}

	void isInOrder(Coder[] array, Comparator<Coder> order) throws ArrayStoreException, NullPointerException {
		for (int index = 1; index < array.length; index++) {
			if (array[index - 1] == null || array[index] == null) {
				throw new NullPointerException("Null found in array");
			}
			if (order.compare(array[index - 1], array[index]) > 0) {
				throw new ArrayStoreException();
			}
		}
	}

	void isInOrder(Coder[] array) throws ArrayStoreException, NullPointerException {
		for (int index = 1; index < array.length; index++) {
			if (array[index - 1] == null || array[index] == null) {
				throw new NullPointerException("Null found in array");
			}
			if (array[index - 1].compareTo(array[index]) > 0) {
				throw new ArrayStoreException();
			}
		}
	}

	void isInOrder(Coder[] array, int from, int to, Comparator<Coder> order)
			throws ArrayStoreException, NullPointerException {
		for (int index = from + 1; index < to; index++) {
			if (array[index - 1] == null || array[index] == null) {
				System.out.format("*** ERROR null in array in %d or %d%n", index - 1, index);
				throw new NullPointerException("Null found in array");
			}
			if (order.compare(array[index - 1], array[index]) > 0) {
				System.out.format("*** ERROR wrong order in array in %d or %d%n", index - 1, index);
				throw new ArrayStoreException();
			}
		}
	}

}
