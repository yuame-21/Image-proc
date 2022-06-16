package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
   * @throws IllegalArgumentException if the file is invalid or the inputs are null
   */
  public void load(String path, String fileName) throws IllegalArgumentException {
    if (path == null || fileName == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }

    String type = path.substring(path.length() - 4);

    if (!(type.equals(".ppm") || type.equals(".png") || type.equals(".jpg") || type.equals(".bmp"))) {
      throw new IllegalArgumentException("Invalid path format");
    }

    if (!type.equals(".ppm")) {
      // moves to next load as this image is not a ppm
      try {
        load(ImageIO.read(new FileInputStream(path)), fileName);
        return;
      } catch (IOException e) {
        throw new IllegalArgumentException("Transmission failed");
      }
    }

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    StringBuilder noComment = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        noComment.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(noComment.toString());
    String token;
    token = sc.next();

    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Given file is not a real ppm");
    }

    int height = sc.nextInt();
    int width = sc.nextInt();
    int maxNum = sc.nextInt();

    Pixel[][] image = new Pixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        image[i][j] = new Pixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
      }
    }

    sc.close();
    this.log.put(fileName, new ImageModelStateImpl(image, maxNum));
  }

  /**
   * Loads non-ppm image files into the program.
   *
   * @param imageFile The image to be loaded
   * @param fileName  The name the image will be saved under in the program
   * @throws IllegalArgumentException Thrown when inputs are null
   */
  private void load(BufferedImage imageFile, String fileName) throws IllegalArgumentException {
    if (imageFile == null || fileName == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }

    int height = imageFile.getHeight();
    int width = imageFile.getWidth();

    Pixel[][] image = new Pixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        image[i][j] =
                new Pixel(new Color(imageFile.getRGB(i, j)).getRed(),
                        new Color(imageFile.getRGB(i, j)).getGreen(),
                        new Color(imageFile.getRGB(i, j)).getBlue());
      }
    }

    this.log.put(fileName, new ImageModelStateImpl(image, 255));
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

    String type = path.substring(path.length() - 3);

    if (!(type.equals("ppm") || type.equals("png") || type.equals("jpg") || type.equals("bmp"))) {
      throw new IllegalArgumentException("Invalid path format");
    }

    ImageModelState saving = find(fileName);

    // creates or locates the file to save to
    FileWriter savePath;
    try {
      savePath = new FileWriter(path);
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    }

    // saves ppm files
    if (type.equals("ppm")) {
      try {
        BufferedWriter writer = new BufferedWriter(savePath);
        writer.write("P3" + "\n");
        writer.write(generateString(fileName));
        // end of file writing
        writer.close();
      } catch (IOException e) {
        throw new IllegalArgumentException("File not found and could not be saved");
      }
    } else {
      int width = saving.getWidth();
      int height = saving.getHeight();

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      // set the RGB of the file
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          Pixel R = saving.getPixel(j, i);
          image.setRGB(i, j, new Color(R.get(0), R.get(1), R.get(2)).getRGB());
        }
      }

      try {
        ImageIO.write(image, type, new File(path));
      } catch (IOException e) {
        throw new IllegalArgumentException("Save failed");
      }
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
    ans.append(saving.getWidth() + "\n");
    ans.append(saving.getHeight() + "\n");
    ans.append(saving.getMaxNum() + "\n");

    for (int r = 0; r < saving.getHeight(); r++) {
      for (int c = 0; c < saving.getWidth(); c++) {
        Pixel RGB = saving.getPixel(r, c);
        for (int i = 0; i < 3; i++) {
          ans.append(RGB.get(i) + "\n");
        }
      }
    }
    return ans.toString();
  }
}