//////////////// FILE HEADER //////////////////////////
//
// Title: Program 06 Treasure Hunt (Visible Object)
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

import java.io.File;
import processing.core.PImage;

/**
 * This class creates all the necessary fields and methods to model the reference type
 * VisibleObject. This class is a direct sub-class of InteractiveObject and inherits all the
 * protected/public fields and methods of this reference type.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class VisibleObject extends InteractiveObject {
  private PImage image; // the graphical representation of this object
  private int x; // the horizontal position (in pixels of this object's left side)
  private int y; // the vertical position (in pixels of this object's top side)

  /**
   * Creates and initializes new VisibleObject object with given name and (x,y) coordinates along
   * with loading the image for this visible object
   * 
   * @param name name of this visible object
   * @param x    horizontal position (in pixels of this object's left side)
   * @param y    vertical position (in pixels of this object's top side)
   */
  public VisibleObject(String name, int x, int y) {
    super(name); // calls the parent class constructor
    this.x = x;
    this.y = y;
    this.image = getProcessing().loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Draws image of this visible object at its position
   * 
   * @return null after drawing image at its position
   */
  @Override
  public Action update() {
    getProcessing().image(image, x, y);
    return null;
  }

  /**
   * Moves this object by adding dx to x and dy to y
   * 
   * @param dx the change in position of this object along the horizontal axis
   * @param dy the change in position of this object along the vertical axis
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  /**
   * Checks whether the point (x,y) is over the image of this visible object
   * 
   * @param x horizontal position of this point
   * @param y vertical position of this point
   * @return true if the point (x,y) is over the image, false otherwise
   */
  public boolean isOver(int x, int y) {
    if (x >= this.x && x <= (this.x + this.image.width)) {
      if (y >= this.y && y <= (this.y + this.image.height)) {
        return true;
      }
      return false;
    }
    return false;
  }

  /**
   * Checks whether another visible object's image overlaps this one's image
   * 
   * @param other another visible object whose image has to be checked for overlapping
   * @return true if other's image overlaps this one's, false otherwise
   */
  public boolean isOver(VisibleObject other) {
    if (other != null) { // checks for null references to avoid NullPointerException
      if (this.image.width <= other.image.width && this.image.height <= other.image.height) {
        if (this.x >= other.x && (this.x + this.image.width) <= (other.x + other.image.width)) {
          if (this.y >= other.y && (this.y + this.image.height) <= (other.y + other.image.height))
            return true;
          return false;
        }
        return false;
      } else {
        if (this.x >= other.x && (this.x) <= (other.x + other.image.width)) {
          if (this.y >= other.y && (this.y) <= (other.y + other.image.height))
            return true;
          return false;
        }
        return false;
      }
    }
    return false;
  }
}
