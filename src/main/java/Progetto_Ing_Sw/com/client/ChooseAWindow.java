package Progetto_Ing_Sw.com.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ChooseAWindow extends Stage {
    Scene Window;

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

        //Bottoni che rappresentano le finestre da scegliere
        Button Window1BTN = new Button();Window1BTN.setPrefSize(386,313);Window1BTN.setTranslateY(-20);Window1BTN.setId("transparentBTN");
        Button Window2BTN = new Button();Window2BTN.setPrefSize(386,313);Window2BTN.setTranslateY(-20);Window2BTN.setId("transparentBTN");
        Button Window3BTN = new Button();Window3BTN.setPrefSize(386,313);Window3BTN.setTranslateY(-20);Window3BTN.setId("transparentBTN");
        Button Window4BTN = new Button();Window4BTN.setPrefSize(386,313);Window4BTN.setTranslateY(-20);Window4BTN.setId("transparentBTN");

        StackPane Board1 = new StackPane();Board1.setTranslateX(1000);
        Board1.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/SymphonyOfLight.json"),Window1BTN);

        StackPane Board2 = new StackPane();Board2.setTranslateX(1000);
        Board2.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/ViaLux.json"),Window2BTN);

        StackPane Board3 = new StackPane();Board3.setTranslateX(1000);
        Board3.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/Industria.json"),Window3BTN);

        StackPane Board4 = new StackPane();Board4.setTranslateX(1000);
        Board4.getChildren().addAll(CreateAGrid("Resources/Cards/GameBoardCards/ShadowThief.json"),Window4BTN);



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


        Text ChooseAWindow = new Text("Choose a Window");ChooseAWindow.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");ChooseAWindow.setTranslateY(-250);

        Button Play = new Button("Next");Play.setTranslateY(250);Play.setId("NextBTN");Play.setPrefSize(150,150);
        Button Play2 = new Button("Next");Play2.setTranslateY(250);Play2.setVisible(false);Play2.setId("NextBTN");Play2.setPrefSize(150,150);
        Button Play3 = new Button("Next");Play3.setTranslateY(250);Play3.setVisible(false);Play3.setId("NextBTN");Play3.setPrefSize(150,150);
        Button Play4 = new Button("Next");Play4.setTranslateY(250);Play4.setVisible(false);Play4.setId("NextBTN");Play4.setPrefSize(150,150);

        Play.setOnAction(event -> {Exit1.play();Enter2.play();Play.setVisible(false);Play2.setVisible(true);});
        Play2.setOnAction(event -> {Exit2.play();Enter3.play();Play2.setVisible(false);Play3.setVisible(true);});
        Play3.setOnAction(event -> {Exit3.play();Enter4.play();Play3.setVisible(false);Play4.setVisible(true);});
        Play4.setOnAction(event -> {Exit4.play();Enter1.play();Play4.setVisible(false);Play.setVisible(true);});

        /*Button Stop = new Button("Stop");Stop.setTranslateY(200);
        Stop.setOnAction(event -> {translateTransition.pause();});*/


        StackPane Animation = new StackPane();
        Animation.setId("ChooseAWindow");
        Animation.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        Animation.getChildren().addAll(ChooseAWindow,Board4,Board3,Board2,Board1,Play,Play2,Play3,Play4);

        Window = new Scene(Animation,720,720);

        this.setScene(Window);
        this.show();

    }
}