package Progetto_Ing_Sw.com.client;

import javafx.animation.PauseTransition;
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

        //Finestre
        ImageView Player1Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer1.png");Player1Window.setTranslateY(-30);
        ImageView Player2Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer2.png");Player2Window.setTranslateY(-30);
        ImageView Player3Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer3.png");Player3Window.setTranslateY(-30);
        ImageView Player4Window = new ImageView("Progetto_Ing_Sw/com/client/GUI/LobbyPlayer4.png");Player4Window.setTranslateY(-30);


        //Bottoni per i singoli giocatori da premere per dare il Ready
        Player1BTN = new Label("Waiting..."); Player1BTN.setId("WaitingBTN");Player1BTN.setTranslateY(155);Player1BTN.setPrefSize(200,180);Player1BTN.setDisable(true);
        Player2BTN = new Label("Waiting..."); Player2BTN.setId("WaitingBTN");Player2BTN.setTranslateY(155);Player2BTN.setPrefSize(200,180);Player2BTN.setDisable(true);
        Player3BTN = new Label("Waiting..."); Player3BTN.setId("WaitingBTN");Player3BTN.setTranslateY(155);Player3BTN.setPrefSize(200,180);Player3BTN.setDisable(true);
        Player4BTN = new Label("Waiting..."); Player4BTN.setId("WaitingBTN");Player4BTN.setTranslateY(155);Player4BTN.setPrefSize(200,180);Player4BTN.setDisable(true);



        //Labels con i nomi dei giocatori
        Player1Label = new Label("Player 1"); Player1Label.setId("PlayerLobbyLabel");Player1Label.setPrefWidth(240);Player1Label.setTranslateY(260);
        Player2Label = new Label("Player 2"); Player2Label.setId("PlayerLobbyLabel");Player2Label.setPrefWidth(240);Player2Label.setTranslateY(260);
        Player3Label = new Label("Player 3"); Player3Label.setId("PlayerLobbyLabel");Player3Label.setPrefWidth(240);Player3Label.setTranslateY(260);
        Player4Label = new Label("Player 4"); Player4Label.setId("PlayerLobbyLabel");Player4Label.setPrefWidth(240);Player4Label.setTranslateY(260);



        //LocalModel.getInstance().registerAsObserver(this);



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

        TimerLabel = new Label("");
        TimerLabel.setId("Timer");
        TimerLabel.setPrefWidth(100);
        TimerLabel.setTranslateY(-10);
        TimerLabel.setTranslateX(590);
        TimerLabel.setTextAlignment(TextAlignment.CENTER);




        //Un Borderpane per domarli, un BorderPane per trovarli,
        //Un BorderPane per ghermirli e nel buio incatenarli,
        BorderPane LobbyPane = new BorderPane();
        LobbyPane.setId("GamemodeSelectionScreen");
        LobbyPane.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        LobbyPane.setTop(TimerLabel);
        LobbyPane.setCenter(Players);

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
                    Player4BTN.setId("ReadyBTN");
                    Player4BTN.setText("READY!");
                    Player4BTN.setDisable(false);
                case 3:
                    Player3Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(2).getName());
                    Player3BTN.setId("ReadyBTN");
                    Player3BTN.setText("READY!");
                    Player3BTN.setDisable(false);
                case 2:
                    Player2Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(1).getName());
                    Player2BTN.setId("ReadyBTN");
                    Player2BTN.setText("READY!");
                    Player2BTN.setDisable(false);
                case 1:
                    Player1Label.setText(LocalModel.getInstance().getClientPlayerArrayList().get(0).getName());
                    Player1BTN.setId("ReadyBTN");
                    Player1BTN.setText("READY!");
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
