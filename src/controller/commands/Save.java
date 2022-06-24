package controller.commands;

import java.io.IOException;

import model.ImageModel;
import view.ImageProcessorView;

/**
 * Command to save the image at a specific location on the computer.
 */
public class Save implements Command {
  private String name;
  private String path;
  private String message;


  /**
   * Saves the image at a given path under a given name.
   *
   * @param name image name to save as
   * @param path where to save
   * @throws IllegalArgumentException if the path or name is null.
   */
  public Save(String path, String name) {
    if (name == null || path == null) {
      throw new IllegalArgumentException("path or name are null!");
    }
    this.name = name;
    this.path = path;
    this.message = null;
  }

  /**
   * Saves the image and updates the command message to suit this command.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    model.save(path, name);
    this.message = ("Saved image to path, " + this.path + "\n");
  }

  /**
   * Renders the command message to given view so the user knows what was performed.
   *
   * @param view where to render the message
   * @throws IllegalStateException    if the rendering fails
   * @throws IllegalArgumentException if the message is null
   */
  @Override
  public void renderCommandMessage(ImageProcessorView view) throws IllegalStateException {
    if (this.message == null) {
      throw new IllegalArgumentException("the command message is null");
    }

    try {
      view.renderMessage(this.message);
    } catch (IOException e) {
      throw new IllegalStateException("failed transmission: could not render message");
    }
  }


}
