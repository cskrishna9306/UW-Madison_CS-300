//////////////// FILE HEADER //////////////////////////
//
// Title: Program 05 Ballot Box (Ballot Box)
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

/**
 * This class creates all the necessary fields and methods to model the reference type BallotBox.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class BallotBox {
  // instance fields
  private ArrayList<Ballot> ballots; // array list of the ballots which have been cast

  /**
   * Creates a new BallotBox object
   */
  public BallotBox() {
    ballots = new ArrayList<Ballot>();
  }

  /**
   * Returns the total number of unique ballots in this ballot box
   * 
   * @return the size of ballots array list
   */
  public int getNumBallots() {
    return ballots.size();
  }

  /**
   * Records all of the existing ballots'votes and determines the winner for the given office
   * 
   * @param office the office for which the winner must be determined
   * @return the candidate with the most votes
   */
  public Candidate getWinner(String office) {
    if (office != null && ballots != null) {
      if (Ballot.getCandidates(office).size() != 0) {
        ArrayList<Candidate> candidates = Ballot.getCandidates(office);
        int[] count = new int[candidates.size()];
        // count the votes for each candidate
        for (int i = 0; i < candidates.size(); i++) {
          for (int j = 0; j < getNumBallots(); j++) {
            if (ballots.get(j).getVote(office).toString().equals(candidates.get(i).toString()))
              count[i]++;
          }
        }

        int index = 0;
        boolean votesReceived = false;
        // gets the index of the candidate with majority of votes and checks whether none of the
        // candidates received any votes
        for (int i = 0; i < count.length; i++) {
          if (count[i] > 0)
            votesReceived = true;
          if (count[i] > count[index])
            index = i;
        }

        if (votesReceived)
          return candidates.get(index);
        else    // returns null if none of the candidates receive any votes
          return null;
      }
      return null;  // returns null if there are no candidates for the given office
    }
    return null;
  }

  /**
   * Adds a ballot to the ballot box if and only if the ballot has not already been added
   * 
   * @param b the ballot to be added
   */
  public void submit(Ballot b) {
    if (b != null) {
      if (!ballots.contains(b)) // adds the ballot b if it has not already been added
        ballots.add(b);
    }
  }

}
