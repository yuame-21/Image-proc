package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageProcessorHistogramTest {

  ImageModelStateImpl twoXTwo;
  Pixel[][] twoBoard;
  String twoName;
  int twoMaxNum;

  @Before
  public void init() {
    this.twoBoard = new Pixel[][]{
            {new Pixel(170, 0, 255), new Pixel(140, 190, 41)},
            {new Pixel(0, 171, 169), new Pixel(255, 0, 127)}
    };
    this.twoName = "2x2";
    this.twoMaxNum = 255;
    this.twoXTwo = new ImageModelStateImpl(twoBoard, twoMaxNum);
  }

  @Test
  public void histogram() {
  }

  @Test
  public void getIntensities() {
  }

  @Test
  public void getBlues() {
  }

  @Test
  public void getGreens() {
  }

  @Test
  public void getReds() {
  }
}