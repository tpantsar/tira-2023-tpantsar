package oy.interact.tira.task_08_hashtable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.factories.HashTableFactory;
import oy.interact.tira.student.Algorithms;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;


@DisplayName("Testing that the implementations are really generic.")
public class GenericTests {

    // Implementations to test:
    static TIRAKeyedContainer<String, Integer> hashTable = null;
    static final int TEST_COUNT = 100;
    static Pair<String, Integer> [] testArray;
    static int testIndex = 0;
    static Integer testValue;
    static boolean testResult;
    static Comparator<String> comparator = new Comparator<>() {
        @Override
        public int compare(String first, String second) {
            return first.compareTo(second);
        }
    };

    @BeforeAll
    static void allocateDataStructure() {
        try {
            hashTable = HashTableFactory.createHashTable();
            if (null == hashTable) {
                fail("HashTableFactory.createHashTable did not instantiate the TIRAKeyedContainer implementation (yet)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("HashTableFactory.createHashTable could not instantiate the TIRAKeyedContainer implementation");
        }
    }

    @Test
    @Order(1)
    // @Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests HashTable by adding, getting and toArray")
    void testHashTable() {
        try {
            if (hashTable == null) {
                System.out.println("======================================================");
                System.out.println("Not testing HashTable yet since it has not been implemented.");
                System.out.println("======================================================");
                fail("HashTableFactory.createHashTable could not instantiate the TIRAKeyedOrderedContainer implementation (yet)");
                return;
            }
            hashTable.clear();
            List<String> randomList = new ArrayList<>();
            for (int index = 0; index < TEST_COUNT; index++) {
                randomList.add(String.valueOf(index));
            }
            Collections.shuffle(randomList);
            for (int index = 0; index < TEST_COUNT; index++) {
                final int findValue = index;
                assertDoesNotThrow(() -> hashTable.add(randomList.get(findValue), Integer.parseInt(randomList.get(findValue))), "HashTable add must not throw");
            }
            System.out.println(">> Testing HashTable with " + hashTable.size() + " entries");
            assertEquals(TEST_COUNT, hashTable.size(), () -> "Inserted count must match");
            for (int index = 0; index < TEST_COUNT; index++) {
                final int findValue = index;
                assertDoesNotThrow( () -> testValue = hashTable.get(String.valueOf(findValue)), "HashTable find must not throw");
                assertEquals(index, testValue, () -> "Inserted and retrieved values must match");
            }
            assertNull(hashTable.get(String.valueOf(TEST_COUNT + 42)), () -> "Must return null when element not in tree");
            assertDoesNotThrow( () -> testArray = hashTable.toArray(), "HashTable toArray must not throw");
            assertNotNull(testArray, () -> "Returned array from toSortedArray must not be null");
            assertEquals(randomList.size(), testArray.length, () -> "Test array and toSortedArray lengths do not match");
            String [] originalArray = new String[randomList.size()];
            int index = 0;
            for (String item : randomList) {
                originalArray[index++] = item;
            }
            Algorithms.fastSort(originalArray);
            String [] keys = new String[testArray.length];
            index = 0;
            for (Pair<String,Integer> pair : testArray) {
                keys[index++] = pair.getKey();
            }
            Algorithms.fastSort(keys);
            for (index = 0; index < TEST_COUNT; index++) {
                assertNotNull(testArray[index], "Array from toSortedArray must not contain null elements");
                assertEquals(originalArray[index], keys[index], () -> "Array elements do not match");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something went wrong in the test." + e.getMessage());
        } finally {
            hashTable.clear();
        }
    }

    @Test
    @Order(2)
    // @Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test that adds identical values")
    void testAddingIdenticalValues() {
        if (hashTable == null) {
            System.out.println("======================================================");
            System.out.println("Not testing HashTable yet since it has not been implemented.");
            System.out.println("======================================================");
            fail("HashTableFactory.createHashTable could not instantiate the TIRAKeyedOrderedContainer implementation (yet)");
            return;
        }
        hashTable.clear();
        String duplicateUUID = UUID.randomUUID().toString();
        String notUsedUUID = UUID.randomUUID().toString();
        assertDoesNotThrow(() -> hashTable.add(duplicateUUID, 21), "Adding to HashTable must not throw");
        assertDoesNotThrow(() -> hashTable.add(duplicateUUID, 42), "Adding to HashTable must not throw");
        assertEquals(1, hashTable.size(), "After adding two identical keys, HashTable size must be one (1)");
        assertDoesNotThrow(() -> testValue = hashTable.find(intValue -> intValue.equals(21)));
        assertNull(testValue, "Value must not be in the dictionary in this test");
        assertDoesNotThrow(() -> testValue = hashTable.get(duplicateUUID), "get(K) should not throw even though key not in HashTable");
        assertNotNull(testValue, "Value for this key must not be null. it was erased by duplicate value, different value");
        assertEquals(42, testValue, "The last value put to hashtable with the same key should be in table");
        assertDoesNotThrow(() -> testValue = hashTable.get(notUsedUUID), "get(K) should not throw when key is in HashTable");
        assertNull(testValue, "Value for this key must not be null");        
        hashTable.clear();
    }

}
