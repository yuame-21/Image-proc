package controller.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import controller.ImageProcessorControllerImpl;
import model.ImageModel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Represents a command to run a .txt file with String commands.
 * Instead of entering commands repeatedly through the console.
 */
public class File implements Command{
  private String file;
  private String message;

  /**
   * A file takes in a file name that must end in .txt.
   * @param file String file name to run
   * @throws IllegalArgumentException if the file name does not end in .txt
   */
  public File(String file) throws IllegalArgumentException{
    if (file.substring(file.length() - 4, file.length()-1).equals(".txt")) {
      throw new IllegalArgumentException("file must be .txt");
    }
    this.file = file;
    this.message = "uploaded file, " + this.file + "to run a stream of commands";
  }

  /**
   * initiates the File command on a model.
   * @param model what to perform the command on
   */
  @Override
  public void initCommand(ImageModel model) {
//    InputStreamReader input;
//    String commands;
//    ImageProcessorView view;
//
//    view = new ImageProcessorTextView();
//
//      this.input = input;
//      this.scanner = new Scanner(input);
//      this.knownCommands = new HashMap<>();
//      this.addCommands();
//
//      new ImageProcessorControllerImpl(model, view, input);
//
//
//    // load new input
//
//    // make new controller
//
//    // call processImage
  }

  /**
   * Renders File's command message to the user to inform of the command enacted.
   * @param view where to render the message
   * @throws IllegalStateException if the message transmission fails
   */
  public void renderCommandMessage(ImageProcessorView view) throws IllegalStateException {
    try{
      view.renderMessage(this.message);
    } catch (IOException e) {
      throw new IllegalStateException("failed transmission: could not render message");
    }
  }


}
