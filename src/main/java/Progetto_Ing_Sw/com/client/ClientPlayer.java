package Progetto_Ing_Sw.com.client;
/* DA USARE SOLO PER SCAMBIO DATI CON SERVER*/
import Progetto_Ing_Sw.com.server.Model.*;

import java.util.ArrayList;
import java.util.Objects;

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

    public void setChoosenGameBoardAndFavourTokens(ClientGameBoardCard choosenGameBoard) {
        this.choosenGameBoard = choosenGameBoard;
        this.favorTokens=choosenGameBoard.getDifficulty();
    }
    public void setDrawnGameBoardCard(ArrayList<ClientGameBoardCard> drawnGameBoardCard) {
    }

    public String getName() {
        return name;
    }

    public void setFavorTokens(int numOfTokens){
        favorTokens=numOfTokens;
        System.err.println("FavorTokens set to: "+numOfTokens);
    }

    public int getFavorTokens() {

        System.err.println("NumOfFavorTokens: "+favorTokens);
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
}

