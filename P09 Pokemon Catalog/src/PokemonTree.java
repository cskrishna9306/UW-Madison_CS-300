//////////////// FILE HEADER //////////////////////////
//
// Title: Program 09 Pokemon Catalog (Pokemon Tree)
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
 * This class implements a binary search tree (BST) which stores a set of Pokemons. The left subtree
 * contains the Pokemons which are less powerful than the Pokemon stored at the parent node. The
 * right subtree contains the Pokemons which are more powerful than the Pokemon stored at the parent
 * node.
 *
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class PokemonTree {
  // instance fields
  private PokemonNode root; // root of this binary search tree
  private int size; // total number of Pokemons stored in this tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PokemonTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null && size == 0;
  }

  /**
   * Returns the number of Pokemons stored in this BST.
   * 
   * @return the size of this PokemonTree
   */
  public int size() {
    return size;
  }

  /**
   * Recursive helper method to add a new Pokemon to a PokemonTree rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Pokemon into
   * @param newPokemon The Pokemon to be added to a BST rooted at current
   * @return true if the newPokemon was successfully added to this PokemonTree, false otherwise
   */
  public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) {
    // Base Case 1 - sets the leftChild of current to newPokemon
    if (current.getLeftChild() == null && newPokemon.compareTo(current.getPokemon()) < 0) {
      current.setLeftChild(new PokemonNode(newPokemon));
      return true;
    }
    // Base Case 2 - sets the rightChild of current to newPokemon
    if (current.getRightChild() == null && newPokemon.compareTo(current.getPokemon()) > 0) {
      current.setRightChild(new PokemonNode(newPokemon));
      return true;
    }

    // Recursive Case 1 - checks the leftChild of current if newPokemon has a lesser CP than the
    // Pokemon at current
    if (newPokemon.compareTo(current.getPokemon()) < 0)
      return addPokemonHelper(newPokemon, current.getLeftChild());
    // Recursive Case 2 - checks the rightChild of current if newPokemon has a greater CP than the
    // Pokemon at current
    if (newPokemon.compareTo(current.getPokemon()) > 0)
      return addPokemonHelper(newPokemon, current.getRightChild());

    // returns false if newPokemon has the same CP value of an already present Pokemon in the BST
    return false;
  }

  /**
   * Adds a new Pokemon to this PokemonTree
   * 
   * @param newPokemon a new Pokemon to add to this BST
   * @return true if the new Pokemon was successfully added to this BST, and returns false if there
   *         is a match with this Pokemon already already stored in this BST
   */
  public boolean addPokemon(Pokemon newPokemon) {
    if (isEmpty()) { // Adds newPokemon to an empty PokemonTree
      root = new PokemonNode(newPokemon);
      size++;
      return true;
    } else { // Adds newPokemon to a non-empty PokemonTree
      if (addPokemonHelper(newPokemon, root)) {
        size++;
        return true;
      } else
        return false;
    }
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current.
   * 
   * @param current reference to the current PokemonNode within this BST
   * @return a String representation of all the Pokemons stored in the sub-tree PokemonTree rooted
   *         at current in increasing order with respect to the CP values. Returns an empty String
   *         "" if current is null
   */
  public static String toStringHelper(PokemonNode current) {
    String pokemons = new String(); // stores the string representation of all Pokemons in the
                                    // sub-tree rooted at current
    
    if (current != null) { // checks if current has a null reference
      // In-Order Traversal to store the Pokemons in the increasing order of their CP values
      if (current.getLeftChild() != null) // Recursive Case 1
        pokemons += toStringHelper(current.getLeftChild());
      pokemons += current.getPokemon().toString() + "\n"; // Base Case
      if (current.getRightChild() != null) // Recursive Case 2
        pokemons += toStringHelper(current.getRightChild());
    }
    return pokemons;
  }

  /**
   * Returns a String representation of all the Pokemons stored within this BST in the increasing
   * order, separated by a newline "\n".
   * 
   * @return a String representation of all the Pokemons stored within this BST sorted in an
   *         increasing order with respect to the CP values. Returns an empty string "" if this BST
   *         is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Search for a Pokemon (Pokemon) given the CP value as lookup key.
   * 
   * @param cp combat power of a Pokemon
   * @return the Pokemon whose CP value equals our lookup key
   * @throws a NoSuchElementException with a descriptive error message if there is no Pokemon found
   *           in this BST having the provided CP value
   */
  public Pokemon lookup(int cp) {
    return lookupHelper(cp, root);
  }

  /**
   * Recursive helper method to lookup a Pokemon given a reference Pokemon with the same CP in the
   * subtree rooted at current
   * 
   * @param cp      the CP of the Pokemon target we are looking for in the BST
   * @param current "root" of the subtree we are looking for a match to find within it
   * @return reference to the Pokemon stored stored in this BST which matches find
   * @throws NoSuchElementException with a descriptive error message if there is no Pokemon whose CP
   *                                value matches target value, stored in this BST.
   */
  public static Pokemon lookupHelper(int cp, PokemonNode current) {
    if (current == null) // Base Case 1 - checks if current has a null reference
      throw new NoSuchElementException("Error! No pokemon with given CP was found in the BST.");
    if (current.getPokemon().getCP() == cp)
      // Base Case 2 - returns the Pokemon at current if its CP matches the target CP
      return current.getPokemon();
    else if (current.getPokemon().getCP() > cp)
      // Recursive Case 1 - checks the leftChild of current for the target CP
      return lookupHelper(cp, current.getLeftChild());
    else
      // Recursive Case 2 - checks the rightChild of current for the target CP
      return lookupHelper(cp, current.getRightChild());
  }

  /**
   * Computes and returns the height of this BST, counting the number of nodes (PokemonNodes) from
   * root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current.
   * 
   * @param current pointer to the current PokemonNode within a PokemonTree
   * @return height of the subtree rooted at current, counting the number of PokemonNodes
   */
  public static int heightHelper(PokemonNode current) {
    // Base Case - stops recursion on reaching a null reference
    if (current == null)
      return 0;
    // recursively stores the height of the left subtree
    int leftChild = heightHelper(current.getLeftChild()) + 1;
    // recursively stores the height of the right subtree
    int rightChild = heightHelper(current.getRightChild()) + 1;
    // returns the greater height between the left and right subtrees
    return Math.max(leftChild, rightChild);
  }

  /**
   * Returns the least powerful Pokemon in this BST.
   * 
   * @return the Pokemon with the lowest CP in this BST and null if this tree is empty
   */
  public Pokemon getLeastPowerfulPokemon() {
    if (isEmpty())
      return null; // returns null if this BST is empty
    
    PokemonNode current = root;
    // iteratively traverses to the leftmost node in the BST
    while (current.getLeftChild() != null) {
      current = current.getLeftChild();
    }
    return current.getPokemon(); // returns the Pokemon in the leftmost node of this BST
  }

  /**
   * Returns the most powerful Pokemon in this BST.
   * 
   * @return the Pokemon with the highest CP in this BST, and null if this tree is empty
   */
  public Pokemon getMostPowerfulPokemon() {
    if (isEmpty())
      return null; // returns null if this BST is empty
    
    PokemonNode current = root;
    // iteratively traverses to the rightmost node in the BST
    while (current.getRightChild() != null) {
      current = current.getRightChild();
    }
    return current.getPokemon(); // returns the Pokemon in the rightmost node of this BST
  }
}
