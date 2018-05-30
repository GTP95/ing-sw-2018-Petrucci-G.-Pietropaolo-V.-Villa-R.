package Progetto_Ing_Sw.com.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseAWindow extends Stage {
    Scene Window1,Window2,Window3,Window3;

    ChooseAWindow(){
        this.setTitle("Choose a Window");
        this.setMaxHeight(500);
        this.setMaxWidth(500);
        this.setResizable(false);


        //Bottoni che rappresentano le finestre da scegliere
        Button Window1BTN = new Button("Window 1");
        Button Window2BTN = new Button("Window 2");
        Button Window3BTN = new Button("Window 3");
        Button Window4BTN = new Button("Window 4");
    }
}
