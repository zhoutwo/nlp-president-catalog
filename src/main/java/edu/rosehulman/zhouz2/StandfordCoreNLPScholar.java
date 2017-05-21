package edu.rosehulman.zhouz2;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class StandfordCoreNLPScholar implements IScholar {

  public StandfordCoreNLPScholar() {
    StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
      "annotators", "tokenize,ssplit,pos,lemma,parse,ner,natlog,dcoref",
      "tokenize.language", "en"
    ));
  }

  @Override
  public void parseText(String name, String source) {

  }

  @Override
  public boolean testStatement(String statement) {
    return false;
  }

  @Override
  public List<Tree> getParseTreeByName(String name) {
    return null;
  }

  @Override
  public Map<String, List<Tree>> getParseTrees() {
    return null;
  }
}
