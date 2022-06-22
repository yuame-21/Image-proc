import java.io.InputStreamReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import controller.ImageProcessorGUIControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

public final class ImageProcessorGUI {

  public static void main(String[] args) {

    ImageModel model = new ImageModelImpl();
    ImageProcessorGUIView view = new ImageProcessorGUIViewImpl();

    ImageProcessorGUIControllerImpl controller = new ImageProcessorGUIControllerImpl(model);
  }
}
