import junit.framework.TestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.ImageModel;
import model.ImageModelImpl;
import model.edit.ColorComponent;
import model.edit.FilterSharpenBlur;
import model.edit.Transform;

/**
 * Provides tests for the {@link ImageModelImpl} class.
 */
public class ImageModelImplTest extends TestCase {

  String expectedValue2x2 = "2\n" +
          "2\n" +
          "255\n" +
          "170\n" +
          "0\n" +
          "255\n" +
          "140\n" +
          "190\n" +
          "41\n" +
          "0\n" +
          "171\n" +
          "169\n" +
          "255\n" +
          "0\n" +
          "127\n";

  String expectedValue2x2PNGBMP = "2\n" +
          "2\n" +
          "255\n" +
          "170\n" +
          "0\n" +
          "255\n" +
          "0\n" +
          "171\n" +
          "169\n" +
          "140\n" +
          "190\n" +
          "41\n" +
          "255\n" +
          "0\n" +
          "127\n";

  String expectedValue2x2JPG = "2\n" +
          "2\n" +
          "255\n" +
          "163\n" +
          "8\n" +
          "250\n" +
          "0\n" +
          "167\n" +
          "167\n" +
          "134\n" +
          "183\n" +
          "32\n" +
          "255\n" +
          "5\n" +
          "143\n";

  String expectedValuePink = "4\n" +
          "4\n" +
          "255\n" +
          "170\n" +
          "0\n" +
          "255\n" +
          "140\n" +
          "190\n" +
          "41\n" +
          "0\n" +
          "171\n" +
          "169\n" +
          "255\n" +
          "0\n" +
          "127\n" +
          "0\n" +
          "0\n" +
          "0\n" +
          "255\n" +
          "0\n" +
          "127\n" +
          "239\n" +
          "150\n" +
          "8\n" +
          "0\n" +
          "0\n" +
          "0\n" +
          "0\n" +
          "171\n" +
          "169\n" +
          "0\n" +
          "0\n" +
          "0\n" +
          "170\n" +
          "0\n" +
          "255\n" +
          "239\n" +
          "150\n" +
          "8\n" +
          "170\n" +
          "0\n" +
          "255\n" +
          "0\n" +
          "0\n" +
          "0\n" +
          "140\n" +
          "190\n" +
          "41\n" +
          "0\n" +
          "0\n" +
          "0\n";

  @Test
  public void testConstructor() {
    try {
      ImageModelImpl modEmpty = new ImageModelImpl();
      modEmpty.generateString("noFiles");
      fail("There are no files");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not valid")) {
        fail("Wrong message");
      }
    }
  }

  @Test
  public void testLoad() {
    ImageModel modelNull = new ImageModelImpl();
    ImageModel model1 = new ImageModelImpl();
    ImageModel model2 = new ImageModelImpl();
    ImageModel model3 = new ImageModelImpl();

    // test exceptions
    try {
      modelNull.load("null", null);
      fail("Inputs cannot be null");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Inputs cannot be null")) {
        fail("Input message should be correct");
      }
    }

    try {
      model1.load("././res/mimi", "me");
      fail("file is not real therefore the path is incorrect");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid path format")) {
        fail("the path is false");
      }
    }

    try {
      model1.load("././res/corrupt.ppm", "slay");
      fail("file is not a real ppm therefore should not be valid");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file is not a real ppm")) {
        fail("File is not a real ppm");
      }
    }

    model1.load("././res/2x2.ppm", "2by2");
    assertEquals(expectedValue2x2, model1.generateString("2by2"));
    model2.load("././res/pink2.ppm", "pink");
    model2.load("././res/2x2.ppm", "22");
    assertEquals(expectedValuePink, model2.generateString("pink"));
    assertEquals(expectedValue2x2, model2.generateString("22"));

    model3.load("././res/2by2.png", "22PNG");
    assertEquals(expectedValue2x2PNGBMP, model3.generateString("22PNG"));
    model3.load("././res/2by2.bmp", "22BMP");
    assertEquals(expectedValue2x2PNGBMP, model3.generateString("22BMP"));
    model3.load("././res/2by2.jpg", "22JPG");
    assertEquals(expectedValue2x2JPG, model3.generateString("22JPG"));
  }

  @Test
  public void testEditImage() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");
    mod1.load("././res/pink.ppm", "Koko");
    mod1.load("././res/2by2.bmp", "BMP");
    mod1.load("././res/2by2.png", "PNG");
    mod1.load("././res/2by2.jpg", "JPG");


    // test exceptions
    try {
      mod1.editImage("BY", "Red22", new ColorComponent("red"));
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not valid")) {
        fail("File name is invalid");
      }
    }

    String red2x2 = "2\n" +
            "2\n" +
            "255\n" +
            "170\n" +
            "170\n" +
            "170\n" +
            "140\n" +
            "140\n" +
            "140\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "255\n" +
            "255\n";

    mod1.editImage("2by2", "Red22", new ColorComponent("red"));
    assertEquals(red2x2, mod1.generateString("Red22"));
    mod1.editImage("2by2", "2by2", new ColorComponent("red"));
    assertEquals(red2x2, mod1.generateString("2by2"));
    mod1.editImage("BMP", "BEEP", new ColorComponent("red"));
  }

  @Test
  public void testSave() {
    ImageModelImpl mod = new ImageModelImpl();
    mod.load("././res/TestSave.ppm", "Test");
    mod.load("././res/pink2.ppm", "pink2");
    mod.load("././res/2by2.png", "22PNG");
    mod.load("././res/2by2.bmp", "22BMP");
    mod.load("././res/2by2.jpg", "22JPG");

    // test exceptions
    try {
      mod.save("././res/slay", "22");
      fail("This is an invalid path");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid path format")) {
        fail("Message should be \" Invalid path format \"");
      }
    }

    try {
      mod.save("././res/Koala.ppm", "hello");
      fail("This is an invalid file name");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not valid")) {
        fail("Message should be \"Given file name is not valid\"");
      }
    }

    // test save

    mod.save("././res/Save.ppm", "Test");
    try {
      Scanner data = new Scanner(new FileReader("././res/Save.ppm"));
      String[] ar = ("P3\n" + mod.generateString("Test")).split("\n");
      int c = 0;
      while (data.hasNext()) {
        assertEquals(data.next(), ar[c]);
        c++;
      }
    } catch (IOException e) {
      fail("File not found");
    }

    mod.save("././res/pink2.ppm", "pink2");
    try {
      Scanner data = new Scanner(new FileReader("././res/pink2.ppm"));
      String[] ar = ("P3\n" + mod.generateString("pink2")).split("\n");
      int c = 0;
      while (data.hasNext()) {
        assertEquals(data.next(), ar[c]);
        c++;
      }
    } catch (IOException e) {
      fail("File not found");
    }

    mod.save("././res/22png.png", "22PNG");
    assertTrue(new File("././res/22png.png").isFile());
    new File("././res/22png.png").delete();

    mod.save("././res/22bmp.bmp", "22BMP");
    assertTrue(new File("././res/22bmp.bmp").isFile());
    new File("././res/22bmp.bmp").delete();

    mod.save("././res/22jpg.jpg", "22JPG");
    assertTrue(new File("././res/22jpg.jpg").isFile());
    new File("././res/22jpg.jpg").delete();
  }

  @Test
  public void testGenerateString() {
    ImageModelImpl model1 = new ImageModelImpl();
    model1.load("././res/pink2.ppm", "pink");
    model1.load("././res/2x2.ppm", "2b2");

    // test exceptions
    try {
      model1.generateString("oops");
      fail("A file with this name does not exist");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not valid")) {
        fail("Should be returning message - Given file name is not found");
      }
    }

    assertEquals(expectedValue2x2, model1.generateString("2b2"));
    assertEquals(expectedValuePink, model1.generateString("pink"));
  }

  @Test
  public void testBlur() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String blurred2x2 = "2\n" +
            "2\n" +
            "255\n" +
            "77\n" +
            "45\n" +
            "98\n" +
            "77\n" +
            "65\n" +
            "49\n" +
            "47\n" +
            "53\n" +
            "73\n" +
            "85\n" +
            "18\n" +
            "53\n";

    mod1.editImage("2by2", "Blur2", new FilterSharpenBlur("blur"));
    String s = mod1.generateString("Blur2");
    assertEquals(blurred2x2, s);
  }

  @Test
  public void testSharpen() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String sharpened2x2 = "2\n" +
            "2\n" +
            "255\n" +
            "255\n" +
            "91\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "179\n" +
            "192\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "151\n" +
            "255\n";

    mod1.editImage("2by2", "Sharpen2", new FilterSharpenBlur("sharpen"));
    assertEquals(sharpened2x2, mod1.generateString("Sharpen2"));
  }

  @Test
  public void testGreyScale() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String grey2x2 = "2\n" +
            "2\n" +
            "255\n" +
            "55\n" +
            "55\n" +
            "55\n" +
            "169\n" +
            "169\n" +
            "169\n" +
            "135\n" +
            "135\n" +
            "135\n" +
            "63\n" +
            "63\n" +
            "63\n";

    mod1.editImage("2by2", "Grey2", new Transform("greyscale"));
    assertEquals(grey2x2, mod1.generateString("Grey2"));
  }

  @Test
  public void testSepia() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String sepia2x2 = "2\n" +
            "2\n" +
            "255\n" +
            "115\n" +
            "102\n" +
            "80\n" +
            "209\n" +
            "186\n" +
            "145\n" +
            "163\n" +
            "146\n" +
            "113\n" +
            "124\n" +
            "110\n" +
            "86\n";

    mod1.editImage("2by2", "sepia2", new Transform("sepia"));
    assertEquals(sepia2x2, mod1.generateString("sepia2"));
  }


}