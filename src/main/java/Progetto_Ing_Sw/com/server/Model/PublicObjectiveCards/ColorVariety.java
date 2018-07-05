package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * <h1>ColorVariety</h1>
 *  The class ColorVariety implements the PublicObjectiveCard ColorVariety
 *  @author Roberto Villa
 */
public class ColorVariety {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/ColorVariety.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private int victoryPoints;
    public ColorVariety() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    /**
     * this methods calculates points (on the windowBoard) following the card
     * @param localBoard is the board where i calulate the points
     * @return it returns the number of points calculated
     */
    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        int red = 0;
        int blue = 0;
        int purple = 0;
        int yellow = 0;
        int green = 0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for (int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed()==true)
                {
                    switch(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getColor()){
                        case(Color.RED): red++;break;
                        case(Color.BLUE): blue++;break;
                        case(Color.PURPLE): purple++;break;
                        case(Color.YELLOW): yellow++;break;
                        case(Color.GREEN): green++;break;
                    }
                }
            }
        }

        int[] colors = new int[5];
        colors[0]=red;
        colors[1]=blue;
        colors[2]=purple;
        colors[3]=yellow;
        colors[4]=green;

        int temp = 0;

        //BubbleSort per prendere il valore minore, che mi indicherà quante volte ho avuto il "set completo"
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < (5 - i); j++) {
                if (colors[j - 1] > colors[j]) {
                    temp = colors[j - 1];
                    colors[j - 1] = colors[j];
                    colors[j] = temp;
                }
            }
        }

        points=colors[0]*getVictoryPoints();

        return points;
    }
}
