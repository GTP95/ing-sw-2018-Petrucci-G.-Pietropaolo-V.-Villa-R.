package Progetto_Ing_Sw.com.server;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class GameBoardCardDeck extends Deck{
    private ArrayList<GameBoardCard> cards;
    private SplittableRandom splittableRandom;

    public GameBoardCardDeck(ArrayList<GameBoardCard> cards) {
        this.cards = cards;
        splittableRandom=new SplittableRandom();
    }

    @Override
    public GameBoardCard draw(){
        GameBoardCard card=null;
        int index=0;
        int cardCode;

        while(card==null) {  //isEmpty?
            index = splittableRandom.nextInt(0, cards.size());
            card = cards.get(index);
        }

        card=cards.remove(index);
        cardCode=card.getOtherSideCode();
        for (int counter=0;counter<cards.size();counter++){//deve essere = in quanto ragiona sugli indici
            if (cards.get(counter).getGameBoardCode()==cardCode) {
                cards.remove(counter);//qui si potrà aggiungere la parte che permetta al giocatore di scegliere quale delle due della coppia
            }
        }
        return card;
    }

    @Override
    public ArrayList<Card> draw(int numOfCards){
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<numOfCards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }
}
