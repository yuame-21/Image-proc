import java.io.IOException;

import view.ImageProcessorView;

/**
 * Mock class of the ImageProcessorView used to test for transmission exceptions.
 */
public class ImageProcessorViewMock implements ImageProcessorView {
  StringBuilder log;

  public ImageProcessorViewMock() {
    this.log = new StringBuilder();
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message.equals("THROW IO")) {
      throw new IOException("ImageProcessorViewMock method, renderMessage,"
              + " throws an IOException when given message, THROW IO.");
    }
    log.append("message to render: " + message);
  }

}
