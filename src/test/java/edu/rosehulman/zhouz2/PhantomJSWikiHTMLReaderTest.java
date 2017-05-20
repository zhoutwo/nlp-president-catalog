package edu.rosehulman.zhouz2;

import org.junit.jupiter.api.*;

import java.io.File;

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
  public void testGetBody() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      reader.getBody();
    });
    assertEquals("HTML file path hasn't been set!", exception.getMessage());
  }

  @Test
  public void testGetVCard() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      reader.getVCardMap();
    });
    assertEquals("HTML file path hasn't been set!", exception.getMessage());
  }
}
