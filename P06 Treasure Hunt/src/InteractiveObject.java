//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Interactive Object)
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

import processing.core.PApplet;

/**
 * This class creates all the necessary fields and methods to model the reference type
 * InteractiveObject.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class InteractiveObject {
  // instance fields
  private final String NAME; // the constant name identifying this interactive object
  private boolean isActive; // active means this interactive object is visible and can be interacted
                            // with
  // static fields
  private static PApplet processing = null;

  /**
   * Creates and initializes new InteractiveObject object with given name and sets isActive to true
   * 
   * @param name name of this interactive object
   */
  public InteractiveObject(String name) {
    NAME = name;
    isActive = true;
  }

  /**
   * Checks whether this object's name matches the given name
   * 
   * @param name the name to be compared with this object's name
   * @return true if contents of the given name equal this object's name, false otherwise
   */
  public boolean hasName(String name) {
    if (name != null)
      return name.equals(NAME);
    return false;
  }

  /**
   * Checks whether this object's isActive is true
   *
   * @return true if isActive is true, false otherwise
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Changes isActive to true
   */
  public void activate() {
    isActive = true;
  }

  /**
   * Changes isActive to false
   */
  public void deactivate() {
    isActive = false;
  }

  /**
   * Returns action objects and this method is overridden in the subclasses of InteractiveObject
   * 
   * @return null
   */
  public Action update() {
    return null;
  }

  /**
   * Initializes this object's processing field with given PApplet reference type
   *
   * @param processing this object's processing static field
   */
  public static void setProcessing(PApplet processing) {
    InteractiveObject.processing = processing;
  }

  /**
   * Getter method to retrieve the processing static field
   * 
   * @return this object's processing static field
   */
  protected static PApplet getProcessing() {
    return processing;
  }
}
