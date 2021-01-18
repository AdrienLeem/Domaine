package Model;

import java.util.ArrayList;

public class Carte {
    private ArrayList<Action> actions;


    public Carte() {
        this.actions = new ArrayList<Action>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
