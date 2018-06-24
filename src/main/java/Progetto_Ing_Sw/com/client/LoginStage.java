package Progetto_Ing_Sw.com.client;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class LoginStage extends Stage {
    Scene UserNameSelectionScene,ServerScene;
    Boolean StartUsernameCheck;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    LoginStage(){
        this.setTitle("Sagrada - Login");
        this.setMaxHeight(630);
        this.setMaxWidth(430);
        this.setResizable(false);
        this.setAlwaysOnTop(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.getIcons().add(windowIcon);

        LocalModel.getInstance().registerAsObserver(this);
        StartUsernameCheck = false;

        //INIZIO Username Selection Scene

        //Finestra Frame
        ImageView frame = new ImageView("Progetto_Ing_Sw/com/client/GUI/LoginScreenFrame.png");

        //Text Fields da riempire
        TextField UsernameField = new TextField(ClientSettings.getInstance().getUsername());
        UsernameField.setId("TextField");
        UsernameField.setMaxWidth(250);
        UsernameField.setTranslateY(-30);

        //Text Fields da riempire
        String host = ClientSettings.getInstance().getHostname();
        int Port = ClientSettings.getInstance().getSocketPort();

        TextField HostField = new TextField();HostField.setId("TextField");
        HostField.setMaxWidth(250);
        HostField.setTranslateY(60);
        HostField.setText(host);
        TextField PortField = new TextField();

        PortField.setId("TextField");
        PortField.setMaxWidth(150);
        PortField.setTranslateY(160);
        PortField.setText(Integer.toString(Port));
        PortField.setTranslateX(-50);




        //Accept Button
        Button AcceptBTN2 = new Button("Proceed");AcceptBTN2.setId("DefaultButton");AcceptBTN2.setTranslateX(100);AcceptBTN2.setTranslateY(250);
        AcceptBTN2.setOnAction(event -> {

        });

        //Accept Button
        Button AcceptBTN = new Button("Proceed");AcceptBTN.setId("DefaultButton");AcceptBTN.setTranslateX(100);AcceptBTN.setTranslateY(250);
        AcceptBTN.setOnAction(event -> {
            ClientSettings.getInstance().setHostname(HostField.getText());
            ClientSettings.getInstance().setSocketPort(Integer.parseInt(PortField.getText()));
            try{
                new Thread (new SocketClient()).start();
            }

            catch(ConnectException e){
                Alert ConnectExpetionAlert = new Alert(Alert.AlertType.ERROR);
                ConnectExpetionAlert.setTitle("Incorrect Port");
                ConnectExpetionAlert.setHeaderText("The port you entered is not valid");
                ConnectExpetionAlert.setContentText("Press OK and enter another Port Number");
                ConnectExpetionAlert.showAndWait();
            }

            catch(UnknownHostException e){
                Alert UnknownHostAlert = new Alert(Alert.AlertType.ERROR);
                UnknownHostAlert.setTitle("Incorrect Host");
                UnknownHostAlert.setHeaderText("The Host you entered does not exist");
                UnknownHostAlert.setContentText("Press OK and enter another Host Name");
                UnknownHostAlert.showAndWait();
            }



            ClientSettings.getInstance().setUsername(UsernameField.getText());
            System.err.println("---------------------CLIENT SETTINGS RICEVE IL NOME---------------------");
            Task checkUsername = new Task() {
                @Override
                protected Boolean call() throws Exception {
                    System.err.println("----------------------------------------TASK AVVIATO----------------------------------------");
                    if (StartUsernameCheck == true){
                        System.err.println("TASK SUCCESSO");
                        this.succeeded();
                        return true;
                        }
                    else {
                        this.failed();
                        System.err.println("TASK FALLITO");
                        return false;
                    }
                }
            };

            Thread threadToCheckUsername = new Thread(checkUsername);
            threadToCheckUsername.start();


            checkUsername.setOnSucceeded(event1 -> {
                System.err.println("----------------------------------------TASK HA AVUTO SUCCESSO----------------------------------------");
                ClientSettings.getInstance().writeSettingsToJSON();
                this.close();
            });

            checkUsername.setOnFailed(event1 ->{
                    System.err.println("---------------------NOME UTENTE SBAGLIATO---------------------");
                    Alert UserNameExceptionAlert = new Alert(Alert.AlertType.ERROR);
                    UserNameExceptionAlert.setTitle("Bad Username");
                    UserNameExceptionAlert.setHeaderText(LocalModel.getInstance().returnTrownException().getMessage());
                    UserNameExceptionAlert.setContentText("Press OK and enter another Name");
                    UserNameExceptionAlert.showAndWait();
                });

            });




        //Title Text
        Text ChooseNameText = new Text("Choose\r\na\r\nName");ChooseNameText.setTextAlignment(TextAlignment.CENTER);ChooseNameText.setId("MenuText");ChooseNameText.setTranslateY(-130);

        //StackPane
        StackPane LoginFrame= new StackPane();
        LoginFrame.setId("GamemodeSelectionScreen");
        LoginFrame.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        LoginFrame.getChildren().addAll(UsernameField,ChooseNameText,HostField,PortField,AcceptBTN,frame);

        UserNameSelectionScene = new Scene(LoginFrame,420,590);
        this.setScene(UserNameSelectionScene);
        
        //Scena Tecnica

        ImageView frame2 = new ImageView("Progetto_Ing_Sw/com/client/GUI/LoginScreenFrame.png");





        //Title Text
        Text ChooseHostText = new Text("Choose\r\nHost");
        ChooseHostText.setTextAlignment(TextAlignment.CENTER);
        ChooseHostText.setId("MenuText");
        ChooseHostText.setTranslateY(-150);



        //StackPane
        StackPane HostFrame= new StackPane();
        HostFrame.setId("GamemodeSelectionScreen");
        HostFrame.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        //HostFrame.getChildren().addAll(HostField,PortField,ChooseHostText,AcceptBTN2,frame2);

        ServerScene = new Scene(HostFrame,420,590);





    }

    public void usernameCheck(){
        Platform.runLater(()->
            StartUsernameCheck = true
    );}

}
