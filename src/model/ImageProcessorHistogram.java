package model;

/**
 * {@code ImageProcessorHistogram} collects all data needed to make a histogram of an image.
 * This class creates arrays of each histogram field from a given ImageModelState.
 */
public class ImageProcessorHistogram {
  private ImageModelState image;
  private int[] reds;
  private int[] greens;
  private int[] blues;
  private int[] intensities;

  /**
   * ImageProcessorHistogram takes in an ImageModelState that it then uses to generate all.
   * Arrays of color values.
   *
   * @param image Image Model State that supplies data to make a histogram
   */
  public ImageProcessorHistogram(ImageModelState image) {
    this.image = image;
    this.reds = this.histogram("red");
    this.greens = this.histogram("green");
    this.blues = this.histogram("blue");
    this.intensities = this.histogram("intensity");
  }

  /**
   * Creates a single histogram array of a given type,
   * either reds, greens, blues, or intensities
   * All color values are integers --- intensity is rounded as needed using {@codeMath.round()}.
   *
   * @param type String type of histogram to make
   * @return int array of histogram values of given type
   * @throws IllegalArgumentException if histogram String type is invalid
   */
  private int[] histogram(String type) throws IllegalArgumentException {
    int arrSize = this.image.getWidth() * this.image.getHeight();
    int[] histogram = new int[arrSize];
    int a = 0;

    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        switch (type) {
          case "red":
            histogram[a] = this.image.getPixel(i, j).get(0);
            break;
          case "green":
            histogram[a] = this.image.getPixel(i, j).get(1);
            break;
          case "blue":
            histogram[a] = this.image.getPixel(i, j).get(2);
            break;
          case "intensity":
            int[] channel = this.image.getPixel(i, j).getChannel();
            int sum = 0;
            for (int c = 0; c < 3; c++) {
              sum += channel[c];
            }
            int intensity = (int) Math.round(sum / 3);
            histogram[a] = intensity;
            break;
          default:
            throw new IllegalArgumentException("Invalid histogram value: " +
                    "must be red, green, blue, or intensity");
        }
        a++;
      }

    }
    return histogram;
  }

  /**
   * Returns a copy of the array of intensities.
   *
   * @return integer array of intensities
   */
  public int[] getIntensities() {
    return this.intensities.clone();
  }

  /**
   * Returns a copy of the array of blue values.
   *
   * @return integer array of blue color values
   */
  public int[] getBlues() {
    return this.blues.clone();
  }

  /**
   * Returns a copy of the array of green values.
   *
   * @return integer array of green color values
   */
  public int[] getGreens() {
    return this.greens.clone();
  }

  /**
   * Returns a copy of the array of red values.
   *
   * @return integer array of red color values
   */
  public int[] getReds() {
    return this.reds.clone();
  }


}
