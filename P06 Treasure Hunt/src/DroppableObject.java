//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Droppable Object)
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
 * DroppableObject. This class is a direct sub-class of DraggableObject and inherits all the
 * protected/public fields and methods of this reference type.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class DroppableObject extends DraggableObject {
  private VisibleObject target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object over target

  /**
   * Creates and initializes new DroppableObject object with given name, (x,y) coordinates, target,
   * and action
   * 
   * @param name   name of this droppable object
   * @param x      horizontal position (in pixels of this object's left side)
   * @param y      vertical position (in pixels of this object's top side)
   * @param target object over which this object can be dropped
   * @param action action that results from dropping this object over target
   */
  public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
    super(name, x, y); // calls the parent class constructor
    this.target = target;
    this.action = action;
  }

  /**
   * Deactivates itself and its target in response to a successful drop
   * 
   * @return this object's action in response to successful drop, null otherwise
   */
  @Override
  protected Action drop() {
    // checks if this object's image overlaps target's image and returns the associated actions
    // while deactivating the two objects
    if (isOver(target) && target.isActive()) {
      this.deactivate();
      target.deactivate();
      return action;
    }
    return null;
  }
}
