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
    System.out.println(scholar.testStatements(
      "Zhou Zhou is a programmer. He is a junior CSSE major at Rose-Hulman Institute of Technology " +
        "and he is going to become a senior in the coming school year. Jerry, on the other hand, is Zhou's classmate " +
        "in CSSE413.", ""));
//    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
//    File adams = new File("data/html/Adams.html");
//    htmlReader.setWikiHTMLFile(adams);
//    String name = htmlReader.getPresidentNameFromTitle();
//    String body = htmlReader.getBody();
//    scholar.parseText(name, body);
//    System.out.println(scholar.getParseTreesByName(name));
  }
}
