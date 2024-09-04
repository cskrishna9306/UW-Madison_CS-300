//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Clickable Object)
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
 * This class creates all the necessary fields and methods to model the reference type
 * ClickableObject. This class is a direct sub-class of VisibleObject and inherits all the
 * protected/public fields and methods of this reference type.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class ClickableObject extends VisibleObject {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()

  /**
   * Creates and initializes new ClickableObject object with given name, (x,y) coordinates, and
   * action along with setting mouseWasPressed to false
   * 
   * @param name   name of this clickable object
   * @param x      horizontal position (in pixels of this object's left side)
   * @param y      vertical position (in pixels of this object's top side)
   * @param action action returned when this object is clicked
   */
  public ClickableObject(String name, int x, int y, Action action) {
    super(name, x, y); // calls the parent class constructor
    this.action = action;
    mouseWasPressed = false;
  }

  /**
   * Calls VisibleObject's update() and returns this object's action when this object is first
   * clicked
   * 
   * @return action only when mouse is first clicked on this object, null otherwise
   */
  @Override
  public Action update() {
    super.update(); // calls parent class's update method
    // returns this object's action only when the mouse is first clicked over multiple times
    if (getProcessing().mousePressed && isOver(getProcessing().mouseX, getProcessing().mouseY)) {
      if (!mouseWasPressed) {
        mouseWasPressed = true;
        return action;
      }
      mouseWasPressed = true;
    } else
      mouseWasPressed = false;

    return null;
  }
}
