package controller.commands;

import model.ImageModel;

/**
 * Command to visualize a color component of an image to greyscale it.
 * A color component is either red, green or blue.
 */
public class ColorComponent extends ACommand {
  String color;

  /**
   * A Color Component Command uses the abstracted command constructor, and takes in an.
   * additional String field that determines which color to visualize.
   *
   * @param originalName old image name
   * @param revisedName  name to change image to
   * @param color        color to visualize in greyscale
   * @throws IllegalArgumentException if an invalid color is given
   */
  public ColorComponent(String originalName, String revisedName, String color) throws IllegalArgumentException {
    super(originalName, revisedName);

    if (!(color.equals("red") || color.equals("green") || color.equals("blue"))) {
      throw new IllegalArgumentException("not a valid color component! " +
          "enter either 'red,' 'green,'or 'blue'");
    }

    this.color = color;
  }


  /**
   * Runs the color component command on the model to edit the image.
   * This method also updates the Command's message to reflect what
   * transformation was performed.
   *
   * @param model where the command will be applied.
   */
  @Override
  public void initCommand(ImageModel model) {
    model.editImage(this.originalName, this.revisedName, new model.edit.ColorComponent(this.color));
    this.updateCommandMessage("Greyscaled image, " + originalName + ", for color component, "
        + this.color + ". Renamed edited image as " + revisedName + "\n");
  }


}
