package Progetto_Ing_Sw.com.server;

import java.io.File;
import java.util.ArrayList;

public class Table {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private static ToolCardDeck toolCardDeck=new ToolCardDeck(new File("/Resources/Cards/ToolCards"));
    private static PublicObjectiveCardDeck publicObjectiveCardDeck=new PublicObjectiveCardDeck(new File("/Resources/Cards/PublicObjectiveCards"));
    private ArrayList<Dice> drawnDice;
    private static Table ourInstance=new Table();
    private static DiceBag diceBag=new DiceBag();
    private static ArrayList<Player> players;
    
    private Table(){
    	int numPlayers=Lobby.getInstance().getNumOfPlayers();
	    drawnDice=diceBag.diceDraw(2*numPlayers+1);
	drawnPublicObjectiveCards=publicObjectiveCardDeck.drawPublicObjectiveCards(3);
	drawnToolCards=toolCardDeck.drawToolCards(3);
    }

    public ToolCardDeck getToolCardDeck() {
        return toolCardDeck;
    }

    public PublicObjectiveCardDeck getPublicObjectiveCardDeck() {
        return publicObjectiveCardDeck;
    }

    public ArrayList<PublicObjectiveCard> getDrawnPublicObjectiveCards() {
        PublicObjectiveCard card;

        for (int counter=0; counter<4; counter ++){
            card=publicObjectiveCardDeck.draw();
            drawnPublicObjectiveCards.add(card);
        }
        return drawnPublicObjectiveCards;
    }

    public ArrayList<ToolCard> getDrawnToolCards() {
        ToolCard card;

        for (int counter=0; counter<4; counter ++){
            card=toolCardDeck.draw();
            drawnToolCards.add(card);
        }
        return drawnToolCards;
    }
    
    public ArrayList<Dice> getDrawnDice(){
    	ArrayList<Dice> clone=new ArrayList();
    	for(Dice dice : drawnDice) clone.add(dice);
    	return clone;
    }
    
}

