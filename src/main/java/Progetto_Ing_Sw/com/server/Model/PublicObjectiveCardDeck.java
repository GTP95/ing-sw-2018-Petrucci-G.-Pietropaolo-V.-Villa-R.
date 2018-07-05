package Progetto_Ing_Sw.com.server.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;
import static Progetto_Ing_Sw.com.tools.JSONCreator.*;

/**
 * this class implements a deck of PublicObjectiveCards
 */
public class PublicObjectiveCardDeck extends Deck {
    private ArrayList<PublicObjectiveCard> cards;
    private SplittableRandom splittableRandom;

    /**
     * this method creates a PublicObjectiveCardDeck from JSON files, following a path from a folder
     * @param folder it's the folder where there are the paths
     */
    public PublicObjectiveCardDeck(File folder){    //folder è il percorso alla cartella con i JSON da caricare
        ArrayList<PublicObjectiveCard> cards=new ArrayList<>();
        for(File file : folder.listFiles()){
            try {
                cards.add(publicObjectiveCardLoaderFromFile(file.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();    //TODO: controllare alternative
            }
        }
        this.cards=cards;

        splittableRandom=new SplittableRandom();
    }

    public PublicObjectiveCardDeck(ArrayList<PublicObjectiveCard> cards){
        this.cards=cards;
        this.splittableRandom=new SplittableRandom();
    }

    /**
     * This method overrides the "draw method" of the Abstract Class, Card
     * @return it returns a single card drawn
     */
    @Override
    public PublicObjectiveCard draw(){  //restituisce un acarta pesacata dal mazzo
        PublicObjectiveCard card=null;
        int index=0;

        while(card==null){  //isEmpty?
            index=splittableRandom.nextInt(0,cards.size());
            card=cards.get(index);
        }
        cards.remove(index);
        return card;
    }

    /**
     * This method overrides the "multiple draw method" of the Abstract Class, Card
     * @param cards this parameter represents the number of card to be drawn
     * @return
     */
    @Override
    public ArrayList<Card> draw(int cards){                 //restituisce un ArrayList contenente il numero di carte specificato
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }

    /**
     * this methoed returns a list of PublicObjectiveCard that is specified by the param cards
     * @param cards is the number of card to draw
     * @return a list of PublicObjectiveCards
     */
    public ArrayList<PublicObjectiveCard> drawPublicObjectiveCards(int cards){  //restituisce un ArrayList contenente il numero di carte specificato
        ArrayList<PublicObjectiveCard> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }


}
