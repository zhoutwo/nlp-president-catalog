package edu.rosehulman.zhouz2;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public interface IScholar {
  /**
   * Add source for the scholar to parse
   * @param source Source texts to learn and parse
   */
  public void addSource(String source);

  /**
   * Verify a statement whether it's known to be true or not
   * @param statement A sentence to be verified by the scholar
   * @return True if the statement is true. False if unknown or false
   */
  public boolean testStatement(String statement);
}
