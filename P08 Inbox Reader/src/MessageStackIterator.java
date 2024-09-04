//////////////// FILE HEADER //////////////////////////
//
// Title: Program 08 Inbox Reader (Message Stack Iterator)
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements an iterator to iterate over the MessageStack class.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class MessageStackIterator implements Iterator<Message> {
  // instance fields
  private LinkedNode<Message> next; // reference to the next element in the iteration

  /**
   * Creates a new MessageStackIterator object with a given reference to the top of a stack
   * 
   * @param next the reference to the top of the stack
   */
  public MessageStackIterator(LinkedNode<Message> next) {
    this.next = next;
  }

  /**
   * Checks whether the current element in the stack has a next element
   * 
   * @return true if the reference to the next element is not null, false otherwise
   */
  @Override
  public boolean hasNext() {
    if (next == null)
      return false;
    return true;
  }

  /**
   * Moves the reference next to the next element in the stack.
   * 
   * @return the message at the element referenced by next in the stack
   * @throws NoSuchElementException if the stack is exhausted and the current element in the
   *                                iteration does not have a next item
   */
  @Override
  public Message next() {
    if (next == null && !hasNext())
      throw new NoSuchElementException("Error! The stack is exhausted.");
    Message temp = next.getData();
    next = next.getNext();
    return temp;
  }
}
