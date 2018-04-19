package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;
import java.util.SplittableRandom;

public abstract class Deck {
    private ArrayList<Card> cards;

    public Card draw(){
        Card card=null;
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
