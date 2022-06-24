import org.junit.Before;
import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import model.ImageModelStateImpl;
import model.ImageProcessorHistogram;
import model.Pixel;
import view.ImageProcessorHistogramView;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the histogram.
 */
public class ImageProcessorHistogramTest {

  ImageModelStateImpl twoXTwoMS;
  ImageModel twoXTwoM;
  Pixel[][] twoBoard;
  String twoName;
  int twoMaxNum;
  ImageProcessorHistogram histogram;

  @Before
  public void init() {
    this.twoBoard = new Pixel[][]{
        {new Pixel(170, 0, 255), new Pixel(140, 190, 41)},
        {new Pixel(0, 171, 169), new Pixel(255, 0, 127)}
    };
    this.twoName = "2x2";
    this.twoMaxNum = 255;
    this.twoXTwoMS = new ImageModelStateImpl(twoBoard, twoMaxNum);
    this.twoXTwoM = new ImageModelImpl();
    this.twoXTwoM.load("././res/2x2.ppm", "temp");
    this.histogram = new ImageProcessorHistogram(this.twoXTwoM);
  }

  @Test
  public void getIntensities() {
    assertArrayEquals(new int[]{141, 123, 113, 127}, this.histogram.getIntensities());
    assertEquals(141, this.histogram.getIntensities()[0]);
    assertEquals(123, this.histogram.getIntensities()[1]);
    assertEquals(113, this.histogram.getIntensities()[2]);
    assertEquals(127, this.histogram.getIntensities()[3]);
  }

  @Test
  public void getBlues() {
    assertArrayEquals(new int[]{255, 41, 169, 127}, this.histogram.getBlues());
    assertEquals(255, this.histogram.getBlues()[0]);
    assertEquals(41, this.histogram.getBlues()[1]);
    assertEquals(169, this.histogram.getBlues()[2]);
    assertEquals(127, this.histogram.getBlues()[3]);
  }

  @Test
  public void getGreens() {
    assertArrayEquals(new int[]{0, 190, 171, 0}, this.histogram.getGreens());
    assertEquals(0, this.histogram.getGreens()[0]);
    assertEquals(190, this.histogram.getGreens()[1]);
    assertEquals(171, this.histogram.getGreens()[2]);
    assertEquals(0, this.histogram.getGreens()[3]);
  }

  @Test
  public void getReds() {
    assertArrayEquals(new int[]{170, 140, 0, 255}, this.histogram.getReds());
    assertEquals(170, this.histogram.getReds()[0]);
    assertEquals(140, this.histogram.getReds()[1]);
    assertEquals(0, this.histogram.getReds()[2]);
    assertEquals(255, this.histogram.getReds()[3]);
  }

  @Test
  public void setHistogramModel() {
    ImageModel m = new ImageModelImpl();
    m.load("././res/pink.ppm", "temp");
    ImageProcessorHistogram hm = new ImageProcessorHistogram(m);
    ImageProcessorHistogramView v = new ImageProcessorHistogramView();
    v.setHistogramModel(hm);

    try {
      v.repaint();
      return;
    } catch (NullPointerException e) {
      fail("should not have thrown");
    }
  }

}