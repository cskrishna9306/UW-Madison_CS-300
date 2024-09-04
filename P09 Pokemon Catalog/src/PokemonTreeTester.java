//////////////// FILE HEADER //////////////////////////
//
// Title: Program 09 Pokemon Catalog (Pokemon Tree Tester)
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

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PokemonTree.
 *
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class PokemonTreeTester {

  /**
   * Checks the correctness of the implementation of both addPokemon() and toString() methods
   * implemented in the PokemonTree class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonToStringSize() {
    try {
      // Scenario 1 - size(), isEmpty(), and toString() work as expected on an empty PokemonTree
      PokemonTree bst = new PokemonTree();
      if (bst.size() != 0 || !bst.isEmpty() || !bst.toString().equals(""))
        return false;
      // Scenario 2 - addPokemon() works as expected on an empty PokemonTree
      Pokemon p1 = new Pokemon("Test 1", "1,2,3");
      if (!bst.addPokemon(p1) || bst.isEmpty() || bst.size() != 1
          || !bst.toString().equals(p1 + "\n"))
        return false;
      // Scenario 3 - adding a 2nd Pokemon more powerful than the one at the root
      Pokemon p2 = new Pokemon("Test 2", "2,2,4");
      if (!bst.addPokemon(p2) || bst.isEmpty() || bst.size() != 2
          || !bst.toString().equals(p1 + "\n" + p2 + "\n"))
        return false;
      // Scenario 4 - adding a 3rd Pokemon less powerful than the one at the root
      Pokemon p3 = new Pokemon("Test 3", "1,1,1");
      if (!bst.addPokemon(p3) || bst.isEmpty() || bst.size() != 3
          || !bst.toString().equals(p3 + "\n" + p1 + "\n" + p2 + "\n"))
        return false;
      // Scenario 5 - adding 1 Pokemon to both the left and right subtrees
      Pokemon p4 = new Pokemon("Test 4", "1,2,2");
      Pokemon p5 = new Pokemon("Test 5", "5,4,6");
      if (!bst.addPokemon(p4) || bst.isEmpty() || bst.size() != 4
          || !bst.toString().equals(p3 + "\n" + p4 + "\n" + p1 + "\n" + p2 + "\n"))
        return false;
      if (!bst.addPokemon(p5) || bst.isEmpty() || bst.size() != 5
          || !bst.toString().equals(p3 + "\n" + p4 + "\n" + p1 + "\n" + p2 + "\n" + p5 + "\n"))
        return false;
      // Scenario 6 - adding a Pokemon whose CP value was used as a key for a Pokemon already stored
      // in the tree
      Pokemon p6 = new Pokemon("Test 6", "2,2,4");
      if (bst.addPokemon(p6) || bst.isEmpty() || bst.size() != 5
          || !bst.toString().equals(p3 + "\n" + p4 + "\n" + p1 + "\n" + p2 + "\n" + p5 + "\n"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the PokemonTree.lookup() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonAndLookup() {
    try {
      // Scenario 1 - lookup() throws an exception when called on an empty PokemonTree
      PokemonTree bst = new PokemonTree();
      try {
        bst.lookup(123);
        return false;
      } catch (NoSuchElementException e) {
        // Expected behavior -- no problem detected
        if (!e.getMessage().equals("Error! No pokemon with given CP was found in the BST."))
          return false;
      }
      // Scenario 2 - lookup() works as expected on a PokemonTree of height 3 and which consists of
      // at least 5 PokemonNodes
      Pokemon p1 = new Pokemon("Test 1", "1,2,3");
      Pokemon p2 = new Pokemon("Test 2", "2,2,4");
      Pokemon p3 = new Pokemon("Test 3", "1,1,1");
      Pokemon p4 = new Pokemon("Test 4", "1,2,2");
      Pokemon p5 = new Pokemon("Test 5", "5,4,6");
      bst.addPokemon(p1);
      bst.addPokemon(p2);
      bst.addPokemon(p3);
      bst.addPokemon(p4);
      bst.addPokemon(p5);
      if (bst.lookup(123) != p1)
        return false;
      if (bst.lookup(546) != p5)
        return false;
      if (bst.lookup(111) != p3)
        return false;
      // Scenario 3 - lookup() throws an exception when called on a non-empty PokemonTree with a CP
      // value not stored in the PokemonTree
      try {
        bst.lookup(723);
        return false;
      } catch (NoSuchElementException e) {
        // Expected behavior -- no problem detected
        if (!e.getMessage().equals("Error! No pokemon with given CP was found in the BST."))
          return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.height() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    try {
      // Scenario 1 - height() works as expected on an empty PokemonTree
      PokemonTree bst = new PokemonTree();
      if (bst.height() != 0)
        return false;
      // Scenario 2 - height() works as expected on a PokemonTree with only one node
      Pokemon p1 = new Pokemon("Test 1", "1,2,3");
      bst.addPokemon(p1);
      if (bst.height() != 1)
        return false;
      // Scenario 3 - height() works as expected on a PokemonTree of height 4 with at least 5
      // PokemonNodes
      Pokemon p2 = new Pokemon("Test 2", "2,2,4");
      Pokemon p3 = new Pokemon("Test 3", "1,1,1");
      Pokemon p4 = new Pokemon("Test 4", "1,2,2");
      Pokemon p5 = new Pokemon("Test 5", "5,4,6");
      Pokemon p6 = new Pokemon("Test 6", "2,1,3");
      Pokemon p7 = new Pokemon("Test 7", "1,2,1");
      bst.addPokemon(p1);
      bst.addPokemon(p2);
      bst.addPokemon(p3);
      bst.addPokemon(p4);
      bst.addPokemon(p5);
      bst.addPokemon(p6);
      bst.addPokemon(p7);
      if (bst.height() != 4)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLeastPowerfulPokemon() {
    try {
      // Scenario 1 - getLeastPowerful() works as expected when called on an empty PokemonTree
      PokemonTree bst = new PokemonTree();
      if (bst.getLeastPowerfulPokemon() != null)
        return false;
      // Scenario 2 - getLeastPowerful() works as expected when called on a PokemonTree with only 1
      // node
      Pokemon p1 = new Pokemon("Test 1", "1,2,3");
      bst.addPokemon(p1);
      if (bst.getLeastPowerfulPokemon() != p1)
        return false;
      // Scenario 3 - getLeastPowerful() works as expected when called on a PokemonTree with at
      // least 5 nodes
      Pokemon p2 = new Pokemon("Test 2", "2,2,4");
      Pokemon p3 = new Pokemon("Test 3", "1,1,1");
      Pokemon p4 = new Pokemon("Test 4", "1,2,2");
      Pokemon p5 = new Pokemon("Test 5", "5,4,6");
      Pokemon p6 = new Pokemon("Test 6", "2,1,3");
      Pokemon p7 = new Pokemon("Test 7", "1,2,1");
      bst.addPokemon(p1);
      bst.addPokemon(p2);
      bst.addPokemon(p3);
      bst.addPokemon(p4);
      bst.addPokemon(p5);
      bst.addPokemon(p6);
      bst.addPokemon(p7);
      if (bst.getLeastPowerfulPokemon() != p3)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetMostPowerfulPokemon() {
    try {
      // Scenario 1 - getMostPowerful() works as expected when called on an empty PokemonTree
      PokemonTree bst = new PokemonTree();
      if (bst.getMostPowerfulPokemon() != null)
        return false;
      // Scenario 2 - getMostPowerful() works as expected when called on a PokemonTree with only 1
      // node
      Pokemon p1 = new Pokemon("Test 1", "1,2,3");
      bst.addPokemon(p1);
      if (bst.getMostPowerfulPokemon() != p1)
        return false;
      // Scenario 3 - getMostPowerful() works as expected when called on a PokemonTree with at least
      // 5 nodes
      Pokemon p2 = new Pokemon("Test 2", "2,2,4");
      Pokemon p3 = new Pokemon("Test 3", "1,1,1");
      Pokemon p4 = new Pokemon("Test 4", "1,2,2");
      Pokemon p5 = new Pokemon("Test 5", "5,4,6");
      Pokemon p6 = new Pokemon("Test 6", "2,1,3");
      Pokemon p7 = new Pokemon("Test 7", "1,2,1");
      bst.addPokemon(p1);
      bst.addPokemon(p2);
      bst.addPokemon(p3);
      bst.addPokemon(p4);
      bst.addPokemon(p5);
      bst.addPokemon(p6);
      bst.addPokemon(p7);
      if (bst.getMostPowerfulPokemon() != p5)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Calls all the tester methods implemented in this class.
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddPokemonToStringSize()\t:" + testAddPokemonToStringSize());
    System.out.println("testAddPokemonAndLookup()\t:" + testAddPokemonAndLookup());
    System.out.println("testHeight()\t\t\t:" + testHeight());
    System.out.println("testGetLeastPowerfulPokemon()\t:" + testGetLeastPowerfulPokemon());
    System.out.println("testGetMostPowerfulPokemon()\t:" + testGetMostPowerfulPokemon());
  }
}
