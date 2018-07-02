package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;
import Progetto_Ing_Sw.com.server.Model.ToolCards.Effect;

import java.util.ArrayList;

public class Player{
   private String name;
   private int favorTokens;
   private int roundNumber;
   private PrivateObjectiveCard privateObjective;
   private int victoryPoints;
   private boolean isActive;
   private GameBoardCard choosenGameBoard;
   private ArrayList<GameBoardCard> drawnGameBoardCard;
   private SocketClientHandler socketClientHandler;
   private WindowBoard choosenWindowBoard;

    public Player(String name,  SocketClientHandler socketClientHandler) {    //Non assegno subito alla creazione del player il privateObjective e le drawnGameBoardCard per evitare di esaurirle in caso di disconnessione se guita da connessione di altri giocatori (nella Lobby, non a gioco iniziato)
        this.name = name;
        this.favorTokens = favorTokens;
        this.roundNumber=2;
        this.victoryPoints = 0;
        this.isActive=true;
        this.socketClientHandler=socketClientHandler;
    }

    public void setChoosenGameBoard(GameBoardCard choosenGameBoard) {
        this.choosenGameBoard = choosenGameBoard;
        this.choosenWindowBoard=new WindowBoard(choosenGameBoard);
        setFavorTokens();
    }
    public void setFavorTokens(){
        favorTokens=choosenGameBoard.getDifficulty();
    }
    public void setDrawnGameBoardCard(ArrayList<GameBoardCard> drawnGameBoardCard) {
        this.drawnGameBoardCard = drawnGameBoardCard;
    }

    public void setPrivateObjective(PrivateObjectiveCard privateObjective) {
        this.privateObjective = privateObjective;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return privateObjective.getColor();
    }

    public int getRoundNumber() {return roundNumber;}
    public void setRoundNumber(int roundNumber) {this.roundNumber = roundNumber;}

    public int getFavorTokens() {
        return favorTokens;
    }

    public PrivateObjectiveCard getPrivateObjective() {
        return privateObjective;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public GameBoardCard getChoosenGameBoard() {
        return choosenGameBoard;
    }

    public ArrayList<GameBoardCard> getDrawnGameBoardCard() {
        return drawnGameBoardCard;
    }

    public GameBoardCard getGameBoardCardFromTitle(String title){
        for(int counter=0; counter<drawnGameBoardCard.size();counter++){
            if(drawnGameBoardCard.get(counter).getTitle().equals(title)) return drawnGameBoardCard.get(counter);
        }
        System.err.println("Non ho trovato la GameBoardCard "+ title);
        return null;
    }

    public WindowBoard getChoosenWindowBoard() {
        return choosenWindowBoard;
    }

    public SocketClientHandler getSocketClientHandler() {
        return socketClientHandler;
    }

    public void useToolCard(Effect toolCardWithEffect) throws NotEnoughFavorTokensException { //per ora si limita a decrementare il numero di segnalini favore
        if(!toolCardWithEffect.isFirstUsage() && favorTokens>=1) favorTokens--;
        else if(favorTokens>=2) favorTokens-=2; //ramo else, si finisce qui se non è il primo utilizzo della carta. Dunque il costo della carta è di due segnalini favore e bisogna controllare che il giocatore ce li abbia
            else throw new NotEnoughFavorTokensException();
    }

    public void chooseAction(){

}

    public void setChoosenWindowBoard(WindowBoard choosenWindowBoard) {
        this.choosenWindowBoard = choosenWindowBoard;
    }
}

