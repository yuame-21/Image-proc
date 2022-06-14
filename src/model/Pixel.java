package model;

public class Pixel {
  int red;
  int green;
  int blue;

  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int get(int i) {
    int c;
    switch (i) {
      case 0:
        c= this.red;
      break;
      case 1:
        c= this.green;
      break;
      case 2:
        c= this.blue;
      break;
      default:
        throw new IllegalArgumentException("invalid color index");
    }
    return c;
  }

  public void set(int i, int set) {
    switch (i) {
      case 0:
        this.red = set;
        break;
      case 1:
        this.green = set;
        break;
      case 2:
        this.blue = set;
        break;
    }
  }


  public int[] getChannel() {
    return new int[]{this.red, this.green, this.blue};
  }


}

