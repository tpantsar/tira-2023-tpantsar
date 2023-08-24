package oy.interact.tira.model;

import java.util.List;
import java.util.Set;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.factories.QueueFactory;
import oy.interact.tira.factories.StackFactory;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.ParenthesesException;
import oy.interact.tira.student.ParenthesisChecker;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.tools.WuTangNameGenerator;
import oy.interact.tira.util.QueueInterface;
import oy.interact.tira.util.StackInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class PhoneBookBase {
	public abstract String name();
	public abstract String getCurrentFileName();
	public abstract int indexOfItem(String toSearch, boolean exactKeySearch);
	public abstract String [] getFriendNames(String [] friendIDs);
	
	public abstract int size();
	public abstract Coder getCoder(int index);
	public abstract Coder getCoder(int index, boolean measure);
	public abstract void sort(CoderSortOrder order) throws Exception;
	public abstract void clearPhonebook();
	public abstract Coder [] toArray() throws Exception;

	public abstract void loadPhoneBook(File file) throws UnsupportedEncodingException, FileNotFoundException;	
	protected abstract void doAddTestData(); 
	
	protected List<PhoneBookModelObserver> observers = new ArrayList<>();
	protected MeasurementsObserver measurementsObserver = null;
	protected int currentSelection = -1;

	private Coder [] testData;

	public void addObserver(PhoneBookModelObserver observer) {
		observers.add(observer);
	}

	public void setMeasurementsObserver(MeasurementsObserver observer) {
		measurementsObserver = observer;
	}

	public void removeObserver(PhoneBookModelObserver observer) {
		observers.remove(observer);
	}

	public void moveObservers(PhoneBookBase toAnotherPhonebook) {
		for (PhoneBookModelObserver observer : observers) {
			toAnotherPhonebook.addObserver(observer);
		}
		observers.clear();
		toAnotherPhonebook.measurementsObserver = measurementsObserver;
		measurementsObserver = null;
	}

	public enum Notification {
		MODEL_CHANGED,
		SELECTION_CHANGED;
	}

	public void notifyObservers(Notification notification) {
		switch (notification) {
			case MODEL_CHANGED:
				for (PhoneBookModelObserver observer : observers) {
					observer.modelChanged();
				}
				break;
			case SELECTION_CHANGED:
				for (PhoneBookModelObserver observer : observers) {
					observer.selectionChanged(currentSelection);
				}
				break;
			default:
				break;
		}
	}

	public void addMeasurement(String item, long duration) {
		if (null != measurementsObserver) {
			System.out.format("[measurement] %-40s %5d%n", item, duration);
			measurementsObserver.newMeasurement(new DurationMeasurement(name() + ": " + item, duration));
		}
	}

	protected Coder coderFromSearchString(final String toSearch) {
		Coder coder = null;
		int firstSpace = toSearch.indexOf(' ', 0);
		if (firstSpace >= 0) {
			String lastName = toSearch.substring(0, firstSpace).trim();
			String firstName = toSearch.substring(firstSpace+1).trim();
			coder = new Coder(firstName, lastName, "");
		}
		return coder;
	}

	public void checkJSONFile(File selectedFile) throws IOException, ParenthesesException {
		String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
		StackInterface<Character> charStack = StackFactory.createCharacterStack();
		if (null != charStack) {
			ParenthesisChecker.checkParentheses(charStack, content);
		}
	}

	public QueueInterface<Coder> getCoders(String knowingProgrammingLanguage) throws Exception {
		QueueInterface<Coder> queue = QueueFactory.createCoderQueue();
		if (null != queue) {
			Coder [] array = toArray();
			if (array.length > 0) {
				for (Coder coder : array) {
					if (coder.knowsLanguage(knowingProgrammingLanguage)) {
						queue.enqueue(coder);
					}
				}	
			}
		}
		return queue;
	}

	protected Coder [] generateTestData() {
		final int TEST_CODERS = 3;
		if (null == testData) {
			testData = new Coder[TEST_CODERS];
			int index = 0;
			Coder antti = new Coder("Antti", "Juustila", "358-40-123 4567");
			antti.setCoderName(WuTangNameGenerator.generateRandomProgrammerName());
			antti.addLanguage("C");
			antti.addLanguage("C++");
			antti.addLanguage("Java");
			antti.addLanguage("Swift");
			Coder jouni = new Coder("Jouni", "Lappalainen", "358-45-987 6543");
			antti.addFriend(jouni.getId());
			testData[index++] = antti;
			jouni.setCoderName(WuTangNameGenerator.generateRandomProgrammerName());
			jouni.addLanguage("C");
			jouni.addLanguage("Java");
			jouni.addLanguage("PHP");
			jouni.addLanguage("JavaScript");
			jouni.addFriend(antti.getId());
			testData[index++] = jouni;
			// https://en.wikipedia.org/wiki/Six_Degrees_of_Kevin_Bacon
			Coder kevin = new Coder("Kevin", "Bacon", "01-555-53846 22266");
			kevin.addLanguage("Java");
			antti.addFriend(kevin.getId());
			jouni.addFriend(kevin.getId());
			kevin.addFriend(antti.getId());
			kevin.addFriend(jouni.getId());
			kevin.setCoderName(WuTangNameGenerator.generateRandomProgrammerName());
			testData[index++] = kevin;
		} 
		return testData;
	}

	public Graph<Coder> createGraph() throws Exception {
		Coder [] coders = toArray();
		Graph<Coder> graph = null;
		if (coders.length > 0) {
			graph = new Graph<>();
			long start = System.currentTimeMillis();
			for (Coder coder : coders) {
				Vertex<Coder> vertex = graph.createVertexFor(coder);
				if (null == vertex) {
					throw new NotYetImplementedException("Graph not yet implemented or bug in implementation!");
				}
			}
			addMeasurement("Graph: added coders as vertices, now do edges...", System.currentTimeMillis() - start);
			start = System.currentTimeMillis();
			for (Coder coder : coders) {
				Vertex<Coder> coderVertex = graph.getVertexFor(coder);
				Set<String> friendIDs = coder.getFriendIDs();
				if (null != friendIDs && !friendIDs.isEmpty()) {
					for (String friendID : friendIDs) {
						Vertex<Coder> friend = graph.getVertexFor(new Coder(friendID));
						graph.addDirectedEdge(coderVertex, friend, 1.0);
					}
				}
			}
			addMeasurement("Graph: added edges between friends", System.currentTimeMillis() - start);	
		}
		return graph;
	}

}
