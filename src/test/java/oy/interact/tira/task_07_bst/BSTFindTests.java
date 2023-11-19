package oy.interact.tira.task_07_bst;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.model.Coder;
import oy.interact.tira.util.JSONConverter;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Find tests for BST")
class BSTFindTests {

    private static final String testFile = "100-city-coders.json";

    private static Coder found = null;
    private static Pair<String, Coder>[] coders2 = null;
    private static TIRAKeyedOrderedContainer<String, Coder> fastBST = null;

    @Test
    @Order(1)
        // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void findWithBST() {
        try {
            System.out.println("Starting BSTFindTests...");
            Comparator<String> strAscComparator = new Comparator<>() {
                @Override
                public int compare(String first, String second) {
                    return first.compareTo(second);
                }
            };
            Comparator<String> strDescComparator = new Comparator<>() {
                @Override
                public int compare(String first, String second) {
                    return second.compareTo(first);
                }
            };
            Comparator<Coder> coderDescComparator = new Comparator<>() {
                @Override
                public int compare(Coder first, Coder second) {
                    return second.getFullName().compareTo(first.getFullName());
                }
            };
            Comparator<Coder> wuTangAscComparator = new Comparator<>() {
                @Override
                public int compare(Coder first, Coder second) {
                    return first.getCoderName().compareTo(second.getCoderName());
                }
            };
            System.out.println("Creating BST and adding to it...");
            fastBST = BSTFactory.createBST(strDescComparator);
            assertNotNull(fastBST,
                    () -> "BSTFactory.createBST() returns null, not yet implemented?");
            Coder[] coders = readCodersFromFile(testFile);
            for (Coder coder : coders) {
                fastBST.add(coder.getFullName(), coder);
            }
            assertEquals(coders.length, fastBST.size(), "Coder array length and BST.size do not match");

            System.out.println("Testing BST.get(K)");
            for (Coder coder : coders) {
                assertDoesNotThrow(() -> found = fastBST.get(coder.getFullName()), "bst.get(K) must not throw");
                assertEquals(coder, found, "Found coder must be equal to the searched coder");
            }
            Arrays.sort(coders, coderDescComparator);
            System.out.println("Testing BST.toArray...");
            assertDoesNotThrow(() -> coders2 = fastBST.toArray(), "");
            assertNotNull(coders2, "Coders array from BST.toArray must not be null");
            assertEquals(coders.length, coders2.length, "Count of coders from JSON and from BST.toArray should be equal");
            for (int index = 0; index < coders.length; index++) {
                assertEquals(coders[index].getFullName(), coders2[index].getValue().getFullName(), "Coders in BST.toArray in wrong order");
            }
            System.out.println("Testing BST.find(Predicate<V>)...");
            for (int index = 0; index < coders.length; index++) {
                final String id = coders[index].getId();
                assertDoesNotThrow(() -> found = fastBST.find(coder -> coder.getId().equals(id)), "BST.find must not throw");
                assertNotNull(found, "Coder must be found by id from BST using predicate");
                assertEquals(id, found.getId(), "The coder found with find(Predicate<V>) is not the correct one");
            }
            System.out.println("Testing BST with Wu Tang name as key...");
            fastBST.clear();
            fastBST = BSTFactory.createBST(strAscComparator);
            for (Coder coder : coders) {
                fastBST.add(coder.getCoderName(), coder);
            }
            assertEquals(coders.length, fastBST.size(), "Coder array length and BST.size do not match");
            Arrays.sort(coders, wuTangAscComparator);
            System.out.println("Testing BST.toArray...");
            assertDoesNotThrow(() -> coders2 = fastBST.toArray(), "");
            assertNotNull(coders2, "Coders array from BST.toArray must not be null");
            assertEquals(coders.length, coders2.length, "Count of coders from JSON and from BST.toArray should be equal");
            for (int index = 0; index < coders.length; index++) {
                assertEquals(coders[index].getCoderName(), coders2[index].getValue().getCoderName(), "Coders in BST.toArray in wrong order");
            }
            System.out.println("Tests finished OK");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could execute test: " + e.getMessage());
        }
    }

    private Coder[] readCodersFromFile(String fileName)
            throws IOException {
        long start = System.currentTimeMillis();
        JSONTokener tokener = new JSONTokener(
                new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)));
        JSONArray array = new JSONArray(tokener);
        System.out.format("JSON Parsing - from file %s to JSONArray it took %d ms%n", fileName,
                System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        Coder[] coders = JSONConverter.codersFromJSONArray(array);
        System.out.format("From JSONArray to Coders array it took %d ms%n",
                System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        return coders;
    }
}
