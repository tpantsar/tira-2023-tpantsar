package oy.interact.tira.student;

/**
 * Exception class to be used in implementing ParenthesisChecker.checkParentheses().
 * 
 * When your code sees that the text to analyse has too many closing parentheses,
 * throw this exeption like this:
 * 
 * <code>
 * throw new ParenthesesException("Invalid parenthesis in file", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES) ;
 * </code>
*/
public class ParenthesesException extends Exception {
   private static final long serialVersionUID = 1426141947123353829L;
   public static final int TOO_MANY_CLOSING_PARENTHESES = -1;
   public static final int TOO_MANY_OPENING_PARENTHESES = -2;
   public static final int PARENTHESES_IN_WRONG_ORDER = -3;
   public static final int STACK_FAILURE = -4;

   public final int code;
   public final int lineNumber;
   public final int columnNumber;

   public ParenthesesException(String message, int lineNumber, int columnNumber, int code) {
      super(message);
      this.code = code;
      this.lineNumber = lineNumber;
      this.columnNumber = columnNumber;
   }

   private String codeAsString() {
      if (code == TOO_MANY_CLOSING_PARENTHESES) {
         return "Too many closing parentheses";
      } else if (code == TOO_MANY_OPENING_PARENTHESES) {
         return "Too few closing parentheses";
      } else if (code == PARENTHESES_IN_WRONG_ORDER) {
         return "Parentheses in wrong order";
      } else if (code == STACK_FAILURE) {
         return "Problems in using internal data structure";
      } else {
         return "Unknown error";
      }
   }
   @Override
   public String getMessage() {
      return String.format("%s in line %d column %d (error code %d)", codeAsString(), lineNumber, columnNumber, code);
   }
}