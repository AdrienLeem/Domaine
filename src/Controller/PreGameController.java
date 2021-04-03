package Controller;

import Model.IA;
import Model.Joueur;
import Model.Partie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PreGameController implements Initializable {
    private Partie partie;
    private String nom;
    private int nbJoueur;
    private boolean levier = false;
    private ArrayList<TextField> listField = new ArrayList<TextField>();
    private ArrayList<CheckBox> listcheckbox = new ArrayList<CheckBox>();
    private ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();

    @FXML
    private TextField nomPartie;

    @FXML
    private VBox vBox;

    @FXML
    private ComboBox<Integer> comboBox;

    @FXML
    void Suivant(ActionEvent event) {
        vBox.setSpacing(50);
        if(!this.levier) {
            if (!this.comboBox.getSelectionModel().isEmpty() && !this.nomPartie.getText().isEmpty()) {
                this.nom = this.nomPartie.getText();
                this.nbJoueur = this.comboBox.getSelectionModel().getSelectedItem();
                this.vBox.getChildren().clear();
                for (int i = 1; i <= this.nbJoueur; i++) {
                    HBox v = new HBox();
                    v.setSpacing(20);
                    TextField t = new TextField();
                    t.setFont(Font.font("Old English Text MT", FontWeight.NORMAL, 24));
                    CheckBox checkBox = new CheckBox();
                    this.listcheckbox.add(checkBox);
                    this.listField.add(t);
                    t.setPromptText("Nom Joueur " + i);
                    v.setAlignment(Pos.CENTER);
                    v.getChildren().add(t);
                    checkBox.setText("IA ");
                    checkBox.setFont(Font.font("Old English Text MT"));
                    v.getChildren().add(checkBox);
                    this.vBox.getChildren().add(v);
                }
                this.levier = true;
            }
        }else{
            ArrayList<String> DefaultName = new ArrayList<>();
            DefaultName.add("Adrien");
            DefaultName.add("Gauthier");
            DefaultName.add("Lucas");
            DefaultName.add("Léo");
            for(int o = 0; o < this.nbJoueur; o++){
                if(this.listField.get(o).getText().isEmpty()){
                    this.listField.get(o).setText(DefaultName.get(0));
                    DefaultName.remove(0);
                }
                for(int o2 = 1; o2 < this.nbJoueur-1; o2++){
                    if(this.listField.get(o).getText() == this.listField.get(o2).getText()){
                        this.listField.get(o).setText(DefaultName.get(0));
                        DefaultName.remove(0);
                    }
                }
            }
            for (int i = 0; i < this.nbJoueur; i++) {
                if(this.listcheckbox.get(i).isSelected()){
                    this.listJoueur.add(new IA(this.listField.get(i).getText()));
                }else{
                    this.listJoueur.add(new Joueur(this.listField.get(i).getText()));
                }
            }
            this.partie = new Partie(this.listJoueur);
            Sauvegarde.sauvegarder(this.partie,this.nom);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/GameView.fxml"));
                String css = this.getClass().getResource("/Style/GameStyle.css").toExternalForm();
                Parent root = fxmlLoader.load();

                GameController gameController = fxmlLoader.getController();
                gameController.init(this.nom);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(css);
                Stage stage = new Stage();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent t) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
                stage.setX(0);
                stage.setY(0);
                stage.setTitle("Domaine :"+this.nom);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/View.fxml"));
            String css = this.getClass().getResource("/Style/ViewStyle.css").toExternalForm();
            Scene scene = new Scene(fxmlLoader.load(), 902, 850);
            scene.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setTitle("Domaine");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBox.getItems().addAll(2,3,4);
    }

}
