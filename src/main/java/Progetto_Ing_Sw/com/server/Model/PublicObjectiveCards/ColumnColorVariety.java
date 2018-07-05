package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * <h1>ColumnColorVariety</h1>
 *  The class ColumnColorVariety implements the PublicObjectiveCard ColumnColorVariety
 *  @author Roberto Villa
 */

public class ColumnColorVariety {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/ColumnColorVariety.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------


    private int victoryPoints;
    public ColumnColorVariety() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    /**
     * this methods calculates points (on the windowBoard) following the card
     * @param localBoard is the board where i calculate the points
     * @return it returns the number of points calculated
     */
    public int calculatePoints(WindowBoard localBoard) {

        int points = 0;
        int red = 0;
        int blue = 0;
        int purple = 0;
        int yellow = 0;
        int green = 0;

        for(int colonne=0;colonne<localBoard.getUsedMatrix().get(0).size();colonne++){
            for (int r=0;r<localBoard.getUsedMatrix().size();r++){
                if(localBoard.getUsedMatrix().get(r).get(colonne).isUsed()==false){
                    //non faccio nulla, salto la riga
                    System.out.println("Non ho fatto nulla... salto la colonna");
                    break;
                }
                else{
                    switch(localBoard.getUsedMatrix().get(r).get(colonne).getDiceContained().getColor()){
                        case(Color.RED): red++;break;
                        case(Color.BLUE): blue++;break;
                        case(Color.PURPLE): purple++;break;
                        case(Color.YELLOW): yellow++;break;
                        case(Color.GREEN): green++;break;
                    }
                }
                System.out.println("COLUMN #"+colonne+" has:");
                System.out.println("********************");
                System.out.println("#RED :"+red);
                System.out.println("#BLUE :"+blue);
                System.out.println("#PURPLE :"+purple);
                System.out.println("#YELLOW :"+yellow);
                System.out.println("#GREEN :"+green);
                System.out.println();
            }
            if((red==1 && blue==1 && purple ==1 && yellow==1 && green ==0)||
                    (red==1 && blue==1 && purple ==1 && yellow==0 && green ==1)||
                    (red==1 && blue==1 && purple ==0 && yellow==1 && green ==1)||
                    (red==1 && blue==0 && purple ==1 && yellow==1 && green ==1)||
                    (red==0 && blue==1 && purple ==1 && yellow==1 && green ==1)){
                points=points+getVictoryPoints();
            }
            red=0;
            blue=0;
            purple=0;
            yellow=0;
            green=0;
        }
        return points;
    }
}
