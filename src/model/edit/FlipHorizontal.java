package model.edit;

import model.Pixel;

/**
 * Edits an image by flipping it horizontally.
 */
public class FlipHorizontal extends AEdit {

  /**
   * Rearranges the pixels in an image to flip the image horizontally.
   *
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @return edited 3-d array of RGB to make up an image.
   */
  @Override
  protected Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum) {
    int[][][] flipped = new int[image.length][image[0].length][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        flipped[i][image[i].length - 1 - j] = image[i][j];
      }
    }

    return flipped;
  }


}
