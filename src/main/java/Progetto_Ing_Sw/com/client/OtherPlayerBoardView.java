package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.MatrixCell;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class OtherPlayerBoardView extends Stage {
    Scene OtherPlayerBoardScene;
    GridPane griglia, DieGrid;
    ArrayList<ArrayList<MatrixCell>> OtherPlayerBoard;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");
    ArrayList<ArrayList<Pane>> GridBlocks;

    public GridPane getWindowBoardDiceBoard (ClientWindowBoard clientWindowBoard) {
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
        GridBlocks = new ArrayList<>();
        for (int r = 0; r < clientWindowBoard.getUsedMatrix().size(); r++) {
            for (int c = 0; c < clientWindowBoard.getUsedMatrix().get(r).size(); c++) {
                Pane block = new Pane();
                if (clientWindowBoard.getUsedMatrix().get(r).get(c).isUsed()) {
                    block.setId(Integer.toString(clientWindowBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue())
                            + new ClientColor().IntToColor(clientWindowBoard.getUsedMatrix().get(r).get(c).getDiceContained().getColor()));
                    block.setStyle("-fx-opacity: 0.90;" + "-fx-background-size: 60 60");
                } else {
                    block.setId("DieBlock");
                }
                Board.add(block, c, r);
            }
        }
        return Board;
    }

    OtherPlayerBoardView(ClientWindowBoard windowBoard){
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);

        int rows = 4;
        int columns = 5;


        //GridPane per la griglia 5x4
        griglia = new GridPane();
        griglia.setTranslateY(-20);
        griglia.setAlignment(Pos.CENTER);
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            griglia.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            griglia.getRowConstraints().add(row);
        }

        OtherPlayerBoard= windowBoard.getUsedMatrix();


        for (int r = 0; r <OtherPlayerBoard.size(); r++) {
            for (int c = 0; c < OtherPlayerBoard.get(r).size(); c++) {
                Pane block = new Pane();
                block.setId("Block");
                switch (OtherPlayerBoard.get(r).get(c).getColor()) {
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
                    case (23):
                        switch (OtherPlayerBoard.get(r).get(c).getShade()){
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
                }

                griglia.add(block, c, r);
            }
        }

        Button goback = new Button();
        goback.setTranslateX(-450);
        goback.setMinSize(256,256);
        goback.setId("BackButton2");
        goback.setOnAction(event -> this.close());

        griglia.setAlignment(Pos.CENTER);

        GridPane Dice = getWindowBoardDiceBoard(windowBoard);


        StackPane OtherBoardStack = new StackPane();
        OtherBoardStack.getChildren().addAll(griglia,Dice,goback);

        OtherPlayerBoardScene = new Scene(OtherBoardStack,1280,720);
        OtherPlayerBoardScene.setFill(Color.rgb(0, 0, 0, 0.75));
        OtherPlayerBoardScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        this.setScene(OtherPlayerBoardScene);
    }
}