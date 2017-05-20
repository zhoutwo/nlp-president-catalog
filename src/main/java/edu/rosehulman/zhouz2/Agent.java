package edu.rosehulman.zhouz2;
import edu.stanford.nlp.dcoref.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.TreeCoreAnnotations.*;
import edu.stanford.nlp.util.*;

import java.io.File;
import java.util.*;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Zhou Zhou, Zhiqiang Qiu
 *         Created May 10, 2017.
 */
public class Agent {

  /**
   * Main method
   */
  public static void main(String[] args) {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution

    StanfordCoreNLP pipeline = new StanfordCoreNLP(
      PropertiesUtils.asProperties(
        "annotators", "tokenize,ssplit,pos,lemma,parse,ner,natlog,dcoref",
        "tokenize.language", "en"));

    // read some text in the text variable
    String text = "My name is Jerry. I am working with Zhou on this project. Zhou understood the concept really fast and hacked the assignment quickly. He is a clever but noisy boy.";
    // create an empty Annotation just with the given text
    Annotation document = new Annotation(text);

    // run all Annotators on this text
    pipeline.annotate(document);
    // these are all the sentences in this document
// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);

    for (CoreMap sentence : sentences) {
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
        // this is the text of the token
        String word = token.get(TextAnnotation.class);
        // this is the POS tag of the token
        String pos = token.get(PartOfSpeechAnnotation.class);
        // this is the NER label of the token
        String ne = token.get(NamedEntityTagAnnotation.class);

        String ca = token.get(CategoryAnnotation.class);

        String la = token.get(LemmaAnnotation.class);

        int ia = token.get(IndexAnnotation.class);

        //System.out.println(word + "-" + pos + "-" + ne + "-" + ca + "-" + la + "-" + ia);
      }
      // this is the parse tree of the current sentence
      Tree tree = sentence.get(TreeAnnotation.class);
      System.out.println(tree.toString());


      // this is the Stanford dependency graph of the current sentence
      SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
      System.out.println(dependencies);
    }

    // This is the coreference link graph
    // Each chain stores a set of mentions that link to each other,
    // along with a method for getting the most representative mention
    // Both sentence and token offsets start at 1!
    Map<Integer, CorefChain> graph =
            document.get(CorefCoreAnnotations.CorefChainAnnotation.class);

    // Test Ghost Driver
    IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
    File adams = new File("data/html/Adams.html");
    htmlReader.setWikiHTMLFile(adams);
    System.out.println(htmlReader.getTitle());
    System.out.println(htmlReader.getBody());
  }
}
