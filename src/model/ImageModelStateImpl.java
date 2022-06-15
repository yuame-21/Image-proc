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

  // We deleted our second constructor for this class as when we wrote our new load class it
  // became redundant.

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
