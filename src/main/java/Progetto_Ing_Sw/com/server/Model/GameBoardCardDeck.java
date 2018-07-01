package Progetto_Ing_Sw.com.server.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;

import static Progetto_Ing_Sw.com.tools.JSONCreator.*;

/**
 * <h1>GameBoardCardDeck</h1>
 * The class GameBoardCardDeck implements the deck of window look-a-like
 * cards, used to play.
 *@author Roberto Villa
 */
public class GameBoardCardDeck extends Deck{
    private ArrayList<GameBoardCard> cards;
    private SplittableRandom splittableRandom;

    /**
     * This method builds the deck of GameBoardCard importing the informations form Json file
     * @param folder this parameter represents the folder where the cards' information are stored
     */
    public GameBoardCardDeck(File folder){
        ArrayList<GameBoardCard> cards=new ArrayList<>();
        for(File file : folder.listFiles()){
            try {
                cards.add(gameBoardCardLoaderFromFile(file.getPath()));
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.cards=cards;
        splittableRandom=new SplittableRandom();
    }

    /**
     * This is the method that builds the deck from an ArrayList of GameBoardCards
     * @param cards this parameter represents the single card that is going to be add in the deck
     */
    public GameBoardCardDeck(ArrayList<GameBoardCard> cards) {
        this.cards = cards;
        splittableRandom=new SplittableRandom();
    }

    /**
     * This method overrides the "draw method" of the Abstract Class, Card
     * @return it returns a single card drawn
     */
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
        for (int counter=0;counter<cards.size();counter++)
        {
            if (cards.get(counter).getGameBoardCode()==cardCode)
            {
                cards.remove(counter);//qui si potrà aggiungere la parte che permetta al giocatore di scegliere quale delle due della coppia
            }
        }
        return card;
    }

    /**
     * This method overrides the "multiple draw method" of the Abstract Class, Card
     * @param numOfCards this parameter represents the number of card to be drawn
     * @return
     */
    @Override
    public ArrayList<Card> draw(int numOfCards){
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<numOfCards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }

    /**
     * This method draws a GameBoardCard, and also removes the back of this cad from the deck, to follow the fact that
     * GameBoardCards have front and rear
     * @return it returns a GameBoardCard(single side)
     */
    public ArrayList<GameBoardCard> drawFrontRear(){

        GameBoardCard card=null;
        ArrayList<GameBoardCard> cardChoosed = new ArrayList<>();
        int index=0;
        int cardCode;

        while(card==null) {  //isEmpty?
            index = splittableRandom.nextInt(0, cards.size());
            card = cards.get(index);
        }

        card=cards.remove(index);
        cardChoosed.add(card);
        cardCode=card.getOtherSideCode();
        for (int counter=0;counter<cards.size();counter++)
        {
            if (cards.get(counter).getGameBoardCode()==cardCode)
            {
                cardChoosed.add(cards.get((counter)));
                cards.remove(counter);//qui si potrà aggiungere la parte che permetta al giocatore di scegliere quale delle due della coppia
            }
        }
        return cardChoosed;
    }

    /**
     * This method draws multiple GameBoardCards, and also removes the back of these card from the deck, to follow the
     * fact that GameBoardCards have front and rear
     * @param num this parameter represents the number of card to be drawn
     * @return it returns an ArrayList of GameBoardCards
     */
    public ArrayList<GameBoardCard> drawMultipleFrontRear(int num){

        ArrayList<GameBoardCard> localCardChoosed;
        ArrayList<GameBoardCard> cardsDrawn = new ArrayList<>();

        for(int i=0;i<num*2;i=i+2){
            localCardChoosed=drawFrontRear();
            cardsDrawn.add(i,localCardChoosed.get(0));//aggiungo il fronte
            cardsDrawn.add(i+1,localCardChoosed.get(1));//aggiungo il retro
        }
        return cardsDrawn;
    }
}
