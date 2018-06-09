package Progetto_Ing_Sw.com.server.Model;

import java.io.File;
import java.util.ArrayList;

public class Table implements TableObserver {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private static ToolCardDeck toolCardDeck=new ToolCardDeck(new File("Resources/Cards/ToolCards"));
    private static PublicObjectiveCardDeck publicObjectiveCardDeck=new PublicObjectiveCardDeck(new File("Resources/Cards/PublicObjectiveCards"));
    private ArrayList<Dice> drawnDice;
    private static DiceBag diceBag=new DiceBag();
    private static Table ourInstance=new Table();
    private static ArrayList<Player> players;
    
    private Table(){
    	int numPlayers=Lobby.getInstance().getNumOfPlayers();
	    drawnDice=diceBag.diceDraw(2*numPlayers+1);
	    drawnPublicObjectiveCards=publicObjectiveCardDeck.drawPublicObjectiveCards(3);
	    drawnToolCards=toolCardDeck.drawToolCards(3);
	    players=Lobby.getInstance().getConnctedPlayers();
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

    public void startGame(){
        System.out.println("Game started!");    //TODO: completare
    }

}

