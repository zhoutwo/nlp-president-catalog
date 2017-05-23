package edu.rosehulman.zhouz2;

//import edu.rosehulman.zhouz2.data.*;
import edu.stanford.nlp.coref.*;
import edu.stanford.nlp.coref.data.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.ling.CoreAnnotations;

import javax.sound.sampled.Line;
import java.util.*;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class StandfordCoreNLPScholar implements IScholar {
  private Map<String, List<Tree>> parseTreeMap;
  private final StanfordCoreNLP pipeline;
  private ArrayList<InfoCard> maps;
  //private final Pool<IEvent> events;
  //private final Pool<ITime> times;
  //private final Pool<IAction> actions;
  //private final Pool<ILocation> locations;
  //private final Pool<ConcreteEntity> entities;

  public StandfordCoreNLPScholar() {
    pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
      "annotators", "tokenize, ssplit, pos, lemma, parse, ner, mention, relation, coref",
      "tokenize.language", "en",
      "ssplit.newlineIsSentenceBreak", "always",
      "coref.algorithm", "neural"
    ));

    parseTreeMap = new HashMap<>();
    maps = new ArrayList<InfoCard>();
//    events = new Pool<>();
//    times = new Pool<>();
//    actions = new Pool<>();
//    locations = new Pool<>();
//    entities = new Pool<>();
  }

  //private static boolean testMatching(Pool<IEvent> statementEvents, Pool<IEvent> truthEvents) {
  //  return false;
  //}

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
    // TODO: Find the name in statement
    String name = "Abraham Lincoln";
    return testStatementByName(name, statement);
  }

  @Override
  public boolean testStatementByName(String name, String statement) {
    List<Tree> trees = parseTreeMap.get(name);
    for (Tree tree : trees) {
      if (testStatements(statement, tree)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean testStatements(String statement, String truth) {
    Annotation truthAnnotation = new Annotation(truth);
    pipeline.annotate(truthAnnotation);
    //Pool<IEvent> statementEvents = generateEvents(statementAnnotation);
    List<CoreMap> truthSentences = truthAnnotation.get(CoreAnnotations.SentencesAnnotation.class);

    Tree NP;
    Tree VP;
    Tree temp = null;
    //int i;
    for (CoreMap truthSentence: truthSentences) {
      //i = 0;
      Tree truthTree = truthSentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      //List<CoreLabel> truthLabel = truthSentence.get(CoreAnnotations.TokensAnnotation.class);
      List<Tree> list = truthTree.getChildrenAsList();
      System.out.println(list.toString());
      NP = truthTree;
      while(!NP.label().toString().equals("NP")) {
        //System.out.println(NP.label());
        temp = NP;
        NP = NP.firstChild();
      }
      //System.out.println("NP is: " + NP.toString());
      VP = temp.children()[1];
      //System.out.println("VP is: " + VP.toString());

//      for (CoreLabel label: truthLabel) {
//        //System.out.println(label.tag() + " " + label.word());
//        if (label.tag().equals("NP")) {
//          NP = truthTree.getChild(i);
//          VP = truthTree.getChild(i+1);
//          System.out.println("Found NP:" + NP.toString() +" and VP:" + VP.toString());
//          break;
//        }
//        i++;
//      }
      maps.add(new InfoCard(NP, VP));
    }
    //The statementAnnotation is supposed to be a single sentence. Thus, the length of the list is 1;
    Annotation statementAnnotation = new Annotation(statement);
    pipeline.annotate(statementAnnotation);
    CoreMap statementSentences = statementAnnotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);

    Tree statementTree = statementSentences.get(TreeCoreAnnotations.TreeAnnotation.class);
    List<Tree> list = statementTree.getChildrenAsList();
    System.out.println(list.toString());
    NP = statementTree;
    while(!NP.label().toString().equals("NP")) {
      //System.out.println(NP.label());
      temp = NP;
      NP = NP.firstChild();
    }
    //System.out.println("NP is: " + NP.toString());
    VP = temp.children()[1];
    //System.out.println("VP is: " + VP.toString());
    InfoCard statementCard = new InfoCard(NP,VP);
    for (InfoCard card: maps) {
      if (card.compareTo(statementCard)) {
        return true;
      }
    }

//    for (CoreMap statementSentence : statementSentences) {]
//      Tree statementTree = statementSentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//      for (CoreMap truthSentence : truthSentences) {
//        Tree truthTree = truthSentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//        if (testStatements(statementTree, truthTree)) {
//          return true;
//        }
//      }
//    }
    return false;
  }

  @Override
  public boolean testStatements(String statement, Tree truthTree) {
    Annotation statementAnnotation = new Annotation(statement);
    pipeline.annotate(statementAnnotation);
    List<CoreMap> statementSentences = statementAnnotation.get(CoreAnnotations.SentencesAnnotation.class);
    for (CoreMap statementSentence : statementSentences) {
      Tree statementTree = statementSentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      if (testStatements(statementTree, truthTree)) {
        return true;
      }
    }
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
    Map<String, List<Tree>> result = new HashMap<>();
    for (String key : parseTreeMap.keySet()) {
      result.put(key, parseTreeMap.get(key));
    }
    return result;
  }

//  private Pool<IEvent> generateEvents(Annotation document) {
//    final Pool<IEvent> events = new Pool<>();
//    final Pool<ITime> times = new Pool<>();
//    final Pool<IAction> actions = new Pool<>();
//    final Pool<ILocation> locations = new Pool<>();
//    final Pool<ConcreteEntity> entities = new Pool<>();
//    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//    int numOfSentences = sentences.size();
//    boolean[] processedSentences = new boolean[numOfSentences];
//    // Iterate through all coref chains, because each chain is an individual identity
//    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
//      // "Proper mention and a mention with more pre-modifiers are preferred",
//      // which means this is believed to be the name, not a pronoun or other reference words
//      String name = cc.getRepresentativeMention().mentionSpan;
//
//      // Create the person in scope
//      ConcreteEntity mainCharacter = new ConcreteEntity();
//      mainCharacter.setName(name);
//      entities.add(mainCharacter);
//
//      // Get all mentions in this coref chain
//      List<CorefChain.CorefMention> mentions = cc.getMentionsInTextualOrder();
//      for (CorefChain.CorefMention mention : mentions) {
//        int sentenceNumber = mention.position.get(0);
//        CoreMap sentence = sentences.get(sentenceNumber);
//        // Tree parsing:
//        // By empirical observation (http://nlpviz.bpodgursky.com/), the grammar for sentence is:
//        // ROOT -> S(entence)
//        // S -> S | S,<CC>S | <NP><VP>
//        // NP -> <NNP>+ (Person's name)| <PRP> (Personal pronoun)
//        // VP -> <VBD><ADVP>*<VP> | <VBN><NP>
//        // VBD -> all verbs, including be, am, is ,are
//        // Some reference was reading: https://stackoverflow.com/questions/1833252/java-stanford-nlp-part-of-speech-labels
//        Tree sentenceTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class).flatten();
//
//      }
//
//    }
//    for (int i = 0; i < numOfSentences; i++) {
//      CoreMap sentence = sentences.get(i);
//      System.out.println("---");
//      System.out.println("mentions");
//
//      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
//        System.out.println("\t" + m);
//      }
//    }
//
//
//    return events;
//  }
}
