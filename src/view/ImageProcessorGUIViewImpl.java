package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import controller.Features;
import model.ImageModel;
import model.ImageProcessorHistogram;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.PAGE_START;

/**
 * Creates the visual implementation for our GUI.
 */
public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {

  private static Color PEONY = new Color(255, 0, 127);

  private JScrollPane mainScrollPane;
  private JScrollPane imageScroll;
  private JPanel mainPanel;
  private JPanel histogramPanel;
  private ImageProcessorHistogramView histogram;
  private JLabel mainImage;
  private JLabel feedback;
  private JLabel fileName;
  private JPanel featuresButtonPanel;
  private JPanel loadSaveExitPanel;
  private JPanel imagePanel;

  private JButton loadButton;
  private JButton saveButton;
  private JButton exitButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton horizFlipButton;
  private JButton vertFlipButton;
  private JButton intensityButton;
  private JButton valueButton;
  private JButton sepiaButton;
  private JButton lumaButton;
  private JButton greyscaleButton;
  private JButton sharpenButton;
  private JButton blurButton;
  private JButton darkenButton;
  private JButton brightenButton;
  private JTextField darkenInput;
  private JTextField brightenInput;

  /**
   * Constructs a visual representation of the GUI.
   */
  public ImageProcessorGUIViewImpl() {
    super();
    this.setTitle("Image Processor");
    this.setSize(500, 500);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(PEONY);

    // constructs the base panel
    this.mainPanel = new JPanel();
    mainPanel.setBackground(PEONY);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    // puts main panel into a scroll pane
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane); // adds to the GUI

    // add label with name of file
    this.fileName = new JLabel();
    mainPanel.add(this.fileName, PAGE_START);

    Color VIOLET = new Color(204, 153, 255);

    // makes a panel for load and save buttons
    loadSaveExitPanel = new JPanel();
    loadSaveExitPanel.setLayout(new FlowLayout());
    loadSaveExitPanel.setBackground(VIOLET);
    mainPanel.add(loadSaveExitPanel);

    // load Button
    // set the button properly
    loadButton = new JButton("Load");
    this.makeButton(loadButton, "Load", loadSaveExitPanel);

    // save button
    saveButton = new JButton("Save");
    this.makeButton(saveButton, "Save", loadSaveExitPanel);

    // exit button
    exitButton = new JButton("Exit");
    this.makeButton(exitButton, "Exit", loadSaveExitPanel);

    Color TEAL = new Color(0, 153, 140);

    // adds image panel with scrollbars
    this.imagePanel = new JPanel();
    this.imagePanel.setBackground(TEAL);
    mainPanel.add(imagePanel, CENTER);

    this.mainImage = new JLabel();
    this.mainImage.setPreferredSize(new Dimension(300, 200));
    imageScroll = new JScrollPane(this.mainImage);
    this.imageScroll.setSize(200, 200);
    this.imagePanel.add(imageScroll);

    // makes feedback message label
    feedback = new JLabel();
    imagePanel.add(feedback);

    Color BLUE = new Color(102, 178, 255);

    // makes a button panel
    featuresButtonPanel = new JPanel();
    featuresButtonPanel.setLayout(new BoxLayout(featuresButtonPanel, BoxLayout.X_AXIS));
    featuresButtonPanel.setBackground(BLUE);
    mainPanel.add(featuresButtonPanel);

    // features buttons
    redButton = new JButton("Red Component");
    this.makeButton(redButton, "Red Component", featuresButtonPanel);
    greenButton = new JButton("Green Component");
    this.makeButton(greenButton, "Green Component", featuresButtonPanel);
    blueButton = new JButton("Blue Component");
    this.makeButton(blueButton, "Blue Component", featuresButtonPanel);
    horizFlipButton = new JButton("Horizontal Flip");
    this.makeButton(horizFlipButton, "Horizontal Flip", featuresButtonPanel);
    vertFlipButton = new JButton("Vertical Flip");
    this.makeButton(vertFlipButton, "Vertical Flip", featuresButtonPanel);
    intensityButton = new JButton("Intensity");
    this.makeButton(intensityButton, "Intensity", featuresButtonPanel);
    valueButton = new JButton("Value");
    this.makeButton(valueButton, "Value", featuresButtonPanel);
    lumaButton = new JButton("Luma");
    this.makeButton(lumaButton, "Luma", featuresButtonPanel);
    sepiaButton = new JButton("Sepia");
    this.makeButton(sepiaButton, "Sepia", featuresButtonPanel);
    greyscaleButton = new JButton("Greyscale");
    this.makeButton(greyscaleButton, "Greyscale", featuresButtonPanel);
    sharpenButton = new JButton("Sharpen");
    this.makeButton(sharpenButton, "Sharpen", featuresButtonPanel);
    blurButton = new JButton("Blur");
    this.makeButton(blurButton, "Blur", featuresButtonPanel);
    darkenButton = new JButton("Darken");
    this.makeButton(darkenButton, "Darken", featuresButtonPanel);
    darkenInput = new JTextField(10);
    featuresButtonPanel.add(darkenInput);
    brightenButton = new JButton("Brighten");
    this.makeButton(brightenButton, "Brighten", featuresButtonPanel);
    brightenInput = new JTextField(10);
    featuresButtonPanel.add(brightenInput);

    Color ORANGE = new Color(255, 204, 153);

    this.histogramPanel = new JPanel();
    histogramPanel.setBackground(ORANGE);

    // make histogram text label
    JLabel textArea = new JLabel();
    textArea.setText("Image Histogram Color Key: red shading = red color " +
        "values; green shading = green color values; blue shading " +
        "= blue color values; grey shading = intensity values");
    textArea.setFont(new java.awt.Font("Papyrus", 1, 10));
    histogramPanel.add(textArea);
    mainPanel.add(histogramPanel);

    this.histogram = new ImageProcessorHistogramView();
    this.histogram.setPreferredSize(new Dimension(400, 300));
    JScrollPane scrollPane = new JScrollPane(this.histogram);
    scrollPane.setPreferredSize(new Dimension(400, 300));
    histogramPanel.add(scrollPane);

    pack();
    this.makeVisible();
  }

  /**
   * Makes a JButton's action commands and adds it to the visual.
   *
   * @param button The button to update
   * @param s      The type of the button
   * @param panel  The panel to add it to
   */
  private void makeButton(JButton button, String s, JPanel panel) {
    button.setActionCommand(s + " Button");
    panel.add(button);
  }

  /**
   * Refreshes the program when changes are made to the histogram.
   */
  @Override
  public void refresh() {
    this.histogram.repaint();
  }

  /**
   * Makes the JFrame visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Connects the features of the controller to the view and allows for functionality to be added
   * to the buttons and boxes.
   *
   * @param features The features of the controller
   */
  @Override
  public void addFeatures(Features features) {
    // connecting button with choosing menu
    loadButton.addActionListener((ActionEvent a) -> {

      // create the choosing
      JFileChooser chooser =
          new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

      // filters the file endings
      FileNameExtensionFilter imageEnds = new FileNameExtensionFilter("Valid types"
          , "png", "jpg", "bmp", "ppm");
      chooser.setFileFilter(imageEnds);
      // opens the actual file thing
      int yes = chooser.showOpenDialog(ImageProcessorGUIViewImpl.this);
      // is this chosen file good?
      if (yes == JFileChooser.APPROVE_OPTION) {
        File imageReq = chooser.getSelectedFile();
        features.load(imageReq.getAbsolutePath());
      }
    });

    saveButton.addActionListener(evt -> {
      JFileChooser saveHere = new JFileChooser(".");
      int yesSave = saveHere.showSaveDialog(this);
      if (yesSave == JFileChooser.APPROVE_OPTION) {
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
    darkenButton.addActionListener(evt -> features.darken(darkenInput.getText()));
    brightenButton.addActionListener(evt ->
        features.brighten(brightenInput.getText()));

  }

  /**
   * Renders a given image in the view.
   *
   * @param image The image desired to be rendered
   */
  @Override
  public void renderImage(BufferedImage image) {
    this.mainImage.setIcon(new ImageIcon(image));
  }

  /**
   * Sets the histogram data from the model into the view.
   *
   * @param model The model of the histogram
   */
  @Override
  public void setHistogram(ImageModel model) {
    this.histogram.setHistogramModel(new ImageProcessorHistogram(model));
  }

  /**
   * Renders a message.
   *
   * @param message text to render
   * @throws IOException Thrown when there is a transmission error
   */
  @Override
  public void renderMessage(String message) throws IOException {
    feedback.setText(message);
    feedback.setFont(new java.awt.Font("Papyrus", 1, 10));
  }


}



