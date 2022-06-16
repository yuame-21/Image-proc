package controller;

/**
 * Controller for the Image Processor program.
 * The controller takes in commands from the user to load, save, or manipulate the image
 * and delegates to the model and view where necessary.
 */
public interface ImageProcessorController {

  /**
   * Runs the image processor program, taking in command inputs from the user.
   * and delegating them where necessary.
   * Renders messages at start, and in response to the reader.
   *
   * @throws IllegalStateException if message rendering fails
   */
  public void processImage() throws IllegalStateException;

}
