package view;

import java.io.IOException;

/**
 * {@code ImageProcessorTextView} implements {@code ImageProcessorView} to render text to the user.
 * The transmissions are sent to a given destination.
 */
public class ImageProcessorTextView implements ImageProcessorView {

  Appendable destination;

  /**
   * The default {@code ImageProcessorTextView} has a destination of System.out.
   */
  public ImageProcessorTextView() {
    this.destination = System.out;
  }

  /**
   * A ImageProcessorTextView that takes in a given destination at which to render text.
   *
   * @param destination where to show information
   */
  public ImageProcessorTextView(Appendable destination) {
    if (destination == null) {
      throw new IllegalArgumentException("provided destination is null!");
    }

    this.destination = destination;
  }

  /**
   * Renders a String to the user at a given location.
   *
   * @param message text to render
   * @throws IOException              if the transmission fails when appending
   * @throws IllegalArgumentException if the message to render is null
   */
  @Override
  public void renderMessage(String message) throws IOException, IllegalArgumentException {
    if (message == null) {
      throw new IllegalArgumentException("the provided message is null");
    }
    this.destination.append(message);
  }


}
