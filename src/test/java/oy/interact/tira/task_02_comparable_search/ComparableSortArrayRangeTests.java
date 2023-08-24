package oy.interact.tira.task_02_comparable_search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.student.Algorithms;

public class ComparableSortArrayRangeTests {

   private static final int ARRAY_SIZE = 11;
   private static final int NUMBER_OF_NULLS = 5;
   private static final int ELEMENT_COUNT = ARRAY_SIZE - NUMBER_OF_NULLS;

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with ascending Comparator (Integer)")
   void sortTestIntegerAscending() {
      Integer[] testArray = getArrayWithNumbers(ARRAY_SIZE, NUMBER_OF_NULLS);

      Integer[] verifyArray = new Integer[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1; counter >= 0; counter--) {
         verifyArray[counter] = testArray[counter];
      }
      Arrays.sort(verifyArray);

      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.insertionSort(testArray, 0, ELEMENT_COUNT, new Comparator<Integer>() {
         @Override
         public int compare(Integer first, Integer second) {
            return first.compareTo(second);
         }
      });
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(verifyArray, Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with ascending Comparator (String)")
   void sortTestStringAscending() {
      String[] testArray = getArrayWithStrings(ARRAY_SIZE, NUMBER_OF_NULLS);

      String[] verifyArray = new String[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1; counter >= 0; counter--) {
         verifyArray[counter] = testArray[counter];
      }
      Arrays.sort(verifyArray);

      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.insertionSort(testArray, 0, ELEMENT_COUNT, new Comparator<String>() {
         @Override
         public int compare(String first, String second) {
            return first.compareTo(second);
         }
      });
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(verifyArray, Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

 
   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with ascending Comparator (Integer)")
   void sortTestIntegerDescending() {
      Integer[] testArray = getArrayWithNumbers(ARRAY_SIZE, NUMBER_OF_NULLS);

      Integer[] verifyArray = new Integer[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1; counter >= 0; counter--) {
         verifyArray[counter] = testArray[counter];
      }

      Comparator<Integer> reverseComparator = new Comparator<Integer>() {
         @Override
         public int compare(Integer first, Integer second) {
            return first.compareTo(second);
         }
      }.reversed();
      Arrays.sort(verifyArray, reverseComparator);
      
      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.insertionSort(testArray, 0, ELEMENT_COUNT, reverseComparator);
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(verifyArray, Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with ascending Comparator (String)")
   void sortTestStringDescending() {
      String[] testArray = getArrayWithStrings(ARRAY_SIZE, NUMBER_OF_NULLS);

      String[] verifyArray = new String[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1; counter >= 0; counter--) {
         verifyArray[counter] = testArray[counter];
      }
      Comparator<String> reverseComparator = new Comparator<String>() {
         @Override
         public int compare(String first, String second) {
            return first.compareTo(second);
         }
      }.reversed();
      Arrays.sort(verifyArray, reverseComparator);

      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.insertionSort(testArray, 0, ELEMENT_COUNT, reverseComparator);
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(verifyArray, Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

   private Integer[] getArrayWithNumbers(int size, int countofElementsNullAtEnd) {
      Integer[] array = new Integer[size];
      for (int i = 0; i < size - 5; i++) {
         array[i] = ThreadLocalRandom.current().nextInt(10);
      }
      return array;
   }

   private String[] getArrayWithStrings(int size, int countofElementsNullAtEnd) {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = 5;
      final Random random = new Random();

      String[] array = new String[size];

      for (int i = 0; i < size - countofElementsNullAtEnd; i++) {
         String generatedString = random.ints(leftLimit, rightLimit + 1)
               .filter(number -> (number <= 57 || number >= 65) && (number <= 90 || number >= 97))
               .limit(targetStringLength)
               .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
               .toString();
         array[i] = generatedString;
      }

      return array;
   }

}
