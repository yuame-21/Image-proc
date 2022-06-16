package model.edit;

import model.Pixel;

/**
 * Edits an image to visualize a color component, creating a greyscale image.
 * It can visualize either red, green, or blue components.
 */
public class ColorComponent extends AEdit {
  String color;

  /**
   * Takes in either red, green, or blue to visualize the color component.
   *
   * @param color String color
   * @throws IllegalArgumentException if the given color is invalid
   */
  public ColorComponent(String color) {
    if (!(color.equals("red") || color.equals("green") || color.equals("blue"))) {
      throw new IllegalArgumentException("not a valid color component! "
          + "enter either 'red,' 'green,'or 'blue'");
    }

    this.color = color;
  }

  /**
   * Sets each RGB value of the image's pixels to equal the color given to visualize.
   *
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @return 3-d array of image that visualizes for a color
   */
  @Override
  protected Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum) {
    Pixel pixel = image[r][c];
    int newRGB;

    switch (this.color) {
      case "red":
        newRGB = pixel.get(0);
        break;
      case "green":
        newRGB = pixel.get(1);
        break;
      case "blue":
        newRGB = pixel.get(2);
        break;
      default:
        throw new IllegalStateException("must enter red, green, or blue");
    }

    for (int rgb = 0; rgb < 3; rgb++) {

      pixel.set(rgb, clamp(newRGB, maxNum));

    }
    return image;

  }

}

