package model.edit;

import java.util.Arrays;

import model.Pixel;

/**
 * Edits an image to visualize intensity.
 * Intensity is the average of the three components in a pixel
 */
public class ILV extends AEdit {
  String s;

  /**
   * Intensity, Luma, or Value, based on String input of type.
   *
   * @param s string type
   */
  public ILV(String s) {
    if (!(s.equals("intensity") || s.equals("luma") || s.equals("value"))) {
      throw new IllegalArgumentException("invalid ILV type");
    }
    this.s = s;
  }

  /**
   * For each pixel in an image, changes the RGB values to be the average of.
   * the pixel's original values.
   *
   * @param image array of colors that make up an image
   * @param r     row location
   * @param c     column location
   * @return image edited to greyscale for intensity.
   */
  @Override
  protected Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum) {
    int newNum = 0;
    Pixel pixel = image[r][c];

    switch (this.s) {
      case "intensity":
        int sum = 0;
        for (int i = 0; i < 3; i++) {
          sum += pixel.get(i);
        }
        newNum = Math.round(sum / 3);
        break;
      case "luma":
        newNum = (int) Math.round(0.2126 * pixel.get(0)+ .7152 * pixel.get(1) + 0.0722 * pixel.get(2));
        break;
      case "value":
        Arrays.sort(pixel.getChannel());
        newNum = pixel.get(2);
        break;
    }
    for (int rgb = 0; rgb < 3; rgb++) {
      image[r][c].set(rgb, clamp(newNum, maxNum));
    }
    return image;
  }


}
