package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param sc the scanner of a file.
   **/
  public static Scanner readPPM(Scanner sc) {

    StringBuilder noComment = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        noComment.append(s + System.lineSeparator());
      }
    }

    // now, the scanner reads from the string we just built
    return new Scanner(noComment.toString());
  }


  /**
   * Demo main.
   *
   * @param args Strings from the user
   */
  public static void main(String[] args) {
    String filename;
    Scanner sc;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    ImageUtil.readPPM(sc);
  }


}
