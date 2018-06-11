package Progetto_Ing_Sw.com.server.Model;

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
    private static DiceBag diceBag=new DiceBag();


    public DiceBag(){
        numDiceRed=18;
        numDiceBlue=18;
        numDicePurple=18;
        numDiceYellow=18;
        numDiceGreen=18;
        splittableRandom = new SplittableRandom();
    }

  /*  public DiceBag getInstance(){
        return diceBag;
    }*/


    public Dice diceDraw(){
        int color;
        while(true) {
            color = splittableRandom.nextInt(1,6);
            if(color==Color.RED && numDiceRed!=0){
                numDiceRed--;
                break;
            }
            if(color==Color.BLUE && numDiceBlue!=0){
                numDiceBlue--;
                break;
            }
            if(color==Color.PURPLE && numDicePurple!=0){
                numDicePurple--;
                break;
            }
            if(color==Color.YELLOW && numDiceYellow!=0){
                numDiceYellow--;
                break;
            }
            if(color==Color.GREEN && numDiceGreen!=0){
                numDiceGreen--;
                break;
            }
        }

        return new Dice(splittableRandom.nextInt(1,7), color);  //nextInt(x,y) ritorna valori interi n nell'intervallo x<=n<y, serve per avere il valore numerico del dado
    }

public ArrayList<Dice> diceDraw(int numDiceToDraw){
        ArrayList<Dice> drawnDice=new ArrayList<>();
        for(;numDiceToDraw>0;numDiceToDraw--){
            drawnDice.add(diceDraw());
        }
        return drawnDice;
}

public void returnDice(Dice dice) throws IllegalDiceException{      //"Restituisce" il dado specificato nel senso che viene reinserito nel sacchetto
        switch(dice.getColor()){
            case Color.RED:
                if(numDiceRed<18) numDiceRed++; //Controllo il numero di dadi prima del reinserimento per evitare inconsistenze
                else throw new IllegalDiceException("The specified dice is not a valid dice (wrong color number)");
                break;
            case Color.BLUE:
                if(numDiceBlue<18) numDiceBlue++;
                else throw new IllegalDiceException("The specified dice is not a valid dice (wrong color number)");
                break;
            case Color.PURPLE:
                if(numDicePurple<18) numDicePurple++;
                else throw new IllegalDiceException("The specified dice is not a valid dice (wrong color number)");
                break;
            case Color.YELLOW:
                if(numDiceYellow<18) numDiceYellow++;
                else throw new IllegalDiceException("The specified dice is not a valid dice (wrong color number)");
                break;
            case Color.GREEN:
                if (numDiceGreen<18) numDiceGreen++;
                else throw new IllegalDiceException("The specified dice is not a valid dice (wrong color number)");
                break;
            default:
                throw new IllegalDiceException("The specified dice is not a valid dice (invalid color number)");
        }
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
