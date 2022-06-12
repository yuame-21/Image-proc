package model;

import model.edit.Edit;

/**
 * {@code ImageModel}  creates an instance of an image.
 * This image can be loaded, saved, or edited.
 */
public interface ImageModel {

  /**
   * Edits the image based on given Edit to complete.
   * (e.g. brighten, darken, flip, greyscale)
   *
   * @param oldName old image name
   * @param newName name to change image to
   * @param editor  how to edit the image
   */
  void editImage(String oldName, String newName, Edit editor);

  /**
   * Loads the image to the model.
   *
   * @param path     where to load the image from.
   * @param fileName name of image file.
   * @throws IllegalArgumentException when the file is invalid/unable to be loaded.
   */
  public void load(String path, String fileName) throws IllegalArgumentException;

  /**
   * Saves the image to the provided path.
   *
   * @param path     where to save the file to
   * @param fileName name of image file
   * @throws IllegalArgumentException when the file is invalid/unable to be loaded.
   */
  public void save(String path, String fileName) throws IllegalArgumentException;

  /**
   * Generates a String representation of a file using the given name.
   *
   * @param name The name of a file to generate into a String
   * @return A String representation of a file
   */
  public String generateString(String name);
  // We want to keep this function public as it will provide a path to give data to the view in
  // future implementations of the model.


}
