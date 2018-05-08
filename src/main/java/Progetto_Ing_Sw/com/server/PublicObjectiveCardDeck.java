package Progetto_Ing_Sw.com.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;
import static Progetto_Ing_Sw.com.tools.JSONCreator.*;

public class PublicObjectiveCardDeck extends Deck {
    private ArrayList<PublicObjectiveCard> cards;
    private SplittableRandom splittableRandom;

    public PublicObjectiveCardDeck(File folder){    //folder è il percorso alla cartella con i JSON da caricare
        ArrayList<PublicObjectiveCard> cards=new ArrayList<>();
        for(File file : folder.listFiles()){
            try {
                cards.add(publicObjectiveCardLoader(file.getPath()));
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
