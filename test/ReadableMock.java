import java.io.IOException;
import java.nio.CharBuffer;

/**
 * mock.
 */
public class ReadableMock implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("ReadableMock's method, read, throws an IO Exception");
  }
}
