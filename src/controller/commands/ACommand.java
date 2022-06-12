package controller.commands;


import java.io.IOException;

import view.ImageProcessorView;

/**
 * An abstract class for image processing commands.
 * All commands will have an original name, a new name, and a message to render to the user
 * after completing the command.
 */
public abstract class ACommand implements Command {
  protected final String originalName;
  protected final String revisedName;
  private String message;

  /**
   * An abstract command takes in two names, checks that they are valid, and sets them.
   *
   * @param originalName original name of image
   * @param revisedName  name to change image to
   * @throws IllegalArgumentException if the parameters are null
   */
  public ACommand(String originalName, String revisedName) throws IllegalArgumentException {
    if (originalName == null || revisedName == null) {
      throw new IllegalArgumentException("given name(s) are null");
    }

    this.originalName = originalName;
    this.revisedName = revisedName;
    this.message = null;
  }


  /**
   * Renders a specific command message to the view based on the command applied.
   *
   * @param view where the render the message
   * @throws IllegalStateException    if the message rendering fails
   * @throws IllegalArgumentException if the message to render is null
   */
  @Override
  public void renderCommandMessage(ImageProcessorView view) throws IllegalStateException,
          IllegalArgumentException {
    if (this.message == null) {
      throw new IllegalArgumentException("the command message is null");
    }

    try {
      view.renderMessage(this.message);
    } catch (IOException e) {
      throw new IllegalStateException("failed transmission: could not render message");
    }
  }

  /**
   * Sets the command's message to the given String.
   *
   * @param message String to change message it.
   */
  protected void updateCommandMessage(String message) {
    this.message = message;
  }


}
