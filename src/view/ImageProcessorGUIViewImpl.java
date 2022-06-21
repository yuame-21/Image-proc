package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

import controller.Features;


public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {

  private JScrollPane mainScrollPane;
  private JPanel histogram, mainImage;

  private JButton loadButton, saveButton, exitButton;
  private JTextField loadInput;
  private JLabel featuresCheckbox;

  public ImageProcessorGUIViewImpl() {
    super();
    this.setTitle("Image Processor");
    this.setSize(500, 500);
    this.setLocation(200,200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    // constructs the image - blank at firstor we can display welcome
    mainImage = new JPanel();

    // puts that image into a scroll pane
    mainScrollPane = new JScrollPane(mainImage);
    add(mainScrollPane); // adds to the GUI

    // load text box
    loadInput = new JTextField(10); // dont know what columns means lol
    this.add(loadInput);

    // load Button
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    this.add(loadButton);

    // save button
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    this.add(saveButton);

    // exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
    setVisible(true); // is this always true?
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

  }

  @Override
  public void renderImage() {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }
//    echoButton.addActionListener(evt -> features.echoOutput(input.getText()));
//    toggleButton.addActionListener(evt -> features.toggleColor());
//    exitButton.addActionListener(evt -> features.exitProgram());
//    this.addKeyListener(new KeyListener() {
//      @Override
//      public void keyTyped(KeyEvent e) {
//        if (e.getKeyChar() == 't') {
//          features.toggleColor();
//        }
//      }
//
//      @Override
//      public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_C) {
//          features.makeUppercase();
//        }
//      }
//
//      @Override
//      public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_C) {
//          features.restoreLowercase();
//        }
//      }
//    });
//  }


}



