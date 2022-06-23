package view;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import model.ImageProcessorHistogram;

public class ImageProcessorHistogramView extends JPanel {

  private static int MIN = 0;
  private static int MAX = 255;
  private static String TITLE = "Histogram";
  private ImageProcessorHistogram histogramModel;

  public ImageProcessorHistogramView() {
    super();
    this.histogramModel = null;
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.setBackground(Color.white);

    Graphics2D g2d = (Graphics2D) g;

    /*
    the origin of the panel is top left. In order
    to make the origin bottom left, we must "flip" the
    y coordinates so that y = height - y

    We do that by using an affine transform. The flip
    can be specified as scaling y by -1 and then
    translating by height.
     */

    AffineTransform originalTransform = g2d.getTransform();

    //the order of transforms is bottom-to-top
    //so as a result of the two lines below,
    //each y will first be scaled, and then translated
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    paintOneColor(Color.lightGray, this.histogramModel.getIntensities(), g2d);
    paintOneColor(Color.green, this.histogramModel.getGreens(), g2d);
    paintOneColor(Color.blue, this.histogramModel.getBlues(),g2d);
    paintOneColor(Color.red, this.histogramModel.getReds(), g2d);
  }

  private void paintOneColor(Color color, int[] arr, Graphics g2d) {
    int alpha = 127;

    Color colorTranslucent = new Color(color.getRed(), color.getBlue(), color.getGreen(), alpha);

    g2d.setColor(colorTranslucent);

    for (int i = 0; i < 255; i++) {
      int height = 0;
      for(int h = 0; h < arr.length; h++){
        if(i == arr[h]){
          height++;
        }
      }
      g2d.drawRect(i, 0, 1, height);
    }

  }

  public void setHistogramModel(ImageProcessorHistogram histogramModel) {
    this.histogramModel = histogramModel;
  }

}
