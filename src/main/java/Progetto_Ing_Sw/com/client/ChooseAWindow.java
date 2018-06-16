package Progetto_Ing_Sw.com.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class ChooseAWindow extends Stage {
    Scene Window,PrivateObjective;
    int difficulty1, difficulty2, difficulty3, difficulty4;
    Label PrivateObjectiveColor;
    String  name1,name2,name3,name4,PrivateObjectiveInfoText;

    public GridPane CreateAGrid (String GridPath){
        int rows = 4;
        int columns = 5;

        GridPane Board = new GridPane();
        Board.setTranslateY(-20);
        Board.setAlignment(Pos.CENTER);
        Board.setId("TheGrid");
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            Board.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            Board.getRowConstraints().add(row);
        }

        JsonElement jelement = null;
        try {
            jelement = new JsonParser().parse(new FileReader(GridPath));
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
                Board.add(block, c, r);

            }
        }
        return Board;
    }

    ChooseAWindow(){
        this.setTitle("Choose a Window");
        //this.setMaxHeight(500);
        //this.setMaxWidth(500);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.alwaysOnTopProperty();

        LocalModel.getInstance().registerAsObserver(this);

        //SCENA WINDOW
        //Bottoni che rappresentano le finestre da scegliere
        Button Window1BTN = new Button();Window1BTN.setPrefSize(386,313);Window1BTN.setTranslateY(-20);Window1BTN.setId("transparentBTN");
        Button Window2BTN = new Button();Window2BTN.setPrefSize(386,313);Window2BTN.setTranslateY(-20);Window2BTN.setId("transparentBTN");
        Button Window3BTN = new Button();Window3BTN.setPrefSize(386,313);Window3BTN.setTranslateY(-20);Window3BTN.setId("transparentBTN");
        Button Window4BTN = new Button();Window4BTN.setPrefSize(386,313);Window4BTN.setTranslateY(-20);Window4BTN.setId("transparentBTN");

        //Label che indicano nome e difficoltà della scheda in questione
        Label info1 = new Label("Name: "+name1+"\n\r"+"Difficulty: "+String.join("", Collections.nCopies(difficulty1, "•")));info1.setId("WindowInfo");info1.setTranslateY(250);
        Label info2 = new Label("Name: "+name2+"\n\r"+"Difficulty: "+String.join("", Collections.nCopies(difficulty2, "•")));info2.setId("WindowInfo");info2.setTranslateY(250);
        Label info3 = new Label("Name: "+name3+"\n\r"+"Difficulty: "+String.join("", Collections.nCopies(difficulty3, "•")));info3.setId("WindowInfo");info3.setTranslateY(250);
        Label info4 = new Label("Name: "+name4+"\n\r"+"Difficulty: "+String.join("", Collections.nCopies(difficulty4, "•")));info4.setId("WindowInfo");info4.setTranslateY(250);

        StackPane Board1 = new StackPane();Board1.setTranslateX(1000);
        Board1.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/SymphonyOfLight.json"),info1,Window1BTN);

        StackPane Board2 = new StackPane();Board2.setTranslateX(1000);
        Board2.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/ViaLux.json"),info2,Window2BTN);

        StackPane Board3 = new StackPane();Board3.setTranslateX(1000);
        Board3.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/Industria.json"),info3,Window3BTN);

        StackPane Board4 = new StackPane();Board4.setTranslateX(1000);
        Board4.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/ShadowThief.json"),info4,Window4BTN);



        //Animazioni Board1
        TranslateTransition Enter1 = new TranslateTransition(Duration.millis(500), Board1);
        Enter1.setFromX(1000);
        Enter1.setToX(0);
        Enter1.setAutoReverse(false);
        Enter1.play();

        TranslateTransition Exit1 = new TranslateTransition(Duration.millis(500), Board1);
        Exit1.setFromX(0);
        Exit1.setToX(-1000);
        Exit1.setAutoReverse(false);

        //Animazioni Board2
        TranslateTransition Enter2 = new TranslateTransition(Duration.millis(500), Board2);
        Enter2.setFromX(1000);
        Enter2.setToX(0);
        Enter2.setAutoReverse(false);

        TranslateTransition Exit2 = new TranslateTransition(Duration.millis(500), Board2);
        Exit2.setFromX(0);
        Exit2.setToX(-1000);
        Exit2.setAutoReverse(false);

        //Animazioni Board3
        TranslateTransition Enter3 = new TranslateTransition(Duration.millis(500), Board3);
        Enter3.setFromX(1000);
        Enter3.setToX(0);
        Enter3.setAutoReverse(false);

        TranslateTransition Exit3 = new TranslateTransition(Duration.millis(500), Board3);
        Exit3.setFromX(0);
        Exit3.setToX(-1000);
        Exit3.setAutoReverse(false);

        //Animazioni Board4
        TranslateTransition Enter4 = new TranslateTransition(Duration.millis(500), Board4);
        Enter4.setFromX(1000);
        Enter4.setToX(0);
        Enter4.setAutoReverse(false);

        TranslateTransition Exit4 = new TranslateTransition(Duration.millis(500), Board4);
        Exit4.setFromX(0);
        Exit4.setToX(-1000);
        Exit4.setAutoReverse(false);


        Text ChooseAWindow = new Text("Choose a Window");
        ChooseAWindow.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");
        ChooseAWindow.setTranslateY(-250);

        Button Play = new Button("Next");Play.setTranslateX(300);Play.setId("NextBTN");Play.setPrefSize(150,150);
        Button Play2 = new Button("Next");Play2.setTranslateX(300);Play2.setVisible(false);Play2.setId("NextBTN");Play2.setPrefSize(150,150);
        Button Play3 = new Button("Next");Play3.setTranslateX(300);Play3.setVisible(false);Play3.setId("NextBTN");Play3.setPrefSize(150,150);
        Button Play4 = new Button("Next");Play4.setTranslateX(300);Play4.setVisible(false);Play4.setId("NextBTN");Play4.setPrefSize(150,150);

        Play.setOnAction(event -> {Exit1.play();Enter2.play();Play.setVisible(false);Play2.setVisible(true);});
        Play2.setOnAction(event -> {Exit2.play();Enter3.play();Play2.setVisible(false);Play3.setVisible(true);});
        Play3.setOnAction(event -> {Exit3.play();Enter4.play();Play3.setVisible(false);Play4.setVisible(true);});
        Play4.setOnAction(event -> {Exit4.play();Enter1.play();Play4.setVisible(false);Play.setVisible(true);});


        StackPane Animation = new StackPane();
        Animation.setId("ChooseAWindow");
        Animation.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        Animation.getChildren().addAll(ChooseAWindow,Board4,Board3,Board2,Board1,Play,Play2,Play3,Play4);

        Window = new Scene(Animation,720,720);
        //FINE SCENA CHOOSE A WINDOW

        //INIZIO SCENA PRIVATE OBJECTIVE

        //Testo
        Text YourColorText = new Text("Your\nPrivate Objective\nis");
        YourColorText.setTextAlignment(TextAlignment.CENTER);
        YourColorText.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");
        YourColorText.setTranslateY(-250);

        PrivateObjectiveInfoText = "purple";
        Text PrivateObjectiveInfo = new Text("• Shades of "+PrivateObjectiveInfoText+" • \n\rPrivate Sum of value on the\n"+PrivateObjectiveInfoText+" dice");
        PrivateObjectiveInfo.setTextAlignment(TextAlignment.CENTER);
        PrivateObjectiveInfo.setStyle("-fx-font: 35 \"Castellar\";-fx-fill: white");
        PrivateObjectiveInfo.setTranslateY(250);

        //Bottoni
        Button ProceedButton = new Button();
        ProceedButton.setId("NextBTN");
        ProceedButton.setMinSize(250,250);
        ProceedButton.setTranslateX(300);
        ProceedButton.setOnAction(event -> this.setScene(Window));

        //Label col Colore
        PrivateObjectiveColor = new Label();
        PrivateObjectiveColor.setMinSize(75,75);
        PrivateObjectiveColor.setId(PrivateObjectiveInfoText);



        StackPane PrivateObjectiveDisplayer = new StackPane();
        PrivateObjectiveDisplayer.setId("ChooseAWindow");
        PrivateObjectiveDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        PrivateObjectiveDisplayer.getChildren().addAll(PrivateObjectiveInfo,YourColorText,ProceedButton,PrivateObjectiveColor);

        PrivateObjective = new Scene(PrivateObjectiveDisplayer,720,720);

        this.setScene(PrivateObjective);
        this.show();

    }

    public void updatePrivateObjective(){
        Platform.runLater(()->{
            int color = LocalModel.getInstance().getPrivateObjectiveCard().getColor();
            switch (color){
                case ClientColor.RED: PrivateObjectiveInfoText = "red"; PrivateObjectiveColor.setId("red");
                    break;
                case ClientColor.BLUE: PrivateObjectiveInfoText = "blue"; PrivateObjectiveColor.setId("blue");
                    break;
                case ClientColor.PURPLE: PrivateObjectiveInfoText = "purple"; PrivateObjectiveColor.setId("purple");
                    break;
                case ClientColor.YELLOW: PrivateObjectiveInfoText = "yellow"; PrivateObjectiveColor.setId("yellow");
                    break;
                case ClientColor.GREEN: PrivateObjectiveInfoText = "green"; PrivateObjectiveColor.setId("green");
                    break;
            }
        });
    }

    public void updateBoards(){
        Platform.runLater(()->{
            ArrayList<ClientGameBoardCard> Gameboardcards=null;

            while (Gameboardcards==null){
                Gameboardcards=LocalModel.getInstance().getDrawnGameBoardCards();
            }

            name1 = Gameboardcards.get(0).getTitle();
            difficulty1 = Gameboardcards.get(0).getDifficulty();

            name2 = Gameboardcards.get(1).getTitle();
            difficulty2 = Gameboardcards.get(1).getDifficulty();

            name3 = Gameboardcards.get(2).getTitle();
            difficulty3 = Gameboardcards.get(2).getDifficulty();

            name4 = Gameboardcards.get(3).getTitle();
            difficulty4 = Gameboardcards.get(3).getDifficulty();
            
        });
    }


}