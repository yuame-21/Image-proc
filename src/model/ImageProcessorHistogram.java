package model;

/**
 * {@code ImageProcessorHistogram} collects all data needed to make a histogram of an image.
 * This class creates arrays of each histogram field from a given ImageModelState.
 */
public class ImageProcessorHistogram {
  private ImageModel image;
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
  public ImageProcessorHistogram(ImageModel image) {
    this.image = image;
    String temp = image.generateString("temp");

    this.reds = this.histogram(temp, "red");
    this.greens = this.histogram(temp, "green");
    this.blues = this.histogram(temp, "blue");
    this.intensities = this.histogram(temp, "intensity");
  }

  /**
   * Creates a single histogram array of a given type,
   * either reds, greens, blues, or intensities
   * All color values are integers --- intensity is rounded as needed using {@code Math.round()}.
   *
   * @param type    String type of histogram to make
   * @param tempArr String version of image pixels and values
   * @return int array of histogram values of given type
   * @throws IllegalArgumentException if histogram String type is invalid
   */
  private int[] histogram(String tempArr, String type) throws IllegalArgumentException {

    String[] ar = tempArr.split("\n");
    int width = Integer.parseInt(ar[0]);
    int height = Integer.parseInt(ar[1]);

    int arrSize = width * height;

    int[] histogram = new int[arrSize];

//    for (int a = 0; a < arrSize; a++) {
    int a = 0;
//    for (int i = 0; i < width; i++) {
//      for (int j = 0; j < height; j++) {
      switch (type) {
        case "red":
          for (int r = 3; r < ar.length; r += 3) {
            histogram[a] = Integer.parseInt(ar[r]);
            a++;
          }
          break;
        case "green":
          for (int g = 4; g < ar.length; g += 3) {
            histogram[a] = Integer.parseInt(ar[g]);
            a++;
          }
          break;
        case "blue":
          for (int b = 5; b < ar.length; b += 3) {
            histogram[a] = Integer.parseInt(ar[b]);
            a++;
          }
          break;
        case "intensity":
          int sum = 0;
          for (int in = 3; in < ar.length; in += 3) {
            sum += Integer.parseInt(ar[in]);
            sum += Integer.parseInt(ar[in + 1]);
            sum += Integer.parseInt(ar[in + 2]);
            int intensity = (int) Math.round(sum / 3);
            histogram[a] = intensity;
            a++;
            sum = 0;
          }

          break;
        default:
          throw new IllegalArgumentException("Invalid histogram value: " +
                  "must be red, green, blue, or intensity");
      }
//    }

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
