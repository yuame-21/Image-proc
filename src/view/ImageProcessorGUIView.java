package view;

import java.awt.image.BufferedImage;

import controller.Features;
import model.ImageModel;

/**
 * Describes the behaviors of a view of the GUI of an image processor.
 */
public interface ImageProcessorGUIView extends ImageProcessorView {

  /**
   * Refreshes the program when changes are made to the histogram.
   */
  public void refresh();

  /**
   * Make the view visible. This is usually called after the view is constructed
   */
  void makeVisible();

  /**
   * Connects the features of the controller to the view and allows for functionality to be added
   * to the buttons and boxes.
   *
   * @param features The features of the controller
   */
  void addFeatures(Features features);

  /**
   * Renders a given image in the view.
   *
   * @param image The image desired to be rendered
   */
  void renderImage(BufferedImage image);

  /**
   * Sets the histogram data from the model into the view.
   *
   * @param model The model of the histogram
   */
  void setHistogram(ImageModel model);

}
