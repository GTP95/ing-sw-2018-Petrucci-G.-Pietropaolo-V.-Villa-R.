package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class PublicObjectiveCardDeck extends Deck {
    private ArrayList<PublicObjectiveCard> cards;
    private SplittableRandom splittableRandom;

    public PublicObjectiveCardDeck(ArrayList<PublicObjectiveCard> cards){

        this.cards=cards;
        splittableRandom=new SplittableRandom();
    }

    @Override
    public PublicObjectiveCard draw(){
        PublicObjectiveCard card=null;
        int index=0;

        while(card==null){  //isEmpty?
            index=splittableRandom.nextInt(0,cards.size());
            card=cards.get(index);
        }
        cards.remove(index);
        return card;
    }

    @Override
    public ArrayList<Card> draw(int cards){
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }


}
