//////////////// FILE HEADER //////////////////////////
//
// Title: Program 01 COVID Tracker
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
 * This class contains methods to manipulate the contents of the arrays pos and neg. The addTest()
 * and removeIndividual() methods are used to add a new record and remove all instances of an
 * individual from both the arrays respectively. The getPopStats() and getIndividualStats() methods
 * are used to display various information about the contents in the array.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class COVIDTracker {
  /**
   * Adds ID to the appropriate test array if there is room.
   * 
   * @param pos   The current array of positive tests
   * @param neg   The current array of negative tests
   * @param id    The tested individual's unique identifier String
   * @param isPos Whether the individual's test results are positive or negative
   * @return true if the new record was added, false otherwise
   */
  public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {
    if (isPos) {
      int k = 0; // Used to check the number of null elements in an array
      for (int i = 0; i < pos.length; i++) {
        if (pos[i] != null)
          k++;
      }

      if (k == pos.length) { // Checks whether the array pos is a completely filled array or not
        return false;
      } else {
        for (int i = 0; i < pos.length; i++) {
          if (pos[i] == null) { // Adds the element id at a null spot in the array pos
            pos[i] = id;
            break;
          }
        }
        return true;
      }
    } else {
      int k = 0; // Used to check the number of null elements in an array
      for (int i = 0; i < neg.length; i++) {
        if (neg[i] != null)
          k++;
      }

      if (k == neg.length) { // Checks whether the array neg is a completely filled array or not
        return false;
      } else {
        for (int i = 0; i < neg.length; i++) {
          if (neg[i] == null) { // Adds the element id at a null spot in the array neg
            neg[i] = id;
            break;
          }
        }
        return true;
      }
    }
  }

  /**
   * Removes all instances of the identifier (ID) from both the arrays.
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @param id  The tested individual's unique identifier String
   * @return true if the identifier has been removed from the two arrays, false if both the arrays
   *         don't contain this identifier.
   */
  public static boolean removeIndividual(String[] pos, String[] neg, String id) {
    boolean posid = false; // Used to check whether id is present in array pos
    boolean negid = false; // Used to check whether id is present in array neg

    // Checks whether the array pos contains id
    for (int i = 0; i < pos.length; i++) {
      if (pos[i] != null) {
        if (pos[i].equals(id)) {
          posid = true;
          break;
        }
      }
    }
    // Checks whether the array neg contains id
    for (int i = 0; i < neg.length; i++) {
      if (neg[i] != null) {
        if (neg[i].equals(id)) {
          negid = true;
          break;
        }
      }
    }
    /*
     * Returns true after removing id from pos and neg (if pos or neg contain id), else returns
     * false
     */
    if (posid || negid) {
      if (posid) {
        for (int i = 0; i < pos.length; i++) {
          if (pos[i] != null) {
            if (pos[i].equals(id)) { // Removes the element id from pos
              pos[i] = null;
            }
          }
        }
        // Compacts the array pos, so that all null elements are pushed to the end of the array
        int j = 0;
        for (int i = 0; i < pos.length; i++) {
          if (pos[i] != null) {
            if (i == 0 || pos[i - 1] != null) {
              j++;
            } else {
              pos[j] = pos[i];
              pos[i] = null;
              j++;
            }
          }
        }
      }

      if (negid) {
        for (int i = 0; i < neg.length; i++) {
          if (neg[i] != null) {
            if (neg[i].equals(id)) { // Removes the element id from neg
              neg[i] = null;
            }
          }
        }
        // Compacts the array neg, so that all null elements are pushed to the end of the array
        int j = 0;
        for (int i = 0; i < neg.length; i++) {
          if (neg[i] != null) {
            if (i == 0 || neg[i - 1] != null) {
              j++;
            } else {
              neg[j] = neg[i];
              neg[i] = null;
              j++;
            }
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Displays the statistics of total tests, total individuals tested, percentage of positive tests,
   * and percentage of positive individuals.
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @return A string containing total tests, total individuals tested, percent positive tests, and
   *         percent positive individuals.
   */
  public static String getPopStats(String[] pos, String[] neg) {
    int positiveTests = 0; // Counts the no. of positive tests
    int negativeTests = 0; // Counts the no. of negative tests
    String[] idTested = new String[100]; // A String of all unique individuals
    int size = 0; // Stores the size of the array idTested
    boolean uniqueId; // Used to check whether an ID is already present in array idTested
    double percentPositiveTests = 0.0; // Stores the percentage of positive tests
    double percentPositiveId = 0.0; // Stores the percentage of positive individuals
    int positiveId = 0; // Counts the no. of positive individuals
    int k = 0;

    for (int i = 0; i < pos.length; i++) {
      if (pos[i] == null)
        k++;
    }

    if (k != pos.length) { // Checks if array pos is an empty array or not
      for (int i = 0; i < pos.length; i++) {
        if (pos[i] != null) {
          uniqueId = true;
          positiveTests++;

          for (int j = 0; j < size; j++) { // Checks whether pos[i] is present in the array idTested
            if (idTested[j].equals(pos[i])) {
              uniqueId = false;
              break;
            }
          }

          if (uniqueId) { // Adds the element pos[i] to idTested if it is not present in idTested
            idTested[size] = pos[i];
            size++;
            positiveId++;
          }
        }
      }
    }

    k = 0;
    for (int i = 0; i < neg.length; i++) {
      if (neg[i] == null)
        k++;
    }

    if (k != neg.length) { // Checks if array neg is an empty array or not
      for (int i = 0; i < neg.length; i++) {
        if (neg[i] != null) {
          uniqueId = true;
          negativeTests++;

          for (int j = 0; j < size; j++) { // Checks whether neg[i] is present in the array idTested
            if (idTested[j].equals(neg[i])) {
              uniqueId = false;
              break;
            }
          }

          if (uniqueId) { // Adds the element neg[i] to idTested if it is not present in idTested
            idTested[size] = neg[i];
            size++;
          }
        }
      }
    }

    int totalTests = (positiveTests + negativeTests);

    if (totalTests != 0) { // Checks if the given parameters are both empty arrays
      percentPositiveTests = ((double) positiveTests / totalTests) * 100;
      percentPositiveId = ((double) positiveId / size) * 100;
    } else {
      percentPositiveTests = 0;
      percentPositiveId = 0;
    }

    return ("Total tests: " + totalTests + "\nTotal individuals tested: " + size
        + "\nPercent positive tests: " + percentPositiveTests + "%\nPercent positive individuals: "
        + percentPositiveId + "%");
  }

  /**
   * Checks all instances of the identifier in both the arrays and displays the total tests,
   * positive tests, negative tests of the individual.
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @param id  The tested individual's unique identifier String
   * @return A string containing the total tests, positive tests, and negative tests corresponding
   *         to the individual.
   */
  public static String getIndividualStats(String[] pos, String[] neg, String id) {
    int positiveTests = 0; // Keeps record of all positive tests for id
    int negativeTests = 0; // Keeps record of all negative tests for id
    int k = 0;

    for (int i = 0; i < pos.length; i++) {
      if (pos[i] == null)
        k++;
    }

    if (k != pos.length) { // Checks whether pos is an empty array or not
      for (int i = 0; i < pos.length; i++) {
        if (pos[i] != null) {
          if (pos[i].equals(id)) {
            positiveTests++; // positiveTests gets incremented for every instance of id in pos
          }
        }
      }
    }

    k = 0;
    for (int i = 0; i < neg.length; i++) {
      if (neg[i] == null)
        k++;
    }

    if (k != neg.length) { // Checks whether neg is an empty array or not
      for (int i = 0; i < neg.length; i++) {
        if (neg[i] != null) {
          if (neg[i].equals(id)) {
            negativeTests++; // negativeTests gets incremented for every instance of id in neg
          }
        }
      }
    }

    return ("Total tests: " + (positiveTests + negativeTests) + "\nPositive: " + positiveTests
        + "\nNegative: " + negativeTests);
  }
}
