package Progetto_Ing_Sw.com.client;
/* DA USARE SOLO PER SCAMBIO DATI CON SERVER*/
import Progetto_Ing_Sw.com.server.Model.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 *
 *
 */

public class ClientPlayer{
   private String name;
   private int favorTokens;
   private int victoryPoints;
   private boolean isActive;
   private ClientGameBoardCard choosenGameBoard;
   private ClientWindowBoard windowBoard;


    public ClientPlayer(String name) {
        this.name = name;
        this.victoryPoints = 0;
        this.isActive=true;

    }




    public String getName() {
        return name;
    }


    public int getFavorTokens() {
        return favorTokens;
    }


    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ClientGameBoardCard getChoosenGameBoard() {
        return choosenGameBoard;
    }

    public ClientWindowBoard getWindowBoard() {
        return windowBoard;
    }

    public boolean isActive() {
        return isActive;
    }

    public void updateWindowBoard(ClientWindowBoard windowBoard){
        this.windowBoard=windowBoard;
    }

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
}


