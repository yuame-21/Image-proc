package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the implementation of an {@code ImageModelState}.
 * this holds the 3-d array of colors that make up an image,
 * the version name, and the max number for colors.
 */
public class ImageModelStateImpl implements ImageModelState {
  private Pixel[][] image;
  private int maxNum;

  /**
   * Takes in the image's pixels, name, and maxNum to represent all of the data for an image.
   *
   * @param image  3-d array of colors which make up an image.
   * @param maxNum max number for colors
   */
  public ImageModelStateImpl(Pixel[][] image, int maxNum) {
    this.image = image;
    this.maxNum = maxNum;
  }

  /**
   * Constructs an {@code ImageModelState} using a given path of an image.
   *
   * @param path The path to an image in the system
   */
//  public ImageModelStateImpl(String path) {
//    Scanner sc;
//
//    try {
//      sc = new Scanner(new FileInputStream(path));
//    } catch (FileNotFoundException e) {
//      throw new IllegalArgumentException("File not found");
//    }
//
//    while (sc.hasNext()) {
//
//    if (!sc.nextLine().equals("P3")) {
//      throw new IllegalArgumentException("Invalid file format");
//    }
////    StringBuilder noComment = new StringBuilder();
//
//    String s = sc.nextLine();
//
////    while (sc.hasNextLine()) {
//      if (s.charAt(0) != '#') {
////        noComment.append(s + System.lineSeparator());
//        s = sc.nextLine();
//      }
////    }
//
////    sc = new Scanner(noComment.toString());
////    String token = sc.next();
////
////    if (!token.equals("P3")) {
////      throw new IllegalArgumentException("Invalid file format");
////    }
//
////    while (sc.hasNext()) {
//      int width = sc.nextInt();
//      int height = sc.nextInt();
//      this.maxNum = sc.nextInt();
//
//      this.image = new int[width][height][3];
//
//      for (int i = 0; i < width ; i++) {
//        for (int j = 0; j < height ; j++) {
////        image[i][j] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
//
//          image[i][j][0] = sc.nextInt();
//          image[i][j][1] = sc.nextInt();
//          image[i][j][2] = sc.nextInt();
//        }
//      }
//    }
//    sc.close();
//  }
  public ImageModelStateImpl(String path) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    StringBuilder noComment = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        noComment.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(noComment.toString());
    String token;
    token = sc.next();

    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid file format");
    }
    int height = sc.nextInt();
    int width = sc.nextInt();
    this.maxNum = sc.nextInt();

    this.image = new Pixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        image[i][j] = new Pixel(i, j, sc.nextInt(), sc.nextInt(), sc.nextInt());
      }
    }

    sc.close();
  }


  /**
   * Returns the width of an image.
   * Which is also the number of columns.
   *
   * @return integer width
   */
  @Override
  public int getWidth() {
    return image[0].length;
  }

  /**
   * Returns the height of an image.
   * Which is also the number or rows .
   *
   * @return integer height
   */
  @Override
  public int getHeight() {
    return image.length;
  }

  /**
   * Returns the RGB pixel at the given location
   *
   * @param row x coordinate
   * @param col y coordinate
   * @return array of three integers
   */
  @Override
  public Pixel getPixel(int row, int col) {
    return this.image[row][col];
  }

  /**
   * Creates a deep copy of the pixel array.
   *
   * @return 3-d array representing an image.
   */
  public Pixel[][] copyImage() {
    Pixel[][] copy = new Pixel[this.getHeight()][this.getWidth()];

    for (int i = 0; i < this.getHeight(); i++) {

      for (int j = 0; j < this.getWidth(); j++) {
        copy[i][j] = this.getPixel(i, j);
      }
    }
    return copy;
  }

  /**
   * Returns the max number for colors.
   *
   * @return integer max number
   */
  @Override
  public int getMaxNum() {
    return this.maxNum;
  }

}
