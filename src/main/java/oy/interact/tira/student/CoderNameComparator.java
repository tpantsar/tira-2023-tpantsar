package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.model.Coder;

public class CoderNameComparator implements Comparator<Coder> {

    @Override
    /**
     * Palauttaa koodareiden lempinimen "Coder Name"
     * vertailun lopputuloksen kutsujalle.
     * Hyödyntää Coder-luokan olemassaolevia metodeja.
     * Käyttää Comparator-rajapintaa Comparable:n sijaan.
     * 
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Coder coder1, Coder coder2) {
        String firstCoderName = coder1.getCoderName();
        String secondCoderName = coder2.getCoderName();
        return firstCoderName.compareTo(secondCoderName);
    }

}
