package oy.interact.tira.student;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import oy.interact.tira.factories.HashTableFactory;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;

public class CodeWordsCounter {

	private TIRAKeyedContainer<String, Integer> codeWords;

	public long cumulativeTimeInMilliseconds;

	private static final int MAX_WORD_SIZE = 4096;

	public CodeWordsCounter() {
		codeWords = HashTableFactory.createHashTable();
	}

	public void countWordsinSourceCodeFiles(File inDirectory) throws IOException {

		if (null == codeWords) {
			System.out.println("No implementation for hashtable, doing nothing.");
			return;
		}
		cumulativeTimeInMilliseconds = 0;
		System.out.println("Started counting, please wait...");

		Files.walkFileTree(inDirectory.toPath(), new SimpleFileVisitor<Path>() {
			PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{c,h,cc,cpp,hpp,java,swift,py,html,css,js}");

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				if (file != null && matcher.matches(file.getFileName())) {
					try {
						countWordsFrom(file.toFile());
					} catch (OutOfMemoryError | IOException e) {
						e.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException e) {
				return FileVisitResult.CONTINUE;
			}

		});
	}

	private void countWordsFrom(File file) throws OutOfMemoryError, IOException {
		String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		// Characters of a single word as Unicode codepoints.
		int[] wordChars = new int[MAX_WORD_SIZE];
		// Index used to fill wordChars array from the string in the file.
		int codeWordIndex = 0;
		System.out.println("File: " + file.getAbsolutePath());
		long start = System.currentTimeMillis();
		for (int index = 0; index < content.length(); index++) {
			// STUDENTS: TODO: Implement this pseudocode to fill the hash table with unique word counts
			// from the source code file.
			// 1. Get a code point at the index from content.
			// 2. If the code point is a letter character...
			//       2.1 Add it to the wordChars array to codeWordIndex and add one to codeWordIndex.
			//    ...else we have a word break char and wordChars contains now a word:
			//       2.2 If the array has 2 or more chars (do not count one char "words")...
			//          2.2.1 Convert the array of chars to String object.
			//          2.2.2 Convert the string to lowercase (we treat "char" and "CHAR" as one "char" word)
			//          2.2.3 Get the word count from the hashtable (word is key, returned value from hashtable is the count)
			//          2.2.4 If we got null, hashtable does not have this word, then...
			//             2.2.4.1 add the word with count 1 to the hashtable; word appears once so far.
			//          2.2.5 ...else, word already appears in hash table, so
			//             2.2.5.1 Add the word to hashtable with count increased by one
			//                     (Remember that adding the same key to hashtable must update the value already in hashtable).
			//       2.3 Reset the codeWordIndex to zero so next new word will start filling the wordChars array from the start.

			int codePoint = content.charAt(index);

			if (Character.isLetter(codePoint)) {
				wordChars[codeWordIndex++] = codePoint;
			} else if (codeWordIndex > 1) {
				// Word break character encountered, check if the array has 2 or more characters
				String word = new String(wordChars, 0, codeWordIndex).toLowerCase();
				Integer wordCount = codeWords.get(word);
				if (wordCount == null) {
					codeWords.add(word, 1);
				} else {
					codeWords.add(word, wordCount + 1);
				}
				// Reset the codeWordIndex to zero so next new word will start filling the wordChars array from the start.
				codeWordIndex = 0;

				// Reset wordChars array for new word
				wordChars = new int[MAX_WORD_SIZE];
			}
		}
		// ^^ STUDENTS: your implementation after the commens.
		cumulativeTimeInMilliseconds += System.currentTimeMillis() - start;
	}

	@SuppressWarnings("unchecked")
	public Pair<String, Integer>[] topCodeWords(int topCount) throws Exception {
		if (null == codeWords) {
			Pair<String, Integer>[] result = new Pair[1];
			result[0] = new Pair<>("Hashtable not implemented yet", 0);
			return result;
		}
		// STUDENTS: Implement this pseudocode to get the top words sorted by frequency of use from hash table.
		// 1. Get, from the hash table, pairs of all words and word counts from hash table to an array.
		// 2. Use your fast sort algorithm to sort the array of pairs by word count, descending (!) order,
		//    so that the word that is most frequent, is the first in the array.
		// 3. Allocate a new array (let's call it result array) of size topCount,
		//        or _smaller_ if the array has _less_ than topCount items.
		//        Let's say the resulting new array size is n.
		// 4. Put the first n items from the array of all pairs to this result array of size n.
		// 5. Return the results array to caller.

		Pair<String, Integer>[] wordsArray = codeWords.toArray();
		CodeWordsComparator wordsComparator = new CodeWordsComparator();
		Algorithms.fastSort(wordsArray, wordsComparator.reversed());

		// Return sorted words array if its size is less than topCount, e.g. size 85 vs. topCount 100
		if (codeWords.size() < topCount) {
			return wordsArray;
		}

		Pair<String, Integer>[] resultArray = new Pair[topCount];
		for (int i = 0; i < topCount; i++) {
			resultArray[i] = wordsArray[i];
		}

		return resultArray;
	}

}
