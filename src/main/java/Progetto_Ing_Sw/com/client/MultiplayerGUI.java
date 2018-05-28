package Progetto_Ing_Sw.com.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Time;


public class MultiplayerGUI extends Stage {
    Scene ChooseConnectionScene,LobbyScene;


    MultiplayerGUI(){
        this.setTitle("Sagrada - Multiplayer");
        this.setResizable(false);

        //INIZIO Choose Connection Scene
        HBox RMISocket = new HBox(80);
        RMISocket.setId("GamemodeSelectionScreen");
        RMISocket.setPrefSize(1280,720);
        RMISocket.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        RMISocket.setAlignment(Pos.CENTER);

        Button socketBTN = new Button("SOCKET");socketBTN.setId("SocketBTN");socketBTN.setPrefSize(250,250);
        //TODO configurare la parte socket all'attivazione
        socketBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                new LoginStage();
                socketBTN.setDisable(true);

            }
        });


        Button RMIBTN = new Button("RMI");
        RMIBTN.setId("RMIBTN");
        RMIBTN.setPrefSize(250,250);
        //TODO: Configurare la parte RMI all'attivazione



        RMISocket.getChildren().addAll(socketBTN,RMIBTN);




        ChooseConnectionScene= new Scene(RMISocket, 1280,720);
        //FINE Choose Connection Scene



        //INIZIO Lobby Scene

        //Finestre
        ImageView Player1Window = new ImageView("file:///../GUI/LobbyPlayer1.png");
        ImageView Player2Window = new ImageView("file:///../GUI/LobbyPlayer2.png");
        ImageView Player3Window = new ImageView("file:///../GUI/LobbyPlayer3.png");
        ImageView Player4Window = new ImageView("file:///../GUI/LobbyPlayer4.png");


        //Bottoni per i singoli giocatori da premere per dare il Ready
        ToggleButton Player1BTN = new ToggleButton("READY!"); Player1BTN.setId("ReadyBTN");Player1BTN.setTranslateY(155);Player1BTN.setPrefHeight(139);
        ToggleButton Player2BTN = new ToggleButton("READY!"); Player2BTN.setId("ReadyBTN");Player2BTN.setTranslateY(155);Player2BTN.setPrefHeight(139);
        ToggleButton Player3BTN = new ToggleButton("READY!"); Player3BTN.setId("ReadyBTN");Player3BTN.setTranslateY(155);Player3BTN.setPrefHeight(139);
        ToggleButton Player4BTN = new ToggleButton("READY!"); Player4BTN.setId("ReadyBTN");Player4BTN.setTranslateY(155);Player4BTN.setPrefHeight(139);


        //Labels con i nomi dei giocatori
        Label Player1Label = new Label("Player 1"); Player1Label.setId("PlayerLobbyLabel");Player1Label.setPrefWidth(240);Player1Label.setTranslateY(255);
        Label Player2Label = new Label("Player 2"); Player2Label.setId("PlayerLobbyLabel");Player2Label.setPrefWidth(240);Player2Label.setTranslateY(255);
        Label Player3Label = new Label("Player 3"); Player3Label.setId("PlayerLobbyLabel");Player3Label.setPrefWidth(240);Player3Label.setTranslateY(255);
        Label Player4Label = new Label("Player 4"); Player4Label.setId("PlayerLobbyLabel");Player4Label.setPrefWidth(240);Player4Label.setTranslateY(255);


        //StackPane Player1
        StackPane Player1V = new StackPane();
        Player1V.getChildren().addAll(Player1BTN,Player1Label,Player1Window);



        //StackPane Player2
        StackPane Player2V = new StackPane();
        Player2V.setAlignment(Pos.CENTER);
        Player2V.getChildren().addAll(Player2BTN,Player2Label,Player2Window);

        //StackPane Player3
        StackPane Player3V = new StackPane();
        Player3V.setAlignment(Pos.CENTER);
        Player3V.getChildren().addAll(Player3BTN,Player3Label,Player3Window);

        //StackPane Player4
        StackPane Player4V = new StackPane();
        Player4V.setAlignment(Pos.CENTER);
        Player4V.getChildren().addAll(Player4BTN,Player4Label,Player4Window);

        //HBox per tutti i giocatori
        HBox Players = new HBox(80);
        Players.setAlignment(Pos.CENTER);
        Players.getChildren().addAll(Player1V,Player2V,Player3V,Player4V);

        //Timer
        HBox TimerBox = new HBox();
        TimerBox.setAlignment(Pos.TOP_CENTER);
        TimerBox.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        Label TimerLabel = new Label("  00:30"); TimerLabel.setId("Timer");TimerLabel.setTranslateY(-10);TimerLabel.setPrefWidth(200);
        TimerBox.getChildren().addAll(TimerLabel);


        //Un Borderpane per domarli, un BorderPane per trovarli,
        //Un BorderPane per ghermirli e nel buio incatenarli,
        BorderPane LobbyPane = new BorderPane();
        LobbyPane.setId("GamemodeSelectionScreen");
        LobbyPane.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        LobbyPane.setTop(TimerBox);
        LobbyPane.setCenter(Players);

        LobbyScene= new Scene(LobbyPane,1280,720);

        //FINE Lobby Scene

        this.setScene(ChooseConnectionScene);

        this.show();

    }
}
