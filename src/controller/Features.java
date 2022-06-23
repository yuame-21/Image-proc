package controller;


public interface Features {

  void redComponent();
  void greenComponent();
  void blueComponent();
  void darken(int degree);
  void brighten(int degree);
  void horizontalFlip();
  void verticalFlip();
  void intensity();
  void luma();
  void value();
  void sepia();
  void greyscale();
  void sharpen();
  void blur();
  void load(String path);
  void save(String path);
  void exitProgram();

}
