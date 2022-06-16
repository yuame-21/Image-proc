package model.edit;

import model.ImageModelState;

/**
 * An {@Edit} is a way in which to manipulate an image.
 * e.g. flip, greyscale, brighten.
 */
public interface Edit {

  /**
   * Applies the edit to the given image.
   *
   * @param newName new name for edited image
   * @param image   what to edit
   * @return ImageModelState with edits made
   */
  ImageModelState applyEdit(String newName, ImageModelState image);


}
