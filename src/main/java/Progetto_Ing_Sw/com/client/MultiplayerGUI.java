package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Lobby;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
import java.util.*;


public class MultiplayerGUI extends Stage {
    Scene ChooseConnectionScene,LobbyScene;
    Label Player1Label,Player2Label,Player3Label,Player4Label;

    private ArrayList<ClientPlayer> PlayersList;

    public void update(){
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size(); i++ ){
            PlayersList.set(i,LocalModel.getInstance().getClientPlayerArrayList().get(i));
        }
        Player1Label.setText(PlayersList.get(0).getName());
        Player2Label.setText(PlayersList.get(1).getName());
        Player3Label.setText(PlayersList.get(2).getName());
        Player4Label.setText(PlayersList.get(3).getName());
    }


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
        socketBTN.setOnAction(event -> {LoginStage Login = new LoginStage(); Login.showAndWait(); this.setScene(LobbyScene);});


        Button RMIBTN = new Button("RMI");
        RMIBTN.setId("RMIBTN");
        RMIBTN.setPrefSize(250,250);
        //TODO: Configurare la parte RMI all'attivazione



        RMISocket.getChildren().addAll(socketBTN,RMIBTN);




        ChooseConnectionScene= new Scene(RMISocket, 1280,720);
        //FINE Choose Connection Scene



        //INIZIO Lobby Scene

        //Finestre
        ImageView Player1Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer1.png");
        ImageView Player2Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer2.png");
        ImageView Player3Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer3.png");
        ImageView Player4Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer4.png");


        //Bottoni per i singoli giocatori da premere per dare il Ready
        ToggleButton Player1BTN = new ToggleButton("READY!"); Player1BTN.setId("ReadyBTN");Player1BTN.setTranslateY(155);Player1BTN.setPrefHeight(139);
        ToggleButton Player2BTN = new ToggleButton("READY!"); Player2BTN.setId("ReadyBTN");Player2BTN.setTranslateY(155);Player2BTN.setPrefHeight(139);
        ToggleButton Player3BTN = new ToggleButton("READY!"); Player3BTN.setId("ReadyBTN");Player3BTN.setTranslateY(155);Player3BTN.setPrefHeight(139);
        ToggleButton Player4BTN = new ToggleButton("READY!"); Player4BTN.setId("ReadyBTN");Player4BTN.setTranslateY(155);Player4BTN.setPrefHeight(139);

        //Player Placeholder
        PlayersList= new ArrayList<>();
        PlayersList.add(0, new ClientPlayer("Player1"));
        PlayersList.add(1, new ClientPlayer("Player2"));
        PlayersList.add(2, new ClientPlayer("Player3"));
        PlayersList.add(3, new ClientPlayer("Player4"));


        //Labels con i nomi dei giocatori
        Player1Label = new Label(PlayersList.get(0).getName()); Player1Label.setId("PlayerLobbyLabel");Player1Label.setPrefWidth(240);Player1Label.setTranslateY(255);
        Player2Label = new Label(PlayersList.get(1).getName()); Player2Label.setId("PlayerLobbyLabel");Player2Label.setPrefWidth(240);Player2Label.setTranslateY(255);
        Player3Label = new Label(PlayersList.get(2).getName()); Player3Label.setId("PlayerLobbyLabel");Player3Label.setPrefWidth(240);Player3Label.setTranslateY(255);
        Player4Label = new Label(PlayersList.get(3).getName()); Player4Label.setId("PlayerLobbyLabel");Player4Label.setPrefWidth(240);Player4Label.setTranslateY(255);

        LocalModel.getInstance().registerAsObserver(this);



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
