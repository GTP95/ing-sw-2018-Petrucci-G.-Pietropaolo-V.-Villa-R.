package Progetto_Ing_Sw.com.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TableGUI extends Stage {
    Scene GameplayScene;

    TableGUI(){
        this.setTitle("Sagrada Game");
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

            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < columns; i++) {
                    Pane block = new Pane();
                    block.setId("Block");
                    griglia.add(block, i, j);

                    if (i == 0 && j == 0) {
                        block.setId("primo_blocco");
                        //block.setStyle("-fx-background-image: none");
                    }

                    if (i == 1 && j == 0) {
                        block.setStyle("-fx-background-color: red");
                    }
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


        //Contenuto del menu ToolCard
        Label ToolCardMenuTitle = new Label("Tool Cards");ToolCardMenuTitle.setId("ToolCardBTN");
        Button ToolCard1 = new Button("1. Tool Card");ToolCard1.setId("ToolCardBTN");ToolCard1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                new ToolCardDisplayer();
                ToolCard1.setDisable(true);

            }
        });
        Button ToolCard2 = new Button("2. Tool Card");ToolCard2.setId("ToolCardBTN");
        Button ToolCard3 = new Button("3. Tool Card");ToolCard3.setId("ToolCardBTN");
        Label emptyspace = new Label();emptyspace.setId("ToolCardBTN");

        //VBox per contenere le carte
        VBox ToolCardList = new VBox(80);ToolCardList.setId("ToolCardMenu");ToolCardList.setAlignment(Pos.BOTTOM_LEFT);ToolCardList.setMaxHeight(280);ToolCardList.setPrefWidth(240);
        ToolCardList.setTranslateY(390);
        ToolCardList.getChildren().addAll(ToolCardMenuTitle,ToolCard1,ToolCard2,ToolCard3,emptyspace);


        //BorderPane per contenere tutti gli altri
        BorderPane GameplayArea = new BorderPane();
        GameplayArea.setId("GamemodeSelectionScreen");
        GameplayArea.setBottom(OtherPlayerBox);
        GameplayArea.setCenter(griglia);
        GameplayArea.setRight(ToolCardList);




            GameplayScene = new Scene(GameplayArea, 1280, 720);
            GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());



        //FINE Gameplay Scene




        this.setScene(GameplayScene);
        this.show();
    }


}
