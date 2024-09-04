//////////////// FILE HEADER //////////////////////////
//
// Title: Program 05 Ballot Box (Party)
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
 * This class creates all the necessary fields and methods to model the reference type Party.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Party {
  // instance fields
  private String name; // party's name
  private ArrayList<Candidate> candidates; // candidates affiliated with the party

  /**
   * Creates a new Party object with a given name
   * 
   * @param name name of this party
   */
  public Party(String name) {
    this.name = name;
    candidates = new ArrayList<Candidate>();
  }

  /**
   * Getter to name instance field
   * 
   * @return the name of this party
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the number of candidates affiliated with the party
   * 
   * @return the size of candidates array list
   */
  public int getSize() {
    return candidates.size();
  }

  /**
   * Finds the candidate from the party running for the given office
   * 
   * @param office the office to be searched
   * @return the size of candidates array list
   * @throws NoSuchElementException if no candidate is found for the given office
   */
  public Candidate getCandidate(String office) throws NoSuchElementException {
    for (int i = 0; i < candidates.size(); i++) {
      if (candidates.get(i).getOffice().equals(office))
        return candidates.get(i);
    }
    throw new NoSuchElementException(
        "Error! No candidate in the party is running for this office.");
  }

  /**
   * Adds a candidate to the party
   * 
   * @param c the candidate to be added
   * @throws IllegalArgumentException if the provided candidate is running for the same office as
   *                                  another member of the party
   */
  public void addCandidate(Candidate c) throws IllegalArgumentException {
    if (c != null) {
      // checks if the party doesn't have a member running for the same office
      for (int i = 0; i < candidates.size(); i++) {
        if (candidates.get(i).getOffice().equals(c.getOffice()))
          throw new IllegalArgumentException(
              "Error! Another candidate is running for the same office.");
      }
      // adds the new candidate if no other candidate from the party is running for the same office
      candidates.add(c);
    }
  }
}
