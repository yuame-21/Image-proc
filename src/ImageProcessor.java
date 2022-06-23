import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import controller.ImageProcessorGUIControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Using this class, the controller can access the method that will initiate the program.
 */
public final class ImageProcessor {

  /**
   * Method that calls upon the controller to start the image processor.
   *
   * @param args String arguments given by the user
   */
  public static void main(String[] args) {
    String s;
    ImageModel model = new ImageModelImpl();

    // GUI
    if (args.length == 0) {
      ImageProcessorGUIView view = new ImageProcessorGUIViewImpl();
      ImageProcessorGUIControllerImpl guiController =
              new ImageProcessorGUIControllerImpl(model, view);
    }

    // Text UI with script
    else if (args.length > 0 && args[0].equals("-file")) {

      String file = args[1];

      if (file.substring(file.length() - 4, file.length() - 1).equals(".txt")) {
        throw new IllegalArgumentException("file must be .txt");
      }

      try {
        s = Files.readString(Paths.get(file));
      } catch (IOException e) {
        throw new IllegalArgumentException("invalid file");
      }

      Readable input = new BufferedReader(new StringReader(s));

      ImageProcessorView textView = new ImageProcessorTextView();
      ImageProcessorController textController = new ImageProcessorControllerImpl(model,
              textView, input);
      textController.processImage();
    }

    // command line entry textUI
    else if (args.length > 0 && args[0].equals("-text")) {
      Readable input = new InputStreamReader(System.in);
      ImageProcessorView textView = new ImageProcessorTextView();
      ImageProcessorController textController = new ImageProcessorControllerImpl(model,
              textView, input);
      textController.processImage();
    } else {
      throw new IllegalArgumentException("invalid main command!");
    }

  }

}
