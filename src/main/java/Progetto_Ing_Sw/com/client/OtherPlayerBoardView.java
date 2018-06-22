package Progetto_Ing_Sw.com.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OtherPlayerBoardView extends Stage {
    /*Scene OtherPlayerBoardScene;
    GridPane griglia;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    OtherPlayerBoardView(){
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


        int [][] matrixTexture = gameBoardCard.getMatrixScheme();

        for (int r = 0; r < matrixTexture.length; r++) {
            for (int c = 0; c < matrixTexture[r].length; c++) {
                Pane block = new Pane();
                block.setId("Block");
                switch (matrixTexture[r][c]) {
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

        griglia.setAlignment(Pos.CENTER);

        OtherPlayerBoardScene = new Scene(griglia,1280,720);
        OtherPlayerBoardScene.setFill(Color.rgb(0, 0, 0, 0.75));
        OtherPlayerBoardScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
    }*/
}