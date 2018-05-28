package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class TableGUI extends Stage{
    Scene GameplayScene;

    TableGUI() {
        this.setTitle("Sagrada Game");
        this.setWidth(1280);
        this.setHeight(720);
        this.setResizable(false);


        //INIZIO Gameplay Scene
        int rows = 4;
        int columns = 5;
        int NumPlayers = 4; //TODO mettere il get number of players

        //GridPane per la griglia 5x4
        GridPane griglia = new GridPane();
        griglia.setAlignment(Pos.CENTER);
        griglia.setId("TheGrid");
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            griglia.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            griglia.getRowConstraints().add(row);
        }

        //Creazione carta da Json - TEST

        JsonElement jelement = null;
        try {
            jelement = new JsonParser().parse(new FileReader("Resources/Cards/GameBoardCards/KaleidoscopicDream.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray matrixTexture = jobject.getAsJsonArray("matrixScheme");
        int[][] matrix = new int[rows][columns];

        for (int r = 0; r < matrixTexture.size(); r++) {
            JsonArray row = matrixTexture.get(r).getAsJsonArray();
            for (int c = 0; c < row.size(); c++) {
                matrix[r][c] = row.get(c).getAsInt();
                    Pane block = new Pane();
                    block.setId("Block");
                    switch (matrix[r][c]) {
                        case (0):
                            break;
                        case (1):
                            block.setStyle("-fx-background-color: red;");
                            break;
                        case (2):
                            block.setStyle("-fx-background-color: #46ddff;");
                            break;
                        case (3):
                            block.setStyle("-fx-background-color: Purple;");
                            break;
                        case (4):
                            block.setStyle("-fx-background-color: Yellow;");
                            break;
                        case (5):
                            block.setStyle("-fx-background-color: #00d700;");
                            break;
                        case (6):
                            block.setId("Shade1");
                            break;
                        case (7):
                            block.setId("Shade2");
                            break;
                        case (8):
                            block.setId("Shade3");
                            break;
                        case (9):
                            block.setId("Shade4");
                            break;
                        case (10):
                            block.setId("Shade5");
                            break;
                        case (11):
                            block.setId("Shade6");
                            break;
                    }
                    griglia.add(block, c, r);

                }
            }


            //HBox Tabs per gli altri giocatori
            HBox OtherPlayerBox = new HBox(80);
            OtherPlayerBox.setAlignment(Pos.CENTER);
            OtherPlayerBox.setTranslateY(15);

            //Bottoni altri giocatori
            for (int i = 0; i < (NumPlayers - 1); i++) {
                Button OtherPlayer = new Button();
                OtherPlayer.setId("DefaultButton");
                OtherPlayerBox.getChildren().addAll(OtherPlayer);
            }

            //INIZIO MENU TOOL CARD

            //Contenuto del menu ToolCard
            Label ToolCardMenuTitle = new Label("           Tool Cards");
            ToolCardMenuTitle.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font: 25 \"Centaur\";");

            //Bottoni che riferiscono alle Tool Cards
            Button ToolCard1 = new Button("9. Cork-backed \r\n Straightedge");
            ToolCard1.setId("ToolCardBTN");
            ToolCard1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Button ToolCard2 = new Button("2. Tool Card");
            ToolCard2.setId("ToolCardBTN");
            ToolCard2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Button ToolCard3 = new Button("3. Tool Card");
            ToolCard3.setId("ToolCardBTN");
            ToolCard3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Label emptyspace = new Label();
            emptyspace.setId("ToolCardBTN");

            //VBox per contenere le carte
            VBox ToolCardList = new VBox(20);
            ToolCardList.setAlignment(Pos.BOTTOM_LEFT);
            ToolCardList.setMaxHeight(280);
            ToolCardList.setPrefWidth(240);
            ToolCardList.setTranslateY(-100);
            ToolCardList.getChildren().addAll(ToolCard1, ToolCard2, ToolCard3);

            //VBox del Menu Tool Card
            VBox ToolCardMenu = new VBox(120);
            ToolCardMenu.setId("CardMenu");
            ToolCardMenu.setAlignment(Pos.BOTTOM_LEFT);
            ToolCardMenu.setMaxHeight(280);
            ToolCardMenu.setPrefWidth(240);
            ToolCardMenu.setTranslateY(450);
            ToolCardMenu.getChildren().addAll(ToolCardMenuTitle, ToolCardList);
            //FINE MENU TOOL CARD

            //INIZIO  PUBLIC OBJECTIVE MENU

            //Contenuto del menu Public Objetives
            Label PublicObjectiveCardMenuTitle = new Label("      Public Objectives");
            PublicObjectiveCardMenuTitle.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font: 25 \"Centaur\";");

            //Bottoni che riferiscono alle Public Objective Cards
            Button PublicObjectiveCard1 = new Button("Public Objective 1");
            PublicObjectiveCard1.setId("ToolCardBTN");
            ToolCard1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Button PublicObjectiveCard2 = new Button("Public Objective 2");
            PublicObjectiveCard2.setId("ToolCardBTN");
            ToolCard2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Button PublicObjectiveCard3 = new Button("Public Objective 3");
            PublicObjectiveCard3.setId("ToolCardBTN");
            ToolCard3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Label emptyspace2 = new Label();
            emptyspace2.setId("ToolCardBTN");

            //VBox per contenere le carte
            VBox PublicObjectiveCardList = new VBox(20);
            PublicObjectiveCardList.setAlignment(Pos.BOTTOM_LEFT);
            PublicObjectiveCardList.setMaxHeight(280);
            PublicObjectiveCardList.setPrefWidth(240);
            PublicObjectiveCardList.setTranslateY(-100);
            PublicObjectiveCardList.getChildren().addAll(PublicObjectiveCard1, PublicObjectiveCard2, PublicObjectiveCard3);

            //VBox del Menu Public Objective Card
            VBox PublicObjectiveMenu = new VBox(120);
            PublicObjectiveMenu.setId("CardMenu");
            PublicObjectiveMenu.setAlignment(Pos.BOTTOM_LEFT);
            PublicObjectiveMenu.setMaxHeight(280);
            PublicObjectiveMenu.setPrefWidth(240);
            PublicObjectiveMenu.setTranslateY(450);
            PublicObjectiveMenu.getChildren().addAll(PublicObjectiveCardMenuTitle, PublicObjectiveCardList);

            //FINE PUBLIC OBJECTIVE MENU


            //BorderPane per contenere tutti gli altri
            BorderPane GameplayArea = new BorderPane();
            GameplayArea.setId("GamemodeSelectionScreen");
            GameplayArea.setBottom(OtherPlayerBox);
            GameplayArea.setCenter(griglia);
            GameplayArea.setRight(ToolCardMenu);
            GameplayArea.setLeft(PublicObjectiveMenu);


            GameplayScene = new Scene(GameplayArea, 1280, 720);
            GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

            //FINE Gameplay Scene


            this.setScene(GameplayScene);
            this.show();
        }


}
