package edu.rosehulman.zhouz2;

import edu.stanford.nlp.coref.*;
import edu.stanford.nlp.coref.data.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

import java.util.*;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class StandfordCoreNLPScholar implements IScholar {
  private Map<String, List<Tree>> parseTreeMap;
  private final StanfordCoreNLP pipeline;

  public StandfordCoreNLPScholar() {
    pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
      "annotators", "tokenize, ssplit, pos, lemma, parse, ner, mention, relation, coref",
      "tokenize.language", "en",
      "ssplit.newlineIsSentenceBreak", "always",
      "coref.algorithm", "neural"
    ));

    parseTreeMap = new HashMap<>();
  }

  @Override
  public void parseText(String name, String source) {
    List<Tree> trees = parseTreeMap.computeIfAbsent(name, k -> new ArrayList<Tree>());
    Annotation document = new Annotation(source);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
    System.out.println("---");
    System.out.println("coref chains");
    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
      System.out.println("\t" + cc);
    }
    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
      System.out.println("---");
      System.out.println("mentions");
      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
        System.out.println("\t" + m);
      }
    }

    for (CoreMap sentence : sentences) {
      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      trees.add(tree);
    }
    parseTreeMap.put(name, trees);
  }

  @Override
  public boolean testStatement(String statement) {
    return false;
  }

  @Override
  public boolean testStatementByName(String name, String statement) {
    return false;
  }

  @Override
  public boolean testStatements(String statement, String truth) {
    return false;
  }

  @Override
  public boolean testStatements(String statement, Tree truthTree) {
    return false;
  }

  @Override
  public boolean testStatements(Tree statementTree, Tree truthTree) {
    return false;
  }

  @Override
  public List<Tree> getParseTreesByName(String name) {
    return parseTreeMap.get(name);
  }

  @Override
  public Map<String, List<Tree>> getParseTrees() {
    return null;
  }
}
