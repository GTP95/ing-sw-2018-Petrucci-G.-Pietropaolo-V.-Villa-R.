package Progetto_Ing_Sw.com.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

public class TableGUI extends Stage {
    Scene GameplayScene;

    TableGUI(){
        this.setTitle("Sagrada Game");
        this.setResizable(false);

        //INIZIO Gameplay Scene
        int rows = 4;
        int columns = 5;
        int NumPlayers= 4; //TODO mettere il get number of players

        //GridPane per la griglia 5x4

        GridPane griglia = new GridPane();griglia.setAlignment(Pos.CENTER);griglia.setId("TheGrid");
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            griglia.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++){
            RowConstraints row = new RowConstraints(75);
            griglia.getRowConstraints().add(row);
        }

        for (int j = 0; j < rows; j++){
            for (int i = 0; i < columns; i++)  {
                Pane block = new Pane();
                block.setId("Block");
                griglia.add(block, i, j);

                if (i==0&&j==0){
                    block.setId("primo_blocco");
                }

                if (i==1&&j==0){
                    block.setStyle("-fx-backgroud-color:red;");
                }
            }
        }

        //HBox Tabs per gli altri giocatori
        HBox OtherPlayerBox = new HBox(80);
        OtherPlayerBox.setAlignment(Pos.CENTER);OtherPlayerBox.setTranslateY(15);

        //Bottoni altri giocatori
        for (int i = 0; i < (NumPlayers-1); i++){
            Button OtherPlayer = new Button();
            OtherPlayer.setId("DefaultButton");
            OtherPlayerBox.getChildren().addAll(OtherPlayer);
        }


        //BorderPane per contenere tutti gli altri
        BorderPane GameplayArea = new BorderPane();
        GameplayArea.setId("GamemodeSelectionScreen");
        GameplayArea.setBottom(OtherPlayerBox);
        GameplayArea.setCenter(griglia);




        GameplayScene = new Scene(GameplayArea,1280,720);
        GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        this.setScene(GameplayScene);
        this.show();
    }


}
