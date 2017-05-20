package edu.rosehulman.zhouz2;

/**
 * Created by JerryQiu on 5/20/17.
 */
public class InfoCard {
    private String subject;
    private String object;
    private String verb;

    public InfoCard(String subject, String verb, String object){
        this.subject = subject;
        this.verb = verb;
        this.object = object;
    }



    public String extract(String sub, String v){
        if (sub.contains(subject) && v.contains(verb)) {
            return verb;
        } else {
            return "-1";
        }
    }
}
