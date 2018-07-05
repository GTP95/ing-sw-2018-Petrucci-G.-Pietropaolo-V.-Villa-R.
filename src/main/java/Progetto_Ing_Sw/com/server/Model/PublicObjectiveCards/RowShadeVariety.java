package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * <h1>RowShadeVariety</h1>
 *  The class RowShadeVariety implements the PublicObjectiveCard RowShadeVariety
 *  @author Roberto Villa
 */

public class RowShadeVariety {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/RowShadeVariety.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------


    private int victoryPoints;
    public RowShadeVariety() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    /**
     * this methods calculates points (on the windowBoard) following the card
     * @param localBoard is the board where i calculate the points
     * @return it returns the number of points calculated
     */
    public int calculatePoints(WindowBoard localBoard){

        int points=0;
        int one=0;
        int two=0;
        int three=0;
        int four=0;
        int five=0;
        int six=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for(int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if (localBoard.getUsedMatrix().get(r).get(c).isUsed() == false) {
                    //se ho una casella vuota non fa nulla, non calcola il punteggio
                    System.out.println("Non ho considerato la riga");
                    break;
                }
                else{
                    switch(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()){
                        case(1):one++;break;
                        case(2):two++;break;
                        case(3):three++;break;
                        case(4):four++;break;
                        case(5):five++;break;
                        case(6):six++;break;
                    }
                }
                System.out.println("ROW #"+(r+1)+" has:");
                System.out.println("********************");
                System.out.println("#ONE :"+one);
                System.out.println("#TWO :"+two);
                System.out.println("#THREE :"+three);
                System.out.println("#FOUR :"+four);
                System.out.println("#FIVE :"+five);
                System.out.println("#SIX :"+six);
            }
            if((one==1 && two ==1 && three==1 && four==1 && five==1 && six==0)||
                    (one==1 && two ==1 && three==1 && four==1 && five==0 && six==1)||
                    (one==1 && two ==1 && three==1 && four==0 && five==1 && six==1)||
                    (one==1 && two ==1 && three==0 && four==1 && five==1 && six==1)||
                    (one==1 && two ==0 && three==1 && four==1 && five==1 && six==1)||
                    (one==0 && two ==1 && three==1 && four==1 && five==1 && six==1))
            {
             points=points+getVictoryPoints();
            }

            one=0;
            two=0;
            three=0;
            four=0;
            five=0;
            six=0;

        }
        return points;
    }
}
