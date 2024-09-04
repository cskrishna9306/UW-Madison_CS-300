//////////////// FILE HEADER //////////////////////////
//
// Title: Program 09 Pokemon Catalog (Pokemon Node)
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
 * This class implements a PokemonNode data type for a binary search tree.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class PokemonNode {
  // instance fields
  private Pokemon data; // data field of this PokemonNode
  private PokemonNode leftChild; // reference to the left child
  private PokemonNode rightChild; // reference to the right child

  /**
   * Creates an instance of PokemonNode with the given data. The created PokemonNode has null as its
   * leftChild and rightChild.
   * 
   * @param data data of this PokemonNode
   */
  public PokemonNode(Pokemon data) {
    if (data == null)
      throw new IllegalArgumentException();
    this.data = data;
    leftChild = null;
    rightChild = null;
  }

  /**
   * Accessor for the leftChild field.
   * 
   * @return the leftChild of this PokemonNode
   */
  public PokemonNode getLeftChild() {
    return leftChild;
  }

  /**
   * Accessor for the rightChild field.
   * 
   * @return the rightChild of this PokemonNode
   */
  public PokemonNode getRightChild() {
    return rightChild;
  }

  /**
   * Accessor for the data field.
   * 
   * @return the data within this PokemonNode
   */
  public Pokemon getPokemon() {
    return data;
  }

  /**
   * Setter for the leftChild field.
   * 
   * @param left the leftChild to be set for this PokemonNode
   */
  public void setLeftChild(PokemonNode left) {
    leftChild = left;
  }

  /**
   * Setter for the rightChild field.
   * 
   * @param right the rightChild to be set for this PokemonNode
   */
  public void setRightChild(PokemonNode right) {
    rightChild = right;
  }
}
