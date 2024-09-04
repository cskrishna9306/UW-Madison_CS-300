//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Action)
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
 * This class creates all the necessary fields and methods to model the reference type Action.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Action {
  private String message; // message printed by this action (or null to do nothing)
  private InteractiveObject object;

  /**
   * Creates and initializes new Action object with given message
   * 
   * @param message the message stored in this action
   */
  public Action(String message) {
    this.message = message;
  }

  /**
   * Creates and initializes new Action object with given interactive object
   * 
   * @param object the interactive object associated with this action
   */
  public Action(InteractiveObject object) {
    this.object = object;
  }

  /**
   * Creates and initializes new Action object with given message and interactive object
   * 
   * @param message the message stored in this action
   * @param object  the interactive object associated with this action
   */
  public Action(String message, InteractiveObject object) {
    this.message = message;
    this.object = object;
  }

  /**
   * Prints this action's message if it is not null, and adds this action's object to an array list
   * of interactive objects
   * 
   * @param objects array list of interactive objects
   */
  public void act(ArrayList<InteractiveObject> objects) {
    if (message != null)
      System.out.println(message);

    // activates and adds the object associated with this action to the current array list of
    // activated game objects
    if (object != null) {
      object.activate();
      objects.add(object);
      object = null;
    }
  }
}
