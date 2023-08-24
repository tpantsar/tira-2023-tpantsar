package oy.interact.tira.task_04_stack;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.factories.StackFactory;
import oy.interact.tira.student.ParenthesesException;
import oy.interact.tira.student.ParenthesisChecker;
import oy.interact.tira.util.StackInterface;

/**
 * Tests for checking if structured parenthesis in a string match correctly.
 * 
 */
@DisplayName("Testing the stack with two structured files with parentheses.")
public class ParenthesisTests {

   static int result = 0;

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Very simple test with correct parentheses.")
   void simpleCorrectParenthesisTest() {
      try {
         String toCheck = "{ ( [ ] ) }";
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertDoesNotThrow(() -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "String \"{ ( [ ] ) }\" is valid so must not throw");
         assertEquals(6, result, () -> "Parentheses count did not match with expected.");
         assertTrue(stackToTest.isEmpty(), () -> "Stack must be empty when finished.");
      } catch (Exception e) {
         fail("Unexpected exception in test: " + e.getMessage());
      }
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Very simple test with incorrect parentheses.")
   void simpleIncorrectParenthesisTest1() {
      try {
         String toCheck = "{ ( { ] ) }";
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertThrows(ParenthesesException.class,
               () -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "String \"{ ( { ] ) }\" is invalid so checker must throw");
      } catch (Exception e) {
         fail("Unexpected exception in test: " + e.getMessage());
      }
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Very simple test with incorrect parentheses.")
   void simpleIncorrectParenthesisTestExceptionData() {
      String toCheck = "{ ( { ] ) }";
      StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
      assertNotNull(stackToTest, "StackFactory failed to create the stack object");
      try {
         ParenthesisChecker.checkParentheses(stackToTest, toCheck);
      } catch (ParenthesesException e) {
         // Must arrive here since exception must be thrown.
         assertEquals(1, e.lineNumber, "Error here is on line 1 but exception had something else");
         assertEquals(7, e.columnNumber, "Error here is on column but exception had something else");
         assertEquals(ParenthesesException.PARENTHESES_IN_WRONG_ORDER, e.code,
               "Error code must be ParenthesesException.PARENTHESES_IN_WRONG_ORDER");
      } catch (Exception others) {
         fail("Must throw ParenthesisExceptions from checkParentheses when parentheses are wrong");
      }
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Very simple test with incorrect parentheses.")
   void simpleIncorrectParenthesisTest2() {
      try {
         String toCheck = "{ ( [ ] } )";
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertThrows(ParenthesesException.class,
               () -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "String \"{ ( { ] ) }\" is invalid so checker must throw");
      } catch (Exception e) {
         fail("Unexpected exception in test: " + e.getMessage());
      }
   }

   /**
    * This test should not fail since the source file {@code SSN.java} is correct.
    */
   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Java file with correct parentheses, should pass the test.")
   void correctJavaParenthesisTest() {
      try {
         String toCheck;
         toCheck = new String(getClass().getClassLoader().getResourceAsStream("SSN.java").readAllBytes());
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertDoesNotThrow(() -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "SSN.java is valid so must not throw");
         assertEquals(70, result, () -> "Parentheses count did not match with expected.");
         assertTrue(stackToTest.isEmpty(), () -> "Stack must be empty when finished.");
      } catch (IOException e) {
         fail("Cannot read the test file " + e.getMessage());
      }
   }

   /**
    * This test must fail since the file to analyse {@code Person.json} is not
    * valid JSON.
    */
   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("JSON file with too many closing parentheses should fail the test.")
   void incorrectJSONParenthesisTest1() {
      try {
         String toCheck;
         toCheck = new String(getClass().getClassLoader().getResourceAsStream("Person.json").readAllBytes());
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertThrows(ParenthesesException.class,
               () -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "Person.json is invalid JSON so must throw");
      } catch (IOException e) {
         fail("Cannot read the test file " + e.getMessage());
      }
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("JSON file with too many closing parentheses should fail the test.")
   void incorrectJSONParenthesisTestExceptionData() {

      try {
         String toCheck = new String(getClass().getClassLoader().getResourceAsStream("Person.json").readAllBytes());
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         ParenthesisChecker.checkParentheses(stackToTest, toCheck);
      } catch (ParenthesesException e) {
         // Must arrive here since exception must be thrown.
         System.out.println("Correctly threw: " + e.toString());
         assertEquals(25, e.lineNumber, "Error here is on line 1 but exception had something else");
         assertEquals(1, e.columnNumber, "Error here is on column but exception had something else");
         assertEquals(ParenthesesException.TOO_MANY_CLOSING_PARENTHESES, e.code,
               "Error code must be ParenthesesException.PARENTHESES_IN_WRONG_ORDER");
      } catch (Exception others) {
         System.out.println("Exception thrown: " + others.getMessage());
         fail("Must throw ParenthesisExceptions from checkParentheses when parentheses are wrong");         
      }
   }

   @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("JSON file with too many opening parentheses should fail the test.")
   void incorrectJSONParenthesisTest2() {
      try {
         String toCheck;
         toCheck = new String(getClass().getClassLoader().getResourceAsStream("Person2.json").readAllBytes());
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         assertThrows(ParenthesesException.class,
               () -> result = ParenthesisChecker.checkParentheses(stackToTest, toCheck),
               "Person2.json is invalid JSON so must throw");
      } catch (IOException e) {
         fail("Cannot read the test file " + e.getMessage());
      }
   }

      @Test
   // @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("JSON file with too many closing parentheses should fail the test.")
   void incorrectJSONParenthesisTest2ExceptionData() {

      try {
         String toCheck = new String(getClass().getClassLoader().getResourceAsStream("Person2.json").readAllBytes());
         StackInterface<Character> stackToTest = StackFactory.createCharacterStack();
         assertNotNull(stackToTest, "StackFactory failed to create the stack object");
         ParenthesisChecker.checkParentheses(stackToTest, toCheck);
      } catch (ParenthesesException e) {
         // Must arrive here since exception must be thrown.
         System.out.println("Correctly threw: " + e.toString());
         assertEquals(25, e.lineNumber, "Error here is on line 25 but exception had something else");
         assertEquals(ParenthesesException.TOO_MANY_OPENING_PARENTHESES, e.code,
               "Error code must be ParenthesesException.TOO_MANY_OPENING_PARENTHESES");
      } catch (Exception others) {
         System.out.println("Exception thrown: " + others.getMessage());
         fail("Must throw ParenthesisExceptions from checkParentheses when parentheses are wrong");         
      }
   }

}
