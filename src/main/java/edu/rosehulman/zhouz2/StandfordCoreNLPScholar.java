package edu.rosehulman.zhouz2;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class StandfordCoreNLPScholar implements IScholar {
  private Map<String, List<Tree>> parseTreeMap;
  private final StanfordCoreNLP pipeline;

  public StandfordCoreNLPScholar() {
    pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
      "annotators", "tokenize,ssplit,pos,lemma,parse,ner,natlog,dcoref",
      "tokenize.language", "en"
    ));

    parseTreeMap = new HashMap<>();
  }

  @Override
  public void parseText(String name, String source) {
    List<Tree> trees = parseTreeMap.computeIfAbsent(name, k -> new ArrayList<Tree>());
    Annotation document = new Annotation(source);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      System.out.println(tree.toString());
      trees.add(tree);
    }
    parseTreeMap.put(name, trees);
  }

  @Override
  public boolean testStatement(String statement) {
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
