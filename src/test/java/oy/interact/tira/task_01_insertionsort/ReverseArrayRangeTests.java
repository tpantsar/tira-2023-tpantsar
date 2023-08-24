package oy.interact.tira.task_01_insertionsort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import oy.interact.tira.student.Algorithms;

public class ReverseArrayRangeTests {

   private static final int ARRAY_SIZE = 11;
   private static final int NUMBER_OF_NULLS = 5;
   private static final int ELEMENT_COUNT = ARRAY_SIZE - NUMBER_OF_NULLS;

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic reverse() with integers")
   void reverseTestInteger() {
      Integer[] testArray = getArrayWithNumbers(ARRAY_SIZE, NUMBER_OF_NULLS);
      Integer[] verifyArray = new Integer[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1, index = 0; counter >= 0 && index < ELEMENT_COUNT; counter--, index++) {
         verifyArray[counter] = testArray[index];
      }
      System.out.format("==> Reverse test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.reverse(testArray, 0, ELEMENT_COUNT);
      System.out.println("Reversed:  " + Arrays.toString(testArray));
      assertEquals(Arrays.toString(verifyArray), Arrays.toString(Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            "Algorithms.reverse does not work as expected");
      System.out.println("-- Reverse test finished");
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic reverse() with strings")
   void reverseTestString() {
      String[] testArray = getArrayWithStrings(ARRAY_SIZE, NUMBER_OF_NULLS);

      String[] verifyArray = new String[ELEMENT_COUNT];
      for (int counter = ELEMENT_COUNT - 1, index = 0; counter >= 0 && index < ELEMENT_COUNT; counter--, index++) {
         verifyArray[counter] = testArray[index];
      }

      System.out.format("==> Reverse test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.reverse(testArray, 0, ELEMENT_COUNT);
      System.out.println("Reversed:  " + Arrays.toString(testArray));
      assertEquals(Arrays.toString(verifyArray), Arrays.toString(Arrays.copyOfRange(testArray, 0, ELEMENT_COUNT)),
            "Algorithms.reverse does not work as expected");
      System.out.println("-- Reverse test finished");
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
