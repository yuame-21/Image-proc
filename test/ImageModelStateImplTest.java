import org.junit.Before;
import org.junit.Test;

import model.ImageModelStateImpl;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Provides test for the {@link ImageModelStateImpl} class.
 */
public class ImageModelStateImplTest {
  ImageModelStateImpl pink;
  Pixel[][] pinkBoard;
  String pinkName;
  int pinkMaxNum;

  ImageModelStateImpl twoXTwo;
  Pixel[][] twoBoard;
  String twoName;
  int twoMaxNum;

  @Before
  public void init() {
    this.pinkBoard = new Pixel[][]{
            {new Pixel(170, 0, 255), new Pixel(140, 190, 41),
                    new Pixel(0, 171, 169), new Pixel(255, 0, 127)}, //r1
            {new Pixel(0, 0, 0), new Pixel(255, 0, 127),
                    new Pixel(239, 150, 8), new Pixel(0, 0, 0)},
            {new Pixel(0, 171, 169), new Pixel(0, 0, 0),
                    new Pixel(170, 0, 255), new Pixel(239, 150, 8)},
            {new Pixel(170, 0, 255), new Pixel(0, 0, 0),
                    new Pixel(140, 190, 41), new Pixel(0, 0, 0)}
    };
    this.pinkName = "pink";
    this.pinkMaxNum = 255;
    this.pink = new ImageModelStateImpl(pinkBoard, pinkMaxNum);


    this.twoBoard = new Pixel[][]{
            {new Pixel(170, 0, 255), new Pixel(140, 190, 41)},
            {new Pixel(0, 171, 169), new Pixel(255, 0, 127)}
    };
    this.twoName = "2x2";
    this.twoMaxNum = 255;
    this.twoXTwo = new ImageModelStateImpl(twoBoard, twoMaxNum);
  }

  @Test
  public void testConstructors() {
    ImageModelStateImpl mods1 = new ImageModelStateImpl(this.twoBoard, 255);
    assertEquals(255, mods1.getMaxNum());
    assertEquals(2, mods1.getHeight());
    assertEquals(2, mods1.getWidth());

    ImageModelStateImpl mods2 = new ImageModelStateImpl("././res/2x2.ppm");
    assertEquals(255, mods2.getMaxNum());
    assertEquals(2, mods2.getWidth());
    assertEquals(2, mods2.getHeight());
  }

  @Test
  public void getWidth() {
    assertEquals(4, this.pink.getWidth());
    assertEquals(2, this.twoXTwo.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(4, this.pink.getHeight());
    assertEquals(2, this.twoXTwo.getHeight());
  }

  @Test
  public void getPixel() {
    Pixel p0 = new Pixel(170, 0, 255);
    Pixel pp4 = new Pixel(255, 0, 127);
    Pixel pp16 = new Pixel(0, 0, 0);
    assertEquals(p0, this.pink.getPixel(0, 0));
    assertEquals(pp4, this.pink.getPixel(0, 3));
    assertEquals(pp16, this.pink.getPixel(3, 3));

    assertEquals(p0, this.twoXTwo.getPixel(0, 0));
    Pixel twop2 = new Pixel(140, 190, 41);
    assertEquals(twop2, this.twoXTwo.getPixel(0, 1));
  }

  @Test
  public void copyImage() {
    assertArrayEquals(this.pinkBoard, this.pink.copyImage());
    assertArrayEquals(this.twoBoard, this.twoXTwo.copyImage());
  }

  @Test
  public void getMaxNum() {
    assertEquals(255, this.pink.getMaxNum());
    assertEquals(255, this.twoXTwo.getMaxNum());
  }
}