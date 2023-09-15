package oy.interact.tira.factories;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.model.Coder;
import oy.interact.tira.student.ArrayQueue;
import oy.interact.tira.student.LinkedListQueue;
import oy.interact.tira.util.QueueInterface;

public class QueueFactory {

	private QueueFactory() {
		// empty
	}

    // TODO: Teachers - both ArrayQueue and LinkedListQueue have been implemented!
	public static QueueInterface<Integer> createIntegerQueue() {
        //return new ArrayQueue<>();
		return new LinkedListQueue<>();
	}

	public static QueueInterface<Integer> createIntegerQueue(int capacity) {
		return new ArrayQueue<>(capacity);
		//return new LinkedListQueue<>(capacity);
	}

	public static QueueInterface<String> createStringQueue() {
		//return new ArrayQueue<>();
		return new LinkedListQueue<>();
	}

	public static QueueInterface<String> createStringQueue(int capacity) {
		//return new ArrayQueue<>(capacity);
		return new LinkedListQueue<>(capacity);
	}

	public static QueueInterface<Coder> createCoderQueue() {
        //return new ArrayQueue<>();
		return new LinkedListQueue<>();
	}
}
