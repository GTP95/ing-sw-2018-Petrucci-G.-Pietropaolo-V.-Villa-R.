package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.PlayerObserver;
import Progetto_Ing_Sw.com.server.RoundTrackObserver;
import Progetto_Ing_Sw.com.server.TableObserver;
import Progetto_Ing_Sw.com.server.WindowBoardObserver;

import java.util.Scanner;

public class View implements PlayerObserver, TableObserver, RoundTrackObserver, WindowBoardObserver {
    private Scanner inputKeyboard;
    private final ClientController controller;

    public View(ClientController controller) {
        this.controller = controller;
        this.inputKeyboard = new Scanner(System.in);
    }
    private String userInput() {
        return inputKeyboard.nextLine();
    }

    private void userAction(){
        //inserire il metodo del client controller che invia il messaggio che gestisce l'azione
    }
    //TODO implementare le tipologie di messaggio che possono arrivare dalla view
}
