package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   /**
    * Vaihtaa first ja second -indekseissä olevien elementtien paikkaa keskenään.
    * 
    * @param <T>
    * @param array
    * @param first
    * @param second
    */
   public static <T> void swap(T[] array, int first, int second) {
      T temp = array[first];
      array[first] = array[second];
      array[second] = temp;
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array, 0, array.length);

      /*
       * int i = 1, j;
       * while (i < array.length) {
       * j = i;
       * while (j > 0 && array[j - 1].compareTo(array[j]) > 0) {
       * swap(array, j, j - 1);
       * j--;
       * }
       * i++;
       * }
       */
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      while (fromIndex < toIndex) {
         int j = fromIndex;
         while (j > 0 && array[j - 1].compareTo(array[j]) > 0) {
            swap(array, j, j - 1);
            j--;
         }
         fromIndex++;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      // TODO: Student, implement this.
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      // TODO: Student, implement this.
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      reverse(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      while (fromIndex < toIndex / 2) {
         int second = toIndex - fromIndex - 1;
         swap(array, fromIndex, second);
         fromIndex++;
      }
   }

   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
