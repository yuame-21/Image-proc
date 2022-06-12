import java.io.IOException;

/**
 * Mock class.
 */
public class AppendableMock implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("appendable mock always throws IO");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("appendable mock always throws IO");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("appendable mock always throws IO");
  }
}
