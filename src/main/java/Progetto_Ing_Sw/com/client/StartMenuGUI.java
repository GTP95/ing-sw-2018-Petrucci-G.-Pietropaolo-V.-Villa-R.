package Progetto_Ing_Sw.com.client;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StartMenuGUI extends Application {
    Scene TitleScreen, SelectGameMode;  //Specifico prima tutte le scene di cui avrò bisogno

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sagrada"); //Il testo che compare come titolo della finestra

        //Start Menu Screen
        HBox startscreen= new HBox(80);
        startscreen.setAlignment(Pos.BOTTOM_LEFT);
        startscreen.setId("startScreen");                                                                             //assegno un ID alla startscreen in modo che sia riconoscibile dal CSS
        startscreen.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm()); //dico alla startscreen di comportarsi come indicato nel file CSS form.css

        //Bottone Start Game
        Button startBTN = new Button();                                                                             //Creo un bottone
        startBTN.setPrefSize(1280,720);                                                         // Dimensione del bottone
        startBTN.setId("startBTN");                                                                            //assegno un ID al bottone in modo che sia riconoscibile dal CSS
        startBTN.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());     //importo le impostazioni di stile dal file CSS

        startscreen.getChildren().addAll(startBTN);                             //Rendo il bottone parte della HBox startscreen dichiarata in precendenza
        startBTN.setOnAction(event -> primaryStage.setScene(SelectGameMode)); //al click del bottone si passa alla scena successiva

        TitleScreen =new Scene(startscreen,1280,720);   // imposto la scena attuale specificando dimensione e richiamando

        // Game Mode Selection Screen
        HBox gamemode= new HBox(80);                                                                   // imposto la scena come una HBox poichè voglio che i due tasti stiano uno di fianco all'altro
        gamemode.setId("GamemodeSelectionScreen");                                                             //assegno un ID al bottone in modo che sia riconoscibile dal CSS
        gamemode.setPrefSize(1280,720);
        gamemode.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());    //importo le impostazioni di stile dal file CSS
        gamemode.setAlignment(Pos.CENTER);                                                                  //posiziono il punto di partenza della HBox al centro





        //Bottone per il Single Player
        Button singlePlayer =new Button();                                      //Creo un nuovo bottone non specifico il testo per mettere un immagine col CSS
        singlePlayer.setId("SinglePlayerButton");                               //assegno un ID al bottone in modo che sia riconoscibile dal CSS
        singlePlayer.setPrefSize(250,250);                    //imposto le dimensioni del bottone
        //TODO setOnAction per passare alla scena del Single Player


        //Bottone per il Multiplayer come quello per il single player
        Button multiPlayer = new Button();
        multiPlayer.setId("MultiPlayerButton");
        multiPlayer.setPrefSize(250,250);
        //TODO setOnAction per passare alla scena del multiplayer (presubilmente una scelta della lobby)

        //Tasto goBack per tornare alla schermata principale
        Button goBack = new Button();
        goBack.setId("BackButton");
        goBack.setPrefSize(125,125);



        goBack.setOnAction(event -> primaryStage.setScene(TitleScreen)); //azione su click che permette di passare alla scene TitleScreen

        gamemode.getChildren().addAll(singlePlayer,multiPlayer,goBack); //assegno i bottoni creati alla HBox gamemode



        SelectGameMode = new Scene(gamemode, 1280,720); //creo la scena impostandone le dimensioni





        primaryStage.setScene(TitleScreen); //faccio in modo che all'avvio il programma mostri la TitleScreen per prima
        primaryStage.show();                //mostra la schermata

    }
}
