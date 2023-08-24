package oy.interact.tira.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONTokener;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.util.JSONConverter;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;


public class PhoneBookBST extends PhoneBookBase {

	private TIRAKeyedOrderedContainer<String, Coder> phoneBook;
	private CoderSortOrder currentSortOrder = CoderSortOrder.FULLNAME_ASCENDING;
	private String currentPhoneBookFile = "";

	public PhoneBookBST() {
		super();
		phoneBook = BSTFactory.createBST(getComparatorFromSortOrder());
		doAddTestData();
	}

	@Override
	public String name() {
		return "PhoneBookBST";
	}

	@Override
	public String getCurrentFileName() {
		return currentPhoneBookFile;
	}

	public TIRAKeyedOrderedContainer<String, Coder> getContainer() {
		return phoneBook;
	}

	@Override
	public int indexOfItem(String toSearch, boolean exactKeySearch) {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}
		long start = System.currentTimeMillis();
		if (exactKeySearch) {
			currentSelection = phoneBook.indexOf(toSearch);
		} else {
			currentSelection = phoneBook.findIndex(coder -> coder.toString().toLowerCase().contains(toSearch.toLowerCase()));
		}
		addMeasurement("Search", System.currentTimeMillis() - start);
		notifyObservers(Notification.SELECTION_CHANGED);
		return currentSelection;
	}

	@Override
	public String [] getFriendNames(String [] friendIDs) {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		long start = System.currentTimeMillis();
		String [] names = new String[friendIDs.length];
		int arrayIndex = 0;
		for (String id : friendIDs) {
			Coder found = phoneBook.find(element -> element.getId().equals(id));
			if (null != found) {
				names[arrayIndex++] = found.getFullName();
			}
		}
		addMeasurement("Getting friend names", System.currentTimeMillis() - start);
		return names;
	}

	@Override
	public void loadPhoneBook(File file) throws UnsupportedEncodingException, FileNotFoundException {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}
		long start = System.currentTimeMillis();
		JSONTokener tokener = new JSONTokener(new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		final String msg = String.format("Reading JSON with  %d items", array.length());
		addMeasurement(msg, System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Coder [] coders = JSONConverter.codersFromJSONArray(array);
		addMeasurement("JSON to Coders array", System.currentTimeMillis() - start);
		if (coders.length > 0) {
			phoneBook.clear();
		}
		start = System.currentTimeMillis();
		for (Coder coder : coders) {
			add(coder);
		}
		final String msg2 = String.format("Add to container with  %d items", phoneBook.size());
		addMeasurement(msg2, System.currentTimeMillis() - start);
		doAddTestData();
		currentPhoneBookFile = file.getName();
		notifyObservers(Notification.MODEL_CHANGED);
	}

	@Override
	public int size() {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		return phoneBook.size();
	}
	
	@Override
	public Coder getCoder(int index) {	
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		return phoneBook.getIndex(index).getValue();
	}

	@Override
	public Coder getCoder(int index, boolean measure) {	
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		long start = System.currentTimeMillis();
		Coder coder = getCoder(index);
		if (measure) {
			addMeasurement("Get coder by index", System.currentTimeMillis() - start);
		}
		return coder;
	}

	@Override
	public void sort(CoderSortOrder order) throws Exception {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}
		// Must sort the phonebook via a shuffled array, when sort order changes.
		// Why?!
		// Because when, for example, the coders are sorted by name, ascending, and then
		// if changing the sort order to name, descending, and adding from old
		// BST to new BST, adding to reverse order makes the new BST practically
		// a linked list! Because adding **from ordered collection** makes the Coders to go
		// always to the left (or right) child node. This is then practically a linked list, which is Very Bad!
		// Everything becomes very slow and deep recursive calls cause stack overflows, etc. etc.
		// So, must 1. change the sort order, 2. export data to array (O(n)), 3. shuffle the array (O(n)), and 
		// 4. then add to the new BST with new order (each add is O(log n), so is O(n log n)). Time complexity
		// of the whole operation is thus O(n log n). This is the same as sorting an array to new order using
		// any good sorting algorithm (and in reality, may take longer than sorting). In teacher's tests,
		// changing the sort order with 100 000 coders in BST took between 100-680 ms, while the array based
		// PhoneBookArray took between 60-190 ms.
		if (!currentSortOrder.equals(order)) {
			boolean isReverseOrder = currentSortOrder.isReversed(order);
			long start = System.currentTimeMillis();
			currentSortOrder = order;
			Pair<String,Coder> [] coders = phoneBook.toArray();
			phoneBook = null;
			List<Pair<String,Coder>> list = Arrays.asList(coders);
			if (isReverseOrder) {
				Collections.shuffle(list);
			}
			addMeasurement("BST to array and possibly shuffling the content", System.currentTimeMillis() - start);	
			phoneBook = BSTFactory.createBST(getComparatorFromSortOrder());
			start = System.currentTimeMillis();
			if (list.size() > 0) {
				for (Pair<String,Coder> item : list) {
					add(item.getValue());
				}
			}
			addMeasurement("Refilling the BST phonebook to new sort order", System.currentTimeMillis() - start);
			notifyObservers(Notification.MODEL_CHANGED);
		}
	}

	@Override
	public void clearPhonebook() {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		currentPhoneBookFile = "";
		phoneBook.clear();
		doAddTestData();
		notifyObservers(Notification.MODEL_CHANGED);
	}

	@Override
	public Coder [] toArray() throws Exception {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		long start = System.currentTimeMillis();
		Pair<String,Coder> [] arrayOfPairs = phoneBook.toArray(); 
		Coder [] coderArray = null;
		if (arrayOfPairs.length > 0) {
			coderArray = new Coder[arrayOfPairs.length];
			int index = 0;
			for (Pair<String,Coder> item : arrayOfPairs) {
				coderArray[index++] = item.getValue();
			}
			addMeasurement("BST to Array of Coders", System.currentTimeMillis() - start);	
		} else {
			coderArray = new Coder[0];
		}
		return coderArray;
	}

	private Comparator<String> getComparatorFromSortOrder() {
		switch (currentSortOrder) {
			case FULLNAME_ASCENDING:
			case CODER_NAME_ASCENDING:
				return new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				};
			case FULLNAME_DESCENDING:
			case CODER_NAME_DESCENDING:
				return new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o2.compareTo(o1);
					}
				};
			default:
				return null;
		}
		
	}
	private void add(Coder coder) {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		switch (currentSortOrder) {
			case FULLNAME_ASCENDING:
				phoneBook.add(coder.getFullName(), coder);
				break;
			case FULLNAME_DESCENDING:
				phoneBook.add(coder.getFullName(), coder);
				break;
			case CODER_NAME_ASCENDING:
				phoneBook.add(coder.getCoderName(), coder);
				break;
			case CODER_NAME_DESCENDING:
				phoneBook.add(coder.getCoderName(), coder);
				break;
			default:
				break;
		}
	}

	@Override
	protected void doAddTestData() {
		if (null == phoneBook) {
			System.out.println("Not yet implemented!");
			throw new NotYetImplementedException("Implemented in 07.TASK, does not work yet!");
		}		
		Coder [] testCoders = generateTestData();
		int phoneBookSize = phoneBook.size();
		for (Coder coder: testCoders) {
			if (phoneBookSize > 21) {
				int randomFriendCount = ThreadLocalRandom.current().nextInt(3, 10);
				while (randomFriendCount > 0) {
					Coder random = phoneBook.getIndex(ThreadLocalRandom.current().nextInt(0, phoneBookSize)).getValue();
					random.addFriend(coder.getId());
					randomFriendCount--;
				}
			}
			add(coder);
		}
	}

}
