package controller.commands;

import controller.commands.ACommand;
import model.ImageModel;
import model.edit.BrightenDarken;

/**
 * Command to darken or brighten an image.
 * This command will either brighten or darken by a given degree
 * based on the Command call.
 */
public class DarkenBrighten extends ACommand {
  private final int degree;
  private final String direction;

  /**
   * Calls the abstract Command constructor, and includes an additional degree of.
   * severity to mutate, and whether to darken or brighten.
   *
   * @param originalName old name
   * @param revisedName  name to change image to
   * @param degree       how much to change the colors (severity)
   * @param direction    either darken or brighten
   * @throws IllegalArgumentException if the direction is not brighten or darken, or if the
   *                                  degree is negative.
   */
  public DarkenBrighten(String originalName, String revisedName, int degree, String direction)
          throws IllegalArgumentException {
    super(originalName, revisedName);
    if ((!(direction.equals("brighten") || direction.equals("darken"))) || (degree < 0)) {
      throw new IllegalArgumentException("invalid brighten/darken direction");
    }
    this.direction = direction;

    if (this.direction.equals("darken")) {
      this.degree = degree * -1;
    } else {
      this.degree = degree;
    }
  }

  /**
   * Applies the darken/brighten to a given model in order to manipulate an image.
   * Also, updates the command message to fit the Darken/Brighten.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    model.editImage(this.originalName, this.revisedName, new BrightenDarken(this.degree));
    this.updateCommandMessage(this.direction + "ed image, " + originalName + ", by "
            + Math.abs(this.degree) + ". Renamed edited image as " + revisedName + "\n");
  }

}
