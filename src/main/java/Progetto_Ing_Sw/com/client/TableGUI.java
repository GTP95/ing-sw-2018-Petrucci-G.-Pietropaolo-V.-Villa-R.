package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class TableGUI extends Stage{
    Scene GameplayScene;

    TableGUI() {
        this.setTitle("Sagrada Game");
        this.setWidth(1280);
        this.setHeight(720);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);


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
            Label ToolCardMenuTitle = new Label("            Tool Cards");
            ToolCardMenuTitle.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: black;" + "-fx-font: 25 \"Centaur\";");

            Label ToolCard1Label= new Label();ToolCard1Label.setMinSize(268,131);ToolCard1Label.setId("ToolCardLabel");
            Label ToolCard2Label= new Label();ToolCard2Label.setMinSize(268,131);ToolCard2Label.setId("ToolCardLabel");
            Label ToolCard3Label= new Label();ToolCard3Label.setMinSize(268,131);ToolCard3Label.setId("ToolCardLabel");

            //Bottoni che riferiscono alle Tool Cards
            Button ToolCard1BTN = new Button("Tool Card1");ToolCard1BTN.setTranslateY(-40);ToolCard1BTN.setTranslateX(10);
            ToolCard1BTN.setId("ToolCardBTN");
            ToolCard1BTN.setOnAction(event -> {
                ToolCard1BTN.setDisable(true);
                ToolCardDisplayer ToolCard1Stage = new ToolCardDisplayer();
                ToolCard1Stage.showAndWait();
                ToolCard1BTN.setDisable(false);
                    });


            Button ToolCard2BTN = new Button("Tool Card2");ToolCard2BTN.setTranslateY(-40);ToolCard2BTN.setTranslateX(10);
            ToolCard2BTN.setId("ToolCardBTN");
            ToolCard2BTN.setOnAction(event -> {
                ToolCard2BTN.setDisable(true);
                ToolCardDisplayer ToolCard2Stage = new ToolCardDisplayer();
                ToolCard2Stage.showAndWait();
                ToolCard2BTN.setDisable(false);
            });


            Button ToolCard3BTN = new Button("Tool Card3");ToolCard3BTN.setTranslateY(-40);ToolCard3BTN.setTranslateX(10);
            ToolCard3BTN.setId("ToolCardBTN");
            ToolCard3BTN.setOnAction(event -> {
                ToolCard3BTN.setDisable(true);
                ToolCardDisplayer ToolCard3Stage = new ToolCardDisplayer();
                ToolCard3Stage.showAndWait();
                ToolCard3BTN.setDisable(false);
            });

            //StackPane Bottoni su Label
            StackPane ToolCard1 = new StackPane();
            ToolCard1.getChildren().addAll(ToolCard1Label,ToolCard1BTN);
            ToolCard1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover1 = new TranslateTransition(Duration.millis(500), ToolCard1);
                Hover1.setFromY(0);
                Hover1.setToY(-10);
                Hover1.setAutoReverse(true);
                Hover1.play();
            });

            ToolCard1.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard1);
                Hover2.setFromY(-10);
                Hover2.setToY(0);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane ToolCard2 = new StackPane();ToolCard2.setTranslateY(75);
            ToolCard2.getChildren().addAll(ToolCard2Label,ToolCard2BTN);
            ToolCard2.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard2);
                Hover2.setFromY(75);
                Hover2.setToY(65);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            ToolCard2.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard2);
                Hover2.setFromY(65);
                Hover2.setToY(75);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane ToolCard3 = new StackPane();ToolCard3.setTranslateY(150);
            ToolCard3.getChildren().addAll(ToolCard3Label,ToolCard3BTN);
            ToolCard3.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard3);
                Hover2.setFromY(150);
                Hover2.setToY(140);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            ToolCard3.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard3);
                Hover2.setFromY(140);
                Hover2.setToY(150);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });


            //StackPane per contenere le carte
            StackPane ToolCardList = new StackPane();
            ToolCardList.setAlignment(Pos.BOTTOM_LEFT);
            ToolCardList.setMaxHeight(280);
            ToolCardList.setPrefWidth(240);
            ToolCardList.setTranslateY(-100);
            ToolCardList.getChildren().addAll(ToolCard1, ToolCard2, ToolCard3);

            //VBox del Menu Tool Card
            VBox ToolCardMenu = new VBox(100);
            ToolCardMenu.setId("ToolCardMenu");
            ToolCardMenu.setMaxHeight(280);
            ToolCardMenu.setMaxWidth(240);
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
                    //ToolCard1BTN.setDisable(true);

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
            PublicObjectiveMenu.setMaxHeight(280);
            PublicObjectiveMenu.setMaxWidth(240);
            PublicObjectiveMenu.getChildren().addAll(PublicObjectiveCardMenuTitle, PublicObjectiveCardList);

            //FINE PUBLIC OBJECTIVE MENU


        //INIZIO Draft Area

        FlowPane DraftPool = new FlowPane();
        DraftPool.setId("DraftPool");
        DraftPool.setHgap(5);
        DraftPool.setVgap(5);
        DraftPool.setPadding(new Insets(4,4,4,4));
        DraftPool.setAlignment(Pos.CENTER);
        DraftPool.setMaxSize(200,200);



        for (int i = 0; i < NumPlayers*2+1; i++){
            ToggleButton Die = new ToggleButton();Die.setPrefSize(50,50);Die.setMaxSize(50,50);Die.setId("Die");
            DraftPool.getChildren().addAll(Die);
        }


        //FINE Draft Area

        //INIZIO Round Track

        Button RoundTrack = new Button();RoundTrack.setId("Round4");RoundTrack.setMinSize(150,150);RoundTrack.setTranslateY(10);RoundTrack.setTranslateX(-10);
        RoundTrack.setOnAction(event -> RoundTrack.setId("Round5"));
        //TODO Observer del round

        //FINE Round Track


            //BorderPane per contenere tutti gli altri
            StackPane GameplayArea = new StackPane();
            GameplayArea.setId("GamemodeSelectionScreen");
            GameplayArea.setAlignment(WindowBoard,Pos.CENTER);
            GameplayArea.setAlignment(PublicObjectiveMenu,Pos.BOTTOM_LEFT);
            GameplayArea.setAlignment(ToolCardMenu,Pos.BOTTOM_RIGHT);
            GameplayArea.setAlignment(DraftPool,Pos.TOP_LEFT);
            GameplayArea.setAlignment(RoundTrack,Pos.TOP_RIGHT);
            GameplayArea.getChildren().addAll(WindowBoard,PublicObjectiveMenu,ToolCardMenu,DraftPool,RoundTrack);



            GameplayScene = new Scene(GameplayArea, 1280, 720);
            GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

            //FINE Gameplay Scene


            this.setScene(GameplayScene);
            this.show();
        }

        public void updateToolCards(){
            Platform.runLater(()->{

            });
        }


}
