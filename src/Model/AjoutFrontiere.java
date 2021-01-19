package Model;

public class AjoutFrontiere extends Action {


    public AjoutFrontiere() {
        this.nom = "Place une frontière sur le plateau";
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void run() {

    }
}
