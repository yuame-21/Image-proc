package controller.commands;

import model.ImageModel;
import model.edit.FlipHZ;

/**
 * Command to flip the image either vertically or horizontally based on the input.
 */
public class Flip extends ACommand {

  String flipDirection;

  /**
   * Uses the abstract constructor, and takes in an additional direction in which to flip.
   *
   * @param originalName old name
   * @param revisedName  name to change image to
   * @param direction    either horizontal or vertical
   * @throws IllegalArgumentException if the direction is not vertical or horizontal
   */
  public Flip(String originalName, String revisedName, String direction)
          throws IllegalArgumentException {
    super(originalName, revisedName);

    if (!(direction.equals("horizontal") || direction.equals("vertical"))) {
      throw new IllegalArgumentException("invalid flip direction input");
    }

    this.flipDirection = direction;

  }

  /**
   * Applies the proper flip to the image based on the input.
   * Updates the command message to fit the action performed.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
      model.editImage(this.originalName, this.revisedName, new FlipHZ(this.flipDirection));
      this.updateCommandMessage(this.flipDirection + "ly flipped image, " + originalName + ". " +
              "Renamed edited image as " + revisedName + "\n");

  }


}
