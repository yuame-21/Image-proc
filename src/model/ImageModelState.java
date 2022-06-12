package model;

/**
 * Represents the data of an ImageModel.
 * Includes all pixels/colors that make up an image.
 */
public interface ImageModelState {

  /**
   * Returns the width of the image.
   * The width is also the number of columns in an image.
   *
   * @return integer width
   */
  int getWidth();

  /**
   * Returns the height of the image.
   * the height is also the number of rows in an image.
   *
   * @return int height
   */
  int getHeight();

  /**
   * Gets the pixel at the provided location.
   * a pixel is a three-integer-array representing R, G, B
   *
   * @param row x coordinate
   * @param col y coordinate
   * @return array of RGB that makes up a pixel
   */
  int[] getPixel(int row, int col);

  /**
   * Return a deep copy of the pixels that make up an image.
   *
   * @return 3-D array of pixels
   */
  int[][][] copyImage();

  /**
   * Returns the max value for color, at most 255.
   *
   * @return integer max color number
   */
  int getMaxNum();

}
