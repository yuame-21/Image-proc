package model;

/**
 * Represents a color Pixel made up of a red, green, and blue value.
 */
public class Pixel {
  int red;
  int green;
  int blue;

  /**
   * A color pixel consists of red, green, and blue integer values.
   * All color values must be <= 255
   *
   * @param red   integer red value
   * @param green integer green value
   * @param blue  integer blue value
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Retrieves the color integer value based on given integer.
   * 0  = red (the first color)
   * 1 = green (the second color)
   * 2 = blue (the third color)
   *
   * @param i integer "position" of color value to get.
   * @return integer color value
   */
  public int get(int i) throws IllegalArgumentException {
    int c;
    switch (i) {
      case 0:
        c = this.red;
        break;
      case 1:
        c = this.green;
        break;
      case 2:
        c = this.blue;
        break;
      default:
        throw new IllegalArgumentException("invalid color index");
    }
    return c;
  }

  /**
   * Sets an individual color value (R,G, or B) to a given value based on location input.
   * i = 0  -> red (the first color)
   * i = 1 -> green (the second color)
   * i = 2 -> blue (the third color)
   *
   * @param i   which color to set.
   * @param set integer value to the set the color value to
   */
  public void set(int i, int set) throws IllegalArgumentException {

    if (set > 255) {
      throw new IllegalArgumentException("color value may be at most 255");
    }

    switch (i) {
      case 0:
        this.red = set;
        break;
      case 1:
        this.green = set;
        break;
      case 2:
        this.blue = set;
        break;
      default:
        throw new IllegalArgumentException("Cannot set anything outside of RGB");
    }
  }

  /**
   * Returns the R,G, and B values of the pixel in the form of a three-integer Array.
   *
   * @return array of length 3
   */
  public int[] getChannel() {
    return new int[]{this.red, this.green, this.blue};
  }


}

