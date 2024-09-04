//////////////// FILE HEADER //////////////////////////
//
// Title: Program 07 Sustenance Boulevard (Restaurant Orders Tester)
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
 * This class implements unit test methods to check the correctness of LinkedOrders and
 * RestaurantOrders classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming
 * assignment.
 *
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    try {
      Order o1 = new Order("Burger", 1);
      Order o2 = new Order("Pizza", 3);
      Order o3 = new Order("Sandwich", 2);
      // Scenario 1 - LinkedOrder objects are created without throwing any exceptions
      LinkedOrder l1 = new LinkedOrder(o1);
      LinkedOrder l2 = new LinkedOrder(o2);
      LinkedOrder l3 = new LinkedOrder(o3, l1, l2);
      // Scenario 2 - getOrder(), getPrevious(), and getNext() methods of LinkedOrder class work as
      // expected
      if (!l1.getOrder().equals(o1) || !l2.getOrder().equals(o2) || !l3.getOrder().equals(o3))
        return false;
      if (l3.getPrevious() != l1 || l3.getNext() != l2)
        return false;
      // Scenario 3 - setPrevious() and setNext() methods of LinkedOrder class work as expected
      l1.setPrevious(l2);
      l1.setNext(l3);
      if (l1.getPrevious() != l2 || l1.getNext() != l3)
        return false;
      // Scenario 4 - LinkedOrder object throws an exception if it is defined with an invalid time
      // stamp
      try {
        Order o4 = new Order("Shawarma", -1);
        LinkedOrder l4 = new LinkedOrder(o4);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    try {
      // Scenario 1 - RestaurantOrders objects are created without throwing any exceptions
      RestaurantOrders r1 = new RestaurantOrders();
      RestaurantOrders r2 = new RestaurantOrders(10);
      // Scenario 2 - capacity() and isEmpty() methods of RestaurantOrders class work as expected
      if (r1.capacity() != 20 || r2.capacity() != 10)
        return false;
      if (!r1.isEmpty() || !r2.isEmpty())
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    try {
      // Scenario 1 - RestaurantOrders object throws an exception if it is defined with an invalid
      // capacity
      try {
        RestaurantOrders r3 = new RestaurantOrders(-10);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder() method when it is
   * passed bad inputs.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    try {
      RestaurantOrders r1 = new RestaurantOrders();
      // Scenario 1 - placeOrder() throws an exception while trying to add null to the list
      try {
        r1.placeOrder(null);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
      }
      // Scenario 2 - placeOrder() throws an exception while trying to add an order with a negative
      // time stamp to the list
      try {
        Order o1 = new Order("Burger", -100);
        r1.placeOrder(o1);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
      }
      // Scenario 3 - placeOrder() throws an exception while trying to add an order with an existing
      // time stamp to the list
      try {
        Order o1 = new Order("Burger", 23);
        Order o2 = new Order("Sandwich", 23);
        r1.placeOrder(o1);
        r1.placeOrder(o2);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder() considering
   * different test scenarios using size(), readForward(), and readBackward() methods of
   * RestaurantOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
    try {
      // Scenario 1 - checks whether size(), readForward(), and readBackward() work as expected when
      // called on an empty list
      RestaurantOrders r1 = new RestaurantOrders();
      if (r1.size() != 0 || !r1.readForward().equals("The list contains 0 order(s)")
          || !r1.readBackward().equals("The list contains 0 order(s)"))
        return false;
      // Scenario 2 - RestaurantOrders.placeOrder() works as expected when adding an order to an
      // empty list
      Order o1 = new Order("Pizza", 3);
      r1.placeOrder(o1);
      if (r1.size() != 1 || !r1.readForward().equals("The list contains 1 order(s): [ Pizza ]")
          || !r1.readBackward().equals("The list contains 1 order(s): [ Pizza ]"))
        return false;
      // Scenario 3 - RestaurantOrders.placeOrder() works as expected when adding at the head of a
      // non-empty restaurant list
      Order o2 = new Order("Burger", 2);
      r1.placeOrder(o2);
      if (r1.size() != 2
          || !r1.readForward().equals("The list contains 2 order(s): [ Burger Pizza ]")
          || !r1.readBackward().equals("The list contains 2 order(s): [ Pizza Burger ]"))
        return false;
      // Scenario 4 - RestaurantOrders.placeOrder() works as expected when adding at the tail of a
      // non-empty restaurant list
      Order o3 = new Order("Pasta", 5);
      r1.placeOrder(o3);
      if (r1.size() != 3
          || !r1.readForward().equals("The list contains 3 order(s): [ Burger Pizza Pasta ]")
          || !r1.readBackward().equals("The list contains 3 order(s): [ Pasta Pizza Burger ]"))
        return false;
      // Scenario 5 - RestaurantOrders.placeOrder() works as expected when adding at the middle of a
      // non-empty restaurant list
      Order o4 = new Order("Biryani", 4);
      r1.placeOrder(o4);
      if (r1.size() != 4
          || !r1.readForward()
              .equals("The list contains 4 order(s): [ Burger Pizza Biryani Pasta ]")
          || !r1.readBackward()
              .equals("The list contains 4 order(s): [ Pasta Biryani Pizza Burger ]"))
        return false;
      // Scenario 6 - checks whether get() and indexOf() methods of RestaurantOrders class work as
      // expected
      Order o5 = new Order("Shawarma", 8);
      if (r1.indexOf(o5) != -1)
        return false;
      if (!r1.get(2).equals(o4) || r1.indexOf(o3) != 3 || !r1.get(1).equals(o1)
          || r1.indexOf(o2) != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.removeOrder() considering
   * different test scenarios using size(), readForward(), and readBackward() methods of
   * RestaurantOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    try {
      // Scenario 1 - try removing an order from an empty list
      RestaurantOrders r1 = new RestaurantOrders();
      try {
        r1.removeOrder(0);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // Expected behavior -- no problem detected
        if (r1.size() != 0 || !r1.readForward().equals("The list contains 0 order(s)")
            || !r1.readBackward().equals("The list contains 0 order(s)"))
          return false;
      }
      // Scenario 2 - removeOrder() throws an exception when trying to pass a negative index
      Order o1 = new Order("Sandwich", 4);
      r1.placeOrder(o1);
      try {
        r1.removeOrder(-10);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // Expected behavior -- no problem detected
        if (r1.size() != 1 || !r1.readForward().equals("The list contains 1 order(s): [ Sandwich ]")
            || !r1.readBackward().equals("The list contains 1 order(s): [ Sandwich ]"))
          return false;
      }
      // Scenario 3 - removing an order (at position index 0) from a list which contains only one
      // order
      if (o1 != r1.removeOrder(0) || !r1.readForward().equals("The list contains 0 order(s)")
          || !r1.readBackward().equals("The list contains 0 order(s)") || r1.size() != 0)
        return false;
      // Scenario 4 - removing an order (position index 0) from a list which contains at least 2
      // orders
      Order o2 = new Order("Falafel", 3);
      r1.placeOrder(o1);
      r1.placeOrder(o2);
      if (o2 != r1.removeOrder(0)
          || !r1.readForward().equals("The list contains 1 order(s): [ Sandwich ]")
          || !r1.readBackward().equals("The list contains 1 order(s): [ Sandwich ]")
          || r1.size() != 1)
        return false;
      // Scenario 5 - removing an order from the middle of a non-empty list containing at least 3
      // orders
      Order o3 = new Order("Pancakes", 5);
      r1.placeOrder(o2);
      r1.placeOrder(o3);
      if (o1 != r1.removeOrder(1)
          || !r1.readForward().equals("The list contains 2 order(s): [ Falafel Pancakes ]")
          || !r1.readBackward().equals("The list contains 2 order(s): [ Pancakes Falafel ]")
          || r1.size() != 2)
        return false;
      // Scenario 6 - removing the order at the end of a list containing at least two orders
      r1.placeOrder(o1);
      if (o3 != r1.removeOrder(2)
          || !r1.readForward().equals("The list contains 2 order(s): [ Falafel Sandwich ]")
          || !r1.readBackward().equals("The list contains 2 order(s): [ Sandwich Falafel ]")
          || r1.size() != 2)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * This method calls all the test methods defined and implemented in the RestaurantOrdersTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    try {
      if (!testLinkedOrders() || !testRestaurantOrdersConstructorIsEmpty()
          || !testRestaurantOrdersConstructorBadInput() || !testRestaurantOrdersAddBadInput()
          || !testRestaurantOrdersAdd() || !testRestaurantOrdersRemove())
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("testLinkedOrders()\t\t\t\t: " + testLinkedOrders());
    System.out.println(
        "testRestaurantOrdersConstructorIsEmpty()\t: " + testRestaurantOrdersConstructorIsEmpty());
    System.out.println("testRestaurantOrdersConstructorBadInput()\t: "
        + testRestaurantOrdersConstructorBadInput());
    System.out
        .println("testRestaurantOrdersAddBadInput()\t\t: " + testRestaurantOrdersAddBadInput());
    System.out.println("testRestaurantOrdersAdd()\t\t\t: " + testRestaurantOrdersAdd());
    System.out.println("testRestaurantOrdersRemove()\t\t\t: " + testRestaurantOrdersRemove());
    System.out.println("runAllTests()\t\t\t\t\t: " + runAllTests());
  }
}
