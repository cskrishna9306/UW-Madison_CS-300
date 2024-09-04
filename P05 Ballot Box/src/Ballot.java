//////////////// FILE HEADER //////////////////////////
//
// Title: Program 05 Ballot Box (Ballot)
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class creates all the necessary fields and methods to model the reference type Ballot.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Ballot {
  // static fields
  private static ArrayList<Party> parties = new ArrayList<Party>(); // array list containing the
                                                                    // parties for the election
  private static int counter = 12903;
  // instance fields
  private Candidate[] votes; // ballot's vote for each office
  private final int ID; // unique id of this ballot

  /**
   * Creates a new Ballot object
   */
  public Ballot() {
    votes = new Candidate[Candidate.OFFICE.length];
    ID = counter;
    counter++;
  }

  /**
   * Gets the vote for a given office that this ballot has voted for
   * 
   * @param office the office to be searched
   * @return the candidate that this ballot has voted for, or null if there is no vote for that
   *         office
   */
  public Candidate getVote(String office) {
    // searches the votes array to find the vote for a given office
    for (int i = 0; i < votes.length; i++) {
      if (office.equals(Candidate.OFFICE[i]))
        return votes[i];
    }
    // returns null if no match was found for the given office
    return null;
  }


  /**
   * Returns true if the argument is equal to this ballot, false otherwise
   * 
   * @param o the object to compare to this ballot
   * @return true if the Ballots have the same ID, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Ballot) {
      if (((Ballot) o).ID == this.ID)
        return true;
      else
        return false;
    } else
      return false;
  }

  /**
   * Records the vote for the given candidate at the appropriate index in the votes array
   * 
   * @param c the candidate for which the ballot voted
   */
  public void vote(Candidate c) {
    if (c != null) {
      for (int i = 0; i < votes.length; i++) {
        if (c.getOffice().equals(Candidate.OFFICE[i]))
          votes[i] = c;
      }
    }
  }

  /**
   * Adds a party to the parties array list
   * 
   * @param p the party to be added
   */
  public static void addParty(Party p) {
    if (p != null && parties != null) {
      boolean unique = true;
      // checks whether the parties array list already contains party
      for (int i = 0; i < parties.size(); i++) {
        if (parties.get(i).getName().equals(p.getName()))
          unique = false;
      }
      // adds the given party if it has not been already added to the parties array list
      if (unique)
        parties.add(p);
    }
  }

  /**
   * Creates and returns an array list of all candidates running for the given office
   * 
   * @param office the office to be searched among all the candidates
   * @return An array list of all candidates running for the given office
   */
  public static ArrayList<Candidate> getCandidates(String office) {
    ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    if (office != null) {
      for (int i = 0; i < parties.size(); i++) {
        if (parties.get(i).getSize() != 0) {
          try {
            candidates.add(parties.get(i).getCandidate(office));
          } catch (NoSuchElementException e) {

          }
        }
      }
      return candidates;
    }
    return null;
  }
}
