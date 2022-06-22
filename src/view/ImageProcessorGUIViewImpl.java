package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

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
  private JTextField darkenInput, brightenInput;

  private String[] featuresList = new String[]{"Red Component", "Green Component", "Blue " +
          "Component", "Horizontal Flip", "Vertical Flip", "Intensity", "Value", "Sepia", "Luma",
          "Greyscale", "Sharpen", "Blur", "Darken", "Brighten"};

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

    // load Button
    this.makeButton(loadButton, "Load", loadSaveExitPanel);

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
    // connecting button with choosing menu
    loadButton.addActionListener((ActionEvent a) -> {
      final JFileChooser chooser =
          new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

      // filters the file endings
      FileNameExtensionFilter imageEnds = new FileNameExtensionFilter("Valid types"
          , "png", "jpg", "bmp", "ppm");
      chooser.setFileFilter(imageEnds);
      // opens the actual file thing
      int yes = chooser.showOpenDialog(null);
      // is this chosen file good?
      if(yes == JFileChooser.APPROVE_OPTION) {
        File imageReq = chooser.getSelectedFile();
        features.load(imageReq.getAbsolutePath());
      }
    });

    saveButton.addActionListener(evt -> {
      final JFileChooser saveHere = new JFileChooser(".");
        int yesSave = saveHere.showSaveDialog(this);
        if(yesSave == JFileChooser.APPROVE_OPTION) {
          File saveImage = saveHere.getSelectedFile();
          features.save(saveImage.getAbsolutePath());
        }
    });
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



