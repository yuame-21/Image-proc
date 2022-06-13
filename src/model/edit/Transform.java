package model.edit;

public class Transform extends AEdit{

  private String transformType;

  /**
   * A Transform takes in a type of transform to apply to the model.
   * @param transformType type of transform (greyscale or sepia)
   * @throws IllegalArgumentException if transform type is invalid
   */
  public Transform(String transformType) {

    if (!(transformType.equals("greyscale") || transformType.equals("sepia"))){
      throw new IllegalArgumentException("invalid transform type: must be greyscale or sepia");
    }
    this.transformType = transformType;
  }


  /**
   * Transforms image pixel array based on transform type.
   * @param image array of colors that make up an imgae
   * @param r     row location
   * @param c     column location
   * @param maxNum max value for RGB
   * @return 3-d array representing an image
   */
  @Override
  protected int[][][] setRGB(int[][][] image, int r, int c, int maxNum) {
    return new int[0][][];
  }
}
