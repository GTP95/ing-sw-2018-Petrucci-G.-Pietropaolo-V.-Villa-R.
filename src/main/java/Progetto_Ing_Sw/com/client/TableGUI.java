package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

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
        GridPane griglia = new GridPane();griglia.setTranslateY(-20);
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
            jelement = new JsonParser().parse(new FileReader("Resources/Cards/GameBoardCards/SymphonyOfLight.json"));
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
                            block.setStyle("-fx-background-color: #a800a8;");
                            break;
                        case (4):
                            block.setStyle("-fx-background-color: Yellow;");
                            break;
                        case (5):
                            block.setStyle("-fx-background-color: #009d1d;");
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


            //Stampa due interi che indicano su che casella sto cliccando
            griglia.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getX() < 71 && e.getX() > 4) {
                    final int Xindex = 1;

                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 146 && e.getX() > 79) {
                    final int Xindex = 2;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 221 && e.getX() > 154) {
                    final int Xindex = 3;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 296 && e.getX() > 229) {
                    final int Xindex = 4;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 371 && e.getX() > 304) {
                    final int Xindex = 5;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getY() > 23 && e.getY() < 91) {
                    final int Yindex = 1;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 98 && e.getY() < 166) {
                    final int Yindex = 2;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 173 && e.getY() < 241) {
                    final int Yindex = 3;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 248 && e.getY() < 316) {
                    final int Yindex = 4;
                    System.out.println("Riga: " + Yindex);
                }



            });


            //HBox che contiene le informazioni sulla carta
            HBox WindowInfo= new HBox(60);WindowInfo.setId("WindowInfo");WindowInfo.setMaxHeight(45);
            Text VictoryPoints = new Text("VP: 10");VictoryPoints.setFill(Paint.valueOf("white"));
            Text WindowTitle = new Text("Via Lux");WindowTitle.setFill(Paint.valueOf("white"));//TODO WindowTitle.setText da file
            Text DifficultyTokens = new Text("Tokens: 4");DifficultyTokens.setFill(Paint.valueOf("white"));//TODO Difficulty.setText da file
            WindowInfo.setTranslateY(150);WindowInfo.setAlignment(Pos.CENTER);
            WindowInfo.getChildren().addAll(VictoryPoints,WindowTitle,DifficultyTokens);

            //StackPane che fa da cornice alla griglia
            StackPane WindowBoard = new StackPane();WindowBoard.setId("WindowBoard");WindowBoard.setMaxSize(400,360);
            WindowBoard.getChildren().addAll(griglia,WindowInfo);


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
            ToolCard1.setOnAction(event -> {
                ToolCard1.setDisable(true);
                ToolCardDisplayer ToolCard1Stage = new ToolCardDisplayer();
                ToolCard1Stage.showAndWait();
                ToolCard1.setDisable(false);
                    });


            Button ToolCard2 = new Button("2. Tool Card");
            ToolCard2.setId("ToolCardBTN");
            ToolCard2.setOnAction(event -> {
                ToolCard2.setDisable(true);
                ToolCardDisplayer ToolCard2Stage = new ToolCardDisplayer();
                ToolCard2Stage.showAndWait();//ToolCard2.setDisable(false);
            });


            Button ToolCard3 = new Button("3. Tool Card");
            ToolCard3.setId("ToolCardBTN");
            ToolCard3.setOnAction(event -> {
                ToolCard3.setDisable(true);
                ToolCardDisplayer ToolCard3Stage = new ToolCardDisplayer();
                ToolCard3Stage.showAndWait();//ToolCard3.setDisable(false);
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
            ToolCardMenu.setTranslateY(430);
            ToolCardMenu.getChildren().addAll(ToolCardMenuTitle, ToolCardList);
            //FINE MENU TOOL CARD

            //INIZIO  PUBLIC OBJECTIVE MENU

            //Contenuto del menu Public Objetives
            Label PublicObjectiveCardMenuTitle = new Label("      Public Objectives");
            PublicObjectiveCardMenuTitle.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font: 25 \"Centaur\";");

            //Bottoni che riferiscono alle Public Objective Cards
            Button PublicObjectiveCard1 = new Button("Public Objective 1");
            PublicObjectiveCard1.setId("ToolCardBTN");
            PublicObjectiveCard1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                }
            });


            Button PublicObjectiveCard2 = new Button("Public Objective 2");
            PublicObjectiveCard2.setId("ToolCardBTN");
            PublicObjectiveCard2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new ToolCardDisplayer();
                    //ToolCard1.setDisable(true);

                }
            });


            Button PublicObjectiveCard3 = new Button("Public Objective 3");
            PublicObjectiveCard3.setId("ToolCardBTN");
            PublicObjectiveCard3.setOnAction(event -> {
                PublicObjectiveCard3.setDisable(true);
                PublicObjectiveCardDisplayer PublicDisplay3 = new PublicObjectiveCardDisplayer();
                PublicDisplay3.showAndWait();
                PublicObjectiveCard3.setDisable(false);
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


            //INIZIO Draft Area

        HBox DraftPool = new HBox(40);
        DraftPool.setId("DraftPool");
        DraftPool.setPadding(new Insets(4,4,4,4));
        DraftPool.setAlignment(Pos.CENTER);
        DraftPool.setMaxSize(5000,300);



        for (int i = 0; i < NumPlayers*2+1; i++){
            ToggleButton Die = new ToggleButton();Die.setPrefSize(50,50);Die.setMaxSize(50,50);Die.setId("Die");
            DraftPool.getChildren().addAll(Die);
        }


            //FINE Draft Area


            //BorderPane per contenere tutti gli altri
            BorderPane GameplayArea = new BorderPane();
            GameplayArea.setId("GamemodeSelectionScreen");
            GameplayArea.setBottom(OtherPlayerBox);
            GameplayArea.setCenter(WindowBoard);
            GameplayArea.setRight(ToolCardMenu);
            GameplayArea.setLeft(PublicObjectiveMenu);
            GameplayArea.setTop(DraftPool);


            GameplayScene = new Scene(GameplayArea, 1280, 720);
            GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

            //FINE Gameplay Scene


            this.setScene(GameplayScene);
            this.show();
        }


}
