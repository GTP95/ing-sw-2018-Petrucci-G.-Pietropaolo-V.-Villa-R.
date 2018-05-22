package Progetto_Ing_Sw.com.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MultiplayerGUI extends Stage {
    Scene ChooseConnectionScene;

    MultiplayerGUI(){
        this.setTitle("Sagrada - Multiplayer");
        this.setResizable(false);

        HBox RMISocket = new HBox(80);
        RMISocket.setId("GamemodeSelectionScreen");
        RMISocket.setPrefSize(1280,720);
        RMISocket.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        RMISocket.setAlignment(Pos.CENTER);

        Button socketBTN = new Button("SOCKET");
        socketBTN.setId("SocketBTN");
        socketBTN.setPrefSize(250,250);
        //TODO configurare la parte socket all'attivazione
      //  socketBTN.setOnAction(event -> this.setScene(LobbyGUI.create(stage)));    commentato perchè contiene errori che impediscono la compilazione


        Button RMIBTN = new Button("RMI");
        RMIBTN.setId("RMIBTN");
        RMIBTN.setPrefSize(250,250);
        //TODO: Configurare la parte RMI all'attivazione


        RMISocket.getChildren().addAll(socketBTN,RMIBTN);

        ChooseConnectionScene= new Scene(RMISocket, 1280,720);

        this.setScene(ChooseConnectionScene);

        this.show();

    }
}
