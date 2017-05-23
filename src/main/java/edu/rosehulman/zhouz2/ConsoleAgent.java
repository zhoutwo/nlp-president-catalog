package edu.rosehulman.zhouz2;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class ConsoleAgent implements IAgent {
  @Override
  public void run() {
    IScholar scholar = new StandfordCoreNLPScholar();
    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
    Scanner reader = new Scanner(System.in);
    String n;
    System.out.println("Parsing Abraham Lincoln's document");
    File toRead = new File("data/html/Lincoln.html");
    htmlReader.setWikiHTMLFile(toRead);
    String name = htmlReader.getPresidentNameFromTitle();
    String body = htmlReader.getBody();
    scholar.parseText(name, body);
    System.out.println("Enter a file name in the data folder to parse, press q to stop: ");
    n = reader.next();
    while (!n.equals("q")) {
      System.out.println("Enter a file name in the data folder to parse, press q to stop: ");
      if (!(n.length() == 0)) {
        toRead = new File("data/html/" + n);
        htmlReader.setWikiHTMLFile(toRead);
        name = htmlReader.getPresidentNameFromTitle();
        body = htmlReader.getBody();
        scholar.parseText(name, body);
      }
      n = reader.next();
    }

    System.out.println("Enter a statement, press q to stop: ");
    n = reader.next();
    while (!n.equals("q")) {
      System.out.println("Enter a statement, press q to stop: ");
      n = reader.next();
      if (scholar.testStatement(n)) {
        System.out.println("The statement is true");
      } else {
        System.out.println("I'm not sure about this one");
      }
    }
  }
}
