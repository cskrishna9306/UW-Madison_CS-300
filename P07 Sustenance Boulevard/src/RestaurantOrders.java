//////////////// FILE HEADER //////////////////////////
//
// Title: Program 07 Sustenance Boulevard (Restaurant Orders)
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
 * This class implements a sorted doubly-linked list to hold orders as they are placed at the right
 * spot in the list: the earliest orders go at the FRONT (head) and the most recent orders are at
 * the BACK (tail) of the list.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class RestaurantOrders implements SortedListADT<Order> {
  // instance fields
  private LinkedOrder head; // front of the doubly-linked list
  private LinkedOrder tail; // end of the doubly-linked list
  private int size; // number of orders currently in the list
  private final int CAPACITY; // maximum number of orders for this list

  /**
   * Creates a new RestaurantOrders object which sets the CAPACITY to 20
   *
   */
  public RestaurantOrders() {
    CAPACITY = 20;
  }

  /**
   * Creates a new RestaurantOrders object with given capacity
   *
   * @param capacity the maximum number of orders for this list
   * @throws IllegalArgumentException if the provided capacity is 0 or negative
   */
  public RestaurantOrders(int capacity) {
    // checks if the given capacity is 0 or negative
    if (capacity <= 0)
      throw new IllegalArgumentException("Error! The given capacity is invalid.");
    CAPACITY = capacity;
  }

  /**
   * Getter to the CAPACITY instance field
   * 
   * @return the capacity of this list
   */
  public int capacity() {
    return CAPACITY;
  }

  /**
   * Creates a string representation of the orders in this list in the forward direction
   * 
   * @return a String representation of the orders in this list from head to tail, space-separated
   */
  public String readForward() {
    LinkedOrder node = head;
    int i = 0;
    String orders = "The list contains " + size + " order(s)";
    if (size != 0)
      orders = orders + ": [ ";
    else
      return orders;

    while (i < size) { // iterates through all the orders in this list from head to tail
      orders = orders + node.getOrder() + " ";
      node = node.getNext();
      i++;
    }

    return orders + "]";
  }

  /**
   * Creates a string representation of the orders in this list in the backward direction
   * 
   * @return a String representation of the orders in this list from tail to head, space-separated
   */
  public String readBackward() {
    LinkedOrder node = tail;
    int i = size - 1;
    String orders = "The list contains " + size + " order(s)";
    if (size != 0)
      orders = orders + ": [ ";
    else
      return orders;

    while (i >= 0) { // iterates through all the orders in this list from tail to head
      orders = orders + node.getOrder() + " ";
      node = node.getPrevious();
      i--;
    }

    return orders + "]";
  }

  /**
   * Removes all orders from this list and resets size to 0
   *
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Iterates through the list to find the order at the given index.
   * 
   * @param index the position of the order to be returned
   * @return the order at position index of this list, without removing it
   * @throws IndexOutOfBoundsException if the given index is invalid
   */
  public Order get(int index) {
    // checks for invalid index
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Error! The entered index is invalid.");

    int i = 0;
    LinkedOrder node = head;
    while (i < size) { // iterates through the list from head to tail
      if (i == index) // returns the Order at given index
        return node.getOrder();
      node = node.getNext();
      i++;
    }
    return null;
  }

  /**
   * Iterates through the list to find the index of the given order.
   * 
   * @param findOrder the Order reference to be searched in the list
   * @return the index of the specified order in the list, or -1 if not found
   */
  public int indexOf(Order findOrder) {
    int i = 0;
    LinkedOrder node = head;
    while (i < size) { // iterates through the list from head to tail
      // returns the index of the order which matches the dishes in the given order
      if (node.getOrder().equals(findOrder))
        return i;
      node = node.getNext();
      i++;
    }
    // returns -1 if the specified order is not found in the list
    return -1;
  }

  /**
   * Checks whether the list is currently empty or not.
   * 
   * @return true if and only if the list is currently empty; false otherwise
   */
  public boolean isEmpty() {
    if (size == 0)
      return true;
    return false;
  }

  /**
   * Adds a new Order to this sorted list in the correct position if there is room in the list
   * 
   * @param newOrder reference to the new Order to be added to the list
   * @throws IllegalArgumentException if newOrder has the same time stamp as an existing order, a
   *                                  negative time stamp, or is null
   */
  public void placeOrder(Order newOrder) {
    Order temp = new Order("", 0);
    int i = 0;
    // checks if newOrder is null or has a negative time stamp
    if (newOrder == null || temp.compareTo(newOrder) == 1)
      throw new IllegalArgumentException("Error! The given order is invalid.");

    LinkedOrder node = head;
    while (i < size) { // iterates through the list from head to tail
      // checks if newOrder has the same time stamp as an existing order in the list
      if (node.getOrder().compareTo(newOrder) == 0) {
        throw new IllegalArgumentException("Error! The given order is invalid.");
      }
      node = node.getNext();
      i++;
    }

    i = 0;
    node = head;

    if (size < CAPACITY) { // adds the new order if there is room in the list
      LinkedOrder order = new LinkedOrder(newOrder);
      if (head == null) { // checks if the list has no orders
        head = order;
        tail = order;
      } else {
        // adds the new order as the head if the current head has a time stamp greater than newOrder
        if (newOrder.compareTo(head.getOrder()) == -1) {
          head.setPrevious(order);
          order.setNext(head);
          head = order;
        }

        // adds the new order as the tail if the current tail has a time stamp lesser than newOrder
        if (newOrder.compareTo(tail.getOrder()) == 1) {
          tail.setNext(order);
          order.setPrevious(tail);
          tail = order;
        }

        node = head;
        i = 0;
        while (i < size) { // iterates through the list from head to tail
          // adds the new order in the correct position in the sorted list
          if (newOrder.compareTo(node.getOrder()) == 1
              && newOrder.compareTo(node.getNext().getOrder()) == -1) {
            order.setPrevious(node);
            order.setNext(node.getNext());
            node.getNext().setPrevious(order);
            node.setNext(order);
          }

          node = node.getNext();
          i++;
        }
      }
      size++; // the size of the linked list is incremented
    }
  }

  /**
   * Iterates through the list to remove the order at the given index.
   * 
   * @param index the position of the order to be removed in the list
   * @return the order at given index which is removed from this list
   * @throws IndexOutOfBoundsException if the given index is invalid
   */
  public Order removeOrder(int index) {
    int i = 0;
    LinkedOrder node = head;
    LinkedOrder removedOrder;

    // checks for invalid index
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Error! The entered index is invalid.");

    if (index == 0) { // checks if the element to be removed is the 1st element in the list
      if (size == 1) { // checks if there is only 1 element in the list
        removedOrder = head;
        head = null;
        tail = null;
      } else {
        removedOrder = head;
        head = head.getNext();
        head.setPrevious(null);
      }
      size--; // the size of the list is decremented
      return removedOrder.getOrder();
    }

    if (index == size - 1) { // checks if the element to be removed is the last element in the list
      removedOrder = tail;
      tail = tail.getPrevious();
      tail.setNext(null);
      size--;
      return removedOrder.getOrder();
    }

    while (i < size) { // iterates through the list from head to tail
      if (i == index) { // removes and returns the order at the given index
        removedOrder = node;
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
        size--;
        return removedOrder.getOrder();
      }

      node = node.getNext();
      i++;
    }
    return null;
  }

  /**
   * Getter to the size instance field
   * 
   * @return the number of orders currently in the list
   */
  public int size() {
    return size;
  }
}


