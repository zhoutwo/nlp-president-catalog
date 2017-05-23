package edu.rosehulman.zhouz2;

/**
 * This class hosts the main method for the program
 *
 * @author Zhou Zhou, Zhiqiang Qiu
 *         Created May 10, 2017.
 */
public class Entry {

  /**
   * Main method
   */
  public static void main(String[] args) {
    Thread agentThread = new Thread(new ConsoleAgent());
    agentThread.run();
  }
}
