package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class ShadeVariety {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/ShadeVariety.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private int victoryPoints;
    public ShadeVariety() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        int one=0;
        int two=0;
        int three=0;
        int four=0;
        int five=0;
        int six=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for (int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed()==true)
                {
                    switch(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()){
                    case(1):one++;break;
                    case(2):two++;break;
                    case(3):three++;break;
                    case(4):four++;break;
                    case(5):five++;break;
                    case(6):six++;break;
                    }
                }
            }
        }

        int[] shades = new int[6];
        shades[0]=one;
        shades[1]=two;
        shades[2]=three;
        shades[3]=four;
        shades[4]=five;
        shades[5]=six;

        int temp = 0;

        //BubbleSort per prendere il valore minore, che mi indicherà quante volte ho avuto il "set completo"
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < (6 - i); j++) {
                if (shades[j - 1] > shades[j]) {
                    temp = shades[j - 1];
                    shades[j - 1] = shades[j];
                    shades[j] = temp;
                }
            }
        }

        points=shades[0]*getVictoryPoints();

        return points;
    }
}
