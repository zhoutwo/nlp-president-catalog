package edu.rosehulman.zhouz2;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

/**
 * Created by Zhou Zhou on 5/18/17.
 */
public interface IWikiHTMLReader {
  /**
   * Sets the HTML file to read
   *
   * @param htmlFile The HTML File to read
   */
  public void setWikiHTMLFile(File htmlFile);

  /**
   * Returns the title of the HTML file
   *
   * @return The page title as defined in the "<title>" tag
   * @throws IllegalStateException If no current HTML file is specified, then this exception is thrown
   */
  public String getTitle() throws IllegalStateException;

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
