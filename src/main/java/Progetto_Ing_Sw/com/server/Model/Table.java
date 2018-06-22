package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.Lobby;

import java.io.File;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Table {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private static ToolCardDeck toolCardDeck=new ToolCardDeck(new File("Resources/Cards/ToolCards"));
    private static PublicObjectiveCardDeck publicObjectiveCardDeck=new PublicObjectiveCardDeck(new File("Resources/Cards/PublicObjectiveCards"));
    private static PrivateObjectiveCardDeck privateObjectiveCardDeck=PrivateObjectiveCardDeck.getInstance();    //Il caricamento da file viene effettuato all'interno della classe stessa
    private static GameBoardCardDeck gameBoardCardDeck=new GameBoardCardDeck(new File("Resources/Cards/GameBoardCards"));
    private ArrayList<Dice> drawnDice;
    private static DiceBag diceBag=new DiceBag();
    private static Table ourInstance=new Table();
    private static ArrayList<Player> players;
    private int currentPlayer;//indice del giocatore che sta giocando
    public static volatile boolean gameRunning=false;   //è volatile per via dell'accesso concorrente da parte di più thread che potrebberio leggerne il valore proprio mentre sta cambiando
    
    private Table(){
    	int numPlayers=Lobby.getInstance().getNumOfPlayers();
	    drawnDice=diceBag.diceDraw(2*numPlayers+1);
	    drawnPublicObjectiveCards=publicObjectiveCardDeck.drawPublicObjectiveCards(3);
	    drawnToolCards=toolCardDeck.drawToolCards(3);
	    players=Lobby.getInstance().getConnctedPlayers();
    }

    public static ArrayList<Player> getPlayers() {return players;}

    public Player getPlayerFromName(String name) throws InvalidUsernameException {
        for(Player player : players){
            if(player.getName().equals(name)){
                return player;
            }
        }
        throw new InvalidUsernameException("The player you're looking for doesn't exists!");
    }

    public Player getActivePlayer(){
        ArrayList<Player> clonePlayers=getPlayers();
        Player selectedPlayer = clonePlayers.get(currentPlayer);
        return selectedPlayer;
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
        randomizePlayerArray();
        for(Player player : players){   //inizializza i giocatori assegnadoli il loro obbiettivo privato e le GmaeBoardCard tra cui scegliere
            player.setPrivateObjective(privateObjectiveCardDeck.draw());
            player.setDrawnGameBoardCard(gameBoardCardDeck.drawMultipleFrontRear(3));
        }
        System.out.println("Game started!");    //TODO: completare
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

    private ArrayList<Player> shufflePlayerArray(){
        ArrayList<Player>arrayToReturn=new ArrayList<>(players.size());
        for(int index1=0,index2=players.size()-1;index1<players.size();index1++, index2--){
            arrayToReturn.add(index2,players.get(index1));
        }
        return arrayToReturn;
    }

    private void changeCurrentPlayer(){ //Imposta il valore currentplayer all'indice dell'arraylist che contiene il giocatore del turno che sta per cominciare
        if(currentPlayer==players.size()-1){
            currentPlayer=0;
            return;
        }
        currentPlayer++;
    }

    private void randomizePlayerArray(){
        ArrayList<Player> randomizedArray=new ArrayList<>(players.size());
        SplittableRandom splittableRandom=new SplittableRandom();
        int index;
        for(Player player : players){
            index=splittableRandom.nextInt(0,players.size());
            while(randomizedArray.get(index)!=null) index=splittableRandom.nextInt(0,players.size());
            randomizedArray.add(index,player);
        }
        players=randomizedArray;
    }
}

