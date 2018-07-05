package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;

import java.util.ArrayList;

/**
 * this class implements the player that is going to play at Sagrada
 */
public class Player{
   private String name;
   private int favorTokens;
   private int numOfTurnsToPlayInTheCurrentRound;
   private PrivateObjectiveCard privateObjective;
   private ArrayList<Integer> victoryPoints;
   private boolean isActive;
   private GameBoardCard choosenGameBoard;
   private ArrayList<GameBoardCard> drawnGameBoardCard;
   private SocketClientHandler socketClientHandler;
   private WindowBoard choosenWindowBoard;
   private int token;

    public Player(String name,  SocketClientHandler socketClientHandler) {    //Non assegno subito alla creazione del player il privateObjective e le drawnGameBoardCard per evitare di esaurirle in caso di disconnessione se guita da connessione di altri giocatori (nella Lobby, non a gioco iniziato)
        this.name = name;
        this.favorTokens = favorTokens;
        this.numOfTurnsToPlayInTheCurrentRound =2;
        this.isActive=true;
        this.socketClientHandler=socketClientHandler;
        this.victoryPoints=new ArrayList<>();
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

    public int getNumOfTurnsToPlayInTheCurrentRound() {return numOfTurnsToPlayInTheCurrentRound;}
    public void setNumOfTurnsToPlayInTheCurrentRound(int numOfTurnsToPlayInTheCurrentRound) {this.numOfTurnsToPlayInTheCurrentRound = numOfTurnsToPlayInTheCurrentRound;}

    public int getFavorTokens() {
        return favorTokens;
    }

    public PrivateObjectiveCard getPrivateObjective() {
        return privateObjective;
    }



    public GameBoardCard getChoosenGameBoard() {
        return choosenGameBoard;
    }

    public ArrayList<GameBoardCard> getDrawnGameBoardCard() {
        return drawnGameBoardCard;
    }

    /**
     * this method returns a GameBoardCard from the title
     * @param title the title of the card chosen
     * @return it returns the gameBoardCard
     */
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

    /**
     * this method is used to apply the use of a tool card; it decrements the favorTokens
     * @param toolCard it returns the toolCard
     * @throws NotEnoughFavorTokensException
     */
    public void useToolCard(ToolCard toolCard) throws NotEnoughFavorTokensException { //per ora si limita a decrementare il numero di segnalini favore
        if(!toolCard.isFirstUsage() && favorTokens>=1) favorTokens--;
        else if(favorTokens>=2) favorTokens-=2; //ramo else, si finisce qui se non è il primo utilizzo della carta. Dunque il costo della carta è di due segnalini favore e bisogna controllare che il giocatore ce li abbia
            else throw new NotEnoughFavorTokensException();
    }

    public void setChoosenWindowBoard(WindowBoard choosenWindowBoard) {
        this.choosenWindowBoard = choosenWindowBoard;
    }

    public void decreaseNumOfTurnsToPlayInTheCurrentRound() throws IllegalNumOfTurnsToPlayInTheCurrentRoundException {
        if(numOfTurnsToPlayInTheCurrentRound>0){
            numOfTurnsToPlayInTheCurrentRound--;
        }
        else throw new IllegalNumOfTurnsToPlayInTheCurrentRoundException();
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addVictoryPoints(Integer points){
        victoryPoints.add(points);
    }

    public ArrayList<Integer> getVictoryPoints() {
        return victoryPoints;
    }
}

