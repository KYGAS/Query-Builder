package queryBuilder.Compiler;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Organizer {

    private ArrayList<Pair<Integer,String>> orderedCalls;

    public Organizer() {
        orderedCalls = new ArrayList<Pair<Integer,String>>();
    }

    public void addCall(String name, String args){

        orderedCalls.add(
                new Pair<Integer, String>( Priority.valueOf(name.toUpperCase(Locale.ROOT)).ordinal()
                        , args)
        );
        orderedCalls.sort(Comparator.comparing(Pair::getKey));

        System.out.println(orderedCalls);
    }

    public ArrayList<Pair<Integer, String>> getOrderedCalls() {
        return orderedCalls;
    }
}
