package model.edit;

/**
 * Edits the image to be brighten or darken based on a provided degree.
 * The degree provided may be negative or positive, thus darkening or brightening.
 */
public class BrightenDarken extends AEdit {
  int degree;

  /**
   * the brightness is changed based on a given degree.
   *
   * @param degree severity of the edit
   */
  public BrightenDarken(int degree) {
    this.degree = degree;
  }

  /**
   * sets each color value in each pixel by adding the given degree.
   *
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @return a 3-d array of colors that make up an image
   */
  @Override
  protected int[][][] setRGB(int[][][] image, int r, int c, int maxNum) {

    for (int rgb = 0; rgb < 3; rgb++) {
      int[] pixel = image[r][c];

      pixel[rgb] = clamp(pixel[rgb] + this.degree, maxNum);

    }
    return image;
  }

}
