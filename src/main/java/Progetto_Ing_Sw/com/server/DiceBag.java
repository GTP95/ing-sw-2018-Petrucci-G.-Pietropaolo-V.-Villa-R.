package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class DiceBag {  //può essere una classe statica a meno che non si voglia implementare un server che gestisce più partite
                        //oppure si può usare pattern singleton
    private int numDiceRed;
    private int numDiceBlue;
    private int numDicePurple;
    private int numDiceYellow;
    private int numDiceGreen;
    private SplittableRandom splittableRandom;


    public DiceBag(){
        numDiceRed=18;
        numDiceBlue=18;
        numDicePurple=18;
        numDiceYellow=18;
        numDiceGreen=18;
        splittableRandom = new SplittableRandom();
    }



    public Dice diceDraw(){
        int color;
        while(true) {
            color = splittableRandom.nextInt(5);
            if(color==0 && numDiceRed!=0){
                numDiceRed--;
                break;
            }
            if(color==1 && numDiceBlue!=0){
                numDiceBlue--;
                break;
            }
            if(color==2 && numDicePurple!=0){
                numDicePurple--;
                break;
            }
            if(color==3 && numDiceYellow!=0){
                numDiceYellow--;
                break;
            }
            if(color==4 && numDiceGreen!=0){
                numDiceGreen--;
                break;
            }
        }

        return new Dice(splittableRandom.nextInt(1,7), color);  //nextInt(x,y) ritorna valori interi n nell'intervallo x<=n<7
    }

public ArrayList<Dice> diceDraw(int numDiceToDraw){
        ArrayList<Dice> drawnDice=new ArrayList<>();
        for(;numDiceToDraw>0;numDiceToDraw--){
            drawnDice.add(diceDraw());
        }
        return drawnDice;
}

    //NOTA: i seguenti getter sono utili solo per i test

    public int getNumDiceRed() {
        return numDiceRed;
    }

    public int getNumDiceBlue() {
        return numDiceBlue;
    }

    public int getNumDicePurple() {
        return numDicePurple;
    }

    public int getNumDiceYellow() {
        return numDiceYellow;
    }

    public int getNumDiceGreen() {
        return numDiceGreen;
    }
}
