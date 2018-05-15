package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;

public class Table {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private ToolCardDeck toolCardDeck;
    private PublicObjectiveCardDeck publicObjectiveCardDeck;
    private ArrayList<Dice> drawnDice;
    private static Table ourInstance=new Table();
    
    private Table(){
    	int numPlayers=Lobby.getInstance.getNumOfPlayers();
	drawnDice=diceBag.drawDice(2*numPlayers+1);
	drawnPublicObjectiveCards=publicObjectiveCardDeck.draw(3);
	DrawnToolCards=toolCardsDeck.draw(3);
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
            DrawnPublicObjectiveCards.add(card);
        }
        return DrawnPublicObjectiveCards;
    }

    public ArrayList<ToolCard> getDrawnToolCards() {
        ToolCard card;

        for (int counter=0; counter<4; counter ++){
            card=toolCardDeck.draw();
            DrawnToolCards.add(card);
        }
        return DrawnToolCards;
    }
    
    public ArrayList<Dice> getDrawnDice(){
    	ArrayList<Dice> clone=new ArrayList()<>;
    	for(Dice dice : drawnDice) clone.add(dice);
    	return clone;
    }
    
}

