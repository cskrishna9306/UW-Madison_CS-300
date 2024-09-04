//////////////// FILE HEADER //////////////////////////
//
// Title: Program 01 COVID Tracker Tester
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
 * This class contains the methods to test all the methods in the COVIDTracker class. This class
 * also contains the main method.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class COVIDTrackerTester {
  /**
   * Calls the test methods implemented in this class and displays output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("testAddTest(): " + testAddTest());
    System.out.println("testRemoveIndividual(): " + testRemoveIndividual());
    System.out.println("testGetPopStats(): " + testGetPopStats());
    System.out.println("testGetIndividualStats(): " + testGetIndividualStats());
  }

  /**
   * Checks whether addTest() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testAddTest() {
    // two empty arrays -> true; also checking that arrays were updated properly
    String[] pos = new String[2];
    String[] neg = new String[2];
    if (!COVIDTracker.addTest(pos, neg, "AB1234", false) || neg[0] == null)
      return false;
    if (!COVIDTracker.addTest(pos, neg, "CD2345", true) || pos[0] == null)
      return false;
    // two arrays with space -> true
    if (!COVIDTracker.addTest(pos, neg, "CD2345", false) || neg[1] == null)
      return false;
    // one full array but adding to one with space -> true
    if (!COVIDTracker.addTest(pos, neg, "EF3456", true) || pos[1] == null)
      return false;
    // one array with space but adding to full one -> false
    String[] pos2 = new String[2];
    if (COVIDTracker.addTest(pos2, neg, "EF3456", false))
      return false;
    // two full arrays -> false
    if (COVIDTracker.addTest(pos, neg, "EF3456", true))
      return false;
    return true;
  }

  /**
   * Checks whether removeIndividual() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testRemoveIndividual() {
    String[] pos = new String[] {null, "CD2345", "EF3456", null, "AB1234"};
    String[] neg = new String[] {"AB1234", null, "CD2345", "EF3456", null};
    String[] posFull = new String[] {"CD2345", "AB1234", "AB1234", "CD2345", "AB1234"};
    String[] negFull = new String[] {"AB1234", "CD2345", "AB1234", "EF3456", "EF3456"};

    // removing from two completely filled arrays -> true
    if (!COVIDTracker.removeIndividual(posFull, negFull, "CD2345"))
      return false;
    // removes "CD2345" from both arrays -> true
    // removing from two partially filled arrays -> true
    if (!COVIDTracker.removeIndividual(pos, neg, "CD2345"))
      return false;
    // removing from two partially filled arrays not having the id -> false
    if (COVIDTracker.removeIndividual(pos, neg, "CD2345"))
      return false;
    // removes "EF3456" from both arrays -> true
    if (!COVIDTracker.removeIndividual(pos, neg, "EF3456"))
      return false;
    // removes "AB1234" from both arrays -> true
    if (!COVIDTracker.removeIndividual(pos, neg, "AB1234"))
      return false;
    // removing from two empty arrays -> false
    if (COVIDTracker.removeIndividual(pos, neg, "AB1234"))
      return false;
    return true;
  }

  /**
   * Checks whether getPopStats() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testGetPopStats() {
    String[] pos =
        new String[] {"CD2345", null, null, "AB1234", null, "EF3456", "CD2345", null, "EF3456"};
    String[] neg =
        new String[] {null, "CD2345", "AB1234", null, "EF3456", "AB1234", null, null, "EF3456"};
    String[] pos2 = new String[3];
    String[] neg2 = new String[5];
    String[] posFull = new String[] {"CD2345", "AB1234", "AB1234", "CD2345", "AB1234"};
    String[] negFull = new String[] {"AB1234", "CD2345", "AB1234", "EF3456", "EF3456"};

    // two completely filled arrays -> true
    if (!COVIDTracker.getPopStats(posFull, negFull).equals(
        "Total tests: 10\nTotal individuals tested: 3\nPercent positive tests: 50.0%\nPercent "
            + "positive individuals: 66.66666666666666%"))
      return false;
    // two partially filled arrays -> true
    if (!COVIDTracker.getPopStats(pos, neg).equals(
        "Total tests: 10\nTotal individuals tested: 3\nPercent positive tests: 50.0%\nPercent "
            + "positive individuals: 100.0%"))
      return false;
    // one empty array and one partially filled array -> true
    if (!COVIDTracker.getPopStats(pos2, neg).equals(
        "Total tests: 5\nTotal individuals tested: 3\nPercent positive tests: 0.0%\nPercent "
            + "positive individuals: 0.0%"))
      return false;
    // two empty arrays -> true
    if (!COVIDTracker.getPopStats(pos2, neg2).equals(
        "Total tests: 0\nTotal individuals tested: 0\nPercent positive tests: 0.0%\nPercent "
            + "positive individuals: 0.0%"))
      return false;
    return true;
  }

  /**
   * Checks whether getIndividualStats() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testGetIndividualStats() {
    String[] pos =
        new String[] {"CD2345", null, null, "AB1234", null, "EF3456", "CD2345", null, "EF3456"};
    String[] neg =
        new String[] {null, "CD2345", "AB1234", null, "EF3456", "AB1234", null, null, "EF3456"};
    String[] pos2 = new String[3];
    String[] neg2 = new String[5];
    String[] posFull = new String[] {"CD2345", "AB1234", "AB1234", "CD2345", "AB1234"};
    String[] negFull = new String[] {"AB1234", "CD2345", "AB1234", "EF3456", "EF3456"};

    // two completely filled arrays -> true
    if (!COVIDTracker.getIndividualStats(posFull, negFull, "AB1234")
        .equals("Total tests: 5\nPositive: 3\nNegative: 2"))
      return false;
    // two empty arrays -> true
    if (!COVIDTracker.getIndividualStats(pos2, neg2, "AB1234")
        .equals("Total tests: 0\nPositive: 0\nNegative: 0"))
      return false;
    // non-existent id should have 0 total, positive, and negative tests -> true
    if (!COVIDTracker.getIndividualStats(pos, neg, "GH5678")
        .equals("Total tests: 0\nPositive: 0\nNegative: 0"))
      return false;
    // two partially filled arrays -> true
    if (!COVIDTracker.getIndividualStats(pos, neg, "CD2345")
        .equals("Total tests: 3\nPositive: 2\nNegative: 1"))
      return false;
    // one empty array and one partially filled array -> true
    if (!COVIDTracker.getIndividualStats(pos, neg2, "EF3456")
        .equals("Total tests: 2\nPositive: 2\nNegative: 0"))
      return false;
    return true;
  }
}
