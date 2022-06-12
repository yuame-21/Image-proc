package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.edit.Edit;

/**
 * Describes the behaviors of an image model and records log of model manipulations.
 * Logs in a map of named {@code ImageModelState}s.
 */
public class ImageModelImpl implements ImageModel {
  private final Map<String, ImageModelState> log;

  /**
   * An {@code ImageModelImpl} starts with a blank log that it will add to as edits occur.
   */
  public ImageModelImpl() {
    this.log = new HashMap<String, ImageModelState>();
  }

  /**
   * Loads an image to the model.
   *
   * @param path     where to load the image from.
   * @param fileName name of image file.
   * @throws IllegalArgumentException if the file is invalid.
   */
  public void load(String path, String fileName) throws IllegalArgumentException {
    this.log.put(fileName, new ImageModelStateImpl(path));
  }

  /**
   * Edits image based on given editor.
   * Then, logs the change to the provided names.
   *
   * @param oldName old image name
   * @param newName name to change image to
   * @param editor  how to edit the image
   */
  @Override
  public void editImage(String oldName, String newName, Edit editor) {

    ImageModelState ms = editor.applyEdit(newName, find(oldName));

    if (oldName.equals(newName)) {
      log.replace(newName, ms);
    } else {
      log.put(newName, ms);
    }
  }

  /**
   * Finds an image file name in the log of edits.
   *
   * @param name image name
   * @return ImageModelState that corresponds to the given name/key
   * @throws IllegalArgumentException if the file does not exist in the log
   */
  private ImageModelState find(String name) throws IllegalArgumentException {
    if (log.containsKey(name)) {
      return log.get(name);
    } else {
      throw new IllegalArgumentException("Given file name is not valid");
    }
  }

  /**
   * Saves the image to the given location under the given name.
   *
   * @param path     where to save the file to
   * @param fileName name of image file
   * @throws IllegalArgumentException if the file is invalid or cannot be saved.
   */
  @Override
  public void save(String path, String fileName) throws IllegalArgumentException {
    if (!path.substring(path.length() - 4).equals(".ppm")) {
      throw new IllegalArgumentException("Invalid path format");
    }

    try {
      BufferedWriter writer = new BufferedWriter((new FileWriter(path)));
      writer.write(generateString(fileName));
      // end of file writing
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found and could not be saved");
    }
  }

  /**
   * Generates a String representing the RGB of an {@code ImageModelState}.
   *
   * @param name The name of the state to be generated
   * @return A String representing the given {@code ImageModelState}
   * @throws IllegalArgumentException Thrown when given name cannot be found in the hashMap
   */
  public String generateString(String name) throws IllegalArgumentException {
    ImageModelState saving = find(name);

    StringBuilder ans = new StringBuilder();
    ans.append("P3" + "\n");
    ans.append(saving.getWidth() + "\n");
    ans.append(saving.getHeight() + "\n");
    ans.append(saving.getMaxNum() + "\n");

    for (int r = 0; r < saving.getHeight(); r++) {
      for (int c = 0; c < saving.getWidth(); c++) {
        int[] RGB = saving.getPixel(r, c);
        for (int i = 0; i < 3; i++) {
          ans.append(RGB[i] + "\n");
        }
      }
    }
    return ans.toString();
  }


}