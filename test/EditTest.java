import org.junit.Before;
import org.junit.Test;

import model.ImageModelStateImpl;
import model.Pixel;
import model.edit.BrightenDarken;
import model.edit.ColorComponent;
import model.edit.FilterSharpenBlur;
import model.edit.FlipHZ;
import model.edit.ILV;
import model.edit.Transform;

import static java.util.Arrays.deepEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for Edit classes within model.
 */
public class EditTest {
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
  public void testColorCompExc() {
    try {
      new ColorComponent("oatmeal");
      fail("should have thrown");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("not a valid color component! enter " +
              "either 'red,' 'green,'or 'blue'")) {
        fail("should have thrown for r,g,b");
      }
    }
  }

  @Test
  public void testILVExc() {
    try {
      new ILV("oatmeal");
      fail("should have thrown");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("invalid ILV type")) {
        fail("should have thrown for r,g,b");
      }
    }
  }

  @Test
  public void applyEditBrightenDarken() {
    deepEquals(new Pixel[][]{{new Pixel(180, 10, 255), new Pixel(150,
                    200, 51)}, {new Pixel(10, 181, 179),
                    new Pixel(255, 10, 137)}},
            new BrightenDarken(10).applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(160, 0, 245), new Pixel(130,
                    180, 31)}, {new Pixel(0, 161, 159),
                    new Pixel(245, 0, 117)}},
            new BrightenDarken(-10).applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertEquals(4, new BrightenDarken(-10).applyEdit("t",
            this.twoXTwo).copyImage().length);
  }

  @Test
  public void applyEditBrightenDarkenTogether() {

    deepEquals(new Pixel[][]{{new Pixel(180, 10, 255), new Pixel(150,
                    200, 51)}, {new Pixel(10, 181, 179),
                    new Pixel(255, 10, 137)}},
            new BrightenDarken(10).applyEdit("test", this.twoXTwo).copyImage());
    new BrightenDarken(10).applyEdit("test", this.twoXTwo);
    new BrightenDarken(-30).applyEdit("test", this.twoXTwo);
    deepEquals(new Pixel[][]{{new Pixel(160, 0, 225), new Pixel(130,
            180, 31)}, {new Pixel(0, 161, 159),
            new Pixel(225, 0, 117)}}, this.twoXTwo.copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);
  }

  @Test
  public void applyEditColor() {
    deepEquals(new Pixel[][]{{new Pixel(170, 170, 170),
                    new Pixel(140, 140, 140)}, {new Pixel(0, 0, 0),
                    new Pixel(255, 255, 255)}},
            new ColorComponent("red").applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(255, 255, 255),
                    new Pixel(41, 41, 41)}, {new Pixel(169,
                    169, 169), new Pixel(127, 127, 127)}},
            new ColorComponent("blue").applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(0, 0, 0),
                    new Pixel(190, 190, 190)}, {new Pixel(171,
                    171, 171), new Pixel(0, 0, 0)}},
            new ColorComponent("green").applyEdit("test", this.twoXTwo).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);

  }

  @Test
  public void applyEditFlip() {
    deepEquals(new Pixel[][]{{new Pixel(140, 190, 41),
                    new Pixel(170, 0, 255)},
                    {new Pixel(255, 0, 127),
                            new Pixel(0, 171, 169)}},
            new FlipHZ("horizontal").applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(0, 171, 169),
                    new Pixel(255, 0, 127)},
                    {new Pixel(170, 0, 255),
                            new Pixel(140, 190, 41)}},
            new FlipHZ("vertical").applyEdit("test", this.twoXTwo).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);
  }

  @Test
  public void applyEditFlipBothWays() {
    init();
    deepEquals(new Pixel[][]{{new Pixel(140, 190, 41),
                    new Pixel(170, 0, 255)},
                    {new Pixel(255, 0, 127),
                            new Pixel(0, 171, 169)}},
            new FlipHZ("horizontal").applyEdit("test", this.twoXTwo).copyImage());
    deepEquals(new Pixel[][]{{new Pixel(255, 0, 127),
                    new Pixel(0, 171, 169)},
                    {new Pixel(140, 190, 41),
                            new Pixel(170, 0, 255)}},
            new FlipHZ("vertical").applyEdit("test",
                    new ImageModelStateImpl(new Pixel[][]{{new Pixel(140, 190, 41),
                            new Pixel(170, 0, 255)},
                            {new Pixel(255, 0, 127),
                                    new Pixel(0, 171, 169)}},
                            255)).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);

  }

  @Test
  public void FlipConsExc() {
    try {
      new FlipHZ("oatmeal");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("only valid flip types are horizontal and vertical")) {
        fail("should have thrown illegal arg exc: only valid flip" +
                " types are horizontal and vertical");
      }
    }
  }

  @Test
  public void applyEditLIV() {
    deepEquals(new Pixel[][]{{new Pixel(141, 141, 141),
                    new Pixel(123, 123, 123)},
                    {new Pixel(113, 113, 113),
                            new Pixel(127, 127, 127)}},
            new ILV("intensity").applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(55, 55, 55),
                    new Pixel(169, 169, 169)},
                    {new Pixel(135, 135, 135),
                            new Pixel(63, 63, 63)}},
            new ILV("luma").applyEdit("test", this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(255, 255, 255),
                    new Pixel(190, 190, 190)},
                    {new Pixel(171, 171, 171),
                            new Pixel(255, 255, 255)}},
            new ILV("value").applyEdit("test", this.twoXTwo).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);

  }


  @Test
  public void applyTransform() {
    deepEquals(new Pixel[][]{{new Pixel(55, 55, 55),
                    new Pixel(169, 169, 169)},
                    {new Pixel(135, 135, 135),
                            new Pixel(63, 63, 63)}},
            new Transform("greyscale").applyEdit("test",
                    this.twoXTwo).copyImage());
    init();
    deepEquals(new Pixel[][]{{new Pixel(115, 102, 80),
                    new Pixel(209, 186, 145)},
                    {new Pixel(163, 146, 113),
                            new Pixel(124, 110, 86)}},
            new Transform("sepia").applyEdit("test",
                    this.twoXTwo).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);

  }

  @Test
  public void TransformConstructor() {
    try {
      new Transform("oatmeal");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("invalid transform type: must be greyscale or sepia"))) {
        fail("should have thrown illegal arg exc: invalid transform type");
      }
    }
  }

  @Test
  public void testFilter() {
    deepEquals(new Pixel[][]{{new Pixel(58, 83, 74),
                    new Pixel(64, 92, 82)},
                    {new Pixel(75, 84, 58),
                            new Pixel(85, 75, 59)}},
            new FilterSharpenBlur("blur").applyEdit("test",
                    this.twoXTwo).copyImage());
    deepEquals(new Pixel[][]{{new Pixel(115, 102, 80),
                    new Pixel(209, 186, 145)},
                    {new Pixel(163, 146, 113),
                            new Pixel(124, 110, 86)}},
            new FilterSharpenBlur("sharpen").applyEdit("test",
                    this.twoXTwo).copyImage());
    assertEquals(4, this.twoXTwo.copyImage().length);

  }

  @Test
  public void testFilterCons() {
    try {
      new FilterSharpenBlur("oatmeal");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("invalid filter type"))) {
        fail("should have thrown illegal arg exc: invalid filter type");
      }
    }
  }

}

