package Model;

public class Alliance extends Action {


    public Alliance() {
        this.description = "";
        this.j = null;
    }


    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j) {

    }
}
