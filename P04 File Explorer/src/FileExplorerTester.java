//////////////// FILE HEADER //////////////////////////
//
// Title: Program 04 File Explorer Tester
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

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class contains methods to test all public methods from the FileExplorer class. This class
 * also contains the main method to check whether each test evaluates to true or false.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class FileExplorerTester {
  /**
   * Calls the test methods implemented in this class and displays either true or false as output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testListContents() \t\t: " + testListContents(new File("cs300")));
    System.out.println("testDeepListBaseCase() \t\t: " + testDeepListBaseCase(new File("cs300")));
    System.out
        .println("testDeepListRecursiveCase() \t: " + testDeepListRecursiveCase(new File("cs300")));
    System.out.println("testSearchByFileName() \t\t: " + testSearchByFileName(new File("cs300")));
    System.out.println("testSearchByKeyBaseCase() \t: " + testSearchByKeyBaseCase(new File("cs300")));
    System.out.println(
        "testSearchByKeyRecursiveCase() \t: " + testSearchByKeyRecursiveCase(new File("cs300")));
    System.out
        .println("testSearchBySizeBaseCase() \t: " + testSearchBySizeBaseCase(new File("cs300")));
    System.out.println(
        "testSearchBySizeRecursiveCase() : " + testSearchBySizeRecursiveCase(new File("cs300")));
  }

  /**
   * Checks whether listContents() works as expected
   * 
   * @param folder The file reference
   * @return true if the method listContents() passes all test scenarios, false otherwise
   */
  public static boolean testListContents(File folder) {
    try {
      // Scenario 1 - list the basic contents of the cs300 folder
      ArrayList<String> listContent = FileExplorer.listContents(folder);
      // expected output content
      String[] contents = new String[] {"grades", "lecture notes", "programs",
          "quizzes preparation", "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java")) {
        System.out.println("Problem detected: p02 folder must contain only one file named "
            + "WisconsinPrairie.java.");
        return false;
      }
      // Scenario 4 - Try to list the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // Expected behavior -- no problem detected
      }
      // Scenario 5 - Try to list the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // catch only the expected exception to be thrown -- no problem detected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FileExplorer.listContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Checks whether the base case for deepListContents() works as expected
   * 
   * @param folder The file reference
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testDeepListBaseCase(File folder) {
    try {
      // Base Case 1 - list contents of a directory with no contents
      File f = new File(folder.getPath() + File.separator + "grades");
      ArrayList<String> list = FileExplorer.deepListContents(f);
      // check the size of the output
      if (list.size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the recursive case for deepListContents() works as expected
   * 
   * @param folder The file reference
   * @return true if the method deepListContents() passes all test scenarios, false otherwise
   */
  public static boolean testDeepListRecursiveCase(File folder) {
    try {
      // Scenario 1 - list the contents of the lecture notes folder
      ArrayList<String> listContent = FileExplorer
          .deepListContents(new File(folder.getPath() + File.separator + "lecture notes"));
      // expected output content
      String[] contents = new String[] {"ExceptionHandling.txt", "proceduralProgramming.txt",
          "UsingObjectsAndArrayLists.txt", "AlgorithmAnalysis.txt", "Recursion.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 5) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FileExplorer.deepListContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FileExplorer.deepListContents(f);
      if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java")) {
        System.out.println("Problem detected: p02 folder must contain only one file named "
            + "WisconsinPrairie.java.");
        return false;
      }
      // Scenario 4 - Try to list the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FileExplorer.deepListContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // Expected behavior -- no problem detected
      }
      // Scenario 5 - Try to list the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FileExplorer.deepListContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // catch only the expected exception to be thrown -- no problem detected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FileExplorer.listContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Checks whether searchByFileName() works as expected
   * 
   * @param folder The file reference
   * @return true if the method searchByName() passes all test scenarios, false otherwise
   */
  public static boolean testSearchByFileName(File folder) {
    try {
      // Scenario 1 - Try to search for an already existing file in a directory
      String path = FileExplorer.searchByName(folder, "ComparisonMethods.java");
      // expected output content
      String expectedPath = "cs300\\programs\\p03\\ComparisonMethods.java";
      // check the contents of the output
      if (!path.equals(expectedPath))
        return false;
      // Scenario 2 - Try to search for a null file name
      try {
        path = FileExplorer.searchByName(folder, null);
        return false;
      } catch (NoSuchElementException e) {
        // catch only the expected exception to be thrown -- no problem detected
      } catch (Exception e) {
        return false;
      }
      // Scenario 3 - Try to search in a file which is not a directory
      File f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02"
          + File.separator + "WisconsinPrairie.java");
      try {
        path = FileExplorer.searchByName(f, "Recursion.txt");
        return false;
      } catch (NoSuchElementException e) {
        // catch only the expected exception to be thrown -- no problem detected
      } catch (Exception e) {
        return false;
      }
      // Scenario 4 - Try to search for a non-existent file
      try {
        path = FileExplorer.searchByName(folder, "abcd");
        return false;
      } catch (NoSuchElementException e) {
        // catch only the expected exception to be thrown -- no problem detected
      } catch (Exception e) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the base case for searchByKeyBaseCase() works as expected
   * 
   * @param folder The file reference
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testSearchByKeyBaseCase(File folder) {
    try {
      // Base Case 1 - Try to search for a unique file in a folder with no sub-folders (base case)
      File f = new File(folder.getPath() + File.separator + "grades");
      ArrayList<String> contents = FileExplorer.searchByKey(f, "codeSamples.java");
      // check the size of the output
      if (contents.size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the recursive case for searchByKey() works as expected
   * 
   * @param folder The file reference
   * @return true if the method searchByKey() passes all test scenarios, false otherwise
   */
  public static boolean testSearchByKeyRecursiveCase(File folder) {
    try {
      // Scenario 1 - Try to search for files with key ".pdf"
      ArrayList<String> contents = FileExplorer.searchByKey(folder, ".pdf");
      // expected output content
      String[] expectedContent = new String[] {"Program01.pdf", "Program02.pdf", "Program03.pdf"};
      List<String> expectedList = Arrays.asList(expectedContent);
      // check the size and the contents of the output
      if (contents.size() != 3)
        return false;
      for (int i = 0; i < expectedList.size(); i++) {
        if (!contents.contains(expectedList.get(i))) {
          return false;
        }
      }
      // Scenario 2 - Try to search for a null key
      contents = FileExplorer.searchByKey(folder, null);
      if (contents.size() != 0)
        return false;
      // Scenario 3 - Try to search in a file which is not a directory
      File f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02"
          + File.separator + "WisconsinPrairie.java");
      contents = FileExplorer.searchByKey(f, "Recursion.txt");
      if (contents.size() != 0 || contents.contains("Recursion.txt"))
        return false;
      // Scenario 4 - Try to search for file references with no matches in the directory
      contents = FileExplorer.searchByKey(folder, "abcd");
      if (contents.size() != 0 || contents.contains("abcd"))
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the base case for searchBySizeBaseCase() works as expected
   * 
   * @param folder The file reference
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testSearchBySizeBaseCase(File folder) {
    try {
      // Base Case 1 - Try to search for a file with unique size in the folder with no sub-folders (base case)
      File f = new File(folder.getPath() + File.separator + "grades");
      ArrayList<String> contents = FileExplorer.searchBySize(f, 0, 100000);
      // check the size of the output
      if (contents.size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the recursive case for searchBySize() works as expected
   * 
   * @param folder The file reference
   * @return true if the method searchBySize() passes all test scenarios, false otherwise
   */
  public static boolean testSearchBySizeRecursiveCase(File folder) {
    try {
      // Scenario 1 - Try to search for files with size between 100000 and 1000000
      ArrayList<String> contents = FileExplorer.searchBySize(folder, 100000, 1000000);
      // expected output content
      String[] expectedContent = new String[] {"Program01.pdf", "Program03.pdf"};
      List<String> expectedList = Arrays.asList(expectedContent);
      // check the size and the contents of the output
      if (contents.size() != 2)
        return false;
      for (int i = 0; i < expectedList.size(); i++) {
        if (!contents.contains(expectedList.get(i))) {
          return false;
        }
      }
      // Scenario 2 - Try to search in a file which is not a directory
      File f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02"
          + File.separator + "WisconsinPrairie.java");
      contents = FileExplorer.searchBySize(f, 0, 0);
      if (contents.size() != 0 || contents.contains("WisconsinPrairie.java"))
        return false;
      // Scenario 3 - Try to search for file sizes with negative values
      contents = FileExplorer.searchBySize(folder, -12, -10000);
      if (contents.size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

}
