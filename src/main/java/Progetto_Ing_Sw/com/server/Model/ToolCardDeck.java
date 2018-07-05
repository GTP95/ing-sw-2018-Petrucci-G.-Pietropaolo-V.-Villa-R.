package Progetto_Ing_Sw.com.server.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.SplittableRandom;

import static Progetto_Ing_Sw.com.tools.JSONCreator.toolCardLoaderFromFile;

/**
 * this class implements a deck of ToolCard
 */

public class ToolCardDeck extends Deck{
    private ArrayList<ToolCard> cards;

    /**
     * this method creates a toolCardDeck from JSON files, following a path from a folder
     * @param folder it's the folder where there are the paths
     */
    public ToolCardDeck(File folder){    //folder è il percorso alla cartella con i JSON da caricare
        ArrayList<ToolCard> cards=new ArrayList<>();
        for(File file :Objects.requireNonNull(folder.listFiles())){
            try {
                cards.add(toolCardLoaderFromFile(file.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.cards=cards;
    }

    public ToolCardDeck(ArrayList<ToolCard> toolCardArrayList){
        cards=toolCardArrayList;
    }

    /**
     * this method is used to draw a single card from the deck
     * @return it returns a toolCard
     */
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

    /**
     * this method return a list of card
     * @param cards it's the number of card returned
     * @return it returns a list of card
     */
    public ArrayList<Card> draw(int cards){
        ArrayList<Card> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }

    /**
     * this method return a list of toolcard
     * @param cards it's the number of toolcard returned
     * @return it returns a list of toolcard
     */
    public ArrayList<ToolCard> drawToolCards(int cards){
        ArrayList<ToolCard> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }
}
