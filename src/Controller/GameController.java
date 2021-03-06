package Controller;

import Model.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController{
    private Partie partie;
    private String nomPartie;
    private Stage stage;

    @FXML
    private ScrollPane MainPane;

    @FXML
    private GridPane Plateau;

    @FXML
    private Text Label_Partie;

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

    }

    @FXML
    void Sauvegarder(ActionEvent event) {

    }

    @FXML
    void SlotCarte1Clicked(MouseEvent event) {
        System.out.println("coucou");
    }

    @FXML
    void SlotCarte2Clicked(MouseEvent event) {

    }

    @FXML
    void SlotCarte3Clicked(MouseEvent event) {

    }

    @FXML
    void SlotChevalierClicked(MouseEvent event) {

    }

    @FXML
    void SlotDucat1Clicked(MouseEvent event) {

    }

    @FXML
    void SlotDucat2Clicked(MouseEvent event) {

    }

    @FXML
    void SlotFrontiereClicked(MouseEvent event) {

    }

    @FXML
    void SlotPiocheClicked(MouseEvent event) {

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
                this.Plateau.add(img,i,j);
            }
        }
    }

    public void MessagePop(String s,boolean yesno){
        VBox v = new VBox();
        Text t = new Text();
        t.setText(s);
        v.getChildren().add(t);
        if(yesno){
            HBox h = new HBox();
            Button yes = new Button();
            yes.setText("Oui");
            Button no = new Button();
            no.setText("no");
            h.getChildren().add(yes);
            h.getChildren().add(no);
            v.getChildren().add(h);
        }else{
            Button ok = new Button();
            ok.setText("OK");
            ok.setOnMousePressed((event) -> {
                this.Plateau.getChildren().remove(v);
            });
            v.getChildren().add(ok);
        }
        this.Plateau.getChildren().add(v);
    }

    public void Clickable(boolean Carte1, boolean Carte2, boolean Carte3, boolean frontiere, boolean ducat1, boolean ducat2, boolean chevalier, boolean pioche, boolean button_finTour){
        this.img_SlotCarte1.setDisable(Carte1);
        this.img_SlotCarte2.setDisable(Carte2);
        this.img_SlotCarte3.setDisable(Carte3);
        this.img_SlotFrontière.setDisable(frontiere);
        this.img_SlotDucat1.setDisable(ducat1);
        this.img_SlotDucat2.setDisable(ducat2);
        this.img_SlotChevalier.setDisable(chevalier);
        this.img_SlotPioche.setDisable(pioche);
        this.Button_Tour.setDisable(button_finTour);
    }

    public void PremierTour(){
        for (int i = 1; i <= 4 + 1; i++){
            for(int j = 0; j < this.partie.getNbJoueurs(); j++) {
                MessagePop("Placer votre "+i+" Pion château sur le terrain.",false);
            }
        }
    }

    public void Jeu(){
        PremierTour();
    }
}
