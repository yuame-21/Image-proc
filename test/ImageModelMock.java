import model.ImageModel;
import model.edit.Edit;

/**
 * Image Model mock for testing.
 * These methods log all inputs to the methods to confirm that they are receiving
 * the correct information.
 */
public class ImageModelMock implements ImageModel {

  StringBuilder log;

  /**
   * An image model mock takes in a log to track inputs.
   * @param log StringBuilder
   */
  public ImageModelMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void editImage(String oldName, String newName, Edit editor) {
    log.append("method: editImage old name: " + oldName + " new name: " + newName);
  }

  @Override
  public void load(String path, String fileName) {
    log.append("method: load path: " + path + " file name: " + fileName);
  }

  @Override
  public void save(String path, String fileName) {
    log.append("method: save path: " + path + " file name: " + fileName);
  }

  @Override
  public String generateString(String name) {
    log.append("generate string: " + name);
    return "generate string: " + name;
  }


}
