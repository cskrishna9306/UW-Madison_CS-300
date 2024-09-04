//////////////// FILE HEADER //////////////////////////
//
// Title: Program 05 Ballot Box (Ballot Box Tester)
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

import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class contains methods to test the public methods of all classes. This class also contains
 * the main method to check whether each test evaluates to true or false.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class BallotBoxTester {
  /**
   * Calls the test methods implemented in this class and displays either true or false as output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // Testing Candidate class methods
    System.out.println("testCandidate()\t\t\t: " + testCandidate());
    // Testing Party class methods
    System.out.println("testPartyConstructor()\t\t: " + testPartyConstructor());
    System.out.println("testPartyGetCandidate()\t\t: " + testPartyGetCandidate());
    System.out.println("testPartyGetSize()\t\t: " + testPartyGetSize());
    // Testing Ballot class methods
    System.out.println("testBallotConstructor()\t\t: " + testBallotConstructor());
    System.out.println("testBallotGetVote()\t\t: " + testBallotGetVote());
    System.out.println("testBallotGetCandidates()\t: " + testBallotGetCandidates());
    // Testing BallotBox class methods
    System.out.println("testBallotBoxSubmit()\t\t: " + testBallotBoxSubmit());
    System.out.println("testBallotBoxGetWinner()\t: " + testBallotBoxGetWinner());
  }

  /**
   * Checks whether all the public methods in Candidate class work as expected
   *
   * @return true if the class Candidate passes all test scenarios, false otherwise
   */
  public static boolean testCandidate() {
    try {
      // Scenario 1 - candidate objects are created without throwing any exceptions
      Candidate c1 = new Candidate("Barack Obama", "President");
      Candidate c2 = new Candidate("Joe Biden", "Vice President");
      // Scenario 2 - getName(), getOffice(), and toString() methods of Candidate class work as
      // expected
      if (!c1.getName().equals("Barack Obama") || !c2.getName().equals("Joe Biden"))
        return false;
      if (!c1.getOffice().equals("President") || !c2.getOffice().equals("Vice President"))
        return false;
      if (!c1.toString().equals("Barack Obama (President)")
          || !c2.toString().equals("Joe Biden (Vice President)"))
        return false;
      // Scenario 3 - candidate object throws an exception if it is defined with an invalid office
      try {
        Candidate c3 = new Candidate("Hillary Clinton", "Secretary of Defense");
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
   * Checks whether the constructor in Party class works as expected
   *
   * @return true if the constructor of class Party passes all test scenarios, false otherwise
   */
  public static boolean testPartyConstructor() {
    try {
      // Scenario 1 - creates two party objects without any problems
      Party p1 = new Party("Republicans");
      Party p2 = new Party("Democrats");
      // Scenario 2 - getName() method of class Party works as expected
      if (!p1.getName().equals("Republicans") || !p2.getName().equals("Democrats"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the methods getSize() and addCandidate() in Party class work as expected
   *
   * @return true if the getSize() and addCandidate() methods in class Party pass all test
   *         scenarios, false otherwise
   */
  public static boolean testPartyGetSize() {
    try {
      Party p1 = new Party("Republicans");
      Party p2 = new Party("Democrats");
      Candidate c1 = new Candidate("Barack Obama", "President");
      Candidate c2 = new Candidate("Joe Biden", "Vice President");
      // Scenario 1 - adds a candidate to a party and checks if it was successfully added
      p2.addCandidate(c1);
      if (p1.getSize() != 0 || p2.getSize() != 1)
        return false;
      p2.addCandidate(c2);
      // Scenario 2 - adds a candidate to a party with an already existing candidate running for the
      // same office
      Candidate c3 = new Candidate("Donald Trump", "President");
      try {
        p2.addCandidate(c3);
        return false;
      } catch (IllegalArgumentException e) {
        // Expected behavior -- no problem detected
        if (p2.getSize() == 3 || p1.getSize() != 0)
          return false;
      }

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the method getCandidate() in Party class works as expected
   *
   * @return true if the getCandidate() method in class Party passes all test scenarios, false
   *         otherwise
   */
  public static boolean testPartyGetCandidate() {
    try {
      Party p = new Party("Democrats");
      Candidate c1 = new Candidate("Barack Obama", "President");
      Candidate c2 = new Candidate("Joe Biden", "Vice President");
      p.addCandidate(c1);
      p.addCandidate(c2);
      // Scenario 1 - the getCandidate() method returns the expected candidates
      if (!p.getCandidate("President").getName().equals("Barack Obama")
          || !p.getCandidate("Vice President").getName().equals("Joe Biden"))
        return false;
      // Scenario 2 - getCandidate() throws an exception if asked to search for an office with no
      // candidates
      try {
        p.getCandidate("Secretary");
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
   * Checks whether the constructor in Ballot class works as expected
   *
   * @return true if the constructor of class Ballot passes all test scenarios, false otherwise
   */
  public static boolean testBallotConstructor() {
    try {
      // Scenario 1 - creates two Ballot objects without any problems
      Ballot b1 = new Ballot();
      Ballot b2 = new Ballot();
      // Scenario 2 - checks whether the two ballots are unique
      if (b1.equals(b2))
        return false;
      if (!b1.equals(b1))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the methods vote() and getVote() in Ballot class work as expected
   *
   * @return true if the vote() and getVote() methods of class Ballot pass all test scenarios, false
   *         otherwise
   */
  public static boolean testBallotGetVote() {
    try {
      Ballot b = new Ballot();
      Candidate c1 = new Candidate("Joe Biden", "President");
      Candidate c2 = new Candidate("Kamala Harris", "Vice President");
      Candidate c3 = new Candidate("Donald Trump", "President");
      Candidate c4 = new Candidate("Mike Pence", "Vice President");
      Party p1 = new Party("Democrats");
      Party p2 = new Party("Republicans");
      p1.addCandidate(c1);
      p1.addCandidate(c2);
      p2.addCandidate(c3);
      p2.addCandidate(c4);
      Ballot.addParty(p1);
      Ballot.addParty(p2);
      // Scenario 1 - records and checks the votes for two candidates in a ballot
      b.vote(c1);
      b.vote(c2);
      if (!b.getVote("President").getName().equals("Joe Biden"))
        return false;
      if (!b.getVote("Vice President").getName().equals("Kamala Harris"))
        return false;
      // Scenario 2 - checks the vote for an office with no votes yet
      if (b.getVote("Secretary") != null)
        return false;
      // Scenario 3 - checks whether a new vote overwrites an already existing vote for an office
      b.vote(c3);
      if (!b.getVote("President").getName().equals("Donald Trump"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the static methods addParty() and getCandidates() in Ballot class work as
   * expected
   *
   * @return true if the addParty() and getCandidates() methods of class Ballot pass all test
   *         scenarios, false otherwise
   */
  public static boolean testBallotGetCandidates() {
    try {
      Candidate c1 = new Candidate("Joe Biden", "President");
      Candidate c2 = new Candidate("Kamala Harris", "Vice President");
      Candidate c3 = new Candidate("Donald Trump", "President");
      Candidate c4 = new Candidate("Mike Pence", "Vice President");
      Party p1 = new Party("Democrats");
      Party p2 = new Party("Republicans");
      p1.addCandidate(c1);
      p1.addCandidate(c2);
      p2.addCandidate(c3);
      p2.addCandidate(c4);
      Ballot.addParty(p1);
      Ballot.addParty(p2);
      // Scenario 1 - gets the array list of potential candidates running for a given office
      ArrayList<Candidate> candidates = Ballot.getCandidates("Vice President");
      if (!candidates.get(0).toString().equals(c2.toString())
          || !candidates.get(1).toString().equals(c4.toString()))
        return false;
      candidates = Ballot.getCandidates("President");
      if (!candidates.get(0).toString().equals(c1.toString())
          || !candidates.get(1).toString().equals(c3.toString()))
        return false;
      // Scenario 2 - gets the candidates for an office with no candidates
      candidates = Ballot.getCandidates("Secretary");
      if (candidates.size() != 0)
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the methods submit() and getNumBallots() in BallotBox class work as expected
   *
   * @return true if the submit() and getNumBallots() methods of class BallotBox pass all test
   *         scenarios, false otherwise
   */
  public static boolean testBallotBoxSubmit() {
    try {
      BallotBox bb = new BallotBox();
      if (bb.getNumBallots() != 0)
        return false;
      Ballot b1 = new Ballot();
      Ballot b2 = new Ballot();
      // Scenario 1 - checks whether two not null ballots are added into the ballot box
      bb.submit(b1);
      bb.submit(b2);
      if (bb.getNumBallots() != 2)
        return false;
      // Scenario 2 - tests whether an already present ballot will not be added into the ballot box
      bb.submit(b1);
      if (bb.getNumBallots() != 2)
        return false;
      // Scenario 3 - adding a null ballot will not alter the number of ballots in the ballot box
      bb.submit(null);
      if (bb.getNumBallots() != 2)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the method getWinner() in BallotBox class works as expected
   *
   * @return true if the getWinner() method of class BallotBox passes all test scenarios, false
   *         otherwise
   */
  public static boolean testBallotBoxGetWinner() {
    try {
      BallotBox bb = new BallotBox();
      Ballot b1 = new Ballot();
      Ballot b2 = new Ballot();
      Ballot b3 = new Ballot();
      Ballot b4 = new Ballot();
      Ballot b5 = new Ballot();
      Ballot b6 = new Ballot();
      Candidate c1 = new Candidate("Joe Biden", "President");
      Candidate c2 = new Candidate("Kamala Harris", "Vice President");
      Candidate c3 = new Candidate("Donald Trump", "President");
      Candidate c4 = new Candidate("Mike Pence", "Vice President");
      Party p1 = new Party("Democrats");
      Party p2 = new Party("Republicans");
      p1.addCandidate(c1);
      p1.addCandidate(c2);
      p2.addCandidate(c3);
      p2.addCandidate(c4);
      Ballot.addParty(p1);
      // Scenario 1 - get the winner where none of the candidates received any votes
      if (bb.getWinner("President") != null)
        return false;
      b1.vote(c1);
      b2.vote(c3);
      b3.vote(c3);
      b4.vote(c3);
      b5.vote(c3);
      b6.vote(c1);
      b1.vote(c4);
      b2.vote(c4);
      b3.vote(c4);
      b4.vote(c4);
      b5.vote(c2);
      b6.vote(c2);
      bb.submit(b1);
      // Scenario 2 - gets the winner when only one ballot is submitted
      if (!bb.getWinner("President").toString().equals(c1.toString()))
        return false;
      Ballot.addParty(p2);
      if (!bb.getWinner("President").toString().equals(c1.toString()))
        return false;
      bb.submit(b2);
      bb.submit(b3);
      bb.submit(b4);
      bb.submit(b5);
      bb.submit(b6);
      // Scenario 3 - get the winner where votes are distributed unequally among two candidates
      if (!bb.getWinner("President").toString().equals(c3.toString())
          || !bb.getWinner("Vice President").toString().equals(c4.toString()))
        return false;

      b3.vote(c1);
      b3.vote(c2);
      // Scenario 4 - get the winner where votes are distributed equally among two candidates
      if (!bb.getWinner("President").toString().equals(c1.toString())
          || !bb.getWinner("Vice President").toString().equals(c2.toString()))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
