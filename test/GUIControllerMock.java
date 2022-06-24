import controller.Features;
import model.ImageModel;
import view.ImageProcessorGUIView;

public class GUIControllerMock implements Features {
  StringBuilder log;
  ImageProcessorGUIView view;
  ImageModel model;

  public GUIControllerMock(ImageProcessorGUIView view, ImageModel model) {
    this.log = new StringBuilder();
    this.view = view;
    this.model = model;
  }

  @Override
  public void redComponent() {
    log.append("red component" + " ");
  }

  @Override
  public void greenComponent() {
    log.append("green component" + " ");
  }

  @Override
  public void blueComponent() {
    log.append("blue component" + " ");
  }

  @Override
  public void darken(String degree) {
    log.append("darken " + degree + " ");
  }

  @Override
  public void brighten(String degree) {
    log.append("brighten " + degree + " ");
  }

  @Override
  public void horizontalFlip() {
    log.append("horizontal flip" + " ");
  }

  @Override
  public void verticalFlip() {
    log.append("vertical flip" + " ");
  }

  @Override
  public void intensity() {
    log.append("intensity" + " ");
  }

  @Override
  public void luma() {
    log.append("luma" + " ");
  }

  @Override
  public void value() {
    log.append("value" + " ");
  }

  @Override
  public void sepia() {
    log.append("sepia" + " ");
  }

  @Override
  public void greyscale() {
    log.append("greyscale" + " ");
  }

  @Override
  public void sharpen() {
    log.append("sharpen" + " ");
  }

  @Override
  public void blur() {
    log.append("blur" + " ");
  }

  @Override
  public void load(String path) {
    log.append("load path: " + path + " ");
  }

  @Override
  public void save(String path) {
    log.append("save path: " + path + " ");

  }

  @Override
  public void exitProgram() {
    log.append("exit" + " ");
  }
}
