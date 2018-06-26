package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
    Scene Window,PrivateObjective,WaitingForOtherPlayers;
    Label PrivateObjectiveColor,PrivateObjectiveColor2, info1,info2,info3,info4,difficulty4, difficulty3,difficulty2,difficulty1;
    ArrayList<String> BoardInfos;
    Text  PrivateObjectiveInfo;
    ClientGameBoardCard ChoosenGameboardCard;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    public GridPane CreateAGrid (ClientGameBoardCard gameBoardCard){
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
        this.getIcons().add(windowIcon);

        LocalModel.getInstance().registerAsObserver(this);


        //-------------------------------------------------------------------------------------SCENA WINDOW------------------------------------------------------------------------------------------
        //Bottoni che rappresentano le finestre da scegliere
        Button Window1BTN = new Button();Window1BTN.setPrefSize(386,313);Window1BTN.setTranslateY(-20);Window1BTN.setId("transparentBTN");
        Window1BTN.setOnAction(event -> {
            LocalModel.getInstance().setChoosenGameBoardCard(LocalModel.getInstance().getDrawnGameBoardCards().get(0));
            ChoosenGameboardCard = LocalModel.getInstance().getDrawnGameBoardCards().get(0);
            this.setScene(WaitingForOtherPlayers);
        });
        Button Window2BTN = new Button();Window2BTN.setPrefSize(386,313);Window2BTN.setTranslateY(-20);Window2BTN.setId("transparentBTN");
        Window2BTN.setOnAction(event -> {
            LocalModel.getInstance().setChoosenGameBoardCard(LocalModel.getInstance().getDrawnGameBoardCards().get(1));
            ChoosenGameboardCard = LocalModel.getInstance().getDrawnGameBoardCards().get(1);
            this.setScene(WaitingForOtherPlayers);
        });
        Button Window3BTN = new Button();Window3BTN.setPrefSize(386,313);Window3BTN.setTranslateY(-20);Window3BTN.setId("transparentBTN");
        Window3BTN.setOnAction(event -> {
            LocalModel.getInstance().setChoosenGameBoardCard(LocalModel.getInstance().getDrawnGameBoardCards().get(2));
            ChoosenGameboardCard = LocalModel.getInstance().getDrawnGameBoardCards().get(2);
            this.setScene(WaitingForOtherPlayers);
        });
        Button Window4BTN = new Button();Window4BTN.setPrefSize(386,313);Window4BTN.setTranslateY(-20);Window4BTN.setId("transparentBTN");
        Window4BTN.setOnAction(event -> {
            LocalModel.getInstance().setChoosenGameBoardCard(LocalModel.getInstance().getDrawnGameBoardCards().get(3));
            ChoosenGameboardCard = LocalModel.getInstance().getDrawnGameBoardCards().get(3);
            this.setScene(WaitingForOtherPlayers);
        });

        //Label che indicano nome e difficoltà della scheda in questione

        info1 = new Label("Virtus");info1.setId("WindowInfo");info1.setTranslateY(175);info1.setStyle("-fx-font-size: 45");
        info2 = new Label("Virtus");info2.setId("WindowInfo");info2.setTranslateY(175);info2.setStyle("-fx-font-size: 45");
        info3 = new Label("Virtus");info3.setId("WindowInfo");info3.setTranslateY(175);info3.setStyle("-fx-font-size: 45");
        info4 = new Label("Virtus");info4.setId("WindowInfo");info4.setTranslateY(175);info4.setStyle("-fx-font-size: 45");

        difficulty1 = new Label("•");difficulty1.setId("WindowInfo");difficulty1.setTranslateY(275);difficulty1.setStyle("-fx-font-size: 45");
        difficulty2 = new Label("•");difficulty2.setId("WindowInfo");difficulty2.setTranslateY(275);difficulty2.setStyle("-fx-font-size: 45");
        difficulty3 = new Label("•");difficulty3.setId("WindowInfo");difficulty3.setTranslateY(275);difficulty3.setStyle("-fx-font-size: 45");
        difficulty4 = new Label("•");difficulty4.setId("WindowInfo");difficulty4.setTranslateY(275);difficulty4.setStyle("-fx-font-size: 45");

        StackPane Board1 = new StackPane();Board1.setTranslateX(1000);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event ->
                Board1.getChildren().addAll(CreateAGrid(LocalModel.getInstance().getDrawnGameBoardCards().get(0)),info1,difficulty1,Window1BTN)
        );
        pause.play();

        StackPane Board2 = new StackPane();Board2.setTranslateX(1000);
        PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
        pause2.setOnFinished(event ->
                Board2.getChildren().addAll(CreateAGrid(LocalModel.getInstance().getDrawnGameBoardCards().get(1)),info2,difficulty2,Window2BTN)
        );
        pause2.play();

        StackPane Board3 = new StackPane();Board3.setTranslateX(1000);
        PauseTransition pause3 = new PauseTransition(Duration.seconds(1));
        pause3.setOnFinished(event ->
                Board3.getChildren().addAll(CreateAGrid(LocalModel.getInstance().getDrawnGameBoardCards().get(2)),info3,difficulty3,Window3BTN)
        );
        pause3.play();

        StackPane Board4 = new StackPane();Board4.setTranslateX(1000);
        PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
        pause4.setOnFinished(event ->
                Board4.getChildren().addAll(CreateAGrid(LocalModel.getInstance().getDrawnGameBoardCards().get(3)),info4,difficulty4,Window4BTN)
        );
        pause4.play();



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

        TranslateTransition EntraDaSinistra1 = new TranslateTransition(Duration.millis(500), Board1);
        EntraDaSinistra1.setFromX(-1000);
        EntraDaSinistra1.setToX(0);
        EntraDaSinistra1.setAutoReverse(false);

        TranslateTransition EsceVersoDestra1 = new TranslateTransition(Duration.millis(500),Board1);
        EsceVersoDestra1.setFromX(0);
        EsceVersoDestra1.setToX(1000);
        EsceVersoDestra1.setAutoReverse(false);

        //Animazioni Board2
        TranslateTransition Enter2 = new TranslateTransition(Duration.millis(500), Board2);
        Enter2.setFromX(1000);
        Enter2.setToX(0);
        Enter2.setAutoReverse(false);

        TranslateTransition Exit2 = new TranslateTransition(Duration.millis(500), Board2);
        Exit2.setFromX(0);
        Exit2.setToX(-1000);
        Exit2.setAutoReverse(false);

        TranslateTransition EntraDaSinistra2 = new TranslateTransition(Duration.millis(500), Board2);
        EntraDaSinistra2.setFromX(-1000);
        EntraDaSinistra2.setToX(0);
        EntraDaSinistra2.setAutoReverse(false);

        TranslateTransition EsceVersoDestra2 = new TranslateTransition(Duration.millis(500),Board2);
        EsceVersoDestra2.setFromX(0);
        EsceVersoDestra2.setToX(1000);
        EsceVersoDestra2.setAutoReverse(false);

        //Animazioni Board3
        TranslateTransition Enter3 = new TranslateTransition(Duration.millis(500), Board3);
        Enter3.setFromX(1000);
        Enter3.setToX(0);
        Enter3.setAutoReverse(false);

        TranslateTransition Exit3 = new TranslateTransition(Duration.millis(500), Board3);
        Exit3.setFromX(0);
        Exit3.setToX(-1000);
        Exit3.setAutoReverse(false);

        TranslateTransition EntraDaSinistra3 = new TranslateTransition(Duration.millis(500), Board3);
        EntraDaSinistra3.setFromX(-1000);
        EntraDaSinistra3.setToX(0);
        EntraDaSinistra3.setAutoReverse(false);

        TranslateTransition EsceVersoDestra3 = new TranslateTransition(Duration.millis(500),Board3);
        EsceVersoDestra3.setFromX(0);
        EsceVersoDestra3.setToX(1000);
        EsceVersoDestra3.setAutoReverse(false);

        //Animazioni Board4
        TranslateTransition Enter4 = new TranslateTransition(Duration.millis(500), Board4);
        Enter4.setFromX(1000);
        Enter4.setToX(0);
        Enter4.setAutoReverse(false);

        TranslateTransition Exit4 = new TranslateTransition(Duration.millis(500), Board4);
        Exit4.setFromX(0);
        Exit4.setToX(-1000);
        Exit4.setAutoReverse(false);

        TranslateTransition EntraDaSinistra4 = new TranslateTransition(Duration.millis(500), Board4);
        EntraDaSinistra4.setFromX(-1000);
        EntraDaSinistra4.setToX(0);
        EntraDaSinistra4.setAutoReverse(false);

        TranslateTransition EsceVersoDestra4 = new TranslateTransition(Duration.millis(500),Board4);
        EsceVersoDestra4.setFromX(0);
        EsceVersoDestra4.setToX(1000);
        EsceVersoDestra4.setAutoReverse(false);

        Text ChooseAWindow = new Text("Choose a Window");
        ChooseAWindow.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");
        ChooseAWindow.setTranslateY(-300);

        PrivateObjectiveColor2 = new Label();
        PrivateObjectiveColor2.setId("purple");
        PrivateObjectiveColor2.setMinSize(40,40);
        PrivateObjectiveColor2.setStyle("-fx-border-color: black;"+"-fx-border-width: 2 2 2 2;" + "-fx-border-radius: 2.5 2.5 2.5 2.5;" + "-fx-background-radius: 3.5 3.5 3.5 3.5;"+ "-fx-background-size: 0.99");
        PrivateObjectiveColor2.setTranslateY(-247);
        PrivateObjectiveColor2.setTranslateX(250);


        Text RememberColor = new Text("Remember your private color is: ");
        RememberColor.setStyle("-fx-font: 40 \"Centaur\";-fx-fill: white");
        RememberColor.setTranslateY(-250);

        Button Play = new Button("Next");Play.setTranslateX(300);Play.setId("NextBTN");Play.setPrefSize(150,150);
        Button Play2 = new Button("Next");Play2.setTranslateX(300);Play2.setVisible(false);Play2.setId("NextBTN");Play2.setPrefSize(150,150);
        Button Play3 = new Button("Next");Play3.setTranslateX(300);Play3.setVisible(false);Play3.setId("NextBTN");Play3.setPrefSize(150,150);
        Button Play4 = new Button("Next");Play4.setTranslateX(300);Play4.setVisible(false);Play4.setId("NextBTN");Play4.setPrefSize(150,150);

        Button Reverse1 = new Button();Reverse1.setTranslateX(-300);Reverse1.setId("BackBTN");Reverse1.setPrefSize(150,150);
        Button Reverse2 = new Button();Reverse2.setTranslateX(-300);Reverse2.setVisible(false);Reverse2.setId("BackBTN");Reverse2.setPrefSize(150,150);
        Button Reverse3 = new Button();Reverse3.setTranslateX(-300);Reverse3.setVisible(false);Reverse3.setId("BackBTN");Reverse3.setPrefSize(150,150);
        Button Reverse4 = new Button();Reverse4.setTranslateX(-300);Reverse4.setVisible(false);Reverse4.setId("BackBTN");Reverse4.setPrefSize(150,150);

        Reverse1.setOnAction(event -> {EsceVersoDestra1.play();EntraDaSinistra4.play();Reverse1.setVisible(false);Reverse2.setVisible(true);Play.setVisible(false);Play4.setVisible(true);});
        Reverse2.setOnAction(event -> {EsceVersoDestra4.play();EntraDaSinistra3.play();Reverse2.setVisible(false);Reverse3.setVisible(true);Play4.setVisible(false);Play3.setVisible(true);});
        Reverse3.setOnAction(event -> {EsceVersoDestra3.play();EntraDaSinistra2.play();Reverse3.setVisible(false);Reverse4.setVisible(true);Play3.setVisible(false);Play2.setVisible(true);});
        Reverse4.setOnAction(event -> {EsceVersoDestra2.play();EntraDaSinistra1.play();Reverse4.setVisible(false);Reverse1.setVisible(true);Play2.setVisible(false);Play.setVisible(true);});

        Play.setOnAction(event -> {Exit1.play();Enter2.play();Play.setVisible(false);Play2.setVisible(true);Reverse1.setVisible(false);Reverse4.setVisible(true);});
        Play2.setOnAction(event -> {Exit2.play();Enter3.play();Play2.setVisible(false);Play3.setVisible(true);Reverse4.setVisible(false);Reverse3.setVisible(true);});
        Play3.setOnAction(event -> {Exit3.play();Enter4.play();Play3.setVisible(false);Play4.setVisible(true);Reverse3.setVisible(false);Reverse2.setVisible(true);});
        Play4.setOnAction(event -> {Exit4.play();Enter1.play();Play4.setVisible(false);Play.setVisible(true);Reverse2.setVisible(false);Reverse1.setVisible(true);});


        StackPane Animation = new StackPane();
        Animation.setId("ChooseAWindow");
        Animation.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        Animation.getChildren().addAll(ChooseAWindow,RememberColor,PrivateObjectiveColor2,Board4,Board3,Board2,Board1,Play,Play2,Play3,Play4,Reverse1,Reverse2,Reverse3,Reverse4);

        Window = new Scene(Animation,720,720);
        //------------------------------------------------------------------------------------FINE SCENA CHOOSE A WINDOW------------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------INIZIO SCENA PRIVATE OBJECTIVE---------------------------------------------------------------------------

        //Testo
        Text YourColorText = new Text("Your\nPrivate Objective\nis");
        YourColorText.setTextAlignment(TextAlignment.CENTER);
        YourColorText.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");
        YourColorText.setTranslateY(-250);


        PrivateObjectiveInfo = new Text("• Shades of  • \n\rPrivate Sum of value on the\ndice");
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
        PrivateObjectiveColor.setId("purple");
        PrivateObjectiveColor.setStyle("-fx-border-color: black;"+"-fx-border-width: 4 4 4 4;" + "-fx-border-radius: 5 5 5 5;" + "-fx-background-radius: 7 7 7 7;"+ "-fx-background-size: 0.99");



        StackPane PrivateObjectiveDisplayer = new StackPane();
        PrivateObjectiveDisplayer.setId("ChooseAWindow");
        PrivateObjectiveDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        PrivateObjectiveDisplayer.getChildren().addAll(PrivateObjectiveInfo,YourColorText,ProceedButton,PrivateObjectiveColor);
        PrivateObjective = new Scene(PrivateObjectiveDisplayer,720,720);

        //-----------------------------------------------------------------------------------FINE SCENA PRIVATE OBJECTIVE---------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------INIZIO SCENE DI ATTESA------------------------------------------------------------------------------

        Text WaitingText = new Text("Waiting for other players");
        WaitingText.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");
        WaitingText.setTranslateY(-300);

        StackPane WaitingForOthers = new StackPane();
        WaitingForOthers.setId("ChooseAWindow");
        WaitingForOthers.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        WaitingForOthers.getChildren().addAll(WaitingText);

        WaitingForOtherPlayers = new Scene(WaitingForOthers,720,720);

        //--------------------------------------------------------------------------------------FINE SCENE DI ATTESA------------------------------------------------------------------------------

        

        this.setScene(PrivateObjective);

        this.show();

    }

    public void StartTable(){
        close();
        new TableGUI(ChoosenGameboardCard);
    }

    public void updateChooseAWindow(){
        updatePrivateObjective();
        updateBoards();
    }

    public void updatePrivateObjective(){
        Platform.runLater(()->{
            int color = LocalModel.getInstance().getPrivateObjectiveCard().getColor();
            PrivateObjectiveInfo.setText("• Shades of " + new ClientColor().IntToColor(color) +  " • \n\rPrivate Sum of value on the\n"+new ClientColor().IntToColor(color)+" dice");
            PrivateObjectiveColor.setId(new ClientColor().IntToColor(color));
            PrivateObjectiveColor2.setId(new ClientColor().IntToColor(color));

        });
    }

    public void updateBoards(){
        Platform.runLater(()->{
            ArrayList<ClientGameBoardCard> Gameboardcards=null;

            while (Gameboardcards==null){
                Gameboardcards=LocalModel.getInstance().getDrawnGameBoardCards();
            }


            info1.setText(Gameboardcards.get(0).getTitle());
            info2.setText(Gameboardcards.get(1).getTitle());
            info3.setText(Gameboardcards.get(2).getTitle());
            info4.setText(Gameboardcards.get(3).getTitle());

            difficulty1.setText("Difficulty: "+String.join("", Collections.nCopies(LocalModel.getInstance().getDrawnGameBoardCards().get(0).getDifficulty(), "•")));
            difficulty2.setText("Difficulty: "+String.join("", Collections.nCopies(LocalModel.getInstance().getDrawnGameBoardCards().get(1).getDifficulty(), "•")));
            difficulty3.setText("Difficulty: "+String.join("", Collections.nCopies(LocalModel.getInstance().getDrawnGameBoardCards().get(2).getDifficulty(), "•")));
            difficulty4.setText("Difficulty: "+String.join("", Collections.nCopies(LocalModel.getInstance().getDrawnGameBoardCards().get(3).getDifficulty(), "•")));
        });
    }



}