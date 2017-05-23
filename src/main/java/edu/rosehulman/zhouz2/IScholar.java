package edu.rosehulman.zhouz2;

import edu.stanford.nlp.pipeline.Annotation;
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
   * Verify a statement about a person whether it's known to be true or not
   * @param name Name of the person in scope
   * @param statement A sentence to be verified by the scholar
   * @return True if the statement is true. False if unknown or false
   */
  public boolean testStatementByName(String name, String statement);

  /**
   * Verify whether a statement is true according to the truth
   * @param statement Statement to be verified
   * @param truth Claim known to be true
   * @return True if truth agrees with the statement, false if truth disagrees with or can't verify the statement
   */
  public boolean testStatements(String statement, String truth);

  /**
   * Verify whether statement is true according to the parsed truth
   * @param statement Statement to be verified
   * @param truthAnnotation Annotation of claim known to be true
   * @return True if truth agrees with the statement, false if truth disagrees with or can't verify the statement
   */
  public boolean testStatements(String statement, Annotation truthAnnotation);

  /**
   * Return all parse trees under the specified name
   * @param name Name of the person to which the parse trees belong
   * @return All annotated documents registered under the person with the name
   */
  public List<Annotation> getAnnotationsByName(String name);

  /**
   * Return all parsgie trees sorted by name
   * @return A map from name to all annotated documents related to the person with that name
   */
  public Map<String, List<Annotation>> getAnnotations();
}
