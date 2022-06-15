package model.edit;

import model.ImageModelState;
import model.ImageModelStateImpl;
import model.Pixel;

/**
 * Shared functionalities of an editor, which includeds part of the edit applier,
 * and the color limitations.
 */
public abstract class AEdit implements Edit {

  /**
   * Applies the edit on an image by altering the image pixel by pixel.
   *
   * @param newName new name for edited image
   * @param image   what to edit
   * @return ImageModelState with changes made
   */
  @Override
  public ImageModelState applyEdit(String newName, ImageModelState image) {
    Pixel[][] filteredBoard = new Pixel[image.getHeight()][image.getWidth()];

    // goes through each row
    for (int r = 0; r < image.getHeight(); r++) {
      // goes through each column
      for (int c = 0; c < image.getWidth(); c++) {
        filteredBoard = setRGB(image.copyImage(), r, c, image.getMaxNum());
      }
    }
    return new ImageModelStateImpl(filteredBoard, image.getMaxNum());
  }

  /**
   * Ensures that the color values to not exceed the max number.
   *
   * @param i number to check
   * @param maxNum maximum number color value of image
   * @return given number if it is under the max, else, the number adjusted to fit the max.
   */
  protected int clamp(int i, int maxNum) {
    int clamped = i;

    if (clamped > 255) {
      clamped = maxNum;
    }
    if (clamped < 0) {
      clamped = 0;
    }

    return clamped;
  }


  /**
   * Sets the RGB of an image to correspond with the instructed edit.
   *
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @return 3-d array of colors to make up an image
   */
  protected abstract Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum);


}
