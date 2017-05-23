package edu.rosehulman.zhouz2;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by JerryQiu on 5/20/17.
 */
public class InfoCard {
    public NP np;
    public VP vp;



    public InfoCard(Tree np, Tree vp){
        this.np = new NP(np);
        this.vp = new VP(vp);
    }

    public InfoCard(Tree tree) {
        System.out.println(tree.toString());
    }

    public boolean compareTo(InfoCard other) {
        if (this.np.compareTo(other.np)) {
            return this.vp.compareTo(other.vp);
        } else {
            return false;
        }
    }
}
