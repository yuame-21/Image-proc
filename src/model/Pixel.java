package model;

public class Pixel {
  int r;
  int c;
  int[] rgb;

  public Pixel(int r, int c, int red, int green, int blue) {
    this.r = r;
    this.c = c;
    this.rgb = new int[]{red, green, blue};
  }

  public int get(int i) {
    return this.rgb[i];
  }

 public void set(int i, int set) {
    this.rgb[i] = set;
 }

}
