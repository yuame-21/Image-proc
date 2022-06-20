package view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class ImageProcessorGUIImpl extends JFrame implements ImageProcessorGUI {
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
  public void renderMessage(String message) throws IOException {

  }
}
