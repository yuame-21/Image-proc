import junit.framework.TestCase;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.ImageModelImpl;
import model.Pixel;
import model.edit.ColorComponent;
import model.edit.Filter;
import model.edit.Transform;

/**
 * Provides tests for the {@link ImageModelImpl} class.
 */
public class ImageModelImplTest extends TestCase {

  String expectedValue2x2 = "P3\n" +
          "2\n" +
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
  String expectedValuePink = "P3\n" +
          "4\n" +
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
      if (!e.getMessage().equals("Given file name is not found")) {
        fail("Wrong message");
      }
    }
  }

  @Test
  public void testLoad() {
    ImageModelImpl modelNull = new ImageModelImpl();
    ImageModelImpl model1 = new ImageModelImpl();
    ImageModelImpl model2 = new ImageModelImpl();

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
      fail("file is not real therefore should be null");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("File not found")) {
        fail("File should be not found");
      }
    }

    try {
      model1.load("././res/corrupt.ppm", "slay");
      fail("file is not a real ppm therefore should not be valid");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Inputs cannot be null")) {
        fail("File should be returning null");
      }
    }

    model1.load("././res/2x2.ppm", "2by2");
    assertEquals(expectedValue2x2, model1.generateString("2by2"));
    model2.load("././res/pink.ppm", "pink");
    model2.load("././res/2x2.ppm", "22");
    assertEquals(expectedValuePink, model2.generateString("pink"));
    assertEquals(expectedValue2x2, model2.generateString("22"));
  }

  @Test
  public void testEditImage() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");
    mod1.load("././res/pink.ppm", "Koko");

    // test exceptions
    try {
      mod1.editImage("BY", "Red22", new ColorComponent("red"));
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not found")) {
        fail("File name is invalid");
      }
    }

    String Red2x2 = "P3\n" +
            "2\n" +
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
    assertEquals(Red2x2, mod1.generateString("Red22"));
    mod1.editImage("2by2", "2by2", new ColorComponent("red"));
    assertEquals(Red2x2, mod1.generateString("2by2"));
  }

  @Test
  public void testSave() {
    ImageModelImpl mod = new ImageModelImpl();
    mod.load("././res/TestSave.ppm", "Test");
    mod.load("././res/pink2.ppm", "pink2");

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
      if (!e.getMessage().equals("Given file name is not found")) {
        fail("Message should be \" Given file name is not found \"");
      }
    }

    mod.save("././res/Save.ppm", "Test");
    try {
      Scanner data = new Scanner(new FileReader("././res/Save.ppm"));
      String[] ar = mod.generateString("Test").split("\n");
      int c = 0;
      while (data.hasNext()) {
        assertEquals(data.next(), ar[c]);
        c++;
      }
    } catch (IOException e) {
      fail("Transmission failed");
    }

    mod.save("././res/pink2.ppm", "pink2");
    try {
      Scanner data = new Scanner(new FileReader("././res/pink2.ppm"));
      String[] ar = mod.generateString("pink2").split("\n");
      int c = 0;
      while (data.hasNext()) {
        assertEquals(data.next(), ar[c]);
        c++;
      }
    } catch (IOException e) {
      fail("Transmission failed");
    }

  }

  @Test
  public void testGenerateString() {
    ImageModelImpl model1 = new ImageModelImpl();
    model1.load("././res/pink.ppm", "pink");
    model1.load("././res/2x2.ppm", "2b2");

    // test exceptions
    try {
      model1.generateString("oops");
      fail("A file with this name does not exist");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given file name is not found")) {
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

    String Blurred2x2 = "P3\n" +
            "2\n" +
            "2\n" +
            "58\n" +
            "83\n" +
            "74\n" +
            "64\n" +
            "92\n" +
            "82\n" +
            "75\n" +
            "84\n" +
            "58\n" +
            "85\n" +
            "75\n" +
            "59\n";

    mod1.editImage("2by2", "Blur2", new Filter("blur"));
    String s = mod1.generateString("Blur2");
    assertEquals(Blurred2x2, s);
  }
  @Test
  public void testSharpen() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String Sharpened2x2 = "P3\n" +
            "2\n" +
            "2\n" +
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

    mod1.editImage("2by2", "Sharpen2", new Filter("sharpen"));
    assertEquals(Sharpened2x2, mod1.generateString("Sharpen2"));
  }

  @Test
  public void testGreyScale() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String Grey2x2 = "P3\n" +
            "2\n" +
            "2\n" +
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
    assertEquals(Grey2x2, mod1.generateString("Grey2"));
  }

  @Test
  public void testSepia() {
    ImageModelImpl mod1 = new ImageModelImpl();
    mod1.load("././res/2x2.ppm", "2by2");

    String Sepia2x2 = "P3\n" +
            "2\n" +
            "2\n" +
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
    assertEquals(Sepia2x2, mod1.generateString("sepia2"));
  }



}