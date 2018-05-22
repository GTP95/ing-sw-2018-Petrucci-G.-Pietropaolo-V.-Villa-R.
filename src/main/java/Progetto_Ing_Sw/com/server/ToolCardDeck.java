package Progetto_Ing_Sw.com.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.SplittableRandom;

import static Progetto_Ing_Sw.com.tools.JSONCreator.toolCardLoaderFromFile;

public class ToolCardDeck extends Deck{
    private ArrayList<ToolCard> cards;

    public ToolCardDeck(File folder){    //folder è il percorso alla cartella con i JSON da caricare
        ArrayList<ToolCard> cards=new ArrayList<>();
        for(File file :Objects.requireNonNull(folder.listFiles())){
            try {
                cards.add(toolCardLoaderFromFile(file.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();    //TODO: controllare alternative
            }
        }
        this.cards=cards;
    }

    public ToolCardDeck(ArrayList<ToolCard> toolCardArrayList){
        cards=toolCardArrayList;
    }

    //TODO aggiungere/modificare il costruttore al fine di poter eseguire tutte le classi di test

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

    public ArrayList<ToolCard> drawToolCards(int cards){
        ArrayList<ToolCard> cardsArrayList=new ArrayList<>();
        for(int counter=0;counter<cards;counter++){
            cardsArrayList.add(draw());
        }
        return cardsArrayList;
    }
}
