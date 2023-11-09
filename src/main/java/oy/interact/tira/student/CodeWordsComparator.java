package oy.interact.tira.student;
import oy.interact.tira.util.Pair;
import java.util.Comparator;

public class CodeWordsComparator implements Comparator<Pair<String, Integer>> {

    @Override
    /**
     * Palauttaa koodissa esiintyvien sanojen vertailun lopputuloksen kutsujalle.
     */
    public int compare(Pair<String, Integer> pair1, Pair<String, Integer> pair2) {
        return pair1.getValue().compareTo(pair2.getValue());
    }
}
