import java.io.File;

//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Draggable Object)
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
 * DraggableObject. This class is a direct sub-class of VisibleObject and inherits all the
 * protected/public fields and methods of this reference type.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class DraggableObject extends VisibleObject {
  private boolean mouseWasPressed; // similar to use in ClickableObject
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * Creates and initializes new DraggableObject object with given name and (x,y) coordinates along
   * with initializing the horizontal and vertical position of mouse during last update
   * 
   * @param name name of this draggable object
   * @param x    horizontal position (in pixels of this object's left side)
   * @param y    vertical position (in pixels of this object's top side)
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y); // calls the parent class constructor
    oldMouseX =
        (x + (getProcessing().loadImage("images" + File.separator + name + ".png").width / 2));
    oldMouseY =
        (y + (getProcessing().loadImage("images" + File.separator + name + ".png").height / 2));
    mouseWasPressed = false;
    isDragging = false;
  }

  /**
   * Calls VisibleObject's update(), and then moves according to mouse drag
   * 
   * @return the action objects returned from drop() each time isDragging changes from true to
   *         false, null otherwise
   */
  @Override
  public Action update() {
    super.update(); // calls parent class's update method
    // starts dragging this object when it is first clicked upon
    if (getProcessing().mousePressed && isOver(getProcessing().mouseX, getProcessing().mouseY)) {
      if (!mouseWasPressed) {
        isDragging = true;
        oldMouseX = getProcessing().mouseX;
        oldMouseY = getProcessing().mouseY;
      }
      mouseWasPressed = true;
    } else
      mouseWasPressed = false;

    // keeps dragging this object until the mouse is pressed
    if (isDragging && getProcessing().mousePressed) {
      isDragging = true;
      move(getProcessing().mouseX - oldMouseX, getProcessing().mouseY - oldMouseY);
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;
    }

    // returns action returned from drop() method whenever this object stops being dragged
    if (!getProcessing().mousePressed && isDragging) {
      isDragging = false;
      return drop();
    }

    return null;
  }

  /**
   * Returns action objects and this method is overridden in the subclasses of DraggableObject
   * 
   * @return null
   */
  protected Action drop() {
    return null;
  }
}
