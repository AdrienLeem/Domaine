package Controller;

import Model.Partie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Charger_PartieController implements Initializable {

    private ArrayList<String> nomFichier = new ArrayList<String>();
    private ArrayList<CheckBox> listCheckbox = new ArrayList<CheckBox>();

    @FXML
    private VBox vBox;

    @FXML
    void Validation(ActionEvent event) {
        int chosen = 0;
        for(int i =0; i<this.listCheckbox.size();i++){
            if(this.listCheckbox.get(i).isSelected()){
                chosen = i+1;
                break;
            }
        }
        if(chosen != 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/GameView.fxml"));
                String css = this.getClass().getResource("/Style/GameStyle.css").toExternalForm();
                Parent root = fxmlLoader.load();
                GameController gameController = fxmlLoader.getController();
                gameController.init(this.nomFichier.get(chosen-1));
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
                stage.setTitle("Domaine :"+this.nomFichier.get(chosen-1));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File dir  = new File("Sauvegarde/");
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile())
            {
                Partie partie = Sauvegarde.charger(item.getName());
                HBox h = new HBox();
                h.setStyle("-fx-border-color:BLACK;");
                h.setPrefWidth(598);
                VBox v = new VBox();
                v.setPrefWidth(Region.USE_COMPUTED_SIZE);
                v.setPrefHeight(Region.USE_COMPUTED_SIZE);
                Text t1 = new Text("Partie : "+item.getName());
                t1.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t1);
                v.setMargin(t1, new Insets( 0, 0, 0, 20 ) );

                Text t3 = new Text("Nombre de tours : "+partie.getNbTour());
                t3.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t3);
                v.setMargin(t3, new Insets( 0, 0, 0, 30 ) );

                Text t4 = new Text("Nombre de joueurs : "+ partie.getNbJoueurs());
                t4.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t4);
                v.setMargin(t4, new Insets( 0, 0, 0, 30 ) );

                h.getChildren().add(v);

                CheckBox checkBox = new CheckBox();
                checkBox.setStyle("-fx-font-size: 20px");
                checkBox.setText("Choisir");
                h.getChildren().add(checkBox);
                h.setMargin(checkBox, new Insets( 50, 10, 0, 0 ) );
                checkBox.setPadding(new Insets( 0, 30, 0, 70 ));
                this.vBox.getChildren().add(h);

                this.nomFichier.add(item.getName());
                this.listCheckbox.add(checkBox);
            }
        }
    }
}
