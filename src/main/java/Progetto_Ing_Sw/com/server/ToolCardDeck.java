package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class ToolCardDeck extends Deck{
    private ArrayList<ToolCard> cards;

    public ToolCardDeck(ArrayList<ToolCard> cards){
        this.cards=cards;
    }

    public ToolCard draw(){
        ToolCard card=null;
        SplittableRandom splittableRandom = new SplittableRandom();
        int index=0;

        while(card==null){  //isEmpty?
            index=splittableRandom.nextInt(0,cards.size());
            card=cards.get(index);
        }
        cards.remove(index);
        return card;
    }

    public ArrayList<Card> draw(int cards){
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }
}
