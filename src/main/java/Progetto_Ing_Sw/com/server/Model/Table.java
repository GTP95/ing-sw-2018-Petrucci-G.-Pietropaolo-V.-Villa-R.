package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.Lobby;
import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SplittableRandom;
import java.util.concurrent.CopyOnWriteArrayList;

public class Table {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private static ToolCardDeck toolCardDeck=new ToolCardDeck(new File("Resources/Cards/ToolCards"));
    private static PublicObjectiveCardDeck publicObjectiveCardDeck=new PublicObjectiveCardDeck(new File("Resources/Cards/PublicObjectiveCards"));
    private static PrivateObjectiveCardDeck privateObjectiveCardDeck=PrivateObjectiveCardDeck.getInstance();    //Il caricamento da file viene effettuato all'interno della classe stessa
    private static GameBoardCardDeck gameBoardCardDeck=new GameBoardCardDeck(new File("Resources/Cards/GameBoardCards"));
    private ArrayList<Dice> drawnDice;
    private static final DiceBag diceBag=new DiceBag();
    private static final Table ourInstance=new Table();
    private static CopyOnWriteArrayList<Player> players;
    private int currentPlayer;//indice del giocatore che sta giocando
    public static volatile boolean gameRunning=false;   //è volatile per via dell'accesso concorrente da parte di più thread che potrebberio leggerne il valore proprio mentre sta cambiando
    private int numOfSetWindowBoards, numOfTurnsPlayedInCurrentRound;
    private CopyOnWriteArrayList<Player> mirrorArray;


    private Table() {
        int numPlayers = Lobby.getInstance().getNumOfPlayers();
        drawnDice = diceBag.diceDraw(2 * numPlayers + 1);
        drawnPublicObjectiveCards = publicObjectiveCardDeck.drawPublicObjectiveCards(3);
        drawnToolCards = toolCardDeck.drawToolCards(3);
        players = Lobby.getInstance().getConnctedPlayers();
        numOfSetWindowBoards = 0;
        numOfTurnsPlayedInCurrentRound=0;
    }

    public static CopyOnWriteArrayList<Player> getPlayers() {
        /*ArrayList<Player> playersToReturn;
        playersToReturn=(ArrayList<Player>) players.clone();*/
        return players;
    }

    public Player getPlayerFromName(String name) throws InvalidUsernameException {
        for(Player player : players){
            if(player.getName().equals(name)){
                return player;
            }
        }
        throw new InvalidUsernameException("The player you're looking for doesn't exists!");
    }

    public Player getActivePlayer(){
       /* ArrayList<Player> clonePlayers=getPlayers();
        Player selectedPlayer = clonePlayers.get(currentPlayer);
        return selectedPlayer;*/
        return mirrorArray.get(currentPlayer);
    }

    public static Table getOurInstance(){
        return ourInstance;
    }

    public ArrayList<PublicObjectiveCard> getDrawnPublicObjectiveCards() {  //restituisce una copia dell'ArrayList per evitare modifiche all'esterno della classe
        ArrayList<PublicObjectiveCard> cloneArrayList=new ArrayList<>();

        for(PublicObjectiveCard card : drawnPublicObjectiveCards){
            cloneArrayList.add(card);
        }

        return cloneArrayList;
    }

    public ArrayList<ToolCard> getDrawnToolCards() {    //restituisce una copia dell'ArrayList per evitare modifiche all'esterno della classe
        ArrayList<ToolCard> cloneArrayList=new ArrayList<>();

        for(ToolCard card : drawnToolCards){
            cloneArrayList.add(card);
        }

        return cloneArrayList;
    }
    
    public ArrayList<Dice> getDrawnDice(){
    	ArrayList<Dice> clone=new ArrayList();
    	for(Dice dice : drawnDice) clone.add(dice);
    	return clone;
    }

    public Boolean removeDice(Dice dice){
        if(drawnDice.contains(dice)){
            int index=drawnDice.indexOf(dice);
            drawnDice.get(index).setValue(0);
            SocketClientHandler socketClientHandler;
            for(Player player:players){
                socketClientHandler=player.getSocketClientHandler();
                socketClientHandler.updateDice=true;
                socketClientHandler.ourThread.interrupt();   //invia notifica ai thread dei SocketClientHandler
            }
            return true;
        }
        return false;
    }

    public Dice getChoosenDice(Dice dice) throws IllegalDiceException{      //Estrae il dado specificato prendendolo tra quelli disponibili. Se il dado indicato non è disponibile lancia eccezione
        for(Dice die : drawnDice){
            if(die.equals(dice)){
                drawnDice.remove(die);
                return die;
            }
        }
        throw new IllegalDiceException();
    }

    public void returnDice(Dice dice){  //"Restituisce" il dado, nel senso che viene riposto nuovamente sul tavolo di gioco a disposizione dei giocatori
        Dice cloneDice=new Dice(dice.getValue(),dice.getColor());   //fa una copia del dado per evitare modifiche del dado al di fuori di questa classe
        drawnDice.add(cloneDice);
    }

    public boolean diceExists(Dice dice){
        return drawnDice.contains(dice);
    }



    public void startGame(){
        gameRunning=true;
       // players=Lobby.getInstance().getConnctedPlayers();
   //  Collections.shuffle(players);  //Ordine casuale dei giocatori per il primo turno.
        currentPlayer=0;
        buildMirrorArray();
        for(Player player : players){   //inizializza i giocatori assegnadoli il loro obbiettivo privato e le GmaeBoardCard tra cui scegliere
            player.setPrivateObjective(privateObjectiveCardDeck.draw());
            player.setDrawnGameBoardCard(gameBoardCardDeck.drawMultipleFrontRear(3));
        }
        System.out.println("Game started!");
        System.out.println("Connected players:");
        for(Player player : players){
            System.out.println(player.getName());
        }
        System.out.println();
        System.out.println("Cards:");
        for (PublicObjectiveCard card : drawnPublicObjectiveCards){
            System.out.println(card.getTitle());
        }
        System.out.println();
        System.out.println("ToolCards:");
        for (ToolCard card : drawnToolCards){
            System.out.println(card.getTitle());
        }
        System.out.println();
        System.out.println("Dice:");
        for(Dice dice : drawnDice){
            System.out.println("Color: "+dice.getColor()+" value: "+dice.getValue());
        }
    }

    public void addDiceFluxBrush(Dice diceRejectedByInsert){
        Dice cloneDice=new Dice(diceRejectedByInsert.getValue(),diceRejectedByInsert.getColor());
        drawnDice.add(cloneDice);
    }

    private CopyOnWriteArrayList<Player> shufflePlayerArray(){ //inverte l'array dei giocatori
        CopyOnWriteArrayList<Player>arrayToReturn=new CopyOnWriteArrayList<>();
        for(int index=players.size()-1;index>=0;index--){
            arrayToReturn.add(players.get(index));
        }
        return arrayToReturn;
    }

    public void changeCurrentPlayer() {//Imposta il valore currentplayer all'indice dell'arraylist che contiene il giocatore del turno che sta per cominciare
        if(currentPlayer<2*players.size()) currentPlayer++;
        else prepareForNextRound();
        System.out.println("indexOfCurrentPlayer: "+currentPlayer);
    }


    public void notifyWindowBoardChange(Thread notifierThread){
        for(Player player : players){
            if(player.getSocketClientHandler().ourThread!=notifierThread){          //in questo caso posso limitarmi a confrontare i riferimenti anzichè usare la equals()
                player.getSocketClientHandler().updateWindowBoards=true;
                player.getSocketClientHandler().ourThread.interrupt();
            }
        }
    }

    public void setChoosenGameBoardCard (String playerName, String gameBoardCardTitle) {
        try {
            getPlayerFromName(playerName).setChoosenGameBoard(getPlayerFromName(playerName).getGameBoardCardFromTitle(gameBoardCardTitle));
            numOfSetWindowBoards++;
            if (numOfSetWindowBoards == players.size()){
                for(Player player:players) player.getSocketClientHandler().updateWindowBoards=true;
                notifyAllSocketClientHandlers();
            }
        }
        catch(InvalidUsernameException e){
            System.err.println(e.getMessage());
        }
    }

    private void notifyAllSocketClientHandlers(){
        for (Player player : players){
            player.getSocketClientHandler().ourThread.interrupt();
        }
    }

private void prepareForNextRound(){     //Cambia l'ordine di gioco dei giocatori al cambio di round
        CopyOnWriteArrayList<Player> newPlayerArray=new CopyOnWriteArrayList<>();
        for(int index=1;index<players.size();index++){
            newPlayerArray.add(index-1,players.get(index));
        }
        newPlayerArray.add(players.get(0));
        players=newPlayerArray;
        buildMirrorArray();
        currentPlayer=0;
}

private void buildMirrorArray(){
        CopyOnWriteArrayList<Player> mirrorArray=new CopyOnWriteArrayList<>();
        int index1,index2;
        for(index1=0;index1<players.size();index1++) mirrorArray.add(players.get(index1));

        for(index2=players.size()-1;index2>=0;index2--) mirrorArray.add(players.get(index2));
        this.mirrorArray=mirrorArray;
        System.out.println("Printing mirrorarray");
        for(Player player:mirrorArray)
            System.out.print(player.getName()+" ");
}

    public CopyOnWriteArrayList<Player> getMirrorArray() {
        return mirrorArray;
    }
}

