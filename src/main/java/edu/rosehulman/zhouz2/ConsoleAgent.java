package edu.rosehulman.zhouz2;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Zhou Zhou on 5/20/17.
 */
public class ConsoleAgent implements IAgent {
  @Override
  public void run() {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution


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

    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

    for (CoreMap sentence : sentences) {
      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      System.out.println(tree.toString());
    }

    Scanner reader = new Scanner(System.in);
    System.out.println("Enter a statement: ");
    String n = reader.next();
    ArrayList<String> lemmas = new ArrayList<>();
    while (n.equals("q")) {
      for (CoreMap sentence : sentences) {

        for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
          String la = token.get(CoreAnnotations.LemmaAnnotation.class);

        }
        // this is the parse tree of the current sentence
        Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

      }


      System.out.println("Enter a statement: ");
      n = reader.next();
    }
  }
  }
}
