package model.edit;

public class Filter extends AEdit {
  private String filterType;

  /**
   * A filter on a model depends on the type of filter given.
   *
   * @param filterType type of filter to apply
   * @throws IllegalArgumentException if the filterType is not blur or sharpen
   */
  public Filter(String filterType) {
    if (!(filterType.equals("blur") || (filterType.equals("sharpen")))) {
      throw new IllegalArgumentException("invalid filter type");
    }


    this.filterType = filterType;
  }


  /**
   * Applies a Filter to the given image array of pixels.
   *
   * @param image  array of colors that make up an imgae
   * @param r      row location
   * @param c      column location
   * @param maxNum
   * @return
   */
  @Override
  protected int[][][] setRGB(int[][][] image, int r, int c, int maxNum) {

    int[][][] kernel = getKernel(r, c, image);
    int preClamp = 0;

    for (int i = 0; i < kernel[0].length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        for (int p = 0; p < 3; p++) {
          preClamp = (int) Math.round(kernel[i][j][p] * this.getFilter()[i][j]);
          image[r][c][p] = clamp(preClamp, maxNum);
        }
      }
    }
    return image;
  }

  /**
   * Gets the correct filter matrix based on filter type.
   *
   * @return matrix of filter values
   */
  private double[][] getFilter() {
    double[][] filter = new double[][]{};
    if (this.filterType.equals("blur")) {
      filter = new double[][]{{1 / 16, 1 / 8, 1 / 16},
              {1 / 8, 1 / 4, 1 / 8},
              {1 / 16, 1 / 8, 1 / 16}};
    } else if (this.filterType.equals("sharpen")) {
      filter = new double[][]{{-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8},
              {-1 / 8, 1 / 4, 1 / 4, 1 / 4 - 1 / 8},
              {-1 / 8, 1 / 4, 1, 1 / 4 - 1 / 8},
              {-1 / 8, 1 / 4, 1 / 4, 1 / 4 - 1 / 8},
              {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}};
    }
    return filter;
  }

  private int[][][] getKernel(int r, int c, int[][][] image) {
    double[][] matrix = this.getFilter();
    int matrixSize = matrix[0].length;
    int[][][] kernel = new int[][][]{};

    for (int i = 0; i < matrixSize; i++) {
      for (int j = 0; j < matrixSize; j++) {

        // floor of half the matrix so we start at the top-left corner when getting kernels
        int halfMatrix = (int) (Math.floor(matrixSize / 2));
        try {
          kernel[i][j] = image[i + (r - halfMatrix)][j + (c - halfMatrix)];
        } catch (IndexOutOfBoundsException e) {
          kernel[i][j] = new int[]{0, 0, 0};
        }
      }
    }
    return kernel;
  }


}
