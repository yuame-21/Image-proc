package controller.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import model.ImageModel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Represents a command to run a .txt file with String commands.
 * Instead of entering commands repeatedly through the console.
 */
public class File implements Command {
  private String file;
  private String message;

  /**
   * A file takes in a file name that must end in .txt.
   *
   * @param file String file name to run
   * @throws IllegalArgumentException if the file name does not end in .txt
   */
  public File(String file) throws IllegalArgumentException {
    if (file.substring(file.length() - 4, file.length() - 1).equals(".txt")) {
      throw new IllegalArgumentException("file must be .txt");
    }
    this.file = file;
    this.message = "uploaded file, " + this.file + "and ran the previous stream of commands.";
  }

  /**
   * initiates the File command on a model.
   *
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
    BufferedReader input;
    String s;
    ImageProcessorView view;
    ImageProcessorController controller;

    view = new ImageProcessorTextView();

    try {
      s = Files.readString(Paths.get(this.file));
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid file");
    }

    input = new BufferedReader(new StringReader(s));

    controller = new ImageProcessorControllerImpl(model, view, input);

    controller.processImage();
  }

  /**
   * Renders File's command message to the user to inform of the command enacted.
   *
   * @param view where to render the message
   * @throws IllegalStateException if the message transmission fails
   */
  public void renderCommandMessage(ImageProcessorView view) throws IllegalStateException {
    try {
      view.renderMessage(this.message);
    } catch (IOException e) {
      throw new IllegalStateException("failed transmission: could not render message");
    }
  }


}
