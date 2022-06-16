import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Mock class for the Readable.
 */
public class ReadableMock implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("ReadableMock's method, read, throws an IO Exception");
  }
}
