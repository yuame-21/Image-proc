package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ColorComponent;
import controller.commands.Command;
import controller.commands.DarkenBrighten;
import controller.commands.File;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.IntensityLumaValue;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transformation;
import model.ImageModel;
import view.ImageProcessorView;

/**
 * {@code ImageProcessorControllerImpl} implements {@code ImageProcessorController}.
 * to control the program. The controller takes in user input and delgates commands
 * to the model and view where appropriate.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {

  private final ImageModel model;
  private ImageProcessorView view;
  private final Map<String, Function<Scanner, Command>> knownCommands;
  private final Scanner scanner;

  /**
   * The controller takes in a given model, view, and input. It adds all known.
   * commands to a Map of commands that may be called by the input.
   *
   * @param model the model contains all data of an image
   * @param view  the view can render text/images for the user
   * @param input user input of commands / manipulations
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public ImageProcessorControllerImpl(ImageModel model, ImageProcessorView view, Readable input)
      throws IllegalArgumentException {
    if (model == null || input == null || view == null) {
      throw new IllegalArgumentException("Model, view, and/or input cannot be null");
    }

    this.model = model;
    this.view = view;
    this.scanner = new Scanner(input);
    this.knownCommands = new HashMap<>();
    this.addCommands();
  }

  /**
   * Adds all commands to the Map of commands that the user can call.
   *
   * <p>In A5, we edited this method to include the new functionalities:
   * transform, filter, and the new load and saves
   */
  private void addCommands() {
    this.knownCommands.put("brighten",
        (Scanner s) -> {
          return new DarkenBrighten(s.next(),
              s.next(), s.nextInt(), "brighten");
        });
    this.knownCommands.put("darken",
        (Scanner s) -> {
          return new DarkenBrighten(s.next(),
              s.next(), s.nextInt(), "darken");
        });
    this.knownCommands.put("red-component",
        (Scanner s) -> {
          return new ColorComponent(s.next(), s.next(), "red");
        });
    this.knownCommands.put("green-component",
        (Scanner s) -> {
          return new ColorComponent(s.next(), s.next(), "green");
        });
    this.knownCommands.put("blue-component",
        (Scanner s) -> {
          return new ColorComponent(s.next(), s.next(), "blue");
        });
    this.knownCommands.put("horizontal-flip",
        (Scanner s) -> {
          return new Flip(s.next(), s.next(), "horizontal");
        });
    this.knownCommands.put("vertical-flip",
        (Scanner s) -> {
          return new Flip(s.next(), s.next(), "vertical");
        });
    this.knownCommands.put("value-component",
        (Scanner s) -> {
          return new IntensityLumaValue(s.next(), s.next(), "value");
        });
    this.knownCommands.put("luma-component",
        (Scanner s) -> {
          return new IntensityLumaValue(s.next(), s.next(), "luma");
        });
    this.knownCommands.put("intensity-component",
        (Scanner s) -> {
          return new IntensityLumaValue(s.next(), s.next(), "intensity");
        });
    this.knownCommands.put("load",
        (Scanner s) -> {
          return new Load(s.next(), s.next());
        });
    this.knownCommands.put("save",
        (Scanner s) -> {
          return new Save(s.next(), s.next());
        });
    this.knownCommands.put("sepia", (Scanner s) -> {
      return new Transformation(s.next(), s.next(), "sepia");
    });
    this.knownCommands.put("greyscale", (Scanner s) -> {
      return new Transformation(s.next(), s.next(), "greyscale");
    });
    this.knownCommands.put("blur", (Scanner s) -> {
      return new Filter(s.next(), s.next(), "blur");
    });
    this.knownCommands.put("sharpen", (Scanner s) -> {
      return new Filter(s.next(), s.next(), "sharpen");
    });
    this.knownCommands.put("-file", (Scanner s) -> {
      return new File(s.next());
    });
  }

  /**
   * Runs the image processor for the user. It delegates text to the view,
   * and checks user input to apply commands to edit the image.
   *
   * @throws IllegalStateException if the view cannot render the message
   */
  @Override
  public void processImage() throws IllegalStateException {
    welcome();

    while (scanner.hasNext()) {
      String next = scanner.next();
      Command c;

      if (next.equalsIgnoreCase("q")) {
        quit();
        return;
      }

      Function<Scanner, Command> command = this.knownCommands.getOrDefault(next, null);

      if (command == null) {
        renderImageWithTryCatch("given command is invalid\n", "invalid command");

      } else {
        try {
          c = command.apply(scanner);
          c.initCommand(this.model);
          c.renderCommandMessage(view);
        } catch (IllegalArgumentException e) {
          renderImageWithTryCatch(e.getMessage() + "\n", "initializing command");
        }
      }

    }

  }

  private void renderImageWithTryCatch(String message, String exc) {
    try {
      view.renderMessage(message);
    } catch (IOException ex) {
      throw new RuntimeException("invalid message transmission: " + exc);
    }
  }


  /**
   * Renders the welcome message for the image processor program.
   */
  private void welcome() {
    renderImageWithTryCatch("Welcome to Mimi and Ella's Image Processor <3\n\n"
        + "To use this program, first load a file with the command:\n"
        + "load + file + name\n"
        + "Or, use a .txt script with:\n"
        + "-file + file.txt\n\n"
        + "Then, you can edit the image in the following ways:\n"
        + "brighten + degree, darken + degree\n"
        + "red-component, green-component, blue-component\n"
        + "horizontal-flip, vertical-flip\n"
        + "value-component, luma-component, intensity-component\n"
        + "greyscale, sepia, blur, sharpen\n"
        + "To save your image, type:\n"
        + "save + file + name\n\n"
        + "To quit, type:\n"
        + "q or Q\n", "rendering the welcome message");
  }

  /**
   * Renders the quit message for the program when the user types Q.
   */
  private void quit() {
    renderImageWithTryCatch("Program quit.\n\n"
        + "bye! <3\n", "rendering the quit message");
  }


}
