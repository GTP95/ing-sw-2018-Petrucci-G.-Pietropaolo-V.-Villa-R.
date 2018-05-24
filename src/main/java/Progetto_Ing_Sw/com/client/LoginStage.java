package Progetto_Ing_Sw.com.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginStage extends Stage {
    Scene UserNameSelectionScene;

    LoginStage(){
        this.setTitle("Sagrada - Login");
        this.setResizable(false);
        this.setAlwaysOnTop(true);

        //INIZIO Username Selection Scene

        //Finestra Frame
        ImageView frame = new ImageView("file:///../GUI/LoginScreenFrame.png");

        //Text Fields da riempire
        TextField UsernameField = new TextField();UsernameField.setId("TextField"); UsernameField.setMaxWidth(250);UsernameField.setTranslateY(50);

        //Accept Button
        Button AcceptBTN = new Button("Proceed");AcceptBTN.setId("DefaultButton");AcceptBTN.setTranslateX(100);AcceptBTN.setTranslateY(250);
        AcceptBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //TODO set exeptions, get text from text field add it to lobby array, show Lobby
            }
        });

        //Title Text
        Text ChooseNameText = new Text("Choose\r\na\r\nName");ChooseNameText.setTextAlignment(TextAlignment.CENTER);ChooseNameText.setId("MenuText");ChooseNameText.setTranslateY(-100);

        //StackPane
        StackPane LoginFrame= new StackPane();
        LoginFrame.setId("GamemodeSelectionScreen");
        LoginFrame.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        LoginFrame.getChildren().addAll(UsernameField,ChooseNameText,AcceptBTN,frame);

        UserNameSelectionScene = new Scene(LoginFrame,420,590);

        this.setScene(UserNameSelectionScene);

        this.show();
    }
}
