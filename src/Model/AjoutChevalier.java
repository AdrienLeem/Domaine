package Model;

public class AjoutChevalier extends Action {

    public AjoutChevalier() {
        this.nom = "Place un chevalier sur le plateau";
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void run() {
        
    }
}
