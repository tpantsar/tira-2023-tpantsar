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
      insertionSort(array, 0, array.length, comparator);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      while (fromIndex < toIndex) {
         int j = fromIndex;
         while (j > 0 && comparator.compare(array[j - 1], array[j]) > 0) {
            swap(array, j, j - 1);
            j--;
         }
         fromIndex++;
      }
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
      if (fromIndex < 0 || fromIndex >= fromArray.length || fromIndex > toIndex) {
         return -1; // Invalid input range
      }

      if (fromIndex == toIndex) {
         if (fromArray[fromIndex].compareTo(aValue) == 0) {
            return fromIndex;
         } else {
            return -1; // Index not found
         }
      } else {
         int middle = fromIndex + (toIndex - fromIndex) / 2;
         if (aValue.compareTo(fromArray[middle]) <= 0) {
            return binarySearch(aValue, fromArray, fromIndex, middle); // recursion, low end
         } else {
            return binarySearch(aValue, fromArray, middle + 1, toIndex); // recursion, high end
         }
      }
   }

   ///////////////////////////////////////////
   // Recursive Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearchRecursive(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      if (fromIndex < 0 || fromIndex >= fromArray.length || fromIndex > toIndex) {
         return -1; // Invalid input range
      }

      if (fromIndex == toIndex) {
         if (comparator.compare(fromArray[fromIndex], aValue) == 0) {
            return fromIndex;
         } else {
            return -1; // Value not found
         }
      } else {
         int middle = fromIndex + (toIndex - fromIndex) / 2;
         if (comparator.compare(aValue, fromArray[middle]) == 0) {
            return binarySearchRecursive(aValue, fromArray, fromIndex, middle, comparator); // recursion, low end
         } else {
            return binarySearchRecursive(aValue, fromArray, middle + 1, toIndex, comparator); // recursion, high end
         }
      }
   }

   ///////////////////////////////////////////
   // Iterative Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearchIterative(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      if (fromIndex < 0 || fromIndex >= fromArray.length || fromIndex > toIndex) {
         return -1; // Invalid input range
      }

      while (fromIndex <= toIndex) {
         int middle = fromIndex + (toIndex - fromIndex) / 2;
         int comparison = comparator.compare(aValue, fromArray[middle]);

         if (comparison == 0) {
            return middle;
         } else if (comparison < 0) {
            toIndex = middle - 1; // iteration, low end
         } else {
            fromIndex = middle + 1; // iteration, high end
         }
      }
      return -1; // Value not found
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
