package model;

import java.util.Arrays;

public class Pixel {
  int[] rgb;

  public Pixel(int red, int green, int blue) {
    this.rgb = new int[]{red, green, blue};
  }

  public int get(int i) {
    return this.rgb[i];
  }

 public void set(int i, int set) {
    this.rgb[i] = set;
 }

 public int[] getChannel() {
    return this.rgb.clone();
 }

}
