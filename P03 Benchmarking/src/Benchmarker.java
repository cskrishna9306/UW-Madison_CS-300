//////////////// FILE HEADER //////////////////////////
//
// Title: Program 03 Simple Benchmarking (Benchmarker)
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
import java.util.NoSuchElementException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains methods to calculate the runtime of brute force and constant-time algorithms,
 * and to write the corresponding output for each input size n into a file. This class also contains
 * the main method to run the createResultsFile() method.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class Benchmarker {
  /**
   * Calls the test methods implemented in this class and displays any exception messages when
   * encountered.
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    File f = new File("Results.txt");
    long[] queryNs = new long[] {100000, 1000000, 10000000, 100000000, 1000000000};
    createResultsFile(f, queryNs);
  }

  /**
   * Calculates the runtime (in milliseconds) for the brute force and constant-time algorithms for a
   * certain input size n.
   * 
   * @param n The input size
   * @return A string containing the input size n, and runtime for the brute force and constant-time
   *         algorithms.
   * @throws NoSuchElementException if values calculated using brute force and constant-time formula
   *                                are not equal.
   */
  public static String compare(long n) throws NoSuchElementException {
    long a = System.currentTimeMillis();
    long bruteForceValue = ComparisonMethods.bruteForce(n);
    long b = System.currentTimeMillis();
    long bruteForceTime = b - a; // calculates the runtime for brute force algorithm

    a = System.currentTimeMillis();
    long constantTimeValue = ComparisonMethods.constantTime(n);
    b = System.currentTimeMillis();
    long formulaTime = b - a; // calculates the runtime for constant-time formula

    // checks if values calculated using two different algorithms are equal
    if (bruteForceValue != constantTimeValue)
      throw new NoSuchElementException(
          "Error! The values returned from the two methods are not equal.");

    return (n + "\t" + bruteForceTime + "\t" + formulaTime + "\n");
  }

  /**
   * Creates test results and writes them into a file using various helper methods. This method also
   * prints different exception messages based on the exception encountered.
   * 
   * @param f       The file name
   * @param queryNs The array containing different input sizes to be tested.
   */
  public static void createResultsFile(File f, long[] queryNs) {
    FileWriter w = null;
    try {
      if (f != null && queryNs != null) { // checks if both f and queryNs are not null references
        w = new FileWriter(f); // may throw IOException if it fails

        for (int i = 0; i < queryNs.length; i++) {
          writeFileWriter(w, queryNs[i]); // writes into the file for every input size n
        }
      }
    } catch (IOException e) {
      System.out.println("Exception encountered, unable to complete method.");
    } finally {
      closeFileWriter(w); // closes the FileWriter object
    }
  }

  /**
   * Method writes into a file. Prints exception message if error is encountered while writing into
   * the file or if compare() method throws an exception.
   * 
   * @param w An object of type FileWriter
   */
  private static void writeFileWriter(FileWriter w, long n) {
    try {
      if (w != null) { // checks if FileWriter is not a null reference
        // write() may throw IOException if it fails and compare() may throw NoSuchElementException
        w.write(compare(n));
      }
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println("Exception encountered while writing for value N = " + n);
    }
  }

  /**
   * Method closes a FileWriter. Prints exception message if closing fails.
   * 
   * @param w An object of type FileWriter
   */
  private static void closeFileWriter(FileWriter w) {
    try {
      if (w != null) { // Ensures FileWriter references a valid object
        w.close(); // close() may throw IOException if it fails
      }
    } catch (IOException e) {
      System.out.println("Exception encountered while closing file.");
    }
  }
}
