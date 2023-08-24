package oy.interact.tira.factories;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.model.Coder;
import oy.interact.tira.util.QueueInterface;

public class QueueFactory {

	private QueueFactory() {
		// empty
	}

	public static QueueInterface<Integer> createIntegerQueue() {
		throw new NotYetImplementedException("Task 05-TASK about Queues not yet implemented");
	}

	public static QueueInterface<Integer> createIntegerQueue(int capacity) {
		throw new NotYetImplementedException("Task 05-TASK about Queues not yet implemented");
	}

	public static QueueInterface<String> createStringQueue() {
		throw new NotYetImplementedException("Task 05-TASK about Queues not yet implemented");
	}

	public static QueueInterface<String> createStringQueue(int capacity) {
		throw new NotYetImplementedException("Task 05-TASK about Queues not yet implemented");
	}

	public static QueueInterface<Coder> createCoderQueue() {
		throw new NotYetImplementedException("Task 05-TASK about Queues not yet implemented");
	}
}
