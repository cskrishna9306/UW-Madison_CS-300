//////////////// FILE HEADER //////////////////////////
//
// Title: Program 08 Inbox Reader (Inbox)
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
 * This class implements the Inbox object where all received/loaded unread and read messages are
 * stored and managed.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Inbox {
  // instance fields
  private MessageStack readMessageBox; // stack which stores read messages
  private MessageStack unreadMessageBox; // stack which stores unread messages

  /**
   * Creates a new empty Inbox object and initializes its instance fields.
   *
   */
  public Inbox() {
    readMessageBox = new MessageStack();
    unreadMessageBox = new MessageStack();
  }

  /**
   * Reads and moves the message at the top of the unreadMessageBox to the readMessageBox.
   * 
   * @return the string representation of the message at the top of the unreadMessageBox, or
   *         "Nothing in Unread" if the unreadMessageBox of this inbox is empty
   */
  public String readMessage() {
    if (unreadMessageBox.isEmpty()) // checks if unreadMessageBox is empty or not
      return "Nothing in Unread";
    Message readMessage = unreadMessageBox.peek();
    readMessageBox.push(readMessage);
    unreadMessageBox.pop();
    return readMessage.toString();
  }

  /**
   * Reads and returns the message at the top of the readMessageBox.
   * 
   * @return the subject of the message at the top of the readMessageBox and "Nothing in Read" if
   *         the readMessageBox is empty
   */
  public String peekReadMessage() {
    if (readMessageBox.isEmpty()) // checks if readMessageBox is empty or not
      return "Nothing in Read";
    return readMessageBox.peek().getSUBJECT();
  }

  /**
   * Marks all messages in the unread message box as read.
   * 
   * @return the total number of messages marked as read
   */
  public int markAllMessagesAsRead() {
    // moves all messages in unreadMessageBox to readMessageBox until it becomes empty
    while (!unreadMessageBox.isEmpty()) {
      readMessageBox.push(unreadMessageBox.peek());
      unreadMessageBox.pop();
    }
    return readMessageBox.size();
  }

  /**
   * Pushes a new Message into the unread message box.
   * 
   * @param newMessage reference to the received message
   */
  public void receiveMessage(Message newMessage) {
    unreadMessageBox.push(newMessage);
  }

  /**
   * Removes permanently all the messages from the readMessageBox.
   * 
   * @return the total number of the removed messages
   */
  public int emptyReadMessageBox() {
    int removedMessages = readMessageBox.size();
    readMessageBox = null; // empties readMessageBox by setting it to a null reference
    return removedMessages;
  }

  /**
   * Gets the statistics of this inbox.
   * 
   * @return a string indicating the total number of unread and read messages in this inbox
   */
  public String getStatistics() {
    return "Unread (" + unreadMessageBox.size() + ")" + "\n" + "Read (" + readMessageBox.size()
        + ")";
  }

  /**
   * Traverses all the unread messages in this inbox and returns a list of their ID and SUBJECT as a
   * string.
   * 
   * @return a string representation of the contents of the unread message box
   */
  public String traverseUnreadMessages() {
    String list = "Unread(" + unreadMessageBox.size() + ")\n";
    // using for-each loop to traverse the contents of unreadMessageBox
    for (Message m : unreadMessageBox) {
      list = list + m.getID() + " " + m.getSUBJECT() + "\n";
    }
    return list;
  }

  /**
   * Traverses all the read messages in this inbox and returns a list of their ID and SUBJECT as a
   * string.
   * 
   * @return a string representation of the contents of the read message box
   */
  public String traverseReadMessages() {
    String list = "Read(" + readMessageBox.size() + ")\n";
    // using for-each loop to traverse the contents of readMessageBox
    for (Message m : readMessageBox) {
      list = list + m.getID() + " " + m.getSUBJECT() + "\n";
    }
    return list;
  }
}
