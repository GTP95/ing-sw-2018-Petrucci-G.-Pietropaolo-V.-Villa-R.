package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;

public class Table {

    private ArrayList<PublicObjectiveCard> DrawnPublicObjectiveCards;
    private ArrayList<ToolCard> DrawnToolCards;
    private ToolCardDeck toolCardDeck;
    private PublicObjectiveCardDeck publicObjectiveCardDeck;

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
}

