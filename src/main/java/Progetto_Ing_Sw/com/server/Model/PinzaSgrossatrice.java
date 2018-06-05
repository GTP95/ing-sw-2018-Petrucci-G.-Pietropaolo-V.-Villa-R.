package Progetto_Ing_Sw.com.server.Model;

import java.util.ArrayList;

public class PinzaSgrossatrice implements ToolCardEffect {

    Dice dice;
    public PinzaSgrossatrice(Dice dice) {
        this.dice= dice;
    }

    @Override
    public ArrayList<ArrayList<MatrixCell>> applyEffect(ArrayList<ArrayList<MatrixCell>> Matrix) {

        System.out.println("Il valore del dado scelto è :"+dice.getValue());

        if(dice.getValue()==1){
            System.out.println("Puoi solo incrementare  il dado di uno");
            dice.setValue(dice.getValue()+1);
        }
        if(dice.getValue()==6){
            System.out.println("Puoi solo decrementare  il dado di uno");
            dice.setValue(dice.getValue()-1);}

        return Matrix;
    }
}
