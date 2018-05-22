package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.PlayerObserver;
import Progetto_Ing_Sw.com.server.RoundTrackObserver;
import Progetto_Ing_Sw.com.server.TableObserver;
import Progetto_Ing_Sw.com.server.WindowBoardObserver;

import java.util.Scanner;

public class View implements PlayerObserver, TableObserver, RoundTrackObserver, WindowBoardObserver {
    private Scanner inputKeyboard;
    private final ClientController controller;

    public View(Scanner inputKeyboard, ClientController controller) {
        this.inputKeyboard = inputKeyboard;
        this.controller = controller;
    }



    //TODO implementare le tipologie di messaggio che possono arrivare dalla view
}
