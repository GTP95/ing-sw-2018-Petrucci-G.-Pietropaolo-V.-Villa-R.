package Progetto_Ing_Sw.com.client;
/* DA USARE SOLO PER SCAMBIO DATI CON SERVER*/
import Progetto_Ing_Sw.com.server.Model.*;

import java.util.ArrayList;
import java.util.Objects;

public class ClientPlayer implements PlayerObserver{
   private String name;
   private int favorTokens;
   private int victoryPoints;
   private boolean isActive;
   private GameBoardCard choosenGameBoard;


    public ClientPlayer(String name) {
        this.name = name;
        this.victoryPoints = 0;
        this.isActive=true;

    }

    public void setChoosenGameBoardandFavourTokens(GameBoardCard choosenGameBoard) {
        this.choosenGameBoard = choosenGameBoard;
        this.favorTokens=choosenGameBoard.getDifficulty();
    }
    public void setDrawnGameBoardCard(ArrayList<GameBoardCard> drawnGameBoardCard) {
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

    public GameBoardCard getChoosenGameBoard() {
        return choosenGameBoard;
    }


}

