//////////////// FILE HEADER //////////////////////////
//
// Title: Program 04 File Explorer
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
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class contains simple and recursive methods to list the contents of a folder. This class
 * also contains recursive methods to search for files by a file name, key, and size.
 * 
 * @author Sai Krishna Chaparala
 * @version 1.0
 */
public class FileExplorer {
  /**
   * Creates and returns an array list containing the names of all files and directories in the
   * given folder without going deeper using a simple function call.
   * 
   * @param currentFolder The file reference
   * @return A list of the names of all files and directories in the given folder.
   * @throws NotDirectoryException if the provided currentFolder does not exist or if it is not a
   *                               directory
   */
  public static ArrayList<String> listContents(File currentFolder) throws NotDirectoryException {
    ArrayList<String> contents = null;
    if (currentFolder != null) { // checks if currentFolder is not a null reference
      if (!currentFolder.isDirectory() || !currentFolder.exists())
        throw new NotDirectoryException(
            "Warning! The given file is not a directory or does not exist.");
      // creates an empty array list if currentFolder is a directory and exists
      contents = new ArrayList<String>();
      // returns an empty array if currentFolder does not contain any files or directories
      if (currentFolder.list() == null)
        return contents;
      // adds all the direct files and folders within currentFolder into the array list contents
      for (int i = 0; i < currentFolder.list().length; i++) {
        contents.add(currentFolder.list()[i]);
      }
    }
    return contents;
  }

  /**
   * Lists the names of all the files (not directories) in the given folder and its sub-folders.
   * 
   * @param currentFolder The file reference
   * @return A list of the names of all files in the given folder.
   * @throws NotDirectoryException if the provided currentFolder does not exist or if it is not a
   *                               directory
   */
  public static ArrayList<String> deepListContents(File currentFolder)
      throws NotDirectoryException {
    if (currentFolder != null) { // checks if currentFolder is not a null reference
      if (!currentFolder.isDirectory() || !currentFolder.exists())
        throw new NotDirectoryException(
            "Warning! The given file is not a directory or does not exist.");
      // calls the helper method helperDeepListContents() to get an array list containing all files
      // in currentFolder
      return deepListContentsHelper(currentFolder);
    }
    return null;
  }

  /**
   * Recursive helper method for deepListContents() lists the names of all the files (not
   * directories) in the given folder and its sub-folders.
   * 
   * @param currentFolder The file reference
   * @return A list of the names of all files in the given folder.
   */
  private static ArrayList<String> deepListContentsHelper(File currentFolder) {
    ArrayList<String> contents = new ArrayList<String>();
    // // Base Case - adds the file name to contents and returns it
    // if (currentFolder.isFile()) {
    // contents.add(currentFolder.getName());
    // return contents;
    // }
    // String[] l = currentFolder.list(); // creates a list of files and directories in
    // currentFolder
    //
    // // iterates through list and uses recursion to add file names in the array list contents
    // for (int i = 0; i < l.length; i++) {
    // File f = new File(currentFolder + File.separator + l[i]);
    // contents.addAll(deepListContentsHelper(f)); // Recursive Case
    // }
    // // returns an empty array if currentFolder is an empty directory
    // return contents;

    String[] l = currentFolder.list();
    if (l.length == 0)
      return contents;

    for (int i = 0; i < l.length; i++) {
      File f = new File(currentFolder + File.separator + l[i]);
      if (f.isFile())
        contents.add(l[i]);
      if (f.isDirectory())
        contents.addAll(deepListContentsHelper(f));
    }
    return contents;

  }

  /**
   * Searches the given folder and all of its sub-folders for an exact match to the provided file
   * name.
   * 
   * @param currentFolder The file reference
   * @param fileName      The file name to be searched
   * @return A path to the file, if it exists.
   * @throws NoSuchElementException if the search operation returns with no results found (including
   *                                the case if fileName is null)
   */
  public static String searchByName(File currentFolder, String fileName)
      throws NoSuchElementException {
    if (currentFolder != null) { // checks if currentFolder is not a null reference
      if (fileName == null || !currentFolder.isDirectory() || !currentFolder.exists()
          || searchByNameHelper(currentFolder, fileName).equals(""))
        throw new NoSuchElementException("Error! Invalid file name.");
      // calls the helper method searchByNameHelper() to get the path for the searched file name
      return searchByNameHelper(currentFolder, fileName);
    }
    return null;
  }

  /**
   * Recursive helper method for searchByName() searches the given folder and all of its sub-folders
   * for an exact match to the provided file name.
   * 
   * @param currentFolder The file reference
   * @param fileName      The file name to be searched
   * @return A path to the file, if it exists.
   */
  private static String searchByNameHelper(File currentFolder, String fileName) {
    String path = "";
    // Base Case - returns the path of the searched file if it is found, else returns ""
    if (currentFolder.isFile()) {
      if (currentFolder.getName().equals(fileName)) {
        path = currentFolder.getPath();
        return path;
      }
      return path;
    }

    String[] l = currentFolder.list(); // creates a list of files and directories in currentFolder
    // iterates through l and uses recursion to go deeper and find a file matching the file name
    for (int i = 0; i < l.length; i++) {
      File f = new File(currentFolder + File.separator + l[i]);
      path = searchByNameHelper(f, fileName); // Recursive Case
      if (!path.equals("")) // returns the file path of the first match found
        return path;
    }
    // returns "" if currentFolder is an empty directory (Base Case), else returns the path to file
    return path;
  }

  /**
   * Searches the given folder and all of its sub-folders for all files that contain the given key
   * in part of their name.
   * 
   * @param currentFolder The file reference
   * @param key           The string of characters to be searched in file names
   * @return An array list of all the names of files that match and an empty operation returns with
   *         no results found (including the case where currentFolder is not a directory).
   */
  public static ArrayList<String> searchByKey(File currentFolder, String key) {
    if (currentFolder != null) { // checks if currentFolder is not a null reference
      if (!currentFolder.isDirectory() || !currentFolder.exists() || key == null)
        return new ArrayList<String>();
      // calls the helper method searchByKeyHelper() to get an array list of file names containing
      // key in their names
      return searchByKeyHelper(currentFolder, key);
    }
    return null;
  }

  /**
   * Recursive helper method for searchByKey() searches the given folder and all of its sub-folders
   * for all files that contain the given key in part of their name.
   * 
   * @param currentFolder The file reference
   * @param key           The string of characters to be searched in file names
   * @return An array list of all the names of files that match and an empty operation returns with
   *         no results found (including the case where currentFolder is not a directory).
   */
  private static ArrayList<String> searchByKeyHelper(File currentFolder, String key) {
    ArrayList<String> list = new ArrayList<String>();

    // Base Case - returns an array list with the name of the file if it contains key, else
    // returns an empty array list
    if (currentFolder.isFile()) {
      if (currentFolder.getName().contains(key)) {
        list.add(currentFolder.getName());
        return list;
      }
      return list;
    }

    String[] l = currentFolder.list(); // creates a list of files and directories in currentFolder
    // iterates through l and uses recursion to go deeper and find a file name containing key
    for (int i = 0; i < l.length; i++) {
      File f = new File(currentFolder + File.separator + l[i]);
      list.addAll(searchByKeyHelper(f, key)); // Recursive Case
    }
    // returns an empty list if currentFolder is an empty directory (Base Case), else returns the
    // list of matching file names
    return list;
  }

  /**
   * Searches the given folder and its sub-folders for all files whose size is within the given
   * maximum and minimum values, inclusive.
   * 
   * @param currentFolder The file reference
   * @param sizeMin       The lower bound for the size range
   * @param sizeMax       The upper bound for the size range
   * @return An array list of all files whose size are within the boundaries if the search operation
   *         returns with no results found (including the case where currentFolder is not a
   *         directory).
   */
  public static ArrayList<String> searchBySize(File currentFolder, long sizeMin, long sizeMax) {
    if (currentFolder != null) { // checks if currentFolder is not a null reference
      if (!currentFolder.isDirectory())
        return new ArrayList<String>();
      // calls the helper method searchBySizeHelper() to get an array list of file names whose file
      // sizes are within given boundaries
      return searchBySizeHelper(currentFolder, sizeMin, sizeMax);
    }
    return null;
  }

  /**
   * Recursive helper method for searchBySize() searches the given folder and its sub-folders for
   * all files whose size is within the given max and min values, inclusive.
   * 
   * @param currentFolder The file reference
   * @param sizeMin       The lower bound for the size range
   * @param sizeMax       The upper bound for the size range
   * @return An array list of all files whose size are within the boundaries if the search operation
   *         returns with no results found (including the case where currentFolder is not a
   *         directory).
   */
  private static ArrayList<String> searchBySizeHelper(File currentFolder, long sizeMin,
      long sizeMax) {
    ArrayList<String> list = new ArrayList<String>();

    // Base Case - returns an array list with the name of the file if its size lies between
    // specified boundaries, else returns an empty array list
    if (currentFolder.isFile()) {
      if (currentFolder.length() >= sizeMin && currentFolder.length() <= sizeMax) {
        list.add(currentFolder.getName());
        return list;
      }
      return list;
    }

    String[] l = currentFolder.list(); // creates a list of files and directories in currentFolder
    // iterates through l and uses recursion to go deeper and find a file with file size between the
    // boundaries
    for (int i = 0; i < l.length; i++) {
      File f = new File(currentFolder + File.separator + l[i]);
      list.addAll(searchBySizeHelper(f, sizeMin, sizeMax)); // Recursive Case
    }
    // returns an empty list if currentFolder is an empty directory (Base Case), else returns a list
    // of all files within the boundaries
    return list;
  }

}
