package model.edit;

import model.Pixel;

public class FilterSharpenBlur extends AEdit {
  private String filterType;

  /**
   * A filter on a model depends on the type of filter given.
   *
   * @param filterType type of filter to apply
   * @throws IllegalArgumentException if the filterType is not blur or sharpen
   */
  public FilterSharpenBlur(String filterType) throws IllegalArgumentException {
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
  protected Pixel[][] setRGB(Pixel[][] image, int r, int c, int maxNum) {

    Pixel[][] kernel = getKernel(r, c, image);
    int preClamp = 0;
    int red = 0;
    int gr =0;
    int bl = 0;


    for (int i = 0; i < kernel[0].length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        red =+ (int) Math.round(kernel[i][j].get(0) * this.getFilter()[i][j]);
        gr =+ (int) Math.round(kernel[i][j].get(1) * this.getFilter()[i][j]);
        bl =+ (int) Math.round(kernel[i][j].get(2) * this.getFilter()[i][j]);
      }
    }

    image[r][c] = new Pixel(clamp(red, maxNum), clamp(gr, maxNum), clamp(bl, maxNum));

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
              {-1 / 8, 1 / 4, 1 / 4, 1 / 4, - 1 / 8},
              {-1 / 8, 1 / 4, 1, 1 / 4 ,- 1 / 8},
              {-1 / 8, 1 / 4, 1 / 4, 1 / 4, - 1 / 8},
              {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}};
    }
    return filter;
  }

  private Pixel[][] getKernel(int r, int c, Pixel[][] image) {
    double[][] matrix = this.getFilter();
    int matrixSize = matrix[0].length;
    Pixel[][] kernel = new Pixel[matrixSize][matrixSize];

    for (int i = 0; i < matrixSize; i++) {
      for (int j = 0; j < matrixSize; j++) {

        // floor of half the matrix so we start at the top-left corner when getting kernels
        int halfMatrix = (int) (Math.floor(matrixSize / 2));
        try {
          kernel[i][j] = image[i + (r - halfMatrix)][j + (c - halfMatrix)];
        } catch (ArrayIndexOutOfBoundsException e) {
          kernel[i][j] = new Pixel(0, 0, 0);
        }
      }
    }
    return kernel;
  }

}