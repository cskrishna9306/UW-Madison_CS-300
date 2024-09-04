//////////////// FILE HEADER //////////////////////////
//
// Title: Program 03 Simple Benchmarking (Comparison Methods)
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
 * This class contains methods to calculate the sum of integers from 1 to n using either a brute
 * force algorithm or a constant time formula.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class ComparisonMethods {
  /**
   * Uses a for loop to calculate the sum of integers from 1 to n.
   * Complexity: O(n)
   * 
   * @param n The input size
   * @return The sum of integers from 1 to n.
   */
  public static long bruteForce(long n) {
    long total = 0;
    for (int i = 1; i <= n; i++) {
      total += i;
    }
    return total;
  }

  /**
   * Uses a constant-time formula to calculate the sum of integers from 1 to n.
   * Complexity: O(1)
   * 
   * @param n The input size
   * @return The sum of integers from 1 to n.
   */
  public static long constantTime(long n) {
    long total = (n * (n + 1)) / 2;
    return total;
  }
}
