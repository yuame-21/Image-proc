package model.edit;

import model.Pixel;

/**
 * Edits an image by flipping it vertically.
 */
public class FlipVertical extends AEdit {

  /**
   * Rearranges the pixels in an image to flip the image vertically.
   *
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @return edited 3-d array of RGB to make up an image.
   */
  @Override
  protected Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum) {
    Pixel[][] flipped = new Pixel[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      flipped[image.length - 1 - i] = image[i];
    }
    return flipped;
  }

}
