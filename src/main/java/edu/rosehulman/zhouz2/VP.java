package edu.rosehulman.zhouz2;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by JerryQiu on 5/22/17.
 */
public class VP {
  private ArrayList<String> verbs;
  private ArrayList<String> subjects;
  private ArrayList<String> timeLocs;


  public VP(Tree tree) {
    verbs = new ArrayList<>();
    subjects = new ArrayList<>();
    timeLocs = new ArrayList<>();
    //System.out.println("VP get:" + tree.toString());
    parseHelper(tree);
  }

  private void parseHelper(Tree tree) {
    //System.out.println("first child: " + tree.firstChild().label().toString());
    for (Tree wordEntry: tree.getChildrenAsList()) {
      //System.out.println("In loop: " + wordEntry.toString());
      if (wordEntry.isPreTerminal()) {
        //System.out.println("Is pre Terminal");
        switch (wordEntry.label().toString()) {
          case "VBZ": case "VBG": case "VB":case "VBD":
            //System.out.println("Find verb: " + wordEntry.label() + " " + wordEntry.lastChild());
            verbs.add(wordEntry.lastChild().toString());
            break;
          case "NP":case "NN":case "NNS":
            addSubjects(wordEntry);
            break;
          case "PP":
            addTimes(wordEntry, timeLocs.size());
            break;
          default:
            //Ignore do nothing
            //System.out.println("The word is ignored:" + wordEntry.label().toString() + " " + wordEntry.value());
            break;
        }
      } else if (wordEntry.depth() > 2){
        parseHelper(wordEntry);
      }
    }
  }

  public ArrayList<String> getVerbs() {
    return verbs;
  }

  public ArrayList<String> getSubjects() {
    return subjects;
  }

  public ArrayList<String> getTimeLocs() {
    return timeLocs;
  }

  private void addSubjects(Tree tree) {
    for (Tree wordEntry: tree.getChildrenAsList()) {
      if (wordEntry.isLeaf()) {
        subjects.add(wordEntry.value());
      } else {
        parseHelper(wordEntry);
      }
    }
  }

  private void addTimes(Tree tree, int index) {
    for (Tree wordEntry: tree.getChildrenAsList()) {
      if (wordEntry.isLeaf()) {
        if (timeLocs.get(index) == null) {
          timeLocs.add(wordEntry.value());
        } else {
          timeLocs.add(index, timeLocs.get(index)+wordEntry.value());
        }
      } else {
        parseHelper(wordEntry);
      }
    }
  }
  /*
  This parameter vp is by default only contains simple sentence so that its vp.index = 1
   */
  public boolean compareTo(VP vp) {
    //System.out.println("verbs: " + verbs.toString());
    //System.out.println("subjects: " + subjects.toString());
    //System.out.println("timeLocs: " + timeLocs.toString());
    ArrayList<String> testVerbs = vp.getVerbs();
    ArrayList<String> testSubjects = vp.getSubjects();
    //ArrayList<String> testTimeLocs = vp.getTimeLocs();
    boolean verbFlag = true;
    boolean subjectFlag = true;
    if (testSubjects.isEmpty()) {
      subjectFlag = false;
    }
    //boolean timeLocFlag = true;

    for (String test: testVerbs) {
      if (this.verbs.contains(test)) {
        verbFlag = false;
      }
    }
    for (String test: testSubjects) {
      if (this.subjects.contains(test)) {
        subjectFlag = false;
      }
    }
//    for (String test: testTimeLocs) {
//      if (this.timeLocs.contains(test)) {
//        timeLocFlag = false;
//      }
//    }
    if (verbFlag || subjectFlag) {
      return false;
    }
    return true;
  }
}
