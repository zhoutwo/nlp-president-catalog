package edu.rosehulman.zhouz2;

import java.nio.file.Path;
import java.util.Map;

/**
 * Created by Zhou Zhou on 5/18/17.
 */
public interface IWikiHTMLReader {
  /**
   * Sets the HTML file to read
   *
   * @param htmlFile Path to the HTML File to read
   */
  public void setWikiHTMLFile(Path htmlFile);

  /**
   * Returns the article body as a whole string
   *
   * @return The body of the Wikipedia article
   * @throws IllegalStateException If no current HTML file is specified, then this exception is thrown
   */
  public String getBody() throws IllegalStateException;

  /**
   * Returns the VCard info of the president
   *
   * @return A map from key to value of info in the VCard
   * @throws IllegalStateException If no current HTML file is specified, then this exception is thrown
   */
  public Map<String, String> getVCardMap() throws IllegalStateException;
}
