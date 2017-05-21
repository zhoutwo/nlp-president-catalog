package edu.rosehulman.zhouz2;

import java.io.File;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class HardcodedTextAgent implements IAgent {
  private final String text;

  public HardcodedTextAgent(String hardcodedText) {
    text = hardcodedText;
  }

  @Override
  public void run() {
    IScholar scholar = new StandfordCoreNLPScholar();
    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
    File adams = new File("data/html/Adams.html");
    htmlReader.setWikiHTMLFile(adams);
    String name = htmlReader.getPresidentNameFromTitle();
    String body = htmlReader.getBody();
    scholar.parseText(name, body);
    System.out.println(scholar.getParseTreeByName(name));
  }
}
