import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import model.ImageProcessorHistogram;
import view.ImageProcessorHistogramView;

import static org.junit.Assert.*;

public class ImageProcessorHistogramViewTest {

  @Test(expected = NullPointerException.class)
  public void setHistogramModelNull() {
    ImageModel m = new ImageModelImpl();
    ImageProcessorHistogramView v = new ImageProcessorHistogramView();
    v.repaint();
  }

  @Test
  public void setHistogramModel() {
    ImageModel m = new ImageModelImpl();
    ImageProcessorHistogram hm = new ImageProcessorHistogram(m);
    ImageProcessorHistogramView v = new ImageProcessorHistogramView();
    v.setHistogramModel(hm);

    try {
      v.repaint();
      return;
    } catch (NullPointerException e) {
      fail("should not have thrown");
    }
  }


}