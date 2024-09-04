//////////////// FILE HEADER //////////////////////////
//
// Title: Program 02 Wisconsin Prairie
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

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class contains methods to add and remove cows from the screen(prairie). This class gives the
 * user freedom to manipulate the position of cows at the command of their mouse. This class also
 * contains the main method which calls all the required callback methods.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class WisconsinPrairie {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the WisconsinPrairie application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Cow[] cows; // array storing the current cows present
  // in the Prairie
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed
                                // into the input argument parameter.

    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    cows = new Cow[10];
    randGen = new Random();
  }

  /**
   * Draws and updates the application display window. This callback method calls an infinite loop.
   */
  public static void draw() {
    // Draws the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores
    // the width [resp. height] of the display window.

    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
        cows[i].draw(); // Draws all cows to the screen
      }
    }
  }

  /**
   * Checks if the mouse is over a given cow whose reference is provided as input parameter
   *
   * @param cow reference to a given cow object
   * @return true if the mouse is over the given cow object (i.e. over the image of the cow), false
   *         otherwise
   */
  public static boolean isMouseOver(Cow cow) {
    if (cow != null) { // returns false if the mouse is present over a null Cow object
      if ((processing.mouseX <= (cow.getPositionX() + (cow.getImage().width / 2)))
          && (processing.mouseX >= (cow.getPositionX() - (cow.getImage().width / 2)))) {
        if ((processing.mouseY <= (cow.getPositionY() + (cow.getImage().height / 2)))
            && (processing.mouseY >= (cow.getPositionY() - (cow.getImage().height / 2))))
          return true; // returns true if the mouse is over the image of a cow
        else
          return false;
      } else
        return false;
    } else
      return false;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < cows.length; i++) {
      if (isMouseOver(cows[i])) { // Drags the cow image as long as the user presses the mouse
        cows[i].setDragging(isMouseOver(cows[i]));
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
        cows[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    // adds a new cow to the array if the key 'c' or 'C' is pressed
    if (processing.key == 'c' || processing.key == 'C') {
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] == null) {
          float x = (float) randGen.nextInt(processing.width); // generates a random x-position of
                                                               // type
          // float within the width of the display window
          float y = (float) randGen.nextInt(processing.height);// generates a random y-position of
                                                               // type
          // float within the height of the display window

          cows[i] = new Cow(processing, x, y); // creates and assigns random positions to newly
                                               // added cows
          break;
        }
      }
    }

    // removes cows from the screen if the key 'd' or 'D' is pressed while the mouse is over a cow
    if (processing.key == 'd' || processing.key == 'D') {
      for (int i = 0; i < cows.length; i++) {
        if (isMouseOver(cows[i])) {
          cows[i] = null; // sets the cow object in the array to null
          break;
        }
      }
    }
  }

}
