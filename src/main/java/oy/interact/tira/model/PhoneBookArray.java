package oy.interact.tira.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONTokener;

import oy.interact.tira.util.JSONConverter;
import oy.interact.tira.util.SimpleContainer;
import oy.interact.tira.util.TIRAContainer;

public class PhoneBookArray extends PhoneBookBase {

	private TIRAContainer<Coder> phoneBook;
	private CoderSortOrder currentSortOrder = CoderSortOrder.FULLNAME_ASCENDING;
	private String currentFileName = "";

	public PhoneBookArray() {
		super();
		phoneBook = new SimpleContainer<>(Coder.class);
		doAddTestData();
		sort(currentSortOrder);
	}

	@Override
	public String name() {
		return "PhoneBookArray";
	}

	@Override
	public String getCurrentFileName() {
		return currentFileName;
	}

	@Override
	public int indexOfItem(String toSearch, boolean exactKeySearch) {
		if (exactKeySearch) {
			Coder toFind = coderFromSearchString(toSearch);
			Comparator<Coder> comparator = currentSortOrder.getComparator();
			if (null != toFind && null != comparator) {
				long start = System.currentTimeMillis();
				currentSelection = phoneBook.indexOf(toFind, comparator);
				addMeasurement("Fast search", System.currentTimeMillis() - start);
			} else {
				System.out.println("Not enough data to search or no implementations of Coder comparators");
			}
		} else {
			long start = System.currentTimeMillis();
			currentSelection = phoneBook
					.findIndex(coder -> (coder.toString().toLowerCase().contains(toSearch.toLowerCase())));
			addMeasurement("Search", System.currentTimeMillis() - start);
		}
		notifyObservers(Notification.SELECTION_CHANGED);
		return currentSelection;
	}

	@Override
	public String[] getFriendNames(String[] friendIDs) {
		long start = System.currentTimeMillis();
		String[] names = new String[friendIDs.length];
		int nameIndex = 0;
		for (String id : friendIDs) {
			Coder found = phoneBook.get(new Coder(id));
			if (null != found) {
				names[nameIndex++] = found.getFullName();
			}
		}
		addMeasurement("Getting friend names", System.currentTimeMillis() - start);
		return names;
	}

	@Override
	public void loadPhoneBook(File file) throws UnsupportedEncodingException, FileNotFoundException {
		long start = System.currentTimeMillis();
		JSONTokener tokener = new JSONTokener(
				new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		final String msg = String.format("Reading JSON with  %d items", array.length());
		addMeasurement(msg, System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Coder[] coders = JSONConverter.codersFromJSONArray(array);
		addMeasurement("JSON to Coders", System.currentTimeMillis() - start);
		if (coders.length > 0) {
			phoneBook.clear();
		}
		start = System.currentTimeMillis();
		for (Coder coder : coders) {
			phoneBook.add(coder);
		}
		doAddTestData();
		sort(currentSortOrder);
		addMeasurement("Added to container & Sorted", System.currentTimeMillis() - start);
		currentFileName = file.getName();
		notifyObservers(Notification.MODEL_CHANGED);
	}

	@Override
	public int size() {
		return phoneBook.size();
	}

	@Override
	public Coder getCoder(int index) {
		return phoneBook.get(index);
	}

	@Override
	public Coder getCoder(int index, boolean measure) {
		long start = System.currentTimeMillis();
		Coder coder = phoneBook.get(index);
		if (measure) {
			addMeasurement("Get coder by index", System.currentTimeMillis() - start);
		}
		return coder;
	}

	@Override
	public void sort(CoderSortOrder order) {
		if (!phoneBook.isSorted() || !currentSortOrder.equals(order)) {
			CoderSortOrder oldOrder = currentSortOrder;
			currentSortOrder = order;
			Comparator<Coder> comparator = currentSortOrder.getComparator();
			if (null != comparator) {
				if (oldOrder.isReversed(order)) {
					long start = System.currentTimeMillis();
					phoneBook.reverse();
					addMeasurement("Reversing", System.currentTimeMillis() - start);
				} else {
					long start = System.currentTimeMillis();
					phoneBook.sort(comparator);
					addMeasurement("Sorting", System.currentTimeMillis() - start);
				}
			} else {
				System.out.println("Comparators not yet implemented, sorting in natural order by full name");
				long start = System.currentTimeMillis();
				phoneBook.sort();
				addMeasurement("Sorting", System.currentTimeMillis() - start);
			}
			currentSortOrder = order;
			notifyObservers(Notification.MODEL_CHANGED);
		}
	}

	@Override
	public void clearPhonebook() {
		phoneBook.clear();
		doAddTestData();
		notifyObservers(Notification.MODEL_CHANGED);
	}

	@Override
	public Coder[] toArray() {
		long start = System.currentTimeMillis();
		Coder[] array = phoneBook.toArray();
		addMeasurement("Array Phonebook to Coder array", System.currentTimeMillis() - start);
		return array;
	}

	@Override
	protected void doAddTestData() {
		Coder[] testCoders = generateTestData();
		int phoneBookSize = phoneBook.size();
		for (Coder coder : testCoders) {
			if (phoneBookSize > 21) {
				int randomFriendCount = ThreadLocalRandom.current().nextInt(2, 5);
				while (randomFriendCount > 0) {
					Coder random = phoneBook.get(ThreadLocalRandom.current().nextInt(0, phoneBookSize));
					random.addFriend(coder.getId());
					randomFriendCount--;
				}
			}
			phoneBook.add(coder);
		}
	}

}
