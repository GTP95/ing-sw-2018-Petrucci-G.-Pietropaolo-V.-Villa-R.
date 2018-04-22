package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class GameBoardCardDeck extends Deck{
    private ArrayList<GameBoardCard> cards;

    public GameBoardCardDeck(ArrayList<GameBoardCard> cards) { this.cards = cards;}

    @Override
    public GameBoardCard draw(){
        GameBoardCard card=null;
        SplittableRandom splittableRandom = new SplittableRandom();
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
