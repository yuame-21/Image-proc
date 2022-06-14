package controller.commands;

import model.ImageModel;
import model.edit.Edit;

/**
 * Command to transform a given image based on String input.
 * Options for transformations are sepia and greyscale.
 */
public class Transform extends ACommand{
  String s;

  /**
   * a Transform changes the image to greyscale or sepia,
   * and saves under the new name.
   * @param originalName old name
   * @param revisedName name to change to
   * @param s transform types are sepia and greyscale
   * @throws IllegalArgumentException if invalid transform type s
   */
  public Transform(String originalName, String revisedName, String s) {
    super(originalName, revisedName);
    if (!(s.equals("sepia") || this.s.equals("greyscale"))) {
      throw new IllegalArgumentException("invalid transform type");
    }
    this.s =s;
  }

  /**
   * Initializes the transformation on an image.
   * and updates the command message to fit the command type (s).
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {

    Edit editor = new model.edit.Transform(this.s);

    model.editImage(this.originalName, this.revisedName, editor);

    this.updateCommandMessage("Transformed image, " + originalName + ", for "
            + this.s + ". Renamed edited image as " + revisedName + "\n");
  }
}
