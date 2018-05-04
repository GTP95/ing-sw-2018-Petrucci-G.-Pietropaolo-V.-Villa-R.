package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;

public class Player {
   private String name;
   private int color;
   private int favorTokens;
   private PrivateObjectiveCard privateObjective;
   private int victoryPoints;
   private int playerNumber;
   private boolean isFirst;
   private GameBoardCard choosenGameBoard;
   private ArrayList<GameBoardCard> drawnGameBoardCard;

    public Player(String name, int color, int favorTokens, PrivateObjectiveCard privateObjective, int playerNumber, boolean isFirst, ArrayList<GameBoardCard> drawnGameBoardCard) {
        this.name = name;
        this.color = color;
        this.favorTokens = favorTokens;
        this.privateObjective = privateObjective;
        this.victoryPoints = 0;
        this.playerNumber = playerNumber;
        this.isFirst = isFirst;
        this.drawnGameBoardCard = drawnGameBoardCard;
    }

    public void setChoosenGameBoard(GameBoardCard choosenGameBoard) {
        this.choosenGameBoard = choosenGameBoard;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public PrivateObjectiveCard getPrivateObjective() {
        return privateObjective;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public GameBoardCard getChoosenGameBoard() {
        return choosenGameBoard;
    }

    public ArrayList<GameBoardCard> getDrawnGameBoardCard() {
        return drawnGameBoardCard;
    }

    public void useToolCard(ToolCard card) throws NotEnoughFavorTokensExeption { //per ora si limita a decrementare il numero di segnalini favore
        if(card.isFirstUsage() && favorTokens>=1) favorTokens--;
        else if(favorTokens>=2) favorTokens-=2; //ramo else, si finisce qui se non è il primo utilizzo della carta. Dunque il costo della carta è di due segnalini favore e bisogna controllare che il giocatore ce li abbia
            else throw new NotEnoughFavorTokensExeption();
    }

    public void chooseAction(){

}



}

