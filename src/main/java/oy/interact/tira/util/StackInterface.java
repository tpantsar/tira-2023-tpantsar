package oy.interact.tira.util;

/**
 * An interface to stack class holding elements of type E.
 * Implement this interface in a separate <code>StackImplementation.java</code> file.
 * 
 */
public interface StackInterface<E> {

   /**
    * For querying the capacity of the stack.
    *
    @return The number of elements the stack can currently hold in total.
    */
   public int capacity();
   
   /**
    * Push an element to the stack.
    * 
    * If the internal array does not have enough room for the element, the implementation MUST
    * first create a larger array, copy the elements from the old array to the new array,
    * and then replace the old array with the new array. Then place the element in the array.
    *
    * @param element The element to push, must not be null, if it is, throw NullPointerException.
    * @throws OutOfMemoryError if no additional room can be allocated for the stack
    * @throws NullPointerException if the element pushed is null.
    */
   public void push(E element) throws OutOfMemoryError, NullPointerException;

   /**
    * Pops an element out of the stack, removing it from the internal data storage.
    *
    * @return The popped element.
    * @throws IllegalStateException if the stack is empty.
    */
   public E pop() throws IllegalStateException;

   /**
    * Returns the element at the top of the stack, not removing it from the stack.
    *
    * @return The element.
    * @throws IllegalStateException if the stack is empty.
    */
   public E peek() throws IllegalStateException;

   /**
    * Returns the size of the stack; the count of elements currently in the stack.
    *
    * @return Size of the stack.
    */
   public int size();

   /**
    *  Use to check if the stack is empty.
    *
    * @return Returns true if the stack is empty, false otherwise.
    */
    public boolean isEmpty();

   /**
    * Clears the stack so that it does not contain any elements.
    */
   public void clear();
}
