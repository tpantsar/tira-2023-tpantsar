package oy.interact.tira.factories;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.student.StackImplementation;
import oy.interact.tira.util.StackInterface;

public class StackFactory {
	private StackFactory() {
		// Empty
	}

	public static StackInterface<Integer> createIntegerStack() {
		return new StackImplementation<>();
	}

	public static StackInterface<Integer> createIntegerStack(int capacity) {
		return new StackImplementation<>(capacity);
	}

	public static StackInterface<Character> createCharacterStack() {
		return new StackImplementation<>();
	}

	public static StackInterface<Character> createCharacterStack(int capacity) {
		return new StackImplementation<>(capacity);
	}

}
