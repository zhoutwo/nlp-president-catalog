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

    StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos,lemma,parse,ner,natlog,dcoref", "tokenize.language", "en"));
    String text;
    //if (args.length != 0 && args[0].equals("-all")) {
    if (false) {
      //Fill in later
      text = "Jerry was born on May 20, 1996.";
    } else {
      // Test Ghost Driver
      IWikiHTMLReader htmlReader = new PhantomJSWikiHTMLReader();
      File adams = new File("data/html/Lincoln.html");
      htmlReader.setWikiHTMLFile(adams);
      System.out.println(htmlReader.getTitle());

      // read some text in the text variable
      text = htmlReader.getBody();
    }
    // create an empty Annotation just with the given text
    Annotation document = new Annotation(text);

    pipeline.annotate(document);

    List<CoreMap> sentences = document.get(SentencesAnnotation.class);

    for (CoreMap sentence : sentences) {
      Tree tree = sentence.get(TreeAnnotation.class);
      System.out.println(tree.toString());
    }

    Scanner reader = new Scanner(System.in);
    System.out.println("Enter a statement: ");
    String n = reader.next();
    ArrayList<String> lemmas = new ArrayList<>();
    while (n.equals("q")) {
      for (CoreMap sentence : sentences) {

        for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
          String la = token.get(LemmaAnnotation.class);
          
        }
        // this is the parse tree of the current sentence
        Tree tree = sentence.get(TreeAnnotation.class);

      }


      System.out.println("Enter a statement: ");
      n = reader.next();
    }
  }


}
