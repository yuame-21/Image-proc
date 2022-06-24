package controller.commands;

import model.ImageModel;
import model.edit.Edit;
import model.edit.ILV;

/**
 * Command to change the image to greyscale using its luma, value, or intensity.
 * It will use either luma, value, or intensity based on a String field.
 */
public class IntensityLumaValue extends ACommand {
  String type;

  /**
   * Uses the abstracted constructor, and an additional String field to choose.
   * either luma, value, or intensity.
   *
   * @param originalName old name
   * @param revisedName  name to change image to
   * @param type         luma, value, or intensity
   * @throws IllegalArgumentException if the String is not value, luma, or intensity
   */
  public IntensityLumaValue(String originalName, String revisedName, String type)
      throws IllegalArgumentException {
    super(originalName, revisedName);
    if (!(type.equals("luma") || type.equals("value") || type.equals("intensity"))) {
      throw new IllegalArgumentException("must enter value, luma, or intensity");
    }
    this.type = type;
  }


  /**
   * Applies the greyscale of the given type to the image.
   * Also, updates the command message to fit the transformation.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    Edit editor = new ILV(this.type);

    model.editImage(this.originalName, this.revisedName, editor);

    this.updateCommandMessage("Greyscaled image for "
        + this.type + ".\n");
  }

}
