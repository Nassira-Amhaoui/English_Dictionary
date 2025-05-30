package application;

import java.sql.SQLException;
import java.util.List;
import application.DictionnaireDAO.MotResult;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import javafx.animation.KeyValue;

import javafx.scene.paint.Color;



public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
      
        primaryStage.setTitle("Dictionnaire Français");

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        Scene Scene1 = new Scene(grid1, 800, 500);
        grid1.setStyle("-fx-background-color:  #D2B48C;"); // Marron dans cet exemple, vous pouvez changer la couleur selon vos préférences

        Button btn1 = new Button("Recherche");
        Button btn2 = new Button("Dictionnaire");
        Button btn3 = new Button("Sortie");
     // Set a fixed width for the buttons
        btn1.setMinWidth(150);  // Adjust the width as needed
        btn2.setMinWidth(150);  // Adjust the width as needed
        btn3.setMinWidth(150);  // Adjust the width as needed

        // Set styles for the buttons
        btn1.setStyle("-fx-background-color: #A52A2A; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        btn2.setStyle("-fx-background-color:#A52A2A; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        btn3.setStyle("-fx-background-color: #A52A2A; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        
     // Affichage de l'image pendant 2 secondes
        Image image = new Image("file:///C:/Users/user/Documents/Amhaoui/src/application/Cap.PNG"); // Remplacez "your_image_path.jpg" par le chemin de votre image
        ImageView imageView = new ImageView(image);
        grid1.add(imageView, 0, 0, 2, 1);

        // Création d'une temporisation pour afficher l'image pendant 2 secondes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {
                    // Supprimer l'image après 2 secondes et afficher l'interface utilisateur
                    grid1.getChildren().remove(imageView);
                    grid1.add(btn1, 0, 0, 2, 1);
                    grid1.add(btn2, 0, 1, 2, 1);
                    grid1.add(btn3, 0, 2, 2, 1);
                    
                })
        );
        timeline.play();

        
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setStyle("-fx-background-color: #D2B48C;"); // Marron dans cet exemple, vous pouvez changer la couleur selon vos préférences
        Scene Scene2 = new Scene(grid2, 800,500);
        
        Label label0 = new Label("Dictionnaire Englich");
        Label label01 = new Label("Entrer le mot:");
        TextField textField = new TextField();
        Button searchButton = new Button("Rechercher");
        Button bt2 = new Button("<<");
        bt2.setStyle("-fx-background-color:#A52A2A; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        searchButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        Label resultLabel1 = new Label();
        Label resultLabel2 = new Label();
        Label resultLabel3 = new Label();
        Label resultLabel4 = new Label();

        // Style du label "Dictionnaire Française"
        label0.setStyle("-fx-text-fill: Red; -fx-font-size: 20pt; -fx-font-weight: bold;");
        label01.setStyle("-fx-text-fill: blue; -fx-font-size: 14pt; -fx-font-weight: bold;");

        // Style du label "Définition :"
        resultLabel1.setStyle("-fx-text-fill: blue; -fx-font-size: 10pt; -fx-font-weight: bold;");
        resultLabel2.setStyle("-fx-text-fill: blue; -fx-font-size: 10pt; -fx-font-weight: bold;");
        resultLabel3.setStyle("  -fx-font-weight: bold;");
        resultLabel4.setStyle("  -fx-font-weight: bold;");

        // Ajout des éléments au grid
        grid2.add(bt2, 0, 0, 2, 1);
        grid2.add(label0, 1, 0, 2, 1);
        grid2.add(label01, 0, 1);
        grid2.add(textField, 1, 1, 2, 1);
        grid2.add(searchButton, 1, 2, 2, 1);
        grid2.add(resultLabel1, 0, 3, 2, 1);
        grid2.add(resultLabel3, 0, 4, 2, 1);
        grid2.add(resultLabel2, 0, 5, 2, 1);
        grid2.add(resultLabel4, 0, 6, 2, 1);
        
        // Gestionnaire d'événements pour le bouton de recherche
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                String motRecherche = textField.getText();
                MotResult motResult = DictionnaireDAO.rechercherMot(motRecherche);

                if (motResult != null) {
                    resultLabel1.setText("Type: ");
                    resultLabel3.setText(motResult.getType());
                    resultLabel2.setText("Définition: ");
                    resultLabel4.setText(motResult.getDefinition());

                } else {
                    resultLabel1.setText("Mot introuvable dans le dictionnaire.");
         
                }
            }
        });

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(Scene2);
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                List<MotResult> mots = DictionnaireDAO.afficherTousLesMots();

             // Créer une table et les colonnes
                TableView<MotResult> table = new TableView<>();
                TableColumn<MotResult, String> motCol = new TableColumn<>("Mot");
                TableColumn<MotResult, String> typeCol = new TableColumn<>("Type");
                TableColumn<MotResult, String> definitionCol = new TableColumn<>("Définition");

                // Associer les propriétés des objets MotResult aux colonnes
                motCol.setCellValueFactory(new PropertyValueFactory<>("mot"));
                typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                definitionCol.setCellValueFactory(new PropertyValueFactory<>("definition"));

                // Ajouter les colonnes à la table
                table.getColumns().addAll(motCol, typeCol, definitionCol);

             // Ajuster la largeur des colonnes pour qu'elles se développent de manière proportionnelle
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
             // Définir les tailles minimales et maximales des colonnes
                motCol.setMinWidth(50);
                motCol.setMaxWidth(150);

                typeCol.setMinWidth(100);
                typeCol.setMaxWidth(200);

                definitionCol.setMinWidth(350);
                definitionCol.setMaxWidth(800);
                
                // Ajouter les données à la table
                table.getItems().addAll(mots);

                // Créer un bouton supplémentaire
                Button Button = new Button("<<");
                Button.setStyle("-fx-background-color:#00FF00; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");

                // Ajouter une action au bouton supplémentaire (remplacez le code à l'intérieur de handle avec votre propre logique)
                Button.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent arg0) {
                        primaryStage.setScene(Scene1);
                    }
                });

                // Créer un conteneur pour la table, le bouton supplémentaire et les autres éléments
                VBox vbox = new VBox();
                vbox.getChildren().addAll(Button, table);

                // Permettre au VBox de prendre toute la hauteur disponible
                VBox.setVgrow(table, Priority.ALWAYS);

                // Créer une scène avec le conteneur
                Scene Scene3 = new Scene(vbox, 800, 500);

                // Changer à la nouvelle scène avec la table
                primaryStage.setScene(Scene3);
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                Platform.exit();
            }
        });
        bt2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
            	primaryStage.setScene(Scene1);
            }
        });

        primaryStage.setScene(Scene1);
        primaryStage.show();
    }  	
}


  
    

