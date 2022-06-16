import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;


import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import controller.commands.File;
import controller.commands.Filter;
import controller.commands.Transformation;
import model.ImageModel;
import model.ImageModelImpl;
import model.ImageModelState;
import model.ImageModelStateImpl;
import model.Pixel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ImageProcessorControllerImplTest {

  ImageProcessorController c1;
  ImageProcessorController c2;
  ImageModel m1;
  ImageModelState pink;
  Pixel[][] pinkBoard;
  String pinkName;
  int pinkMaxNum;
  Appendable app;

  ImageProcessorView v1;

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
    this.m1 = new ImageModelImpl();
    this.app = new StringBuilder();
    this.v1 = new ImageProcessorTextView(this.app);
  }

  @Test
  public void testConstructor() {

    try {
      this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, new ReadableMock());
      return;
    } catch (IllegalArgumentException e) {
      fail("valid model and view, so controller construction should not fail");
    }
    try {
      this.c1 = new ImageProcessorControllerImpl(null, this.v1, new ReadableMock());
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model, view, and/or input cannot be null")) {
        fail("model cannot be null");
      }
    }
    try {
      this.c1 = new ImageProcessorControllerImpl(this.m1, null, new ReadableMock());
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model, view, and/or input cannot be null")) {
        fail("view cannot be null");
      }
    }
    try {
      this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model, view, and/or input cannot be null")) {
        fail("readable cannot be null");
      }
    }
  }


  @Test
  public void processImageWelcomeQuit() {

    Readable r = new StringReader("q");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Welcome to Mimi and Ella's Image Processor <3", arr[0]);
    assertEquals("To use this program, first load a file with the command:", arr[2]);
    assertEquals("load + file + name", arr[3]);
    assertEquals("Or, use a .txt script with:", arr[4]);
    assertEquals("-file + file.txt", arr[5]);
    assertEquals("Then, you can edit the image in the following ways:", arr[7]);
    assertEquals("brighten + degree, darken + degree", arr[8]);
    assertEquals("red-component, green-component, blue-component", arr[9]);
    assertEquals("horizontal-flip, vertical-flip", arr[10]);
    assertEquals("value-component, luma-component, intensity-component", arr[11]);
    assertEquals("greyscale, sepia, blur, sharpen", arr[12]);
    assertEquals("To save your image, type:", arr[13]);
    assertEquals("save + file + name", arr[14]);
    assertEquals("To quit, type:", arr[16]);
    assertEquals("q or Q", arr[17]);
    assertEquals("Program quit.", arr[18]);
    assertEquals("bye! <3", arr[20]);
  }

  @Test
  public void processImageLoad() {

    Readable r = new StringReader("load  ././res/pink.ppm pink \n q\n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
  }

  @Test
  public void processImageLoadSave() {

    Readable r = new StringReader("load  ././res/pink.ppm pink \n save " +
            "././res/pink.ppm pink \n q \n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Saved image, pink to path, ././res/pink.ppm", arr[19]);
  }


  @Test
  public void processImageBrightenDarken() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n  brighten pink" +
            " pinkBrighten 10 \n darken pinkBrighten pinkDarken 30 \n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("brightened image, pink, by 10. Renamed edited image as" +
            " pinkBrighten", arr[19]);
    assertEquals("darkened image, pinkBrighten, by 30. Renamed edited image " +
            "as pinkDarken", arr[20]);
  }


  @Test
  public void processImageColorComponent() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n red-component pink pinkRed " +
            "\n green-component pinkRed pinkGreen \n blue-component pinkGreen pinkRed\n q\n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Greyscaled image, pink, for color component, red. " +
            "Renamed edited image as pinkRed", arr[19]);
    assertEquals("Greyscaled image, pinkRed, for color component, green. " +
            "Renamed edited image as pinkGreen", arr[20]);
    assertEquals("Greyscaled image, pinkGreen, for color component, blue. " +
            "Renamed edited image as pinkRed", arr[21]);
  }


  @Test
  public void processImageFlip() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n horizontal-flip " +
            "pink pinkhoriz \n vertical-flip pinkhoriz pinkhv \n q\n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Horizontally flipped image, pink. " +
            "Renamed edited image as pinkhoriz", arr[19]);
    assertEquals("Vertically flipped image, pinkhoriz." +
            " Renamed edited image as pinkhv", arr[20]);
  }

  @Test
  public void processImageIntensityLumaValue() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n luma-component pink " +
            "pinkluma \n intensity-component pinkluma pinkli \n value-component " +
            "pinkli pinkliv\n q \n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Greyscaled image, pink, for luma." +
            " Renamed edited image as pinkluma", arr[19]);
    assertEquals("Greyscaled image, pinkluma, for intensity. " +
            "Renamed edited image as pinkli", arr[20]);
    assertEquals("Greyscaled image, pinkli, for value. " +
            "Renamed edited image as pinkliv", arr[21]);
  }

  @Test
  public void processImageMixedInputs() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n oatmeal\n " +
            "horizontal-flip pink pinkH \n q \n ");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("given command is invalid", arr[19]);
    assertEquals("Horizontally flipped image, pink. Renamed edited" +
            " image as pinkH", arr[20]);

  }

  @Test
  public void processImageRepeatedCommand() {
    String s = "load ././res/pink.ppm pink \n";

    for (int i = 0; i < 100; i++) {
      s = s.concat("horizontal-flip pink pink \n");
    }

    Readable r = new StringReader(s);
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);
    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);

    for (int i = 19; i < 117; i++) {
      assertEquals("Horizontally flipped image, pink. " +
              "Renamed edited image as pink", arr[i]);
    }
  }

  @Test
  public void testProcessImageExc() {

    Readable r = new StringReader("load ././bug/pink.ppm pink \n oatmeal\n " +
            "load ././res/pink pink \n save ././red/pink pink");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("File not found", arr[18]);
    assertEquals("given command is invalid", arr[19]);
    assertEquals("File not found", arr[20]);
    assertEquals("Invalid path format", arr[21]);

  }


  @Test
  public void testInputsMock() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n save " +
            "././res/pink.ppm pink \n horizontal-flip pink pink \n");
    StringBuilder log = new StringBuilder();
    this.m1 = new ImageModelMock(log);
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    assertEquals("method: load path: ././res/pink.ppm file name: pinkmethod: save path:" +
            " ././res/pink.ppm file name: pinkmethod: editImage old name: " +
            "pink new name: pink", log.toString());
  }


  @Test
  public void testTransform() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n sepia pink " +
            "pinkSepia \n greyscale pinkSepia pinkSG \n q \n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Transformed image, pink, to sepia. " +
            "Renamed edited image as pinkSepia", arr[19]);
    assertEquals("Transformed image, pinkSepia, to greyscale. " +
            "Renamed edited image as pinkSG", arr[20]);

  }

  @Test
  public void testTransformCons() {
    try {
      new Transformation("o", "n", "oatmeal");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("invalid transform type")) {
        fail("should have thrown illegal arg exc: invalid transform type");
      }
    }
  }


  @Test
  public void testFilter() {

    Readable r = new StringReader("load ././res/pink.ppm pink \n blur pink " +
            "pinkB \n sharpen pinkB pinkS \n q \n");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");

    assertEquals("Loaded image, pink. From path, ././res/pink.ppm", arr[18]);
    assertEquals("Filtered image, pink, to blur. Renamed edited image as pinkB", arr[19]);
    assertEquals("Filtered image, pinkB, to sharpen. " +
            "Renamed edited image as pinkS", arr[20]);
  }

  @Test
  public void testFilterCons() {
    try {
      new Filter("o", "n", "oatmeal");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("valid filters are blur and sharpen")) {
        fail("should have thrown illegal arg exc:valid filters are blur and sharpen");
      }
    }
  }

  @Test
  public void testFileCons() {
    try {
      new File("pink.ppm");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("invalid file")) {
        fail("should have thrown illegal arg exc: invalid file");
      }
    }
  }

  @Test
  public void testFile() {
    Readable r = new StringReader("-file ././res/miniscript.txt");
    this.c1 = new ImageProcessorControllerImpl(this.m1, this.v1, r);

    this.c1.processImage();

    String[] arr = app.toString().split("\n");
    assertEquals("uploaded file, ././res/miniscript.txtand ran the " +
            "previous stream of commands.", arr[18]);
  }

}