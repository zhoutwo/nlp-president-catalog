package edu.rosehulman.zhouz2;

import edu.stanford.nlp.trees.Tree;

import java.util.*;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public interface IScholar {
  /**
   * Add source for the scholar to parse. You can call this method several times before calling testStatement()
   * @param name Name of the person the source is related to
   * @param source Source texts to learn and parse
   */
  public void parseText(String name, String source);

  /**
   * Verify a statement whether it's known to be true or not
   * @param statement A sentence to be verified by the scholar
   * @return True if the statement is true. False if unknown or false
   */
  public boolean testStatement(String statement);

  /**
   * Return all parse trees under the specified name
   * @param name Name of the person to which the parse trees belong
   * @return All parse trees registered under the person with the name
   */
  public List<Tree> getParseTreeByName(String name);

  /**
   * Return all parsgie trees sorted by name
   * @return A map from name to all parse trees related to the person with that name
   */
  public Map<String, List<Tree>> getParseTrees();
}
