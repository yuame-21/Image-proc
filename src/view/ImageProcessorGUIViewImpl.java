package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

import controller.Features;


public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {

  public ImageProcessorGUIViewImpl() {
    super();
    this.setTitle("Image Processor");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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



