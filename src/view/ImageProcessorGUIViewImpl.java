package view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import controller.Features;

public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {

  public static Color VIOLET = new Color(204, 153, 255);
  public static Color PEONY = new Color(255, 0, 127);
  public static Color TEAL = new Color(0, 153, 140);
  public static Color ORANGE = new Color(255, 204, 153);
  public static Color BLUE = new Color(102, 178, 255);

  private JScrollPane mainScrollPane, mainImageScroll;
  private JPanel histogram;
  private JPanel featuresButtonPanel, loadSaveExitPanel;

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

    // constructs the base panel
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(PEONY);

    // puts main panel into a scroll pane
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane); // adds to the GUI

    // adds image panel with scrollbars
    JPanel mainImage = new JPanel(); // starts blank - then mutates to be actual image somehow
    mainImageScroll = new JScrollPane(mainImage);
    mainPanel.add(mainImageScroll);

    // makes a panel for load and save buttons
    loadSaveExitPanel = new JPanel();
    loadSaveExitPanel.setLayout(new FlowLayout());
    loadSaveExitPanel.setBackground(VIOLET);
    mainPanel.add(loadSaveExitPanel);

    // load text box
    loadInput = new JTextField(10); // dont know what columns means lol
    loadSaveExitPanel.add(loadInput);

    // load Button
    this.makeButton(loadButton, "Load", loadSaveExitPanel);

    // save text box
    saveInput = new JTextField(10); // dont know what columns means lol
    loadSaveExitPanel.add(saveInput);

    // save button
    this.makeButton(saveButton, "Save", loadSaveExitPanel);

    // exit button
    this.makeButton(exitButton, "Exit", loadSaveExitPanel);

    // makes a button panel
    featuresButtonPanel = new JPanel();
    featuresButtonPanel.setLayout(new BoxLayout(featuresButtonPanel, BoxLayout.PAGE_AXIS));
    featuresButtonPanel.setBackground(BLUE);
    mainPanel.add(featuresButtonPanel);

    // features bottons
    this.makeButton(redButton, "Red Component", featuresButtonPanel);
    this.makeButton(greenButton, "Green Component", featuresButtonPanel);
    this.makeButton(blueButton, "Blue Component", featuresButtonPanel);
    this.makeButton(horizFlipButton, "Horizontal Flip", featuresButtonPanel);
    this.makeButton(vertFlipButton, "Vertical Flip", featuresButtonPanel);
    this.makeButton(intensityButton, "Intensity", featuresButtonPanel);
    this.makeButton(valueButton, "Value", featuresButtonPanel);
    this.makeButton(lumaButton, "Luma", featuresButtonPanel);
    this.makeButton(sepiaButton, "Sepia", featuresButtonPanel);
    this.makeButton(greyscaleButton, "Greyscale", featuresButtonPanel);
    this.makeButton(sharpenButton, "Sharpen", featuresButtonPanel);
    this.makeButton(blurButton, "Blur", featuresButtonPanel);
    this.makeButton(darkenButton, "Darken", featuresButtonPanel);
    darkenInput = new JTextField(10);
    featuresButtonPanel.add(darkenInput);
    this.makeButton(brightenButton, "Brighten", featuresButtonPanel);
    brightenInput = new JTextField(10);
    featuresButtonPanel.add(brightenInput);

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

  private void makeButton(JButton button, String s, JPanel panel) {
    button = new JButton(s);
    button.setActionCommand(s + " Button");
    panel.add(button);
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



