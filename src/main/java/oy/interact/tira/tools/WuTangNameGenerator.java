package oy.interact.tira.tools;

import java.util.concurrent.ThreadLocalRandom;

public class WuTangNameGenerator {

	private static final String[] firstNames = { "Hex", "⦶ctal", "Bōōlean", "Linεar", "$tatic", "Global", "∧bstract",
																"∫inal", "0verridden", "Private", "ℙrotected", "Parallel", "Ⓒorrupt",
																"Infinite∞", "Ðisconnected", "R∀ndom", "Quadrati⪽", "Ïnvariant", "Qubic",
																"Artificial"};
	private static final String[] lastNames = { "∑tack", "Ĥeap", "Lõõp", "Re¢ursion", "Øverflow", "3reakpoint",
															  "Warning", "1nstance", "De🪳ger", "βyte", "Package", "Terminal",
															  "Thread", "Process", "Packet", "Mütex", "Compiler", "Rege𝒳", "⨃nderflow",
															  "⟐verflow", "Mutex", "Semaphore", "Multiplexer", "Flag", "Package"};

	public static int possibleCombinations() {
		return firstNames.length * lastNames.length;
	}

	public static String generateRandomProgrammerName() {
		StringBuilder builder = new StringBuilder();
		int index = ThreadLocalRandom.current().nextInt(firstNames.length);
		builder.append(firstNames[index]);
		builder.append(" ");
		index = ThreadLocalRandom.current().nextInt(lastNames.length);
		builder.append(lastNames[index]);
		return builder.toString();
	}

}
