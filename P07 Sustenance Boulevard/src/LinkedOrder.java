//////////////// FILE HEADER //////////////////////////
//
// Title: Program 07 Sustenance Boulevard (Linked Order)
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

/**
 * This class will function as a wrapper for the Order class to facilitate the doubly linked list
 * implementation.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class LinkedOrder {
  // instance fields
  private final Order ORDER; // data field of this LinkedOrder
  private LinkedOrder previous; // reference to the Order before this one
  private LinkedOrder next; // reference to the Order after this one

  /**
   * Creates a new LinkedOrder object with a given order
   * 
   * @param order data field of this LinkedOrder
   * @throws IllegalArgumentException if order's time stamp is negative
   */
  public LinkedOrder(Order order) {
    Order temp = new Order("", 0);
    // checks if the given order has a negative time stamp
    if (temp.compareTo(order) == 1)
      throw new IllegalArgumentException("Error! The given order has a negative timestamp.");
    previous = null;
    next = null;
    ORDER = order;
  }

  /**
   * Creates a new LinkedOrder object with a given order, and reference to previous and next
   * LinkedOrder
   * 
   * @param order data field of this LinkedOrder
   * @param prev  reference to the Order before this one
   * @param next  reference to the Order after this one
   * @throws IllegalArgumentException if order's time stamp is negative
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next) {
    Order temp = new Order("", 0);
    // checks if the given order has a negative time stamp
    if (temp.compareTo(order) == 1)
      throw new IllegalArgumentException("Error! The given order has a negative timestamp.");
    ORDER = order;
    previous = prev;
    this.next = next;
  }

  /**
   * Getter to ORDER instance field
   * 
   * @return the data field (ORDER) of this LinkedOrder
   */
  public Order getOrder() {
    return ORDER;
  }

  /**
   * Getter to previous instance field
   * 
   * @return the reference to the LinkedOrder before this LinkedOrder
   */
  public LinkedOrder getPrevious() {
    return previous;
  }

  /**
   * Getter to next instance field
   * 
   * @return the reference to the LinkedOrder after this LinkedOrder
   */
  public LinkedOrder getNext() {
    return next;
  }

  /**
   * Sets the previous LinkedOrder attached to this one with the given reference to LinkedOrder
   * 
   * @param previous the reference to the LinkedOrder before this LinkedOrder
   */
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }

  /**
   * Sets the next LinkedOrder attached to this one with the given reference to LinkedOrder
   * 
   * @param next the reference to the LinkedOrder after this LinkedOrder
   */
  public void setNext(LinkedOrder next) {
    this.next = next;
  }


}
