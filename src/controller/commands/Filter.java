package controller.commands;

import model.ImageModel;
import model.edit.Edit;

/**
 * Command Filter can filter an image based on String input type.
 * This class accepts either "blur" or "greyscale" as valid filters.
 */
public class Filter extends ACommand {
  String s;

  /**
   * A Filter filters an image based on type s and renames appropriately.
   * @param originalName old name
   * @param revisedName name to change to
   * @param s filter type: blur or sharpen
   * @throws IllegalArgumentException if the filter type is invalid
   */
  public Filter(String originalName, String revisedName, String s) {
    super(originalName, revisedName);

    if (!(s.equals("blur") || s.equals("sharpen"))) {
      throw new IllegalArgumentException("valid filters are blur and sharpen");
    }
    this.s = s;
  }

  /**
   * Initializes the filter command on a model to apply given filter (type s).
   * And updates the command message appropriately
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    Edit editor = new model.edit.Filter(this.s);

    model.editImage(this.originalName, this.revisedName, editor);

    this.updateCommandMessage("Filtered image, " + originalName + ", for "
            + this.s + ". Renamed edited image as " + revisedName + "\n");
  }

}
