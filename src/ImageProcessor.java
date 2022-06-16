import java.io.InputStreamReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
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

    ImageModel model = new ImageModelImpl();
    ImageProcessorView view = new ImageProcessorTextView();

    ImageProcessorController controller = new ImageProcessorControllerImpl(model,
        view, new InputStreamReader(System.in));

    controller.processImage();

  }

}
