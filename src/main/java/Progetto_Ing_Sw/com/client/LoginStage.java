package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class LoginStage extends Stage {
    Scene UserNameSelectionScene,ServerScene;

    LoginStage(){
        this.setTitle("Sagrada - Login");
        this.setMaxHeight(630);
        this.setMaxWidth(430);
        this.setResizable(false);
        this.setAlwaysOnTop(true);

        //INIZIO Username Selection Scene

        //Finestra Frame
        ImageView frame = new ImageView("file:///../GUI/LoginScreenFrame.png");

        //Text Fields da riempire
        TextField UsernameField = new TextField();UsernameField.setId("TextField"); UsernameField.setMaxWidth(250);UsernameField.setTranslateY(50);



        //Accept Button
        Button AcceptBTN = new Button("Proceed");AcceptBTN.setId("DefaultButton");AcceptBTN.setTranslateX(100);AcceptBTN.setTranslateY(250);
        AcceptBTN.setOnAction(event -> {this.setScene(ServerScene);Model.getInstance().setUsername(UsernameField.getText());});



        //Title Text
        Text ChooseNameText = new Text("Choose\r\na\r\nName");ChooseNameText.setTextAlignment(TextAlignment.CENTER);ChooseNameText.setId("MenuText");ChooseNameText.setTranslateY(-100);

        //StackPane
        StackPane LoginFrame= new StackPane();
        LoginFrame.setId("GamemodeSelectionScreen");
        LoginFrame.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        LoginFrame.getChildren().addAll(UsernameField,ChooseNameText,AcceptBTN,frame);

        UserNameSelectionScene = new Scene(LoginFrame,420,590);
        
        //Scena Tecnica

        ImageView frame2 = new ImageView("file:///../GUI/LoginScreenFrame.png");

        //Text Fields da riempire
        String host = Model.getInstance().getHostname();
        int Port = Model.getInstance().getSocketPort();

        TextField HostField = new TextField();HostField.setId("TextField"); HostField.setMaxWidth(250);HostField.setTranslateY(10);HostField.setText(host);
        TextField PortField = new TextField();PortField.setId("TextField"); PortField.setMaxWidth(150);PortField.setTranslateY(110);PortField.setText(Integer.toString(Port));PortField.setTranslateX(-50);




        //Accept Button
        Button AcceptBTN2 = new Button("Proceed");AcceptBTN2.setId("DefaultButton");AcceptBTN2.setTranslateX(100);AcceptBTN2.setTranslateY(250);
        AcceptBTN2.setOnAction(event ->
        {Model.getInstance().setHostname(HostField.getText());Model.getInstance().setSocketPort(Integer.parseInt(PortField.getText()));this.close();
            try{new Thread (new SocketClient()).start();}

            catch(UnknownHostException e){
                Alert UnknownHostAlert = new Alert(Alert.AlertType.CONFIRMATION);
                UnknownHostAlert.showAndWait();
            }



        });



        //Title Text
        Text ChooseHostText = new Text("Choose\r\nHost");ChooseHostText.setTextAlignment(TextAlignment.CENTER);ChooseHostText.setId("MenuText");ChooseHostText.setTranslateY(-120);



        //StackPane
        StackPane HostFrame= new StackPane();
        HostFrame.setId("GamemodeSelectionScreen");
        HostFrame.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        HostFrame.getChildren().addAll(HostField,PortField,ChooseHostText,AcceptBTN2,frame2);

        ServerScene = new Scene(HostFrame,420,590);



        this.setScene(UserNameSelectionScene);


        this.showAndWait();
    }
}
