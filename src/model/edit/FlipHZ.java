package model.edit;

import model.Pixel;

/**
 * Edits an image by flipping it vertically.
 */
public class FlipHZ extends AEdit {
  private String s;

  /**
   * A FlipHZ is either a horizontal flip or vertical flip, based on the string input.
   * @param s valid flip types are 'horizontal' or 'vertical'
   * @throws IllegalArgumentException if invalid flip type is entered
   */
  public FlipHZ(String s) throws IllegalArgumentException {
    if (!((s.equals("horizontal")) || s.equals("vertical"))) {
      throw new IllegalArgumentException("only valid flip types are horizontal and vertical");
    }
    this.s = s;
  }

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


      if (this.s.equals("vertical")) {
        flipped[image.length - 1 - i] = image[i];
      }

      if (this.s.equals("horizontal")) {
        for (int j = 0; j < image[i].length; j++) {
          flipped[i][image[i].length - 1 - j] = image[i][j];
        }
      }
    }
    return flipped;
  }

}
