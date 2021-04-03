package Controller;

import Model.*;
import Model.Action;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

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
    private boolean isCarteVendu;
    private boolean shutdown;

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
    private Text PointJ2;

    @FXML
    private Text PointJ4;

    @FXML
    private Text PointJ1;

    @FXML
    private Text PointJ3;

    @FXML
    private ImageView imgJ3;

    @FXML
    private ImageView imgJ4;

    @FXML
    void CarteVendu(MouseEvent event) {
        pasClickable(true,true,true,true,true,true);
        VBox LVbox = new VBox();
        LVbox.setAlignment(Pos.CENTER);
        LVbox.setStyle("-fx-background-color: WHITE");
        VBox v = new VBox();
        HBox h = new HBox();

        for(int i =0;i<this.partie.getCartesVendu().size();i++) {
            ImageView img = new ImageView();
            img.setFitHeight(100);
            img.setFitWidth(65);
            if(i==this.partie.getCartesVendu().size()-1){
                img.setDisable(this.isCarteVendu);
            }
            img.setImage(new Image("img/Carte_" + this.partie.getCartesVendu().get(i).getNom() + ".png"));
            int finalI = i;
            img.setOnMouseClicked(event2 -> {
                pasClickable(true,true,true,true,true,false);
                String l = this.LabelJoueur.getText().split(":")[1];
                this.partie.getJoueurbyname(l).getMain().add(this.partie.getCartesVendu().get(finalI));
                this.partie.getCartesVendu().remove(finalI);
                afficherBord(this.partie.getJoueurbyname(l));
                this.anchorPane.getChildren().remove(LVbox);
            });
            h.getChildren().add(img);


        }

        while (h.getChildren().size() !=0) {
            HBox hhBox = new HBox();
            if(h.getChildren().size() >=5) {
                for (int j = 0; j < 5; j++) {
                    hhBox.getChildren().add(h.getChildren().get(0));
                }
            }else {
                int nb = h.getChildren().size();
                for (int j = 0; j < nb; j++) {
                    hhBox.getChildren().add(h.getChildren().get(0));
                }
            }
            v.getChildren().add(hhBox);
        }

        Button buttonP = new Button();
        buttonP.setText("Retour");
        buttonP.setOnMousePressed((event3) -> {
            pasClickable(true,true,true,false,false,true);
            this.anchorPane.getChildren().remove(LVbox);
        });
        LVbox.getChildren().add(buttonP);
        LVbox.getChildren().add(v);

        LVbox.setLayoutX(this.Plateau.getWidth()/2 - LVbox.getWidth()/2);
        LVbox.setLayoutY(this.Plateau.getHeight()/2 - LVbox.getHeight()/2);
        this.anchorPane.getChildren().add(LVbox);
    }

    @FXML
    void Finir_Tour(ActionEvent event) {
        this.WaitTour = false;
        this.isCarteVendu = false;
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
        this.SlotSelect = 1;
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
    }

    @FXML
    void SlotCart2Clicked(MouseEvent event) {
        this.SlotSelect = 2;
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
    }

    @FXML
    void SlotCart3Clicked(MouseEvent event) {
        this.SlotSelect = 3;
        MessagePop("Voulez-vous jouer ou vendre la carte ?",true,2,"Jouer","Vendre");
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
        this.shutdown = false;
        initGame();
    }

    public void initGame(){
        this.action =false;
        initPlateau();

        if(this.partie.getNbJoueurs()==2) {
            this.imgJ3.setVisible(false);
            this.PointJ3.setVisible(false);
            this.imgJ4.setVisible(false);
            this.PointJ4.setVisible(false);
        }else if(this.partie.getNbJoueurs()==3) {
            this.imgJ4.setVisible(false);
            this.PointJ4.setVisible(false);
        }

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
        if (this.partie.getPioche().size() > 45) this.img_SlotPioche.setImage(new Image("img/Carte_Verso_A.png"));
        else if (this.partie.getPioche().size() > 30) this.img_SlotPioche.setImage(new Image("img/Carte_Verso_B.png"));
        else if (this.partie.getPioche().size() > 15) this.img_SlotPioche.setImage(new Image("img/Carte_Verso_C.png"));
        else this.img_SlotPioche.setImage(new Image("img/Carte_Verso_D.png"));
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
                    int x = GridPane.getRowIndex(img);
                    int y = GridPane.getColumnIndex(img);

                    this.CaseClicked.clear();
                    this.CaseClicked.add(x);
                    this.CaseClicked.add(y);
                    this.action = true;
                });
                this.Plateau.add(img,j,i);
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
                    this.Plateau.add(img, chateau.getY(), chateau.getX());
                }
            }
            for(PionChevalier chevalier : joueur.getChevaliers()){
                if(chevalier.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/PionChevalier" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chevalier.getY(), chevalier.getX());
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
                    this.Plateau.add(img, j, i);
                }
                if(p.getCase(i,j).isfOuest()){
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereOuest.png"));
                    this.Plateau.add(img, j, i);
                }
                if(p.getCase(i,j).isfNord()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereNord.png"));
                    this.Plateau.add(img, j, i);
                }
                if(p.getCase(i,j).isfSud()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/FrontiereSud.png"));
                    this.Plateau.add(img, j, i);
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
                        this.shutdown  =true;
                        Platform.exit();
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
            String l = this.LabelJoueur.getText().split(":")[1];
            Joueur j = this.partie.getJoueurbyname(l);
            if (action==2){
                if (j.getDucat() >= j.getMain().get(SlotSelect-1).getPrixAction()) {
                    h.getChildren().add(yes);
                }
                h.getChildren().add(no);
            }
            else {
                h.getChildren().add(yes);
                h.getChildren().add(no);
            }
            v.getChildren().add(h);
        }else{
            Button ok = new Button();
            ok.setText(bt1);
            ok.setOnMousePressed((event) -> {
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
            });
            v.getChildren().add(ok);
        }
        v.setLayoutX(this.Plateau.getWidth()/2 - v.getWidth()/2);
        v.setLayoutY(this.Plateau.getHeight()/2 - v.getHeight()/2);
        this.anchorPane.getChildren().add(v);
    }

    public void PremierTour(){
        pasClickable(true,true,true,true,true,true);
        int y =0;
        addNbTour();
        int nbpieces;
        if (this.partie.getJoueurs().size()<4){
            nbpieces = 4;
        } else nbpieces =3;
        for (int i = 0; i < nbpieces; i++){
            for( Joueur j : this.partie.getJoueurs()) {
                this.LabelJoueur.setText("Joueur :"+j.getNom());
                if (j instanceof IA) {
                    int xIA;
                    int yIA;
                    do {
                        xIA = ((IA) j).getRandomNumberBetween(0,11);
                        yIA = ((IA) j).getRandomNumberBetween(0,11);
                    } while (!(this.partie.pionValide(j.getChateaux().get(y), this.partie.getPlateau().getCase(xIA, yIA), j, true, i)));
                    ImageView img1 = new ImageView();
                    img1.setFitHeight(65);
                    img1.setFitWidth(65);
                    img1.setDisable(true);
                    img1.setImage(new Image("img/Pion_Chateau_" + j.getCouleur() + ".png"));
                    int finalY1 = y;
                    int finalXIA = xIA;
                    int finalYIA = yIA;
                    Platform.runLater(() -> placer(img1, j, j.getChateaux().get(finalY1), finalXIA, finalYIA));
                    int rand;
                    Case c = null;
                    do {
                        rand = ((IA) j).getRandomNumberBetween(1,4);
                        if (xIA == 0 && yIA == 0) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA, yIA + 1);
                                default -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                            }
                        }
                        else if (xIA == 0 && yIA == 11) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA, yIA - 1);
                                default -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                            }
                        }
                        else if (xIA == 11 && yIA == 0) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA, yIA + 1);
                                default -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                            }
                        }
                        else if (xIA == 11 && yIA == 11) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA, yIA - 1);
                                default -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                            }
                        }
                        else if (xIA == 0) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA, yIA + 1);
                                case 3 -> c = this.partie.getPlateau().getCase(xIA, yIA - 1);
                                default -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                            }
                        }
                        else if (yIA == 0) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                                case 3 -> c = this.partie.getPlateau().getCase(xIA, yIA + 1);
                                default -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                            }
                        }
                        else if (xIA == 11) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA, yIA + 1);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                                case 3 -> c = this.partie.getPlateau().getCase(xIA, yIA - 1);
                                default -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                            }
                        }
                        else if (yIA == 11) {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA - 1, yIA);
                                case 3 -> c = this.partie.getPlateau().getCase(xIA, yIA - 1);
                                default -> c = this.partie.getPlateau().getCase(xIA + 1, yIA);
                            }
                        }
                        else {
                            switch (rand) {
                                case 1 -> c = this.partie.getPlateau().getCase(xIA+1, yIA);
                                case 2 -> c = this.partie.getPlateau().getCase(xIA-1, yIA);
                                case 3 -> c = this.partie.getPlateau().getCase(xIA, yIA+1);
                                case 4 -> c = this.partie.getPlateau().getCase(xIA, yIA-1);
                            }
                        }
                    } while (!(this.partie.pionValide(j.getChevaliers().get(y), c, j, true, i)));
                    ImageView img2 = new ImageView();
                    img2.setFitHeight(65);
                    img2.setFitWidth(65);
                    img2.setDisable(true);
                    img2.setImage(new Image("img/PionChevalier" + j.getCouleur() + ".png"));
                    Case finalC = c;
                    Platform.runLater(() -> placer(img2, j, j.getChevaliers().get(finalY1), finalC.getX(), finalC.getY()));
                }
                else {
                    boolean WaitTour = true;
                    while(WaitTour){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.LabelInformation.setText("Placer votre château.");
                        if (this.action) {
                            if (this.partie.pionValide(j.getChateaux().get(y), this.partie.getPlateau().getCase(CaseClicked.get(0), CaseClicked.get(1)), j, true, i)) {
                                ImageView img = new ImageView();
                                img.setFitHeight(65);
                                img.setFitWidth(65);
                                img.setDisable(true);
                                img.setImage(new Image("img/Pion_Chateau_" + j.getCouleur() + ".png"));
                                int finalY = y;
                                Platform.runLater(() -> placer(img, j, j.getChateaux().get(finalY)));
                                WaitTour = false;
                            } else this.LabelInformation.setText("Case non Valide");
                            this.action = false;
                        }
                        if(this.shutdown == true){
                            return;
                        }
                    }
                    WaitTour = true;

                    while(WaitTour){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.LabelInformation.setText("Placer votre chevalier.");
                        if (this.action) {
                            if (this.partie.pionValide(j.getChevaliers().get(y), this.partie.getPlateau().getCase(CaseClicked.get(0), CaseClicked.get(1)), j, true, i)) {
                                ImageView img = new ImageView();
                                img.setFitHeight(65);
                                img.setFitWidth(65);
                                img.setDisable(true);
                                img.setImage(new Image("img/PionChevalier" + j.getCouleur() + ".png"));
                                int finalY = y;
                                Platform.runLater(() -> placer(img, j, j.getChevaliers().get(finalY)));
                                WaitTour = false;
                            } else this.LabelInformation.setText("Case non Valide");
                            this.action = false;
                        }
                        if(this.shutdown == true){
                            return;
                        }
                    }
                }
            }
            y++;
        }
        this.partie.creationDeck();
        this.partie.distribuerCarte();
    }

    public void placer(Node node, Joueur j,Pion p, int...coord) {
        int x;
        int y;
        if (j instanceof IA) {
            x = coord[0];
            y = coord[1];
        }
        else {
            x = this.CaseClicked.get(0);
            y = this.CaseClicked.get(1);
        }
        this.Plateau.add(node,y,x);
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
            if (j instanceof IA) {
                pasClickable(true,true,true,true,true,true);
                if (j.getDucat() <= 2) {
                    VendreCarte(j);
                }
                else {
                    boolean bool = false;
                    for (int a = 0; a < j.getMain().size(); a++) {
                        if (j.getMain().get(a).getPrixAction() < j.getDucat()) {
                            bool = true;
                            break;
                        }
                    }
                    if (!bool) {
                        VendreCarte(j);
                    }
                    else {
                        int rand = ((IA) j).getRandomNumberBetween(1,10);
                        if (rand == 10) {
                            VendreCarte(j);
                        }
                        else {
                            ArrayList<Integer> carte = ((IA) j).getCarteChevalierFrontiere();
                            if (carte.size() == 0) VendreCarte(j);
                            else JouerCarte(j);
                        }
                    }
                }
                j.piocher(this.partie.getPioche().get(0));
                this.partie.getPioche().remove(0);
                afficherBord(j);
            }
            else {
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
                    if(this.shutdown == true){
                        return;
                    }
                }
            }
        }
        this.partie.revenuMines();
        this.Dernierjoueur = 0;
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
        if (j instanceof IA) {
            System.out.println("vendre");
            int index = 0;
            int prixVente = 0;
            for (int i = 0; i < j.getMain().size(); i++) {
                if (j.getMain().get(i).getPrixVente() > prixVente) index = i;
            }
            Carte c = j.vendreCarte(index);
            this.partie.setCartesVendu(c);
        }
        else {
            this.isCarteVendu = true;
            Carte c = j.vendreCarte(this.SlotSelect-1);
            this.partie.setCartesVendu(c);
            afficherBord(j);
            this.LabelInformation.setText("Choisissez la pioche ou le marché.");
            pasClickable(true,true,true,false,false,true);
        }
    }

    public void JouerCarte(Joueur j){
        this.carteAction = false;
        if (j instanceof IA) {
            System.out.println("jouer");
            ArrayList<Integer> carte = ((IA) j).getCarteChevalierFrontiere();
            Action a = j.getMain().get(carte.get(0)).getActions().get(carte.get(1));
            while (a.getNombre() != 0) {
                if (j.getMain().get(carte.get(0)).getActions().get(carte.get(1)) instanceof AjoutChevalier) {
                    System.out.println("cheval");
                    int x1IA, y1IA;
                    do {
                        x1IA = ((IA) j).getRandomNumberBetween(0, 11);
                        y1IA = ((IA) j).getRandomNumberBetween(0, 11);
                    } while (!(this.partie.pionValide(j.getChevaliers().get(j.getProchainchevalier()), this.partie.getPlateau().getCase(x1IA, y1IA), j, false)));
                    j.jouerCarte(carte.get(0), carte.get(1), this.partie.getPlateau(), Optional.empty(), x1IA, y1IA);
                } else {
                    System.out.println("frontiere");
                    int x2IA, y2IA;
                    Case c = null;
                    do {
                        x2IA = ((IA) j).getRandomNumberBetween(0, 11);
                        y2IA = ((IA) j).getRandomNumberBetween(0, 11);
                        int choix = ((IA) j).getRandomNumberBetween(1, 4);
                        if (x2IA == 0 && y2IA == 0) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                            }
                        }
                        else if (x2IA == 0 && y2IA == 11) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                            }
                        }
                        else if (x2IA == 11 && y2IA == 0) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                            }
                        }
                        else if (x2IA == 11 && y2IA == 11) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                            }
                        }
                        else if (x2IA == 0) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                case 3 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                            }
                        }
                        else if (y2IA == 0) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 3 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                            }
                        }
                        else if (x2IA == 11) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 3 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                            }
                        }
                        else if (y2IA == 11) {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 3 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                                default -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                            }
                        }
                        else {
                            switch (choix) {
                                case 1 -> c = this.partie.getPlateau().getCase(x2IA + 1, y2IA);
                                case 2 -> c = this.partie.getPlateau().getCase(x2IA - 1, y2IA);
                                case 3 -> c = this.partie.getPlateau().getCase(x2IA, y2IA + 1);
                                case 4 -> c = this.partie.getPlateau().getCase(x2IA, y2IA - 1);
                            }
                        }
                    } while (!(this.partie.frontiereValide(this.partie.getPlateau().getCase(x2IA, y2IA), c)));
                    j.jouerCarte(carte.get(0), carte.get(1), this.partie.getPlateau(), Optional.empty(), x2IA, y2IA, c.getX(), c.getY());
                }
            }
            this.partie.refreshDomaine();
            setPoint();
;       }
        else {
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
            v.setLayoutX(this.Plateau.getWidth()/2 - v.getWidth()/2);
            v.setLayoutY(this.Plateau.getHeight()/2 - v.getHeight()/2);

            Platform.runLater(() -> this.anchorPane.getChildren().add(v));
            while (!this.carteAction){
                if(this.shutdown == true){
                    return;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean WaitTour = true;
            this.action = false;
            while(WaitTour) {
                if(this.shutdown == true){
                    return;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Action a = j.getMain().get(this.SlotSelect - 1).getActions().get(this.carteActionChoix);
                while (a.getNombre() != 0) {
                    if(this.shutdown == true){
                        return;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    this.LabelInformation.setText("selectionner une case valide");
                    if (this.action) {
                        int Case_1_X = this.CaseClicked.get(0);
                        int Case_1_Y = this.CaseClicked.get(1);
                        if (a instanceof AjoutFrontiere || a instanceof Transfuge || a instanceof Alliance) {
                            this.action = false;
                            boolean WaitTour2 = true;
                            while (WaitTour2) {
                                if(this.shutdown == true){
                                    return;
                                }
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                this.LabelInformation.setText("selectionner une case valide supplementaire");
                                if (this.action) {
                                    int Case_2_X = this.CaseClicked.get(0);
                                    int Case_2_Y = this.CaseClicked.get(1);
                                    Domaine d1 = this.partie.caseOnDomaine(this.partie.getPlateau().getCase(Case_1_X, Case_1_Y));
                                    Domaine d2 = this.partie.caseOnDomaine(this.partie.getPlateau().getCase(Case_2_X, Case_2_Y));
                                    Pion p = this.partie.pionOncase(this.partie.getPlateau().getCase(Case_1_X, Case_1_Y));
                                    if (a instanceof AjoutFrontiere
                                            && this.partie.frontiereValide(this.partie.getPlateau().getCase(Case_1_X, Case_1_Y),this.partie.getPlateau().getCase(Case_2_X,Case_2_Y))) {
                                        j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix, partie.getPlateau(), Optional.empty(),Case_1_X, Case_1_Y, Case_2_X, Case_2_Y);//AddFrontiere
                                    }
                                    else if (a instanceof Transfuge
                                            && (d1 != null && d2 != null)
                                            && this.partie.domaineAdjacent(d1,d2)
                                            && !this.partie.isInAlliance(d1,d2)
                                            && p!=null
                                            && this.partie.pionValide(j.getChevaliers().get(j.getProchainchevalier()),this.partie.getPlateau().getCase(Case_2_X, Case_2_Y),j,false)) {
                                        j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix,partie.getPlateau(), Optional.of(p), Case_2_X, Case_2_Y);//Transfuge
                                    }
                                    else if (a instanceof Alliance
                                            && (d1 != null && d2 != null)
                                            && this.partie.domaineAdjacent(d1,d2)){
                                        this.partie.addAlliance(d1,d2);
                                        j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix,partie.getPlateau(), Optional.empty(), Case_2_X, Case_2_Y);
                                    }
                                    else {
                                        this.LabelInformation.setText("Cases non Valides");
                                    }
                                    Platform.runLater(this::RefreshPlateau);
                                    WaitTour2 = false;
                                }
                            }
                        } else if (a instanceof AjoutChevalier) {
                            if (this.partie.pionValide(j.getChevaliers().get(j.getProchainchevalier()),this.partie.getPlateau().getCase(CaseClicked.get(0), CaseClicked.get(1)),j,false))
                                j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix, partie.getPlateau(),Optional.empty(),Case_1_X, Case_1_Y);//Addchevalier
                        } else if (a instanceof ExtensionDomaine) {
                            j.jouerCarte(this.SlotSelect - 1, this.carteActionChoix, partie.getPlateau(),Optional.empty(),Case_1_X, Case_1_Y); //extension
                        } else this.LabelInformation.setText("Case non Valide");
                    }
                    this.partie.refreshDomaine();
                    setPoint();
                    this.action = false;
                    WaitTour = false;
                }
            }
        }
        Platform.runLater(this::RefreshPlateau);
        afficherBord(j);
        this.LabelInformation.setText("Choisissez la pioche ou le marché.");
        pasClickable(true,true,true,false,false,true);
    }

    public void RefreshPlateau(){
        Model.Plateau p = this.partie.getPlateau();
        ArrayList<Joueur> listjoueurs = this.partie.getJoueurs();

        this.Plateau.getChildren().clear();
        //init du plateau de base
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
                    int x = GridPane.getRowIndex(img);
                    int y = GridPane.getColumnIndex(img);

                    this.CaseClicked.clear();
                    this.CaseClicked.add(x);
                    this.CaseClicked.add(y);
                    this.action = true;
                });
                this.Plateau.add(img,j,i);

                if(p.getCase(i,j).isfEst()){
                    ImageView img2 = new ImageView();
                    img2.setFitHeight(65);
                    img2.setFitWidth(65);
                    img2.setDisable(true);
                    img2.setImage(new Image("img/FrontiereEst.png"));
                    this.Plateau.add(img2, j, i);
                }
                if(p.getCase(i,j).isfOuest()){
                    ImageView img2 = new ImageView();
                    img2.setFitHeight(65);
                    img2.setFitWidth(65);
                    img2.setDisable(true);
                    img2.setImage(new Image("img/FrontiereOuest.png"));
                    this.Plateau.add(img2, j, i);
                }
                if(p.getCase(i,j).isfNord()) {
                    ImageView img2 = new ImageView();
                    img2.setFitHeight(65);
                    img2.setFitWidth(65);
                    img2.setDisable(true);
                    img2.setImage(new Image("img/FrontiereNord.png"));
                    this.Plateau.add(img2, j, i);
                }
                if(p.getCase(i,j).isfSud()) {
                    ImageView img2 = new ImageView();
                    img2.setFitHeight(65);
                    img2.setFitWidth(65);
                    img2.setDisable(true);
                    img2.setImage(new Image("img/FrontiereSud.png"));
                    this.Plateau.add(img2, j, i);
                }
            }
        }

        for(Joueur joueur : listjoueurs) {
            for (PionChateau chateau : joueur.getChateaux()) {
                if (chateau.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/Pion_Chateau_" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chateau.getY(), chateau.getX());
                }
            }
            for (PionChevalier chevalier : joueur.getChevaliers()) {
                if (chevalier.estPlace()) {
                    ImageView img = new ImageView();
                    img.setFitHeight(65);
                    img.setFitWidth(65);
                    img.setDisable(true);
                    img.setImage(new Image("img/PionChevalier" + joueur.getCouleur() + ".png"));
                    this.Plateau.add(img, chevalier.getY(), chevalier.getX());
                }
            }
        }
    }

    public void setPoint(){
        if(this.partie.getNbJoueurs() == 2){
            this.PointJ1.setText(String.valueOf(this.partie.getJoueurs().get(0).getPoint()));
            this.PointJ2.setText(String.valueOf(this.partie.getJoueurs().get(1).getPoint()));
        }
        if(this.partie.getNbJoueurs() == 3){
            this.PointJ1.setText(String.valueOf(this.partie.getJoueurs().get(0).getPoint()));
            this.PointJ2.setText(String.valueOf(this.partie.getJoueurs().get(1).getPoint()));
            this.PointJ3.setText(String.valueOf(this.partie.getJoueurs().get(2).getPoint()));
        }
        if(this.partie.getNbJoueurs() == 4){
            this.PointJ1.setText(String.valueOf(this.partie.getJoueurs().get(0).getPoint()));
            this.PointJ2.setText(String.valueOf(this.partie.getJoueurs().get(1).getPoint()));
            this.PointJ3.setText(String.valueOf(this.partie.getJoueurs().get(2).getPoint()));
            this.PointJ4.setText(String.valueOf(this.partie.getJoueurs().get(3).getPoint()));
        }
    }

    public void Jeu(){
        if(!this.partie.getJoueurs().get(0).getChateaux().get(0).estPlace()){
            setLabelNbTour();
            new Thread(() -> {
                PremierTour();
                while (!this.partie.FinPartie()){
                    if(this.shutdown == true){
                        return;
                    }
                    Tour();
                }
                Platform.runLater(() -> MessagePop("Le Gagnant est : "+this.partie.getGagnant().getNom(),false,0,"Terminer",""));

            }).start();
        }else {
            setLabelNbTour();
            new Thread(() -> {
                while (!this.partie.FinPartie()){
                    if(this.shutdown == true){
                        return;
                    }
                    Tour();
                }
                Platform.runLater(() -> MessagePop("Le Gagnant est : "+this.partie.getGagnant().getNom(),false,0,"Terminer",""));
            }).start();
        }
    }
}
