package controller.commands;

import model.ImageModel;
import view.ImageProcessorView;

/**
 * A command is used to manipulate a given image.
 * A command changes the image itself, and renders a message to
 * explain what action was performed.
 */
public interface Command {

  /**
   * Applies the commmand to a given model in order to manipulate an image.
   *
   * @param model what to perform the command on
   */
  void initCommand(ImageModel model);

  /**
   * Renders a message specific to the command to describe to the user.
   *
   * @param view where to render the message
   * @throws IllegalStateException if the transmission fails.
   */
  void renderCommandMessage(ImageProcessorView view) throws IllegalStateException;

}
