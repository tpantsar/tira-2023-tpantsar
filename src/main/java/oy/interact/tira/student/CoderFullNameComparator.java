package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.model.Coder;

public class CoderFullNameComparator implements Comparator<Coder> {

    @Override
    /**
     * Palauttaa koodareiden koko nimen vertailun lopputuloksen kutsujalle.
     * Hyödyntää Coder-luokan olemassaolevia metodeja.
     * Käyttää Comparator-rajapintaa Comparable:n sijaan.
     * 
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Coder coder1, Coder coder2) {
        return coder1.getFullName().compareTo(coder2.getFullName());
    }

}
