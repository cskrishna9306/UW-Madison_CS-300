//////////////// FILE HEADER //////////////////////////
//
// Title: Program 08 Inbox Reader (Inbox Reader Tester)
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
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class InboxReaderTester {

  /**
   * Calls all the tester methods implemented in this class.
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(
        "testStackConstructorIsEmptyPushPeek()\t: " + testStackConstructorIsEmptyPushPeek());
    System.out.println("testStackPop()\t\t\t\t: " + testStackPop());
    System.out.println("testInboxReadMessage()\t\t\t: " + testInboxReadMessage());
    System.out.println("testInboxReceiveMessage()\t\t: " + testInboxReceiveMessage());
    System.out.println("testInboxMarkAllMessagesAsRead()\t: " + testInboxMarkAllMessagesAsRead());
    System.out.println("testInboxTraverseUnreadMessages()\t: " + testInboxTraverseUnreadMessages());
    System.out.println("testInboxTraverseReadMessages()\t\t: " + testInboxTraverseReadMessages());
    System.out.println("testMessageStackIterator()\t\t: " + testMessageStackIterator());
    System.out.println("runInboxReaderTestSuite()\t\t: " + runInboxReaderTestSuite());
  }

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek() {
    try {
      // Scenario 1 - MessageStack objects are created without throwing any exceptions
      MessageStack stack = new MessageStack();
      // Scenario 2 - isEmpty() and size() work as expected on an empty stack
      if (!stack.isEmpty() || stack.size() != 0)
        return false;
      // Scenario 3 - peek() throws an exception when called on an empty stack
      try {
        stack.peek();
        return false;
      } catch (EmptyStackException e) {
        // Expected behavior -- no problem detected
      }
      // Scenario 4 - push() works as expected on an empty stack
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      stack.push(m1);
      if (stack.peek() != m1 || stack.size() != 1 || stack.isEmpty())
        return false;
      // Scenario 5 - push() works as expected on a non-empty stack
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      stack.push(m2);
      if (stack.peek() != m2 || stack.size() != 2 || stack.isEmpty())
        return false;
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      stack.push(m3);
      if (stack.peek() != m3 || stack.size() != 3 || stack.isEmpty())
        return false;
      // Scenario 6 - push() throws an exception when called with an invalid input
      try {
        stack.push(null);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
        if (!e.getMessage().equals("Error! The given element has a null reference."))
          return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop() {
    try {
      // Scenario 1 - pop() throws an exception when called on an empty stack
      MessageStack stack = new MessageStack();
      try {
        stack.pop();
        return false;
      } catch (EmptyStackException e) {
        // Expected behavior -- no problem detected
      }
      // Scenario 2 - pop() works as expected when called on a stack with only 1 element
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      stack.push(m1);
      if (stack.pop() != m1 || stack.size() != 0 || !stack.isEmpty())
        return false;
      // Scenario 3 - pop() works as expected when called on a stack with at least 3 elements
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      stack.push(m1);
      stack.push(m2);
      stack.push(m3);
      if (stack.pop() != m3 || stack.size() != 2 || stack.isEmpty())
        return false;
      if (stack.pop() != m2 || stack.size() != 1 || stack.isEmpty())
        return false;
      if (stack.pop() != m1 || stack.size() != 0 || !stack.isEmpty())
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.readMessage() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage() {
    try {
      // Scenario 1 - Inbox object is created without throwing any exceptions
      Inbox inbox = new Inbox();
      // Scenario 2 - readMessage(), peekReadMessage(), and getStatistics() work as expected when
      // called on an empty stack
      if (!inbox.readMessage().equals("Nothing in Unread")
          || !inbox.peekReadMessage().equals("Nothing in Read")
          || !inbox.getStatistics().equals("Unread (0)\nRead (0)"))
        return false;
      // Scenario 3 - readMessage() works as expected when called on a stack with 1 element
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      inbox.receiveMessage(m1);
      if (!inbox.readMessage().equals(m1.toString())
          || !inbox.getStatistics().equals("Unread (0)\nRead (1)")
          || !inbox.peekReadMessage().equals(m1.getSUBJECT()))
        return false;
      // Scenario 4 - readMessage() works as expected when called on a stack at least 3 elements
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      Message m4 = new Message("Test Topic 4", "Test Text 4");
      inbox.receiveMessage(m2);
      inbox.receiveMessage(m3);
      inbox.receiveMessage(m4);
      if (!inbox.getStatistics().equals("Unread (3)\nRead (1)"))
        return false;
      if (!inbox.readMessage().equals(m4.toString())
          || !inbox.getStatistics().equals("Unread (2)\nRead (2)")
          || !inbox.peekReadMessage().equals(m4.getSUBJECT()))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * Checks for the correctness of the Inbox.receiveMessage() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() {
    try {
      Inbox inbox = new Inbox();
      // Scenario 1 - receiveMessage() works as expected on an empty stack
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      inbox.receiveMessage(m1);
      if (!inbox.peekReadMessage().equals("Nothing in Read")
          || !inbox.getStatistics().equals("Unread (1)\nRead (0)"))
        return false;
      // Scenario 2 - receiveMessage() works as expected on a stack with 1 element
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      inbox.receiveMessage(m2);
      if (!inbox.peekReadMessage().equals("Nothing in Read")
          || !inbox.getStatistics().equals("Unread (2)\nRead (0)"))
        return false;
      // Scenario 3 - receiveMessage() works as expected on a stack with at least 3 elements
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      Message m4 = new Message("Test Topic 4", "Test Text 4");
      inbox.receiveMessage(m3);
      inbox.receiveMessage(m4);
      if (!inbox.peekReadMessage().equals("Nothing in Read")
          || !inbox.getStatistics().equals("Unread (4)\nRead (0)"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() {
    try {
      Inbox inbox = new Inbox();
      // Scenario 1 - markAllMessagesAsRead() works as expected when called on empty stacks
      if (inbox.markAllMessagesAsRead() != 0
          || !inbox.getStatistics().equals("Unread (0)\nRead (0)"))
        return false;
      // Scenario 2 - markAllMessagesAsRead() works as expected when called on a non-empty
      // unreadMessageBox stack and empty readMessageBox stack
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      inbox.receiveMessage(m1);
      if (inbox.markAllMessagesAsRead() != 1
          || !inbox.getStatistics().equals("Unread (0)\nRead (1)"))
        return false;
      // Scenario 3 - markAllMessagesAsRead() works as expected when called on an empty
      // unreadMessageBox stack and non-empty readMessageBox stack
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      inbox.receiveMessage(m2);
      inbox.readMessage();
      if (inbox.markAllMessagesAsRead() != 2
          || !inbox.getStatistics().equals("Unread (0)\nRead (2)"))
        return false;
      // Scenario 4 - markAllMessagesAsRead() works as expected when called on a non-empty
      // unreadMessageBox stack and non-empty readMessageBox stack
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      Message m4 = new Message("Test Topic 4", "Test Text 4");
      Message m5 = new Message("Test Topic 5", "Test Text 5");
      inbox.receiveMessage(m3);
      inbox.receiveMessage(m4);
      inbox.receiveMessage(m5);
      if (inbox.markAllMessagesAsRead() != 5
          || !inbox.getStatistics().equals("Unread (0)\nRead (5)"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() {
    try {
      // Scenario 1 - 1st call of next() returns the head of the linked nodes when called on a chain
      // of at least 3 linked nodes
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      LinkedNode<Message> n1 = new LinkedNode<Message>(m1,
          new LinkedNode<Message>(m2, new LinkedNode<Message>(m3, null)));
      MessageStackIterator iterator = new MessageStackIterator(n1);
      if (iterator.next() != m1)
        return false;
      // Scenario 2 - hasNext() works as expected on a chain of at least 3 linked nodes
      if (!iterator.hasNext())
        return false;
      // Scenario 3 - 2nd call of next() returns the 2nd element of the chain of linked nodes
      if (iterator.next() != m2)
        return false;
      // Scenario 4 - 3rd call of next() returns the 3rd element of the chain of linked nodes
      if (iterator.next() != m3)
        return false;
      // Scenario 5 - hasNext() works as expected and returns false when the linked node isn't
      // pointing to another linked node
      if (iterator.hasNext())
        return false;
      // Scenario 6 - 4th call of next() throws an exception when there are no more linked nodes to
      // iterate
      try {
        iterator.next();
        return false;
      } catch (NoSuchElementException e) {
        // Expected behavior -- no problem detected
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of Inbox.traverseUnreadMessages() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxTraverseUnreadMessages() {
    try {
      Message.resetIdGenerator();
      // Scenario 1 - traverseUnreadMessages() works as expected on an empty unreadMessageBox stack
      Inbox inbox = new Inbox();
      if (!inbox.traverseUnreadMessages().equals("Unread(0)\n"))
        return false;
      // Scenario 2 - traverseUnreadMessages() works as expected on a stack with 1 element
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      inbox.receiveMessage(m1);
      if (!inbox.traverseUnreadMessages().equals("Unread(1)\n1 Test Topic 1\n"))
        return false;
      // Scenario 3 - traverseUnreadMessages() works as expected on a stack with at least 3 elements
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      inbox.receiveMessage(m2);
      inbox.receiveMessage(m3);
      if (!inbox.traverseUnreadMessages()
          .equals("Unread(3)\n3 Test Topic 3\n2 Test Topic 2\n1 Test Topic 1\n"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of Inbox.traverseReadMessages() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxTraverseReadMessages() {
    try {
      Message.resetIdGenerator();
      // Scenario 1 - traverseReadMessages() works as expected on an empty readMessageBox stack
      Inbox inbox = new Inbox();
      if (!inbox.traverseReadMessages().equals("Read(0)\n"))
        return false;
      // Scenario 2 - traverseReadMessages() works as expected on a stack with 1 element
      Message m1 = new Message("Test Topic 1", "Test Text 1");
      inbox.receiveMessage(m1);
      inbox.markAllMessagesAsRead();
      if (!inbox.traverseReadMessages().equals("Read(1)\n1 Test Topic 1\n"))
        return false;
      // Scenario 3 - traverseReadMessages() works as expected on a stack with at least 3 elements
      Message m2 = new Message("Test Topic 2", "Test Text 2");
      Message m3 = new Message("Test Topic 3", "Test Text 3");
      inbox.receiveMessage(m2);
      inbox.receiveMessage(m3);
      inbox.markAllMessagesAsRead();
      if (!inbox.traverseReadMessages()
          .equals("Read(3)\n2 Test Topic 2\n3 Test Topic 3\n1 Test Topic 1\n"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Calls and checks the correctness of all test methods defined and implemented in the
   * InboxReaderTester class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runInboxReaderTestSuite() {
    if (!testStackConstructorIsEmptyPushPeek() || !testStackPop() || !testInboxReadMessage()
        || !testInboxReceiveMessage() || !testInboxMarkAllMessagesAsRead()
        || !testInboxTraverseUnreadMessages() || !testInboxTraverseReadMessages()
        || !testMessageStackIterator())
      return false;
    return true;
  }
}
