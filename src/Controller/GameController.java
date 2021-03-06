package Controller;

import Model.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private List<Integer> CaseClicked = new ArrayList<>();

    @FXML
    private ScrollPane MainPane;

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
    private ImageView img_SlotDucat2;

    @FXML
    private ImageView img_SlotChevalier;

    @FXML
    private ImageView img_SlotPioche;

    @FXML
    void Finir_Tour(ActionEvent event) {

    }

    @FXML
    void Quitter(ActionEvent event) {
        MessagePop("Êtes-vous sûr de vouloir quitter votre partie ?",true,0);
    }

    @FXML
    void Sauvegarder(ActionEvent event) {
        MessagePop("Êtes-vous sûr de vouloir sauvegarder votre partie ?",true,1);
    }

    public void save(){
        Sauvegarde.sauvegarder(this.partie,this.nomPartie);
    }

    public void init(String message) {
        Label_Partie.setText("Partie : "+message);
        this.nomPartie = message;
        this.partie = Sauvegarde.charger(this.nomPartie);
        initGame();
    }

    public void initGame(){
        this.action =false;
        initPlateau();
        initSlot();
        Jeu();
    }

    public void initSlot(){
        this.img_SlotCarte1.setImage(new Image("img/pp.PNG"));
        this.img_SlotCarte2.setImage(new Image("img/pp.PNG"));
        this.img_SlotCarte3.setImage(new Image("img/pp.PNG"));
        this.img_SlotFrontière.setImage(new Image("img/pp.PNG"));
        this.img_SlotDucat1.setImage(new Image("img/pp.PNG"));
        this.img_SlotDucat2.setImage(new Image("img/pp.PNG"));
        this.img_SlotChevalier.setImage(new Image("img/pp.PNG"));
        this.img_SlotPioche.setImage(new Image("img/pp.PNG"));
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
                    GridPane gridPane = (GridPane) img.getParent();
                    int y = gridPane.getRowIndex(img);
                    int x = gridPane.getColumnIndex(img);
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

    }

    public void MessagePop(String s,boolean yesno,int action) {
        VBox v = new VBox();
        v.setStyle("-fx-background-color: WHITE");
        Text t = new Text();
        t.setText(s);
        v.getChildren().add(t);
        if(yesno){
            HBox h = new HBox();
            Button yes = new Button();
            yes.setText("Oui");
            yes.setOnMousePressed((event) -> {
                switch (action){
                    case 1 -> {
                        Sauvegarde.sauvegarder(this.partie,this.nomPartie);
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.Plateau.getChildren().remove(v);
                    }
                    case 0 ->{
                        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        this.Plateau.getChildren().remove(v);
                        this.stage.close();
                    }
                }
            });
            Button no = new Button();
            no.setText("no");
            no.setOnMousePressed((event) -> {
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.Plateau.getChildren().remove(v);
            });
            h.getChildren().add(yes);
            h.getChildren().add(no);
            v.getChildren().add(h);
        }else{
            Button ok = new Button();
            ok.setText("OK");
            ok.setOnMousePressed((event) -> {
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.Plateau.getChildren().remove(v);
            });
            v.getChildren().add(ok);
        }
        this.Plateau.getChildren().add(v);
    }


    public void PremierTour(){
        int y =0;
        for (int i = 1; i <= 4; i++){
            addNbTour();
            for( Joueur j : this.partie.getJoueurs()) {
                this.LabelJoueur.setText("Joueur : "+j.getNom());
                boolean WaitTour = true;
                this.LabelInformation.setText("Placer votre château.");
                while(WaitTour){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(this.action){
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/Pion_Chateau_"+j.getCouleur()+".png"));
                        int finalY = y;
                        Platform.runLater(() -> placer(img,j,j.getChateaux().get(finalY)));
                        this.action = false;
                        WaitTour = false;
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
                        ImageView img = new ImageView();
                        img.setFitHeight(65);
                        img.setFitWidth(65);
                        img.setDisable(true);
                        img.setImage(new Image("img/PionChevalier"+j.getCouleur()+".png"));
                        int finalY = y;
                        Platform.runLater(() -> placer(img,j,j.getChevaliers().get(finalY)));
                        this.action = false;
                        WaitTour = false;
                    }
                }
            }
            y++;
        }
        this.LabelInformation.setText("oui");
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

    public void Jeu(){
        if(this.partie.getJoueurs().get(0).getChateaux().get(0).estPlace()){
            setLabelNbTour();
        }else{
            setLabelNbTour();
            new Thread(() -> {
                PremierTour();
            }).start();
        }
    }
}
