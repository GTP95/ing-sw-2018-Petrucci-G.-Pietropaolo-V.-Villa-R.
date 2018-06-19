package Progetto_Ing_Sw.com.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartMenuGUI extends Application {
    Scene TitleScreen, SelectGameMode;  //Specifico prima tutte le scene di cui avrò bisogno

    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Sagrada"); //Il testo che compare come titolo della finestra
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(windowIcon);

        //INIZIO Start Screen
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
        //FINE Start Screen


        //INIZIO Scena Selezione Modalità

        //Bottone per il Single ClientPlayer
        Button singlePlayer =new Button("Debug Mode");                                      //Creo un nuovo bottone non specifico il testo per mettere un immagine col CSS
        singlePlayer.setId("SinglePlayerButton");                               //assegno un ID al bottone in modo che sia riconoscibile dal CSS
        singlePlayer.setPrefSize(350,350);                    //imposto le dimensioni del bottone
        singlePlayer.setOnAction(event ->{
                new ToolCardDisplayer("Title",2,"Description","Progetto_Ing_Sw/com/client/GUI/ToolCards/3ToolCard.png","blue",true);
                //new PublicObjectiveCardDisplayer("Title","Descriptio",5);
        });


        //Bottone per il Multiplayer come quello per il single player
        Button multiPlayer = new Button("Multiplayer");
        multiPlayer.setId("MultiPlayerButton");
        multiPlayer.setPrefSize(350,350);

        multiPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //new ChooseAWindow();
                //new TableGUI();
                new MultiplayerGUI(); //TODO rimetti new MultiplayerGUI(); qua
                primaryStage.close();

            }
        });

        // HBox per la selezione della modalità
        HBox gamemode= new HBox(80);                                                                   // imposto la scena come una HBox poichè voglio che i due tasti stiano uno di fianco all'altro
        gamemode.setPrefSize(1280,720);
        gamemode.setAlignment(Pos.CENTER);                                                                  //posiziono il punto di partenza della HBox al centro
        gamemode.getChildren().addAll(singlePlayer,multiPlayer); //assegno i bottoni creati alla HBox gamemode

        //Tasto goBack per tornare alla schermata principale
        Button goBack = new Button();
        goBack.setId("BackButton");
        goBack.setPrefSize(125,80);
        goBack.setOnAction(event -> primaryStage.setScene(TitleScreen)); //azione su click che permette di passare alla scene TitleScreen


        //BorderPane che contiene tutto
        BorderPane SelectionScreenPane= new BorderPane();
        SelectionScreenPane.setId("GamemodeSelectionScreen");
        SelectionScreenPane.setCenter(gamemode);
        SelectionScreenPane.setRight(goBack);
        SelectionScreenPane.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());    //importo le impostazioni di stile dal file CSS



        SelectGameMode = new Scene(SelectionScreenPane, 1280,720); //creo la scena impostandone le dimensioni





        primaryStage.setScene(TitleScreen); //faccio in modo che all'avvio il programma mostri la TitleScreen per prima
        primaryStage.show();                //mostra la schermata

    }
}
