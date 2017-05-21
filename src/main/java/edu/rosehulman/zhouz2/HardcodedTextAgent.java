package edu.rosehulman.zhouz2;

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
  }
}
