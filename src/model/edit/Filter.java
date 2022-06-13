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
    if (!(filterType.equals("blur") || (filterType.equals("sharpen")))){
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
    return new int[0][][];
    // call kernels with double loop

    // edit pixels within  loop
    // use clamp()
  }

  /**
   * Gets the correct filter matrix based on filter type.
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


}
