//////////////// FILE HEADER //////////////////////////
//
// Title: Program 08 Inbox Reader (Message Stack)
// Course: CS 300 Fall 2020
//
// Author: Sai Krishna Chaparala
// Email: chaparala@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * This class implements the StackADT<T> interface and models a stack which contains only elements
 * of type Message.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class MessageStack implements StackADT<Message>, Iterable<Message> {
  // instance fields
  private LinkedNode<Message> top; // reference to the top of the linked stack
  private int size; // number of messages currently in the linked stack

  /**
   * Adds an element to this stack.
   * 
   * @param element an element to be added
   * @throws IllegalArgumentException with a descriptive error message if the input element is null
   */
  @Override
  public void push(Message element) {
    if (element == null) // checks if the input element is null
      throw new IllegalArgumentException("Error! The given element has a null reference.");
    // adds the new element at the top of the linked stack
    if (top == null && size == 0)
      top = new LinkedNode<Message>(element);
    else {
      LinkedNode<Message> temp = new LinkedNode<Message>(element, top);
      top = temp;
    }
    size++; // increments the size of the linked stack by 1
  }

  /**
   * Removes and returns the element on the top of this stack.
   * 
   * @return the element removed from the top of this stack
   * @throws EmptyStackException without error message if the stack is empty
   */
  @Override
  public Message pop() {
    if (top == null && size == 0) // checks if this stack is currently empty or not
      throw new EmptyStackException();
    Message removedElement = top.getData();
    // removes the element at the top of this stack
    top = top.getNext();
    size--;
    return removedElement;
  }

  /**
   * Gets the element on the top of this stack.
   * 
   * @return the element on the stack top
   * @throws EmptyStackException without error message if the stack is empty
   */
  @Override
  public Message peek() {
    if (top == null && size == 0) // checks if this stack is currently empty or not
      throw new EmptyStackException();
    return top.getData();
  }

  /**
   * Checks whether this stack is empty or not.
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    return top == null && size == 0;
  }

  /**
   * Gets the number of elements in this stack.
   * 
   * @return the size of the stack
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Creates and returns a new MessageStackIterator that starts at the the top of of this stack of
   * messages.
   * 
   * @return reference to a MessageStackIterator that starts at the the top of of this stack
   */
  @Override
  public Iterator<Message> iterator() {
    return new MessageStackIterator(top);
  }
}
