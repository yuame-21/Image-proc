package controller;

/**
 * Describes the behaviors of the features of an image processor.
 */
public interface Features {

  /**
   * The feature red component that visualizes the red color component to grey scale.
   */
  void redComponent();

  /**
   * The feature green component that visualizes the green color component to grey scale.
   */
  void greenComponent();

  /**
   * The feature blue component that visualizes the blue color component to grey scale.
   */
  void blueComponent();

  /**
   * The feature that darkens an image based on given degree.
   *
   * @param degree The degree to which to darken an image
   */
  void darken(String degree);

  /**
   * The feature that brightens an image based on the given degree.
   *
   * @param degree The degree to which to brighten an image
   */
  void brighten(String degree);

  /**
   * The feature that flips an image horizontally.
   */
  void horizontalFlip();

  /**
   * The feature that flips an image vertically.
   */
  void verticalFlip();

  /**
   * The feature that edits the image to the average of each pixel's RGB.
   */
  void intensity();

  /**
   * The feature that converts the image to greyscale.
   */
  void luma();

  /**
   * The feature that edits each pixel to have the RGB be the max value.
   */
  void value();

  /**
   * The feature that edits an image with a sepia filter.
   */
  void sepia();

  /**
   * The feature that edits an image to be greyscale.
   */
  void greyscale();

  /**
   * The feature that makes an image sharper.
   */
  void sharpen();

  /**
   * The feature that blurs an image.
   */
  void blur();

  /**
   * Loads an image into the controller based on the given path.
   *
   * @param path The path the file we would like to load
   */
  void load(String path);

  /**
   * The feature to save a given image.
   *
   * @param path The path to the place to save the image
   */
  void save(String path);

  /**
   * The feature to exit the program.
   */
  void exitProgram();

}
