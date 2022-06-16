import org.junit.Before;
import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Testing class to test class, Pixel, and its methods.
 */
public class PixelTest {
  Pixel p1;
  Pixel p2;

  @Before
  public void init() {
    this.p1 = new Pixel(1, 2, 3);
    this.p2 = new Pixel(100, 200, 0);
  }

  @Test
  public void get() {
    assertEquals(1, this.p1.get(0));
    assertEquals(2, this.p1.get(1));
    assertEquals(3, this.p1.get(2));
    assertEquals(100, this.p2.get(0));
    assertEquals(200, this.p2.get(1));
    assertEquals(0, this.p2.get(2));

    try {
      this.p1.get(5);
      fail("should have thrown illegal arg exc");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("invalid color index")) {
        fail("should have thrown illegal arg exc: invalid color index");
      }
    }
  }

  @Test
  public void set() {
    this.p1.set(0, 100);
    assertEquals(100, this.p1.get(0));

    try {
      this.p1.set(0, 500);
      fail("should have thrown illegal arg exc");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("color value may be at most 255")) {
        fail("should have thrown illegal arg exc : color value may be at most 255");
      }
    }

    this.p1.set(1, 11);
    assertEquals(11, this.p1.get(1));
    this.p1.set(2, 200);
    assertEquals(200, this.p1.get(2));
  }

  @Test
  public void getChannel() {
    assertArrayEquals(new int[]{1, 2, 3}, this.p1.getChannel());
    assertArrayEquals(new int[]{100, 200, 0}, this.p2.getChannel());
  }
}