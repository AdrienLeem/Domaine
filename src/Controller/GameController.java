package Controller;

import Model.*;
import Model.Action;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController{
    private Partie partie;
    private String nomPartie;
    private Stage stage;
    private Boolean action;
    private final List<Integer> CaseClicked = new ArrayList<>();
    private boolean JouerCarte;
    private boolean VendreCarte;
    private int SlotSelect;
    private boolean WaitTour;
    private int carteActionChoix;
    private boolean carteAction;
    private int Dernierjoueur;

    @FXML
    private ScrollPane MainPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane Plateau;

    @FXML
    private Text Label_Partie;

    @FXML
    private Text LabelJoueur;

    @FXML
    private Text LabelNbTour;

    @FXML
    private Text LabelInformation;

    @FXML
    private Button Button_Sauvegarde;

    @FXML
    private Button Button_Quitter;

    @FXML
    private Button Button_Tour;


    @FXML
    private ImageView img_SlotCarte1;

    @FXML
    private ImageView img_SlotCarte2;

    @FXML
    private ImageView img_SlotCarte3;

    @FXML
    private ImageView img_SlotFrontière;

    @FXML
    private ImageView img_SlotDucat1;

    @FXML
    private ImageView img_SlotChevalier;

    @FXML
    private ImageView img_SlotPioche;

    @FXML
    private Text LabelCart1;

    @FXML
    private Text LabelCart2;

    @FXML
    private Text LabelCart3;

    @FXML
    private Text LabelFrontiere;

    @FXML
    private Text LabelDucat1;

    @FXML
    private Text LabelChevalier;

    @FXML
    private Text LabelPioche;

    @FXML
    private ImageView img_SlotVendu;

    @FXML
    private Text LabelVendu;

    @FXML
    void CarteVendu(MouseEvent event) {
        pasClickable(true,true,true,true,true,false);
    }

    @FXML
    void Finir_Tour(ActionEvent event) {
        this.WaitTour = false;
    }

    @FXML
    void Quitter(ActionEvent event) {
        MessagePop("Êtes-vous sûr de vouloir quitter votre partie ?",true,0,"Oui","Non");
    }

    @FXML
    void Sauvegarder(ActionEvent event) {
        MessagePop("Êtes-vous sûr de vouloir sauvegarder votre partie ?",true,1,"Oui","Non");
    }

    @FXML
    void SlotCart1Clicked(MouseEvent event) {
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
        this.SlotSelect = 1;
    }

    @FXML
    void SlotCart2Clicked(MouseEvent event) {
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
        this.SlotSelect = 2;
    }

    @FXML
    void SlotCart3Clicked(MouseEvent event) {
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
        this.SlotSelect = 3;
    }

    @FXML
    void PiocheClicked(MouseEvent event) {
        String l = this.LabelJoueur.getText().split(":")[1];
        this.partie.getJoueurbyname(l).piocher(this.partie.getPioche().get(0));
        this.partie.getPioche().remove(0);
        afficherBord(this.partie.getJoueurbyname(l));
        pasClickable(true,true,true,true,true,false);
    }

    public void init(String message) {
        Label_Partie.setText("Partie : "+message);
        this.nomPartie = message;
        this.partie = Sauvegarde.charger(this.nomPartie);
        this.Dernierjoueur = this.partie.getDernierjoueur();
        initGame();
    }

    public void initGame(){
        this.action =false;
        initPlateau();
        initSlot();
        Jeu();
    }

    public void initSlot(){
        this.img_SlotCarte1.setImage(new Image("img/Carte_Face_Vierge.png"));
        this.img_SlotCarte2.setImage(new Image("img/Carte_Face_Vierge.png"));
        this.img_SlotCarte3.setImage(new Image("img/Carte_Face_Vierge.png"));

        this.img_SlotFrontière.setImage(new Image("img/Grenzen_gross.PNG"));
        this.img_SlotDucat1.setImage(new Image("img/Dukaten_gross.PNG"));
        this.img_SlotChevalier.setImage(new Image("img/PionChevalierBlanc.PNG"));
        this.img_SlotPioche.setImage(new Image("img/Carte_Verso_A.png"));
        this.img_SlotVendu.setImage(new Image("img/Carte_Dos.png"));
    }

    public void initPlateau(){
        Model.Plateau p = this.partie.getPlateau();
        ArrayList<Joueur> listjoueurs = this.partie.getJoueurs();

        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                ImageView img = new ImageView();
                img.setFitHeight(65);
                img.setFitWidth(65);
                if(p.getCase(i,j) instanceof CaseForet){
                    img.setImage(new Image("img/Bush.png"));
                }else if(p.getCase(i,j) instanceof CaseMineOr){
                    img.setImage(new Image("img/MineOr.png"));
                }else if(p.getCase(i,j) instanceof CaseMineArgent){
                    img.setImage(new Image("img/MineArgent.png"));
                }else if(p.getCase(i,j) instanceof CaseMineCuivre){
                    img.setImage(new Image("img/MineCuivre.png"));
                }else if(p.getCase(i,j) instanceof CaseMineDiamant){
                    img.setImage(new Image("img/MineDiamant.png"));
                }else if(p.getCase(i,j) instanceof CaseCiteRoyale){
                    img.setImage(new Image("img/CaseChateauRoi.png"));
                }else if(p.getCase(i,j) instanceof CaseVillage){
                    img.setImage(new Image("img/TerrainVille.png"));
                }else {
                    img.setImage(new Image("img/terrain.png"));
                }
                img.setOnMouseClicked(event -> {
                    int y = GridPane.getRowIndex(img);
                    int x = GridPane.getColumnIndex(img);
                    this.CaseClicked.clear();
                    this.CaseClicked.add(x);
                    this.CaseClicked.add(y);
                    this.action = true;
                });
                this.Plateau.add(img,i,j);
            }
        }
        for(Joueur joueur : listjoueurs){
            for(PionChateau chateau : joueur.getChateaux()){
                if(chateau.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/Pion_Chateau_" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chateau.getX(), chateau.getY());
                }
            }
            for(PionChevalier chevalier : joueur.getChevaliers()){
                if(chevalier.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/PionChevalier" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chevalier.getX(), chevalier.getY());
                }
            }
        }
        for(int i=0;i<12;i++) {
            for (int j = 0; j < 12; j++) {
                if(p.getCase(i,j).isfEst()){
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereEst.png"));
                    this.Plateau.add(img, i, j);
                }
                if(p.getCase(i,j).isfOuest()){
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereOuest.png"));
                    this.Plateau.add(img, i, j);
                }
                if(p.getCase(i,j).isfNord()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereNord.png"));
                    this.Plateau.add(img, i, j);
                }
                if(p.getCase(i,j).isfSud()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereSud.png"));
                    this.Plateau.add(img, i, j);
                }
            }
        }

    }

    public void MessagePop(String s,boolean yesno,int action, String bt1, String bt2) {
        VBox v = new VBox();
        v.setPrefWidth(Region.USE_COMPUTED_SIZE);
        v.setPrefHeight(Region.USE_COMPUTED_SIZE);
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: WHITE");
        Text t = new Text();
        t.setText(s);
        v.getChildren().add(t);
        if(yesno){
            HBox h = new HBox();
            h.setSpacing(10);
            h.setPrefWidth(Region.USE_COMPUTED_SIZE);
            h.setPrefHeight(Region.USE_COMPUTED_SIZE);
            h.setAlignment(Pos.CENTER);
            Button yes = new Button();
            yes.setText(bt1);
            yes.setOnMousePressed((event) -> {
                switch (action){
                    case 1 -> {
                        Sauvegarde.sauvegarder(this.partie,this.nomPartie);
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.anchorPane.getChildren().remove(v);
                    }
                    case 0 ->{
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.anchorPane.getChildren().remove(v);
                        this.stage.close();
                    }
                    case  2 ->{
                        this.JouerCarte = true;
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.anchorPane.getChildren().remove(v);
                    }
                }
            });
            Button no = new Button();
            no.setText(bt2);
            no.setOnMousePressed((event) -> {
                switch (action){
                    case 1, 0 -> {
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.anchorPane.getChildren().remove(v);
                    }
                    case  2 ->{
                        this.VendreCarte = true;
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.anchorPane.getChildren().remove(v);
                    }
                }
            });
            h.getChildren().add(yes);
            h.getChildren().add(no);
            v.getChildren().add(h);
        }else{
            Button ok = new Button();
            ok.setText(bt1);
            ok.setOnMousePressed((event) -> {
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.Plateau.getChildren().remove(v);
            });
            v.getChildren().add(ok);
        }
        v.setLayoutX(this.Plateau.getWidth()/2 - 100);
        v.setLayoutY(this.Plateau.getHeight()/2 - 100);
        this.anchorPane.getChildren().add(v);
    }


    public void PremierTour(){
        int y =0;
        for (int i = 1; i <= 4; i++){
            addNbTour();
            for( Joueur j : this.partie.getJoueurs()) {
                this.LabelJoueur.setText("Joueur :"+j.getNom());
                boolean WaitTour = true;
                this.LabelInformation.setText("Placer votre château.");
                while(WaitTour){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(this.action){
                        // If bonne case pour chateau
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/Pion_Chateau_"+j.getCouleur()+".png"));
                        int finalY = y;
                        Platform.runLater(() -> placer(img,j,j.getChateaux().get(finalY)));
                        this.action = false;
                        WaitTour = false;
                        // la
                    }
                }
                WaitTour = true;
                this.LabelInformation.setText("Placer votre chevalier.");
                while(WaitTour){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(this.action){
                        // If bonne case pour chevalier
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/PionChevalier"+j.getCouleur()+".png"));
                        int finalY = y;
                        Platform.runLater(() -> placer(img,j,j.getChevaliers().get(finalY)));
                        this.action = false;
                        WaitTour = false;
                        // la
                    }
                }
            }
            y++;
        }
        this.partie.creationDeck();
        this.partie.distribuerCarte();
        this.LabelInformation.setText("oui ...");
    }

    public void placer(Node node, Joueur j,Pion p){
        int x = this.CaseClicked.get(0);
        int y = this.CaseClicked.get(1);
        this.Plateau.add(node,x,y);
        j.placePion(p,x,y);
    }

    public void setLabelNbTour(){
        this.LabelNbTour.setText("Tour n° "+this.partie.getNbTour());
    }

    public void addNbTour(){
        this.partie.setNbTour(this.partie.getNbTour()+1);
        setLabelNbTour();
    }

    public void afficherBord(Joueur j){

        if (j.getMain().size() == 3) {
            this.img_SlotCarte1.setImage(new Image("img/Carte_"+j.getMain().get(0).getNom()+".png"));
            this.img_SlotCarte2.setImage(new Image("img/Carte_"+j.getMain().get(1).getNom()+".png"));
            this.img_SlotCarte3.setImage(new Image("img/Carte_"+j.getMain().get(2).getNom()+".png"));
        } else if (j.getMain().size() == 2) {
            this.img_SlotCarte1.setImage(new Image("img/Carte_"+j.getMain().get(0).getNom()+".png"));
            this.img_SlotCarte2.setImage(new Image("img/Carte_"+j.getMain().get(1).getNom()+".png"));
            this.img_SlotCarte3.setImage(new Image("img/Carte_Face_Vierge.png"));
        } else if (j.getMain().size() == 1) {
            this.img_SlotCarte1.setImage(new Image("img/Carte_"+j.getMain().get(0).getNom()+".png"));
            this.img_SlotCarte2.setImage(new Image("img/Carte_Face_Vierge.png"));
            this.img_SlotCarte3.setImage(new Image("img/Carte_Face_Vierge.png"));
        } else {
            this.img_SlotCarte1.setImage(new Image("img/Carte_Face_Vierge.png"));
            this.img_SlotCarte2.setImage(new Image("img/Carte_Face_Vierge.png"));
            this.img_SlotCarte3.setImage(new Image("img/Carte_Face_Vierge.png"));
        }

        this.LabelDucat1.setText(String.valueOf(j.getDucat()));
        this.LabelChevalier.setText(String.valueOf(j.getChevalierNonPlacer()));
        this.LabelPioche.setText(String.valueOf(this.partie.getPioche().size()));
        this.LabelVendu.setText(String.valueOf(this.partie.getCartesVendu().size()));
    }

    public void Tour(){
        this.SlotSelect = 0;
        this.JouerCarte = false;
        this.VendreCarte = false;
        this.addNbTour();
        this.setLabelNbTour();

        for (int i = this.Dernierjoueur;i<this.partie.getJoueurs().size();i++) {
            this.partie.setDernierjoueur(i);
            Joueur j = this.partie.getJoueurs().get(i);
            pasClickable(false,false,false,true,true,true);
            this.WaitTour = true;
            this.LabelJoueur.setText("Joueur :"+j.getNom());
            afficherBord(j);
            this.LabelInformation.setText("Choisissez une carte.");
            while (this.WaitTour){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(this.SlotSelect != 0){
                    if(this.VendreCarte){
                        VendreCarte(j);
                        this.VendreCarte = false;
                    }else if(this.JouerCarte){
                        JouerCarte(j);
                        this.JouerCarte = false;
                    }
                }
            }
        }
        this.Dernierjoueur = 0;
        this.partie.refreshDomaine();
    }

    public void pasClickable(boolean slot1, boolean slot2, boolean slot3,boolean pioche, boolean vendu, boolean button){
        this.img_SlotCarte1.setDisable(slot1);
        this.img_SlotCarte2.setDisable(slot2);
        this.img_SlotCarte3.setDisable(slot3);
        this.img_SlotPioche.setDisable(pioche);
        this.img_SlotVendu.setDisable(vendu);
        this.Button_Tour.setDisable(button);
    }

    public void VendreCarte(Joueur j){
        Carte c = j.vendreCarte(this.SlotSelect-1);
        this.partie.setCartesVendu(c);
        afficherBord(j);
        this.LabelInformation.setText("Choisissez la pioche ou le marcher.");
        pasClickable(true,true,true,false,false,true);
    }

    public void JouerCarte(Joueur j){
        this.carteAction = false;

        VBox v = new VBox();
        v.setPrefWidth(Region.USE_COMPUTED_SIZE);
        v.setPrefHeight(Region.USE_COMPUTED_SIZE);
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: WHITE");
        Text t = new Text();
        t.setText("Quelle action ?");
        v.getChildren().add(t);
        HBox h = new HBox();
        h.setPrefWidth(Region.USE_COMPUTED_SIZE);
        h.setPrefHeight(Region.USE_COMPUTED_SIZE);
        h.setSpacing(10);
        h.setAlignment(Pos.CENTER);
        for(int i =0;i<j.getMain().get(this.SlotSelect-1).getActions().size();i++) {
            Button yes = new Button();
            yes.setPrefWidth(Region.USE_COMPUTED_SIZE);
            yes.setPrefHeight(Region.USE_COMPUTED_SIZE);
            yes.setText(j.getMain().get(this.SlotSelect-1).getActions().get(i).getDescription());
            int finalI = i;
            yes.setOnMousePressed((event) -> {
                this.carteActionChoix = finalI;
                this.carteAction = true;
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.anchorPane.getChildren().remove(v);
            });
            h.getChildren().add(yes);
        }
        v.getChildren().add(h);
        v.setLayoutX(this.Plateau.getWidth()/2 - 200);
        v.setLayoutY(this.Plateau.getHeight()/2 - 100);

        Platform.runLater(() -> this.anchorPane.getChildren().add(v));
        while (!this.carteAction){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean WaitTour = true;
        this.action = false;
        while(WaitTour) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Action a = j.getMain().get(this.SlotSelect - 1).getActions().get(this.carteActionChoix);
            while (a.getNombre() != 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.action) {
                    int Case_1_X = this.CaseClicked.get(0);
                    int Case_1_Y = this.CaseClicked.get(1);
                    if (a instanceof AjoutFrontiere || a instanceof Transfuge) {
                        this.action = false;
                        boolean WaitTour2 = true;
                        while (WaitTour2) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (this.action) {
                                int Case_2_X = this.CaseClicked.get(0);
                                int Case_2_Y = this.CaseClicked.get(1);
                                if (((Case_2_X - Case_1_X) == 1 && (Case_2_Y - Case_1_Y) == 0) || ((Case_2_X - Case_1_X) == -1 && (Case_2_Y - Case_1_Y) == 0) || ((Case_2_X - Case_1_X) == 0 && (Case_2_Y - Case_1_Y) == -1) || ((Case_2_X - Case_1_X) == 0 && (Case_2_Y - Case_1_Y) == 1)) {
                                    j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix, partie.getPlateau(), Case_1_X, Case_1_Y, Case_2_X, Case_2_Y);
                                    Platform.runLater(this::RefreshPlateau);
                                    WaitTour2 = false;
                                }
                            }
                        }
                    } else {
                        j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix, partie.getPlateau(), Case_1_X, Case_1_Y);
                    }
                    this.action = false;
                    WaitTour = false;
                }
            }
        }
        Platform.runLater(this::RefreshPlateau);
        afficherBord(j);
        this.LabelInformation.setText("Choisissez la pioche ou le marche.");
        pasClickable(true,true,true,false,false,true);
    }

    public void RefreshPlateau(){
        Model.Plateau p = this.partie.getPlateau();
        ArrayList<Joueur> listjoueurs = this.partie.getJoueurs();

        for(Joueur joueur : listjoueurs){
            for(PionChateau chateau : joueur.getChateaux()){
                if(chateau.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/Pion_Chateau_" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chateau.getX(), chateau.getY());
                }
            }
            for(PionChevalier chevalier : joueur.getChevaliers()){
                if(chevalier.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/PionChevalier" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chevalier.getX(), chevalier.getY());
                }
            }
            for(int i=0;i<12;i++) {
                for (int j = 0; j < 12; j++) {
                    if(p.getCase(i,j).isfEst()){
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/FrontiereEst.png"));
                        this.Plateau.add(img, i, j);
                    }
                    if(p.getCase(i,j).isfOuest()){
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/FrontiereOuest.png"));
                        this.Plateau.add(img, i, j);
                    }
                    if(p.getCase(i,j).isfNord()) {
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/FrontiereNord.png"));
                        this.Plateau.add(img, i, j);
                    }
                    if(p.getCase(i,j).isfSud()) {
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/FrontiereSud.png"));
                        this.Plateau.add(img, i, j);
                    }
                }
            }
        }
    }

    public void Jeu(){
        if(!this.partie.getJoueurs().get(0).getChateaux().get(0).estPlace()){
            setLabelNbTour();
            new Thread(() -> {
                PremierTour();
                while (true){
                    Tour();
                }
            }).start();
        }else {
            setLabelNbTour();
            new Thread(() -> {
                while (true){
                    Tour();
                }
            }).start();
        }
    }
}
