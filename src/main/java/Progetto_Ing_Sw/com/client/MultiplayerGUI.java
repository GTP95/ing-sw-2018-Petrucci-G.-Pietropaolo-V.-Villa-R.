package Progetto_Ing_Sw.com.client;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;


public class MultiplayerGUI extends Stage {
    Scene ChooseConnectionScene,LobbyScene;
    Label Player1Label,Player2Label,Player3Label,Player4Label,TimerLabel,Player1BTN, Player2BTN, Player3BTN,Player4BTN;

    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");


    MultiplayerGUI(){
        this.setTitle("Sagrada - Multiplayer");
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.getIcons().add(windowIcon);

        LocalModel.getInstance().registerAsObserver(this);

        //INIZIO Choose Connection Scene
        HBox RMISocket = new HBox(80);
        RMISocket.setId("GamemodeSelectionScreen");
        RMISocket.setPrefSize(1280,720);
        RMISocket.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        RMISocket.setAlignment(Pos.CENTER);

        Button socketBTN = new Button("SOCKET");socketBTN.setId("SocketBTN");socketBTN.setPrefSize(250,250);

        socketBTN.setOnAction(event -> {
            LoginStage Login = new LoginStage();
            Login.showAndWait();
            this.setScene(LobbyScene);
        });


        Button RMIBTN = new Button("RMI");
        RMIBTN.setId("RMIBTN");
        RMIBTN.setPrefSize(250,250);
        //TODO: Configurare la parte RMI all'attivazione



        RMISocket.getChildren().addAll(socketBTN,RMIBTN);




        ChooseConnectionScene= new Scene(RMISocket, 1280,720);
        //FINE Choose Connection Scene



        //INIZIO Lobby Scene




        //Bottoni per i singoli giocatori da premere per dare il Ready
        Player1BTN = new Label(); Player1BTN.setId("WaitingPlayer1");Player1BTN.setTranslateY(155);Player1BTN.setPrefSize(150,150);Player1BTN.setDisable(true);
        Player2BTN = new Label(); Player2BTN.setId("WaitingPlayer2");Player2BTN.setTranslateY(155);Player2BTN.setPrefSize(150,150);Player2BTN.setDisable(true);
        Player3BTN = new Label(); Player3BTN.setId("WaitingPlayer3");Player3BTN.setTranslateY(155);Player3BTN.setPrefSize(150,150);Player3BTN.setDisable(true);
        Player4BTN = new Label(); Player4BTN.setId("WaitingPlayer4");Player4BTN.setTranslateY(155);Player4BTN.setPrefSize(150,150);Player4BTN.setDisable(true);



        //Labels con i nomi dei giocatori
        Player1Label = new Label("Player 1"); Player1Label.setId("TextField");Player1Label.setPrefWidth(240);Player1Label.setTranslateY(200);
        Player2Label = new Label("Player 2"); Player2Label.setId("TextField");Player2Label.setPrefWidth(240);Player2Label.setTranslateY(200);
        Player3Label = new Label("Player 3"); Player3Label.setId("TextField");Player3Label.setPrefWidth(240);Player3Label.setTranslateY(200);
        Player4Label = new Label("Player 4"); Player4Label.setId("TextField");Player4Label.setPrefWidth(240);Player4Label.setTranslateY(200);



        //LocalModel.getInstance().registerAsObserver(this);



        //StackPane Player1
        HBox Player1V = new HBox(30);
        Player1V.getChildren().addAll(Player1Label,Player1BTN);
        Player1V.setTranslateX(50);


        //StackPane Player2
        HBox Player2V = new HBox(30);
        Player2V.getChildren().addAll(Player2Label,Player2BTN);
        Player2V.setTranslateX(50);
        Player2V.setTranslateY(250);


        //StackPane Player3
        HBox Player3V = new HBox(30);
        Player3V.getChildren().addAll(Player3BTN,Player3Label);
        Player3V.setTranslateX(800);


        //StackPane Player4
        HBox Player4V = new HBox(30);
        Player4V.getChildren().addAll(Player4BTN,Player4Label);
        Player4V.setTranslateX(800);
        Player4V.setTranslateY(250);

        //HBox per tutti i giocatori
        StackPane Players = new StackPane();
        Players.setAlignment(Pos.CENTER);
        Players.getChildren().addAll(Player1V,Player2V,Player3V,Player4V);

        //Timer Label
        TimerLabel = new Label("");
        TimerLabel.setId("Timer");
        TimerLabel.setPrefSize(150,150);
        TimerLabel.setTextAlignment(TextAlignment.CENTER);

        //Lobby Label

        Label LobbyLabel = new Label(" Lobby");
        LobbyLabel.setId("DefaultButtonBlack");
        LobbyLabel.setPrefSize(200,100);
        LobbyLabel.setTranslateY(-330);




        //StackPane che contiene tutti i nodi
        StackPane LobbyPane = new StackPane();
        LobbyPane.setId("GamemodeSelectionScreen");
        LobbyPane.getChildren().addAll(Players,TimerLabel,LobbyLabel);
        LobbyPane.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        LobbyScene= new Scene(LobbyPane,1280,720);

        //FINE Lobby Scene



        this.setScene(ChooseConnectionScene);

        this.show();


    }



    public void StartGame(){
        Platform.runLater(() ->{
            close();
            new ChooseAWindow();
        });
    }



    public void update(){
        Platform.runLater(() ->{
            System.err.println("-------------------------------------------------AGGIORNO LA LOBBY------------------------------------");
            int numOfPlayers=LocalModel.getInstance().getClientPlayerArrayList().size();
            switch(numOfPlayers){
                case 4:
                    Player4Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(3).getName());
                    Player4BTN.setId("ReadyPlayer4");
                    Player4BTN.setDisable(false);
                case 3:
                    Player3Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(2).getName());
                    Player3BTN.setId("ReadyPlayer3");
                    Player3BTN.setDisable(false);
                case 2:
                    Player2Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(1).getName());
                    Player2BTN.setId("ReadyPlayer2");
                    Player2BTN.setDisable(false);
                case 1:
                    Player1Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(0).getName());
                    Player1BTN.setId("ReadyPlayer1");
                    Player1BTN.setDisable(false);
            }
        });
    }

    public void updateTimer(){
        Platform.runLater(()->{
            TimerLabel.setText(" "+Integer.toString(LocalModel.getInstance().getCountdownValue()));
        });
    }


}
