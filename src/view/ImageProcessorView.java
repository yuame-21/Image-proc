package view;

import java.io.IOException;

/**
 * View component for the image processor program.
 * Contains method that will render text for the user to see at a given destination.
 */
public interface ImageProcessorView {

  /**
   * Renders a given String for the user to view.
   * @param message text to render
   * @throws IOException if the transmission fails
   */
  void renderMessage(String message) throws IOException;
}
