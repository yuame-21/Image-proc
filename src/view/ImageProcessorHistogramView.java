package view;

import java.awt.*;

import javax.swing.*;

import model.ImageProcessorHistogram;

public class ImageProcessorHistogramView extends JPanel {

  private static int MIN = 0;
  private static int MAX = 255;
  private static String TITLE = "Histogram";
  private Graphics reds, greens, blues, intensities;
  private ImageProcessorHistogram histogramModel;

  public ImageProcessorHistogramView(ImageProcessorHistogram histogramModel, Graphics g) {
    this.histogramModel = histogramModel;
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintOneColor(Color.red, this.histogramModel.getReds(), g);
    paintOneColor(Color.green, this.histogramModel.getGreens(), g);
    paintOneColor(Color.blue, this.histogramModel.getBlues(),g);
    paintOneColor(Color.lightGray, this.histogramModel.getIntensities(), g);
  }

  private void paintOneColor(Color color, int[] arr, Graphics g) {
    for (int i = 0; i < arr.length; i++) {
      g.drawRect(i, 0, 1, arr[i]);
      g.setColor(color);
    }
  }

}
