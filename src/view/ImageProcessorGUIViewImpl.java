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
    JPanel mainPanel = new JPanel();

    // puts that image into a scroll pane
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane); // adds to the GUI

    // adds image panel with scrollbars
    JPanel mainImage = new JPanel(); // starts blank - then mutates to be actual image somehow
    mainImageScroll = new JScrollPane(mainImage);
    mainPanel.add(mainImageScroll);

    // load text box
    loadInput = new JTextField(10); // dont know what columns means lol
    mainPanel.add(loadInput);

    // load Button
    this.makeButton(loadButton, "Load", mainPanel);

    // save text box
    saveInput = new JTextField(10); // dont know what columns means lol
    mainPanel.add(saveInput);

    // save button
    this.makeButton(saveButton, "Save", mainPanel);

    // exit button
    this.makeButton(exitButton, "Exit", mainPanel);

    // features bottons
    this.makeButton(redButton, "Red Component", mainPanel);
    this.makeButton(greenButton, "Green Component", mainPanel);
    this.makeButton(blueButton, "Blue Component", mainPanel);
    this.makeButton(horizFlipButton, "Horizontal Flip", mainPanel);
    this.makeButton(vertFlipButton, "Vertical Flip", mainPanel);
    this.makeButton(intensityButton, "Intensity", mainPanel);
    this.makeButton(valueButton, "Value", mainPanel);
    this.makeButton(lumaButton, "Luma", mainPanel);
    this.makeButton(sepiaButton, "Sepia", mainPanel);
    this.makeButton(greyscaleButton, "Greyscale", mainPanel);
    this.makeButton(sharpenButton, "Sharpen", mainPanel);
    this.makeButton(blurButton, "Blur", mainPanel);
    this.makeButton(darkenButton, "Darken", mainPanel);
    darkenInput = new JTextField(10);
    mainPanel.add(darkenInput);
    this.makeButton(brightenButton, "Brighten", mainPanel);
    brightenInput = new JTextField(10);
    mainPanel.add(brightenInput);

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
//    mainPanel.add();

    pack();
    this.makeVisible();
  }

  private void makeButton(JButton button, String s, JPanel mainPanel) {
    button = new JButton(s);
    button.setActionCommand(s + " Button");
    mainPanel.add(button);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

//  @Override
//  public void setCommandButtonListener(JButton button, ActionListener actionEvent) {
//    button.addActionListener(actionEvent);
//  }

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
    intensityButton.addActionListener(evt -> features.intensity());
    valueButton.addActionListener(evt -> features.value());
    lumaButton.addActionListener(evt -> features.luma());
    sepiaButton.addActionListener(evt -> features.sepia());
    greyscaleButton.addActionListener(evt -> features.greyscale());
    sharpenButton.addActionListener(evt -> features.sharpen());
    blurButton.addActionListener(evt -> features.blur());
    darkenButton.addActionListener(evt -> features.darken(Integer.valueOf(darkenInput.getText())));
    brightenButton.addActionListener(evt ->
            features.brighten(Integer.valueOf(brightenInput.getText())));
  }

  @Override
  public void renderImage() {

  }


  @Override
  public void renderMessage(String message) throws IOException {

  }

}



