package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.client.ChooseAWindow;
import Progetto_Ing_Sw.com.client.LoginStage;
import Progetto_Ing_Sw.com.client.TableGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*public class DebugMode extends Stage {
    Scene DebugMenu;

    DebugMode(){

        //Bottoni per le varie sezioni
        Button LoginStageBTN = new Button("Login Stage");
        LoginStageBTN.setOnAction(event -> {
            LoginStage login = new LoginStage();
            login.showAndWait();
        });


        Button TableStageBTN = new Button("Table Stage");
        TableStageBTN.setOnAction(event -> {
            TableGUI table = new TableGUI();
            table.showAndWait();
        });


        Button ChooseAWindowStageBTN = new Button("ChooseAWindow Stage");
        //ChooseAWindowStageBTN.setOnAction();

        Button LobbyStageBTN = new Button("Lobby Stage");

        HBox DebugMenuBox = new HBox(80);
        DebugMenuBox.getChildren().addAll(LobbyStageBTN,TableStageBTN,ChooseAWindowStageBTN,LoginStageBTN);

        DebugMenu = new Scene(DebugMenuBox,1280,720);

        this.setScene(DebugMenu);
        this.show();

    }
}*/