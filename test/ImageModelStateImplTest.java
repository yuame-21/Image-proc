import org.junit.Before;
import org.junit.Test;

import model.ImageModelStateImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Provides test for the {@link ImageModelStateImpl} class.
 */
public class ImageModelStateImplTest {
  ImageModelStateImpl pink;
  int[][][] pinkBoard;
  String pinkName;
  int pinkMaxNum;

  ImageModelStateImpl twoXTwo;
  int[][][] twoBoard;
  String twoName;
  int twoMaxNum;

  @Before
  public void init() {
    this.pinkBoard = new int[][][]{
            {{170, 0, 255}, {140, 190, 41}, {0, 171, 169}, {255, 0, 127}}, //r1
            {{0, 0, 0}, {255, 0, 127}, {239, 150, 8}, {0, 0, 0}},
            {{0, 171, 169}, {0, 0, 0}, {170, 0, 255}, {239, 150, 8}},
            {{170, 0, 255}, {0, 0, 0}, {140, 190, 41}, {0, 0, 0}}
    };
    this.pinkName = "pink";
    this.pinkMaxNum = 255;
    this.pink = new ImageModelStateImpl(pinkBoard, pinkMaxNum);


    this.twoBoard = new int[][][]{
            {{170, 0, 255}, {140, 190, 41}}, {{0, 171, 169}, {255, 0, 127}}
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
    int[] p0 = new int[]{170, 0, 255};
    int[] pp4 = new int[]{255, 0, 127};
    int[] pp16 = new int[]{0, 0, 0};
    assertArrayEquals(p0, this.pink.getPixel(0, 0));
    assertArrayEquals(pp4, this.pink.getPixel(0, 3));
    assertArrayEquals(pp16, this.pink.getPixel(3, 3));

    assertArrayEquals(p0, this.twoXTwo.getPixel(0, 0));
    int[] twop2 = new int[]{140, 190, 41};
    assertArrayEquals(twop2, this.twoXTwo.getPixel(0, 1));
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