//////////////// FILE HEADER //////////////////////////
//
// Title: Program 05 Ballot Box (Candidate)
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
 * This class creates all the necessary fields and methods to model the reference type Candidate.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Candidate {
  // static fields
  protected static final String[] OFFICE = {"President", "Vice President", "Secretary"};
  // instance fields
  private String name; // candidate's name
  private String office; // candidate's office

  /**
   * Creates a new Candidate object with a given name and office
   * 
   * @param name   name of this candidate
   * @param office office of this candidate
   * @throws IllegalArgumentException if the given office is invalid
   */
  public Candidate(String name, String office) throws IllegalArgumentException {
    boolean valid = false;
    this.name = name;
    for (int i = 0; i < OFFICE.length; i++) { // checks whether office is valid or not
      if (office.equals(OFFICE[i]))
        valid = true;
    }
    if (!valid)
      throw new IllegalArgumentException("Error! Please enter a valid office.");
    this.office = office;
  }

  /**
   * Getter to name instance field
   * 
   * @return the name of this candidate
   */
  public String getName() {
    return name;
  }

  /**
   * Getter to office instance field
   * 
   * @return the office of this candidate
   */
  public String getOffice() {
    return office;
  }

  /**
   * Returns the name and office
   * 
   * @return A string representation of the name and office of this candidate
   */
  public String toString() {
    return name + " (" + office + ")";
  }
}
