import org.junit.Before;
import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the GUI controller.
 */
public class ImageProcessorGUIControllerImplTest {
  GUIControllerMock controller;

  @Before
  public void init() {
    ImageModel model = new ImageModelImpl();
    ImageProcessorGUIView view = new ImageProcessorGUIViewImpl();
    this.controller = new GUIControllerMock(view, model);
  }

  @Test
  public void redComponent() {
    this.controller.redComponent();
    assertEquals(controller.log.toString(), "red component ");
  }

  @Test
  public void greenComponent() {
    this.controller.greenComponent();
    assertEquals(controller.log.toString(), "green component ");
  }

  @Test
  public void blueComponent() {
    this.controller.blueComponent();
    assertEquals(controller.log.toString(), "blue component ");
  }

  @Test
  public void darken() {
    this.controller.darken("10");
    assertEquals(controller.log.toString(), "darken 10 ");
  }

  @Test
  public void brighten() {
    this.controller.brighten("10");
    assertEquals(controller.log.toString(), "brighten 10 ");
  }

  @Test
  public void horizontalFlip() {
    this.controller.horizontalFlip();
    assertEquals(controller.log.toString(), "horizontal flip ");
  }

  @Test
  public void verticalFlip() {
    this.controller.verticalFlip();
    assertEquals(controller.log.toString(), "vertical flip ");
  }

  @Test
  public void intensity() {
    this.controller.intensity();
    assertEquals(controller.log.toString(), "intensity ");
  }

  @Test
  public void luma() {
    this.controller.luma();
    assertEquals(controller.log.toString(), "luma ");
  }

  @Test
  public void value() {
    this.controller.value();
    assertEquals(controller.log.toString(), "value ");
  }

  @Test
  public void sepia() {
    this.controller.sepia();
    assertEquals(controller.log.toString(), "sepia ");
  }

  @Test
  public void greyscale() {
    this.controller.greyscale();
    assertEquals(controller.log.toString(), "greyscale ");
  }

  @Test
  public void sharpen() {
    this.controller.sharpen();
    assertEquals(controller.log.toString(), "sharpen ");
  }

  @Test
  public void blur() {
    this.controller.blur();
    assertEquals(controller.log.toString(), "blur ");
  }

  @Test
  public void load() {
    this.controller.load("path");
    assertEquals(controller.log.toString(), "load path: path ");
  }

  @Test
  public void save() {
    this.controller.save("path");
    assertEquals(controller.log.toString(), "save path: path ");
  }

  @Test
  public void exitProgram() {
    this.controller.exitProgram();
    assertEquals(controller.log.toString(), "exit ");
  }

  @Test
  public void mixedFeatures() {
    this.controller.load("path");
    this.controller.brighten("10");
    this.controller.luma();
    this.controller.greyscale();
    this.controller.verticalFlip();
    this.controller.exitProgram();

    assertEquals(this.controller.log.toString(), "load path: path " +
        "brighten 10 luma greyscale vertical flip exit ");

  }
}