package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import controller.commands.ColorComponent;
import controller.commands.Command;
import controller.commands.DarkenBrighten;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.IntensityLumaValue;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transformation;
import model.ImageModel;
import view.ImageProcessorGUIView;

/**
 * Creates the GUI controller for an image processor. Generates a graphical popup of the image
 * processor.
 */
public class ImageProcessorGUIControllerImpl implements Features {
  private ImageModel model;
  private ImageProcessorGUIView view;
  private Command command;
  private static String TEMP_NAME = "temp";

  /**
   * Constructs the GUI controller.
   *
   * @param model The model to build the GUI off of
   * @param view  The view to display the GUI
   */
  public ImageProcessorGUIControllerImpl(ImageModel model, ImageProcessorGUIView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
    this.view.makeVisible();
  }

  /**
   * Calls the command interface that runs the given command on the model and then renders the
   * edited image.
   */
  private void initAndRender() {
    this.command.initCommand(this.model);
    this.view.renderImage(createImage(this.model.generateString(TEMP_NAME)));
    this.command.renderCommandMessage(this.view);
    this.view.setHistogram(this.model);
    this.view.refresh();
  }

  /**
   * The feature red component that visualizes the red color component to grey scale.
   */
  @Override
  public void redComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "red");
    this.initAndRender();
  }

  /**
   * The feature green component that visualizes the green color component to grey scale.
   */
  @Override
  public void greenComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "green");
    this.initAndRender();
  }

  /**
   * The feature blue component that visualizes the blue color component to grey scale.
   */
  @Override
  public void blueComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "blue");
    this.initAndRender();
  }

  /**
   * The feature that darkens an image based on given degree.
   *
   * @param degree The degree to which to darken an image
   */
  @Override
  public void darken(String degree) {
    try {
      int d = Integer.parseInt(degree);
      this.command = new DarkenBrighten(TEMP_NAME, TEMP_NAME, d, "darken");
      this.initAndRender();
    } catch (NumberFormatException e) {
      try {
        this.view.renderMessage("UNABLE TO DARKEN: To darken, please enter an integer");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  /**
   * The feature that brightens an image based on the given degree.
   *
   * @param degree The degree to which to brighten an image
   */
  @Override
  public void brighten(String degree) {
    try {
      int d = Integer.parseInt(degree);
      this.command = new DarkenBrighten(TEMP_NAME, TEMP_NAME, d, "brighten");
      this.initAndRender();
    } catch (NumberFormatException e) {
      try {
        this.view.renderMessage("UNABLE TO BRIGHTEN: To brighten, please enter an integer");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  /**
   * The feature that flips an image horizontally.
   */
  @Override
  public void horizontalFlip() {
    this.command = new Flip(TEMP_NAME, TEMP_NAME, "horizontal");
    this.initAndRender();
  }

  /**
   * The feature that flips an image vertically.
   */
  @Override
  public void verticalFlip() {
    this.command = new Flip(TEMP_NAME, TEMP_NAME, "vertical");
    this.initAndRender();
  }

  /**
   * The feature that edits the image to the average of each pixel's RGB
   */
  @Override
  public void intensity() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "intensity");
    this.initAndRender();
  }

  /**
   * The feature that converts the image to greyscale.
   */
  @Override
  public void luma() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "luma");
    this.initAndRender();
  }

  /**
   * The feature that edits each pixel to have the RGB be the max value.
   */
  @Override
  public void value() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "value");
    this.initAndRender();
  }

  /**
   * The feature that edits an image with a sepia filter.
   */
  @Override
  public void sepia() {
    this.command = new Transformation(TEMP_NAME, TEMP_NAME, "sepia");
    this.initAndRender();
  }

  /**
   * The feature that edits an image to be greyscale.
   */
  @Override
  public void greyscale() {
    this.command = new Transformation(TEMP_NAME, TEMP_NAME, "greyscale");
    this.initAndRender();
  }

  /**
   * The feature that makes an image sharper.
   */
  @Override
  public void sharpen() {
    this.command = new Filter(TEMP_NAME, TEMP_NAME, "sharpen");
    this.initAndRender();
  }

  /**
   * The feature that blurs an image.
   */
  @Override
  public void blur() {
    this.command = new Filter(TEMP_NAME, TEMP_NAME, "blur");
    this.initAndRender();
  }

  /**
   * Loads an image into the controller based on the given path.
   *
   * @param path The path the file we would like to load
   */
  @Override
  public void load(String path) {
    this.command = new Load(path, TEMP_NAME);
    this.initAndRender();
    this.view.setHistogram(this.model);
  }

  /**
   * Parses through a String of data of an image to create a buffered image.
   *
   * @param imageData The data of a given image
   * @return A BufferedImage representing the image to display
   */
  private BufferedImage createImage(String imageData) {
    String[] ar = imageData.split("\n");
    int width = Integer.parseInt(ar[0]);
    int height = Integer.parseInt(ar[1]);
    int maxNum = Integer.parseInt(ar[2]);

    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int c = 3;
    // set the RGB of the file
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        result.setRGB(i, j, new Color(Integer.parseInt(ar[c]), Integer.parseInt(ar[c + 1]),
            Integer.parseInt(ar[c + 2])).getRGB());
        c = c + 3;
      }
    }
    return result;
  }

  /**
   * The feature to save a given image.
   *
   * @param path The path to the place to save the image
   */
  @Override
  public void save(String path) {
    this.command = new Save(path, TEMP_NAME);
    this.initAndRender();
  }

  /**
   * The feature to exit the program.
   */
  @Override
  public void exitProgram() {
    System.exit(0);
  }

}
