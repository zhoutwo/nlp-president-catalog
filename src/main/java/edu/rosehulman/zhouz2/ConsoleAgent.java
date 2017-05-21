package edu.rosehulman.zhouz2;

import java.util.Scanner;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class ConsoleAgent implements IAgent {
  @Override
  public void run() {
    IScholar scholar = new StandfordCoreNLPScholar();
    Scanner reader = new Scanner(System.in);
    String n;
    do {
      System.out.println("Enter a statement: ");
      n = reader.next();
      if (scholar.testStatement(n)) {
        System.out.println("The statement is true");
      } else {
        System.out.println("I'm not sure about this one");
      }
    } while (!n.equals("q"));
  }
}
