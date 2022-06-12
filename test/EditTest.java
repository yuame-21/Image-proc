import org.junit.Before;
import org.junit.Test;

import model.ImageModelStateImpl;
import model.edit.BrightenDarken;
import model.edit.ColorComponent;
import model.edit.FlipHorizontal;
import model.edit.FlipVertical;
import model.edit.ILV;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;


public class EditTest {
  ImageModelStateImpl twoXTwo;
  int[][][] twoBoard;
  String twoName;
  int twoMaxNum;

  @Before
  public void init() {
    this.twoBoard = new int[][][]{{{170, 0, 255}, {140, 190, 41}}, {{0, 171, 169}, {255, 0, 127}}};
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
    assertArrayEquals(new int[][][]{{{180, 10, 255}, {150, 200, 51}},
                    {{10, 181, 179}, {255, 10, 137}}},
            new BrightenDarken(10).applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{160, 0, 245}, {130, 180, 31}},
                    {{0, 161, 159}, {245, 0, 117}}},
            new BrightenDarken(-10).applyEdit("test", this.twoXTwo).copyImage());
  }

  @Test
  public void applyEditBrightenDarkenTogether() {

    assertArrayEquals(new int[][][]{{{180, 10, 255}, {150, 200, 51}},
                    {{10, 181, 179}, {255, 10, 137}}},
            new BrightenDarken(10).applyEdit("test", this.twoXTwo).copyImage());
    new BrightenDarken(10).applyEdit("test", this.twoXTwo);
    new BrightenDarken(-30)
            .applyEdit("test", this.twoXTwo);
    assertArrayEquals(new int[][][]{{{160, 0, 225}, {130, 180, 31}},
            {{0, 161, 159}, {225, 0, 117}}}, this.twoXTwo.copyImage());
  }

  @Test
  public void applyEditColor() {
    assertArrayEquals(new int[][][]{{{170, 170, 170}, {140, 140, 140}},
                    {{0, 0, 0}, {255, 255, 255}}},
            new ColorComponent("red").applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{255, 255, 255}, {41, 41, 41}},
                    {{169, 169, 169}, {127, 127, 127}}},
            new ColorComponent("blue").applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {190, 190, 190}},
                    {{171, 171, 171}, {0, 0, 0}}},
            new ColorComponent("green").applyEdit("test", this.twoXTwo).copyImage());
  }

  @Test
  public void applyEditFlip() {
    assertArrayEquals(new int[][][]{{{140, 190, 41}, {170, 0, 255}},
                    {{255, 0, 127}, {0, 171, 169}}},
            new FlipHorizontal().applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{0, 171, 169}, {255, 0, 127}},
                    {{170, 0, 255}, {140, 190, 41}}},
            new FlipVertical().applyEdit("test", this.twoXTwo).copyImage());
  }

  @Test
  public void applyEditFlipBothWays() {
    init();
    assertArrayEquals(new int[][][]{{{140, 190, 41}, {170, 0, 255}},
                    {{255, 0, 127}, {0, 171, 169}}},
            new FlipHorizontal().applyEdit("test", this.twoXTwo).copyImage());
    assertArrayEquals(new int[][][]{{{255, 0, 127}, {0, 171, 169}},
                    {{140, 190, 41}, {170, 0, 255}}},
            new FlipVertical().applyEdit("test",
                    new ImageModelStateImpl(new int[][][]{{{140, 190, 41}, {170, 0, 255}},
                            {{255, 0, 127}, {0, 171, 169}}}, 255)).copyImage());
  }

  @Test
  public void applyEditLIV() {
    assertArrayEquals(new int[][][]{{{141, 141, 141}, {123, 123, 123}},
                    {{113, 113, 113}, {127, 127, 127}}},
            new ILV("intensity").applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{55, 55, 55}, {169, 169, 169}},
                    {{135, 135, 135}, {63, 63, 63}}},
            new ILV("luma").applyEdit("test", this.twoXTwo).copyImage());
    init();
    assertArrayEquals(new int[][][]{{{255, 255, 255}, {190, 190, 190}},
                    {{171, 171, 171}, {255, 255, 255}}},
            new ILV("value").applyEdit("test", this.twoXTwo).copyImage());
  }


}

