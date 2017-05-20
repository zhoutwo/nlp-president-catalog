package edu.rosehulman.zhouz2;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Zhou Zhou on 5/19/17.
 */
public class PhantomJSWikiHTMLReaderTest {
  private IWikiHTMLReader reader;

  @BeforeEach
  public void setUp() {
    reader = new PhantomJSWikiHTMLReader();
  }

  @Test
  public void testGetTitle() {
    Throwable exception= assertThrows(IllegalStateException.class, () -> {
      reader.getTitle();
    });
    assertEquals("HTML file path hasn't been set!", exception.getMessage());
    reader.setWikiHTMLFile(new File("data/html/Adams.html"));
    assertEquals("John Adams - Wikipedia", reader.getTitle());
  }

  @Test
  public void testGetBody() throws IOException {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      reader.getBody();
    });
    assertEquals("HTML file path hasn't been set!", exception.getMessage());
    File bodyText = new File("src/test/java/edu/rosehulman/zhouz2/PhantomJSWikiHTMLReaderBody.txt");
    StringBuffer buffer = new StringBuffer(64711);
    FileReader fileReader = new FileReader(bodyText);
    int nextChar;
    while ((nextChar = fileReader.read()) != -1) {
      buffer.append((char) nextChar);
    }
    reader.setWikiHTMLFile(new File("data/html/Adams.html"));
    assertEquals(buffer.toString(), reader.getBody());
  }

  @Test
  public void testGetVCard() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      reader.getVCardMap();
    });
    assertEquals("HTML file path hasn't been set!", exception.getMessage());
  }
}
