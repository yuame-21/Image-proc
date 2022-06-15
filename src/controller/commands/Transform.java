package controller.commands;

import model.ImageModel;


/**
 * Command to transform a given image based on String input.
 * Options for transformations are sepia and greyscale.
 */
public class Transform extends ACommand {
  String s;

  /**
   * a Transform changes the image to greyscale or sepia,
   * and saves under the new name.
   *
   * @param originalName old name
   * @param revisedName  name to change to
   * @param string            transform types are sepia and greyscale
   * @throws IllegalArgumentException if invalid transform type s
   */
  public Transform(String originalName, String revisedName, String string) {
    super(originalName, revisedName);
    if (!(string.equals("sepia") || string.equals("greyscale"))) {
      throw new IllegalArgumentException("invalid transform type");
    }
    this.s = string;
  }

  /**
   * Initializes the transformation on an image.
   * and updates the command message to fit the command type (s).
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {

    model.editImage(this.originalName, this.revisedName, new model.edit.Transform(this.s));

    this.updateCommandMessage("Transformed image, " + originalName + ", to "
            + this.s + ". Renamed edited image as " + revisedName + "\n");
  }


}
