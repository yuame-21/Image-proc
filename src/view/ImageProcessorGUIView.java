package view;

import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Features;

public interface ImageProcessorGUIView extends ImageProcessorView{

  /**
   * Signal the view to draw itself.
   */
  public void refresh();

  /**
   * Make the view visible. This is usually called-
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for-
   * the button that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener
   *
   * @param actionEvent to set button command to
   * @param  button which button to set
   */
  void setCommandButtonListener(JButton button, ActionListener actionEvent);

  /**
   * Transmit an error message to the view, in case-
   * the command could not be processed correctly
   *
   * @param error error message to render
   */
  void showErrorMessage(String error);

  void addFeatures(Features features);

  void renderImage();

}
