package Controller;

import Model.Partie;
import Model.Plateau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
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

    public void transferMessage(String message) {
        Label_Partie.setText("Partie : "+message);
        this.nomPartie = message;
    }

    public void initGame(){
        initPlateau();
        initSlot();
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
                img.setImage(new Image("img/pp.PNG"));
                img.setFitHeight(65);
                img.setFitWidth(65);
                this.Plateau.add(img,i,j);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partie = Sauvegarde.charger(this.nomPartie);
        initGame();
    }
}
