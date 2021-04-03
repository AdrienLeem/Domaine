package Model;

import java.io.Serializable;
import java.util.*;

import static java.lang.Math.abs;

public class Partie implements Serializable {
    private final Plateau plateau;
    private final ArrayList<Joueur> joueurs;
    private final ArrayList<Carte> pioche;
    private final ArrayList<Carte> cartesVendu;
    private int NbTour;
    private int dernierjoueur;
    private final HashMap<Domaine, ArrayList<Domaine>> map;

    public ArrayList<Carte> getCartesVendu() {
        return cartesVendu;
    }

    public Partie() {
        this.plateau = new Plateau();
        this.joueurs = null;
        this.pioche = new ArrayList<>();
        this.cartesVendu = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public Partie(ArrayList<Joueur> j) {
        this.NbTour = 0;
        List<String> couleur = new ArrayList<>();
        couleur.add("Rouge");
        couleur.add("Bleu");
        couleur.add("Orange");
        couleur.add("Blanc");
        this.plateau = new Plateau();
        for(int i =0;i<j.size();i++){
            j.get(i).setCouleur(couleur.get(i));
        }
        this.joueurs = j;
        this.pioche = new ArrayList<Carte>();
        this.cartesVendu = new ArrayList<Carte>();
        this.map = new HashMap<>();
    }


    public void melanger(ArrayList<Carte> cartes) {
        Collections.shuffle(cartes);
    }

    public void creationDeck(){
        ArrayList<Carte> paquetA = new ArrayList<Carte>();
        ArrayList<Carte> paquetB = new ArrayList<Carte>();
        ArrayList<Carte> paquetC = new ArrayList<Carte>();
        ArrayList<Carte> paquetD = new ArrayList<Carte>();

        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<2){
                carte.setAction(new AjoutFrontiere(1));
                carte.setNom("F_1_1_2");
                carte.setPrixAction(1);
                carte.setPrixVente(2);
            }
            else if (i<5){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_6_5");
                carte.setPrixAction(6);
                carte.setPrixVente(5);
            }
            else if (i<10){
                carte.setAction(new AjoutChevalier(1));
                carte.setNom("C_1_2_3");
                carte.setPrixAction(2);
                carte.setPrixVente(3);
            }
            else if (i<12){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else {
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            paquetA.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<3){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_3_2");
                carte.setPrixAction(3);
                carte.setPrixVente(2);
            }
            else if (i<5){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            else if (i<6){
                carte.setAction(new AjoutChevalier(1));
                carte.setNom("C_1_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else if (i<7){
                carte.setAction(new AjoutChevalier(2));
                carte.setNom("C_2_7_5");
                carte.setPrixAction(7);
                carte.setPrixVente(5);
            }
            else if (i<8){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<9){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<11){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<13){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<14){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_5_5");
                carte.setPrixAction(5);
                carte.setPrixVente(5);
            }
            paquetB.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<1){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<2){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_7_4");
                carte.setPrixAction(7);
                carte.setPrixVente(4);
            }
            else if (i<3){
                carte.setAction(new AjoutChevalier(2));
                carte.setNom("C_2_7_5");
                carte.setPrixAction(7);
                carte.setPrixVente(5);
            }
            else if (i<4){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<5){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<11){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<13){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_7_4");
                carte.setPrixAction(7);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_5_5");
                carte.setPrixAction(5);
                carte.setPrixVente(5);
            }
            paquetC.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<2){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_4_2");
                carte.setPrixAction(4);
                carte.setPrixVente(2);
            }
            else if (i<3){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<5){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<9){
                carte.setAction(new AjoutChevalier(2));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_2_1_8_4");
                carte.setPrixAction(8);
                carte.setPrixVente(4);
            }
            else if (i<13){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<14){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_8_4");
                carte.setPrixAction(8);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            paquetD.add(carte);
        }
        melanger(paquetA);
        melanger(paquetB);
        melanger(paquetC);
        melanger(paquetD);
        pioche.addAll(paquetA);
        pioche.addAll(paquetB);
        pioche.addAll(paquetC);
        pioche.addAll(paquetD);
    }
    public void distribuerCarte() {
        for (int i=0; i<3;i++) {
            for (Joueur j:joueurs) {
                j.piocher(pioche.get(0));
                pioche.remove(0);
            }

        }
    }

    public void assignDomaine() {
        for (Joueur joueur : this.joueurs) {
            joueur.getDomaines().clear();
        }
        ArrayList<Integer> lint = new ArrayList<>();
        for (int i = 0; i < this.plateau.getDomaines().size(); i++) {
            lint.clear();
            for (int a = 0; a < this.joueurs.size(); a++) {
                lint.add(0);
            }
            for (int j = 0; j < this.plateau.getDomaines().get(i).getCases().size(); j++) {
                for (Joueur joueur : this.joueurs) {
                    for (int k = 0; k < joueur.getChateaux().size(); k++) {
                        if (this.plateau.getDomaines().get(i).getCases().get(j).getX() == joueur.getChateaux().get(k).getX() && this.plateau.getDomaines().get(i).getCases().get(j).getY() == joueur.getChateaux().get(k).getY()) {
                            switch (joueur.getCouleur()) {
                                case "Rouge" -> lint.set(0, lint.get(0) + 1);
                                case "Bleu" -> lint.set(1, lint.get(1) + 1);
                                case "Orange" -> lint.set(2, lint.get(2) + 1);
                                case "Blanc" -> lint.set(3, lint.get(3) + 1);
                            }
                        }
                    }
                }
            }
            int cpt = 0;
            for (Integer integer : lint) {
                cpt += integer;
            }
            if (cpt == 1) {
                for (int i1 = 0; i1 < lint.size(); i1++) {
                    if (lint.get(i1) == 1) this.joueurs.get(i1).getDomaines().add(this.plateau.getDomaines().get(i));
                }
            }
        }
    }

    public void calculPoint() {
        for (Joueur j : this.joueurs) {
            int nbPoint = 0;
            for (int i = 0; i < j.getDomaines().size(); i++) {
                nbPoint += j.getDomaines().get(i).calculPoint();
            }
            j.setPoint(nbPoint);
        }
    }

    public void refreshDomaine() {
        this.plateau.setDomaine();
        this.assignDomaine();
        this.verifFrontieredomaine();
        this.calculPoint();
    }


    public void verifFrontieredomaine(){
        for (Joueur j : this.getJoueurs())
            for (Domaine d: j.getDomaines())
                for (Case c: d.getCases()) {
                    if (c.isfOuest()) {
                        Case c2 = this.plateau.getCase(c.getX(),c.getY()-1);
                        if (d.getCases().contains(c2)){
                            c.setfOuest(false);
                            c2.setfEst(false);
                        }
                    } if (c.isfEst()) {
                        Case c2 = this.plateau.getCase(c.getX(),c.getY()+1);
                        if (d.getCases().contains(c2)){
                            c.setfEst(false);
                            c2.setfOuest(false);
                        }
                    } if (c.isfSud()) {
                        Case c2 = this.plateau.getCase(c.getX()+1,c.getY());
                        if (d.getCases().contains(c2)){
                            c.setfSud(false);
                            c2.setfNord(false);
                        }
                    } if (c.isfNord()) {
                        Case c2 = this.plateau.getCase(c.getX()-1,c.getY());
                        if (d.getCases().contains(c2)){
                            c.setfNord(false);
                            c2.setfSud(false);
                        }
                    }
                }
    }

    public void revenuMines() {
        for (Joueur j : this.joueurs) {
            int mineCuivre = 0;
            int mineArgent = 0;
            int mineOr = 0;
            int mineDiamant = 0;
            if (j.getDomaines().size() > 0) {
                for (int i = 0; i < j.getDomaines().size(); i++) {
                    for (int k = 0; k < j.getDomaines().get(i).getCases().size(); k++) {
                        if (this.plateau.getCase(j.getDomaines().get(i).getCases().get(k).getX(),j.getDomaines().get(i).getCases().get(k).getY()) instanceof CaseMineCuivre) mineCuivre += 1;
                        else if (this.plateau.getCase(j.getDomaines().get(i).getCases().get(k).getX(),j.getDomaines().get(i).getCases().get(k).getY()) instanceof CaseMineArgent) mineArgent += 1;
                        else if (this.plateau.getCase(j.getDomaines().get(i).getCases().get(k).getX(),j.getDomaines().get(i).getCases().get(k).getY()) instanceof CaseMineOr) mineOr += 1;
                        else if (this.plateau.getCase(j.getDomaines().get(i).getCases().get(k).getX(),j.getDomaines().get(i).getCases().get(k).getY()) instanceof CaseMineDiamant) mineDiamant += 1;
                    }
                }
            }
            if (mineCuivre > 0 && mineArgent == 0 && mineOr == 0 && mineDiamant == 0) j.setDucat(1);
            else if (mineCuivre == 0 && mineArgent > 0 && mineOr == 0 && mineDiamant == 0) j.setDucat(1);
            else if (mineCuivre == 0 && mineArgent == 0 && mineOr > 0 && mineDiamant == 0) j.setDucat(1);
            else if (mineCuivre == 0 && mineArgent == 0 && mineOr == 0 && mineDiamant > 0) j.setDucat(1);
            else if (mineCuivre > 0 && mineArgent > 0 && mineOr == 0 && mineDiamant == 0) j.setDucat(2);
            else if (mineCuivre > 0 && mineArgent == 0 && mineOr > 0 && mineDiamant == 0) j.setDucat(2);
            else if (mineCuivre > 0 && mineArgent == 0 && mineOr == 0 && mineDiamant > 0) j.setDucat(2);
            else if (mineCuivre == 0 && mineArgent > 0 && mineOr > 0 && mineDiamant == 0) j.setDucat(2);
            else if (mineCuivre == 0 && mineArgent == 0 && mineOr > 0 && mineDiamant > 0) j.setDucat(2);
            else if (mineCuivre == 0 && mineArgent > 0 && mineOr == 0 && mineDiamant > 0) j.setDucat(2);
            else if (mineCuivre > 0 && mineArgent > 0 && mineOr > 0 && mineDiamant == 0) j.setDucat(3);
            else if (mineCuivre > 0 && mineArgent > 0 && mineOr == 0 && mineDiamant > 0) j.setDucat(3);
            else if (mineCuivre > 0 && mineArgent == 0 && mineOr > 0 && mineDiamant > 0) j.setDucat(3);
            else if (mineCuivre == 0 && mineArgent > 0 && mineOr > 0 && mineDiamant > 0) j.setDucat(3);
            else if (mineCuivre > 0 && mineArgent > 0 && mineOr > 0 && mineDiamant > 0) j.setDucat(4);
        }
    }

    public int getNbJoueurs(){
        return this.joueurs.size();
    }

    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    public Plateau getPlateau(){
        return this.plateau;
    }

    public int getNbTour() {
        return NbTour;
    }

    public void setNbTour(int nbTour) {
        NbTour = nbTour;
    }

    public ArrayList<Carte> getPioche() {
        return pioche;
    }

    public void setCartesVendu(Carte cartesVendu) {
        this.cartesVendu.add(cartesVendu);
    }

    public Joueur getJoueurbyname(String n){
        for (Joueur joueur : this.joueurs) {
            if (joueur.getNom().equals(n)) {
                return joueur;
            }
        }
        return null;
    }

    public int getDernierjoueur() {
        return dernierjoueur;
    }

    public void setDernierjoueur(int dernierjoueur) {
        this.dernierjoueur = dernierjoueur;
    }

    public boolean pionValide(Pion p, Case c, Joueur a, Optional<Carte> carte ,boolean init, int... i)
    {
        for (Joueur j : getJoueurs()) {
            for (Pion chateau : j.getChateaux()) {
                if (chateau.getX() == c.getX() && chateau.getY() == c.getY()) return false;
            }
            for (Pion chevalier : j.getChevaliers()){
                if (chevalier.getX() == c.getX() && chevalier.getY() == c.getY()) return false;
            }
            if (!j.equals(a))
                for (Domaine d : j.getDomaines())
                    for (Case e : d.getCases())
                        if (c.getX() == e.getX() && c.getY() == e.getY())
                            return false;
        }
        if (!(c instanceof CasePrairie || c instanceof CaseForet)) return false;
        else if (c instanceof CaseForet) {
            if (p instanceof PionChateau) return false;
            else {
                if (!init) {
                    if (carte.isPresent()){
                        if (chevalierValide(c,a,init,i) && a.getDucat()-carte.get().getPrixAction()>0) a.setDucat(-1);
                        else return false;
                    }
                }
            }
        }
        if (p instanceof PionChevalier){
            return chevalierValide(c, a, init, i);
        } else {
            if (init)
                return chateauValide(c, a);
        }
        return true;
    }

    private boolean chevalierValide(Case c, Joueur j, boolean init, int[] i) {
        if (init){
            return j.getChateaux().get(i[0]).getX() - c.getX() == 0 && j.getChateaux().get(i[0]).getY() - c.getY() == -1
                    || j.getChateaux().get(i[0]).getX() - c.getX() == 0 && j.getChateaux().get(i[0]).getY() - c.getY() == 1
                    || j.getChateaux().get(i[0]).getX() - c.getX() == -1 && j.getChateaux().get(i[0]).getY() - c.getY() == 0
                    || j.getChateaux().get(i[0]).getX() - c.getX() == 1 && j.getChateaux().get(i[0]).getY() - c.getY() == 0;
        }
        else {
            for (Pion chevalier : j.getChevaliers())
                if (chevalier.getX() == c.getX() && chevalier.getY() == c.getY() - 1
                        || chevalier.getX() == c.getX() && chevalier.getY() == c.getY() + 1
                        || chevalier.getX() == c.getX() - 1 && chevalier.getY() == c.getY()
                        || chevalier.getX() == c.getX() + 1 && chevalier.getY() == c.getY())
                    return true;
            for (Pion chateau : j.getChateaux()) {
                if (chateau.getX() - c.getX() == 0 && chateau.getY() - c.getY() == -1
                        || chateau.getX() - c.getX() == 0 && chateau.getY() - c.getY() == 1
                        || chateau.getX() - c.getX() == -1 && chateau.getY() - c.getY() == 0
                        || chateau.getX() - c.getX() == 1 && chateau.getY() - c.getY() == 0)
                    return true;
            }
        }
            return false;
    }

    public boolean chateauValide(Case c, Joueur j) {
        for (Pion chateau : j.getChateaux()) {
            if (!(abs(chateau.getX() - c.getX()) + abs(chateau.getY() - c.getY()) > 5))
                return false;
        }
        return true;
    }

    public boolean frontiereValide(Case c1, Case c2) {
        if (!(c2.getX() - c1.getX() == 1 && (c2.getY() - c1.getY()) == 0)
                && !(c2.getX() - c1.getX() == -1 && (c2.getY() - c1.getY()) == 0)
                && !(c2.getX() - c1.getX() == 0 && (c2.getY() - c1.getY()) == -1)
                && !(c2.getX() - c1.getX() == 0 && (c2.getY() - c1.getY()) == 1)){
            return false;
        }
        for (Joueur j : this.getJoueurs())
            for (Domaine d : j.getDomaines())
                for (Case c : d.getCases())
                    if (c.equals(c1) || c.equals(c2)) return false;
        return (!c1.isfNord() || (c2.getX() != c1.getX() - 1 || c2.getY() != c1.getY()))
                && (!c1.isfSud() || (c2.getX() != c1.getX() + 1 || c2.getY() != c1.getY()))
                && (!c1.isfEst() || (c2.getX() != c1.getX() || c2.getY() != c1.getY() + 1))
                && (!c1.isfOuest() || (c2.getX() != c1.getX() || c2.getY() != c1.getY() - 1));
    }

    public Pion pionOncase(Case c) {
        for (Joueur j: getJoueurs()) {
            for (PionChevalier chevalier : j.getChevaliers()){
                if (c.getX() == chevalier.getX() && c.getY() == chevalier.getY())
                    return chevalier;
            }
            for (PionChateau chateau: j.getChateaux()) {
                if (c.getX() == chateau.getX() && c.getY() == chateau.getY())
                    return chateau;
            }
        }
        return null;
    }

    public Domaine caseOnDomaine(Case c) {
        for (Joueur joueur : getJoueurs()){
            for (Domaine d : joueur.getDomaines())
                if (d.getCases().contains(c))
                        return d;
        }
        return null;
    }

    public void addAlliance(Domaine djoueur, Domaine dadversaire){
        if (!map.containsKey(djoueur)) map.put(djoueur, new ArrayList<>());
        if (!map.containsKey(dadversaire)) map.put(dadversaire, new ArrayList<>());
        map.forEach((key, value) -> {
            if (value == null){
                value = new ArrayList<Domaine>();
                map.put(key, value);
            }
            if (djoueur.equals(key) && !value.contains(dadversaire)) {
                value.add(dadversaire);
            }
            if (dadversaire.equals(key) && !value.contains(djoueur)) {
                value.add(djoueur);
            }
        });
    }

    public boolean isInAlliance(Domaine djoueur, Domaine dadversaire){
        for (Map.Entry<Domaine, ArrayList<Domaine>> entry : map.entrySet()) {
            Domaine key = entry.getKey();
            ArrayList<Domaine> value = entry.getValue();
            for (Case k : key.getCases()) {
                if (djoueur.getCases().contains(k)) {
                    for (Domaine v : value) {
                        for (Case vc : v.getCases()){
                            if (dadversaire.getCases().contains(vc)) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean domaineAdjacent(Domaine d1, Domaine d2){
        for (Case c1 : d1.getCases()){
            if (d2.getCases().contains(this.plateau.getCase(c1.getX()+1,c1.getY()))
                    || d2.getCases().contains(this.plateau.getCase(c1.getX()-1,c1.getY()))
                    || d2.getCases().contains(this.plateau.getCase(c1.getX(),c1.getY()-1))
                    || d2.getCases().contains(this.plateau.getCase(c1.getX(),c1.getY()+1))) return true;
        }
        return false;
    }

    public boolean verifCarteJouable(Joueur j,Action a) {
        if (a instanceof Alliance || a instanceof Transfuge || a instanceof ExtensionDomaine) {
            if (j.getDomaines().size() == 0) return false;
            if (a instanceof Alliance || a instanceof Transfuge){
                for (Joueur k : joueurs){
                    if (!k.equals(j)){
                        for (Domaine kdom :k.getDomaines()){
                            for (Domaine jdom : j.getDomaines()){
                                if(domaineAdjacent(jdom,kdom) && !isInAlliance(jdom,kdom)) {
                                    if (a instanceof Transfuge){
                                        for (PionChevalier chevalier : k.getChevaliers()){
                                            Case c = this.plateau.getCase(chevalier.getX(), chevalier.getY());
                                            if (kdom.getCases().contains(c)) return true;
                                        }
                                    }
                                    else return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public void removeAndAdd(Pion p){
        for (Joueur j : this.getJoueurs()){
            boolean remove = j.getChevaliers().remove(p);
            if (remove) j.getChevaliers().add(new PionChevalier());
        }
    }

    public boolean caseAdjacenteDomaineJoueur(Joueur joueur, Case c) {
        for (int i = 0; i < joueur.getDomaines().size(); i++) {
            for (int j = 0; j < joueur.getDomaines().get(i).getCases().size(); j++) {
                int x = joueur.getDomaines().get(i).getCases().get(j).getX();
                int y = joueur.getDomaines().get(i).getCases().get(j).getY();
                if (x == 0 && y == 0) {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                }
                else if (x == 0 && y == 11) {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
                else if (x == 11 && y == 0) {
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                }
                else if (x == 11 && y == 11) {
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
                else if (x == 0) {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
                else if (y == 0) {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                }
                else if (x == 11) {
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
                else if (y == 11) {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
                else {
                    if (this.plateau.getCase(x + 1, y) == c) return true;
                    if (this.plateau.getCase(x - 1, y) == c) return true;
                    if (this.plateau.getCase(x, y + 1) == c) return true;
                    if (this.plateau.getCase(x, y - 1) == c) return true;
                }
            }
        }
        return false;
    }

    public Domaine getDomaineJoueur(Joueur j, Case c) {
        for (Domaine d : j.getDomaines()) {
            if (d.getCases().contains(this.plateau.getCase(c.getX() - 1, c.getY()))
                    || d.getCases().contains(this.plateau.getCase(c.getX() + 1, c.getY()))
                    || d.getCases().contains(this.plateau.getCase(c.getX(), c.getY() - 1))
                    || d.getCases().contains(this.plateau.getCase(c.getX(), c.getY() + 1))) {
                return d;
            }
        }
        return null;
    }

    public boolean verifCountchevalier(Domaine djoueur, Domaine dadversaire){
        int jnbchevalier = 0;
        int advnbchevalier = 0;
        for (Case cj : djoueur.getCases()){
            if (this.pionOncase(cj) instanceof PionChevalier){
                jnbchevalier++;
            }
        }
        for (Case cadv : dadversaire.getCases()){
            if(this.pionOncase(cadv) instanceof PionChevalier){
                advnbchevalier++;
            }
        }
        return jnbchevalier > advnbchevalier;
    }

    public boolean finPartie() {
        if (this.joueurs.size() == 2 && (this.joueurs.get(0).getPoint() >= 50 || this.joueurs.get(1).getPoint() >= 50)) {
            return true;
        }
        else if (this.joueurs.size() == 3 && (this.joueurs.get(0).getPoint() >= 40 || this.joueurs.get(1).getPoint() >= 40 || this.joueurs.get(2).getPoint() >= 40)) {
            return true;
        }
        else if (this.joueurs.size() == 4 && (this.joueurs.get(0).getPoint() >= 30 || this.joueurs.get(1).getPoint() >= 30 || this.joueurs.get(2).getPoint() >= 30 || this.joueurs.get(3).getPoint() >= 30)) {
            return true;
        }
        else if (this.pioche.size() == 0) {
            for (Joueur j : this.joueurs) {
                for (int i = 0; i < j.getMain().size(); i++) {
                    j.vendreCarte(i);
                }
            }
            return true;
        }
        return false;
    }

    public Joueur getGagnant() {
        int[] array = new int[4];
        int index = 0;
        for (int i = 0; i < this.joueurs.size(); i++) {
            array[i] = this.joueurs.get(i).getPoint();
            index = i;
        }
        Arrays.sort(array);
        Joueur tempJ = this.joueurs.get(0);
        for (Joueur joueur : this.joueurs) {
            if (joueur.getPoint() == array[index]) joueur.setPoint(joueur.getPoint() + 5);
            else if (joueur.getPoint() == array[index - 1]) joueur.setPoint(joueur.getPoint() + 3);
            if (joueur.getPoint() > tempJ.getPoint()) tempJ = joueur;
        }
        return tempJ;
    }
}
