package Progetto_Ing_Sw.com.server.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;

import static Progetto_Ing_Sw.com.tools.JSONCreator.privateObjectiveCardLoaderFromFile;


public class PrivateObjectiveCardDeck extends Deck{

    ArrayList<PrivateObjectiveCard> privateObjectiveCards;
    SplittableRandom splittableRandom;

    private static PrivateObjectiveCardDeck ourInstance = new PrivateObjectiveCardDeck(new File("Resources/Cards/PrivateObjectiveCards"));

    public static PrivateObjectiveCardDeck getInstance() {
        return ourInstance;
    }

    private PrivateObjectiveCardDeck(File folder) {
        ArrayList<PrivateObjectiveCard> cards=new ArrayList<>();
        for(File file : folder.listFiles()){
            try {
                cards.add(privateObjectiveCardLoaderFromFile(file.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();    //TODO: controllare alternative
            }
        }
        this.privateObjectiveCards=cards;

        splittableRandom=new SplittableRandom();
    }

    @Override
    public PrivateObjectiveCard draw(){
        PrivateObjectiveCard card=null;
        int index=0;

        while(card==null){  //isEmpty?
            index=splittableRandom.nextInt(0,privateObjectiveCards.size());
            card=privateObjectiveCards.get(index);
        }
        privateObjectiveCards.remove(index);
        return card;
    }


}
