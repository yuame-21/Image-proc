package model.edit;

public class Transform extends AEdit {

  private String transformType;

  /**
   * A Transform takes in a type of transform to apply to the model.
   *
   * @param transformType type of transform (greyscale or sepia)
   * @throws IllegalArgumentException if transform type is invalid
   */
  public Transform(String transformType) {

    if (!(transformType.equals("greyscale") || transformType.equals("sepia"))) {
      throw new IllegalArgumentException("invalid transform type: must be greyscale or sepia");
    }
    this.transformType = transformType;
  }

  /**
   * Transforms image pixel array based on transform type.
   *
   * @param image  array of colors that make up an imgae
   * @param r      row location
   * @param c      column location
   * @param maxNum max value for RGB
   * @return 3-d array representing an image
   */
  @Override
  protected int[][][] setRGB(int[][][] image, int r, int c, int maxNum) {
    double[][] transform = this.getTransform();

    for (int i = 0; i < 3; i++) {
      int preClamp = (int) Math.round(image[r][c][0] * transform[i][0]
              + image[r][c][1] * transform[i][1] + image[r][c][2] * transform[i][2]);
      image[r][c][i] = clamp(preClamp, maxNum);
    }
    return image;
  }

  /**
   * returns transform value matrix based on transformType
   *
   * @return matrix of values to apply to pixels
   */
  private double[][] getTransform() {
    double[][] transform = new double[][]{};

    if (this.transformType.equals("greyscale")) {
      transform = new double[][]{{0.2126, 0.7152, 0.0722},
              {0.2126, 0.7152, 0.0722},
              {0.2126, 0.7152, 0.0722}};
    } else if (this.transformType.equals("sepia")) {
      transform = new double[][]{{0.393, 0.769, 0.189},
              {0.349, 0.686, 0.168},
              {0.272, 0.534, 0.131}};
    }
    return transform;
  }


}
