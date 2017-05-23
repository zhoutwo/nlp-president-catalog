package edu.rosehulman.zhouz2;

import edu.stanford.nlp.coref.*;
import edu.stanford.nlp.coref.data.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.ling.CoreAnnotations;

import java.util.*;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class StandfordCoreNLPScholar implements IScholar {
  private Map<String, List<Annotation>> parseAnnotationMap;
  private final StanfordCoreNLP pipeline;
  private List<InfoCard> maps;

  public StandfordCoreNLPScholar() {
    pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
      "annotators", "tokenize, ssplit, pos, lemma, parse, ner, mention, relation, coref",
      "tokenize.language", "en",
      "ssplit.newlineIsSentenceBreak", "always",
      "coref.algorithm", "neural"
    ));

    parseAnnotationMap = new HashMap<>();
    maps = new ArrayList<>();
  }

  @Override
  public void parseText(String name, String source) {
    List<Annotation> annotations = parseAnnotationMap.computeIfAbsent(name, k -> new ArrayList<Annotation>());
    Annotation document = new Annotation(source);
    pipeline.annotate(document);
    annotations.add(document);
    parseAnnotationMap.put(name, annotations);
  }

  @Override
  public boolean testStatement(String statement) {
    // TODO: Find the name in statement
    String name = "Abraham Lincoln";
    return testStatementByName(name, statement);
  }

  @Override
  public boolean testStatementByName(String name, String statement) {
    List<Annotation> annotations = parseAnnotationMap.get(name);
    for (Annotation document : annotations) {
      if (testStatements(statement, document)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean testStatements(String statement, String truth) {
    Annotation truthAnnotation = new Annotation(truth);
    pipeline.annotate(truthAnnotation);
    return testStatements(statement, truthAnnotation);
  }

  @Override
  public boolean testStatements(String statement, Annotation truthAnnotation) {
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
  public List<Annotation> getAnnotationsByName(String name) {
    return parseAnnotationMap.get(name);
  }

  @Override
  public Map<String, List<Annotation>> getAnnotations() {
    Map<String, List<Annotation>> result = new HashMap<>();
    for (String key : parseAnnotationMap.keySet()) {
      result.put(key, parseAnnotationMap.get(key));
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
