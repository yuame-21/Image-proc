package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import controller.Features;

public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {

  private JScrollPane mainScrollPane, mainImageScroll;
  private JPanel histogram;

  private JButton loadButton, saveButton, exitButton, redButton, greenButton, blueButton,
      horizFlipButton, vertFlipButton, intensityButton, valueButton, sepiaButton, lumaButton,
      greyscaleButton, sharpenButton, blurButton, darkenButton, brightenButton;
  private JTextField loadInput, saveInput;
  private JTextField darkenInput, brightenInput;
//  private JLabel featuresCheckbox;

  private String[] featuresList = new String[]{"Red Component", "Green Component", "Blue " +
      "Component", "Horizontal Flip", "Vertical Flip", "Intensity", "Value", "Sepia", "Luma",
      "Greyscale", "Sharpen", "Blur", "Darken", "Brighten"}; // Darken? Brighten?

  public ImageProcessorGUIViewImpl() {
    super();
    this.setTitle("Image Processor");
    this.setSize(500, 500);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    // constructs the base image
    JPanel baseImage = new JPanel();

    // puts that image into a scroll pane
    mainScrollPane = new JScrollPane(baseImage);
    add(mainScrollPane); // adds to the GUI

    // adds image panel with scrollbars
    JPanel mainImage = new JPanel(); // starts blank - then mutates to be actual image somehow
    mainImageScroll = new JScrollPane(mainImage);
    mainScrollPane.add(mainImage);

    // load text box
    loadInput = new JTextField(10); // dont know what columns means lol
    mainScrollPane.add(loadInput);

    // load Button
    this.makeButton(loadButton, "Load");

    // save text box
    saveInput = new JTextField(10); // dont know what columns means lol
    mainScrollPane.add(saveInput);

    // save button
    this.makeButton(saveButton, "Save");

    // exit button
    this.makeButton(exitButton, "Exit");

    // features bottons
    this.makeButton(redButton, "Red Component");
    this.makeButton(greenButton, "Green Component");
    this.makeButton(blueButton, "Blue Component");
    this.makeButton(horizFlipButton, "Horizontal Flip");
    this.makeButton(vertFlipButton, "Vertical Flip");
    this.makeButton(intensityButton, "Intensity");
    this.makeButton(valueButton, "Value");
    this.makeButton(lumaButton, "Luma");
    this.makeButton(sepiaButton, "Sepia");
    this.makeButton(greyscaleButton, "Greyscale");
    this.makeButton(sharpenButton, "Sharpen");
    this.makeButton(blurButton, "Blur");
    this.makeButton(darkenButton, "Darken");
    darkenInput = new JTextField(10);
    mainImageScroll.add(darkenInput);
    this.makeButton(brightenButton, "Brighten");
    brightenInput = new JTextField(10);
    mainImageScroll.add(brightenInput);

//    // features check boxes
//    JPanel checkBox = new JPanel();
//    checkBox.setBorder(BorderFactory.createTitledBorder("Edit Image:"));
//    checkBox.setLayout(new BoxLayout(checkBox, BoxLayout.PAGE_AXIS));
//
//    JCheckBox[] checkBoxes = new JCheckBox[16];
//    for (int i = 0; i < featuresList.length; i++) {
//      checkBoxes[i] = new JCheckBox(featuresList[i]);
//      checkBoxes[i].setSelected(false);
//      checkBoxes[i].setActionCommand("CB" + (i + 1)); // dont know
//      checkBox.add(checkBoxes[i]);
//    }
//    mainScrollPane.add(checkBox);
    // I think we should make a pop up box or something for darken and brighten

    // histogram
    mainImageScroll.add();

    pack();
    this.makeVisible();
  }

  private void makeButton(JButton button, String s) {
    button = new JButton(s);
    button.setActionCommand(s + " Button");
    mainScrollPane.add(button);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandButtonListener(JButton button, ActionListener actionEvent) {
    button.addActionListener(actionEvent);
  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.load(loadInput.getText()));
  saveButton.addActionListener(evt -> features.save(saveInput.getText())); // fix save and load
    // -- hsouldnt be taking in text
    exitButton.addActionListener(evt -> features.exitProgram());
    redButton.addActionListener(evt -> features.redComponent());
    greenButton.addActionListener(evt -> features.greenComponent());
    blueButton.addActionListener(evt -> features.blueComponent());
    horizFlipButton.addActionListener(evt -> features.horizontalFlip());
    vertFlipButton.addActionListener(evt -> features.verticalFlip());

///    loadButton, saveButton,
// exitButton,
// redButton,
// greenButton,
// blueButton,
//        horizFlipButton, vertFlipButton, intensityButton, valueButton, sepiaButton, lumaButton,
//        greyscaleButton, sharpenButton, blurButton, darkenButton, brightenButton;
    private JTextField loadInput, saveInput;


  }

  @Override
  public void renderImage() {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }

}



