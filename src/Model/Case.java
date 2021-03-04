package Model;

import java.io.Serializable;

public abstract class Case implements Serializable {
    private int x;
    private int y;
    private int valeur;
    private boolean fNord;
    private boolean fSud;
    private boolean fEst;
    private boolean fOuest;

    public Case(int x, int y,int valeur) {
        this.x = x;
        this.y = y;
        this.valeur = valeur;
        this.fNord = false;
        this.fSud = false;
        this.fEst = false;
        this.fOuest = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValeur() {
        return valeur;
    }

    public boolean isfNord() {
        return fNord;
    }

    public void setfNord(boolean fNord) {
        this.fNord = fNord;
    }

    public boolean isfSud() {
        return fSud;
    }

    public void setfSud(boolean fSud) {
        this.fSud = fSud;
    }

    public boolean isfEst() {
        return fEst;
    }

    public void setfEst(boolean fEst) {
        this.fEst = fEst;
    }

    public boolean isfOuest() {
        return fOuest;
    }

    public void setfOuest(boolean fOuest) {
        this.fOuest = fOuest;
    }
}
