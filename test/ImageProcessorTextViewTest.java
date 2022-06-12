import org.junit.Test;

import java.io.IOException;

import view.ImageProcessorTextView;
import view.ImageProcessorView;

import static org.junit.Assert.fail;


public class ImageProcessorTextViewTest {

  ImageProcessorView v1;
  ImageProcessorView v2;


  @Test
  public void testViewContructor() {
    try {
      this.v1 = new ImageProcessorTextView(null);
      fail("should have thrown illegal arg exc");
    } catch (IllegalArgumentException e) {
      if (! e.getMessage().equals("provided destination is null!")) {
        fail();
      }
    }
    try {
      this.v2 = new ImageProcessorTextView(new StringBuilder());
      return;
    } catch (IllegalArgumentException e) {
      fail("should not have thrown an exception; StringBuilder is a valid parameter");
    }
  }

  @Test
  public void renderMessage() {
    this.v1 = new ImageProcessorTextView(new AppendableMock());
    try {
      this.v1.renderMessage("hi");
      fail("should have thrown io exc");
    } catch (IOException e) {
      if (! e.getMessage().equals("appendable mock always throws IO")) {
        fail("the appendable mock should have thrown an io");
      }
    }
    this.v2 = new ImageProcessorTextView(new StringBuilder());
    try {
      this.v2.renderMessage("hi");
      return;
    } catch (IOException e) {
      fail("valid appendable should not throw");
    }
  }
}