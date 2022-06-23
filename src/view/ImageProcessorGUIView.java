package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.Features;
import model.ImageModel;

public interface ImageProcessorGUIView extends ImageProcessorView {

  /**
   * Signal the view to draw itself.
   */
  public void refresh();

  /**
   * Make the view visible. This is usually called-
   * after the view is constructed
   */
  void makeVisible();

//  /**
//   * Provide the view with an action listener for-
//   * the button that should cause the program to
//   * process a command. This is so that when the button
//   * is pressed, control goes to the action listener
//   *
//   * @param actionEvent to set button command to
//   * @param  button which button to set
//   */
//  void setCommandButtonListener(JButton button, ActionListener actionEvent);


  void addFeatures(Features features);

  void renderImage(BufferedImage image);

  void makeHistogram(ImageModel model);

}
