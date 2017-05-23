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
    parseHelper(tree);
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

  private void parseHelper(Tree tree) {
    for (Tree wordEntry: tree.getChildrenAsList()) {
      if (wordEntry.isLeaf()) {
        switch (wordEntry.label().toString()) {
          case "VBZ": case "VBG": case "VB":
            verbs.add(wordEntry.value());
            break;
          case "NP":
            addSubjects(tree);
            break;
          case "PP":
            addTimes(tree, timeLocs.size());
            break;
          default:
            //Ignore do nothing
            break;
        }
      } else {
        parseHelper(wordEntry);
      }
    }
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
    ArrayList<String> testVerbs = vp.getVerbs();
    ArrayList<String> testSubjects = vp.getSubjects();
    ArrayList<String> testTimeLocs = vp.getTimeLocs();
    boolean verbFlag = true;
    boolean subjectFlag = true;
    boolean timeLocFlag = true;
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
    for (String test: testTimeLocs) {
      if (this.timeLocs.contains(test)) {
        timeLocFlag = false;
      }
    }
    if (verbFlag || subjectFlag || timeLocFlag) {
      return false;
    }
    return true;
  }
}
