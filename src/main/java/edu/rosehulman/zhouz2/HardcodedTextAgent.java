package edu.rosehulman.zhouz2;

import java.io.File;
import java.util.Scanner;

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
    //String truth = "Zhou Zhou is a programmer. He is a junior CSSE major at Rose-Hulman Institute of Technology, and he is going to become a senior in the coming school year. Jerry, on the other hand, is Zhou's classmate in CSSE413. Zhou watches anime in library. Zhou studys human and animal on weekends in a far away land called Djdfhsdufh.";
//    String truth = "Lincoln initially concentrated on the military and political dimensions of the war.";
//    scholar.parseText("Zhou", truth);
    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
    Scanner reader = new Scanner(System.in);
    String n;
    System.out.println("Parsing Abraham Lincoln's document");
    File toRead = new File("data/html/Lincoln.html");
    htmlReader.setWikiHTMLFile(toRead);
    String name = htmlReader.getPresidentNameFromTitle();
    String body = htmlReader.getBody();
    scholar.parseText(name, body);
    System.out.println(scholar.testStatement(text));
//    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
//    File adams = new File("data/html/Adams.html");
//    htmlReader.setWikiHTMLFile(adams);
//    String name = htmlReader.getPresidentNameFromTitle();
//    String body = htmlReader.getBody();
//    scholar.parseText(name, body);
//    System.out.println(scholar.getParseTreesByName(name));
  }
}
