package edu.rosehulman.zhouz2;

import edu.stanford.nlp.trees.Tree;
import java.util.ArrayList;

/**
 * Created by JerryQiu on 5/22/17.
 */
public class NP {

  //private ArrayList<String> labels;
  private ArrayList<String> words;

  public NP(Tree tree) {
    //this.labels = new ArrayList<>();
    this.words = new ArrayList<>();
    //System.out.println("NP get:" + tree.toString());
    parseHelper(tree);
  }

  private void parseHelper(Tree tree){
    for (Tree wordEntry: tree.getChildrenAsList()) {
      if (wordEntry.isLeaf()) {
        //labels.add(wordEntry.label().toString());
        words.add(wordEntry.value());
      } else {
        parseHelper(wordEntry);
      }
    }
  }

//  public ArrayList<String> getLabels() {
//    return labels;
//  }

  public ArrayList<String> getWords() {
    return words;
  }

  /*
  This parameter vp is by default only contains simple sentence so that its vp.index = 1
   */
  public boolean compareTo(NP np) {
    //System.out.println("labels: " + labels.toString());
    //System.out.println("words: " + words.toString());
    boolean PRPFlag = true;
    boolean mentionFlag = false;
    ArrayList<String> testWords = np.getWords();
    //ArrayList<String> testLabels = np.getLabels();
    for (int i = 0; i < testWords.size(); i++) {
      //if (!testLabels.get(i).equals("PRP")) {
      PRPFlag = false;
      //}
      if (!this.words.contains(testWords.get(i))) {
        mentionFlag = true;
      }
    }
    if (PRPFlag || mentionFlag) {
      return false;
    }
    return true;
  }
}
