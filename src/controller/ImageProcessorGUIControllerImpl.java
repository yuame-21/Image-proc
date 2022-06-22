package controller;

import java.io.IOException;

import controller.commands.ColorComponent;
import controller.commands.Command;
import controller.commands.DarkenBrighten;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.IntensityLumaValue;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transformation;
import model.ImageModel;
import view.ImageProcessorGUIView;

public class ImageProcessorGUIControllerImpl implements Features {
  private ImageModel model;
  private ImageProcessorGUIView view;
  private Command command;
  private static String TEMP_NAME = "temp";

  public ImageProcessorGUIControllerImpl(ImageModel model, ImageProcessorGUIView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
    this.view.makeVisible();
    this.view.refresh();
  }

  private void initAndRender() {
    this.command.initCommand(this.model);
//    this.view.renderImage();
    this.command.renderCommandMessage(this.view);
  }


  @Override
  public void redComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "red");
    this.initAndRender();
  }

  @Override
  public void greenComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "green");
    this.initAndRender();
  }

  @Override
  public void blueComponent() {
    this.command = new ColorComponent(TEMP_NAME, TEMP_NAME, "blue");
    this.initAndRender();
  }

  @Override
  public void darken(int degree) {
    this.command = new DarkenBrighten(TEMP_NAME, TEMP_NAME, degree, "darken");
    this.initAndRender();
  }

  @Override
  public void brighten(int degree) {
    this.command = new DarkenBrighten(TEMP_NAME, TEMP_NAME, degree, "brighten");
    this.initAndRender();
  }

  @Override
  public void horizontalFlip() {
    this.command = new Flip(TEMP_NAME, TEMP_NAME, "horizontal");
    this.initAndRender();
  }

  @Override
  public void verticalFlip() {
    this.command = new Flip(TEMP_NAME, TEMP_NAME, "vertical");
    this.initAndRender();
  }

  @Override
  public void intensity() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "intensity");
    this.initAndRender();
  }

  @Override
  public void luma() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "luma");
    this.initAndRender();
  }

  @Override
  public void value() {
    this.command = new IntensityLumaValue(TEMP_NAME, TEMP_NAME, "value");
    this.initAndRender();
  }

  @Override
  public void sepia() {
    this.command = new Transformation(TEMP_NAME, TEMP_NAME, "sepia");
    this.initAndRender();
  }

  @Override
  public void greyscale() {
    this.command = new Transformation(TEMP_NAME, TEMP_NAME, "greyscale");
    this.initAndRender();
  }

  @Override
  public void sharpen() {
    this.command = new Filter(TEMP_NAME, TEMP_NAME, "sharpen");
    this.initAndRender();
  }

  @Override
  public void blur() {
    this.command = new Transformation(TEMP_NAME, TEMP_NAME, "blur");
    this.initAndRender();
  }

  @Override
  public void load(String path) {
    this.command = new Load(path, TEMP_NAME);
    this.initAndRender();
  }

  @Override
  public void save(String path) {
    this.command = new Save(path, TEMP_NAME);
    this.initAndRender();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

}
