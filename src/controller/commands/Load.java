package controller.commands;

import java.io.IOException;

import model.ImageModel;
import view.ImageProcessorView;

/**
 * Command to load an image into the program.
 * Then this image can be edited or saved based on further user commands.
 */
public class Load implements Command {
  private String name;
  private String path;
  private String message;


  /**
   * Loads image from the given path to a given name.
   *
   * @param name image name for image processor program
   * @param path where to retrieve the image from
   * @throws IllegalArgumentException if the name or path is null
   */
  public Load(String path, String name) throws IllegalArgumentException {
    if (name == null || path == null) {
      throw new IllegalArgumentException("path or name are null!");
    }
    this.name = name;
    this.path = path;
    this.message = null;
  }

  /**
   * Loads the image and sets the command message to suit the command.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    model.load(path, name);
    this.message = ("Loaded image, " + this.name + ". From path, " + this.path + "\n");
  }

  /**
   * Renders the command message to the view so the user knows what was performed.
   *
   * @param view where to render the message
   * @throws IllegalStateException    if rendering transmission fails
   * @throws IllegalArgumentException if the message is null.
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
