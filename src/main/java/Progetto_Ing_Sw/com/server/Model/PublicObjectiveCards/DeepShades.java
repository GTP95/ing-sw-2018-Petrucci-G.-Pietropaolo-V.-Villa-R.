package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * <h1>DeepShades</h1>
 *  The class DeepShades implements the PublicObjectiveCard DeepShades
 *  @author Roberto Villa
 */

public class DeepShades {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/DeepShades.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private int victoryPoints;
    public DeepShades() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    /**
     * this methods calculates points (on the windowBoard) following the card
     * @param localBoard is the board where i calculate the points
     * @return it returns the number of points calculated
     */
    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        int five=0;
        int six=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for (int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed() && (localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==5 || localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==6))
                {
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==5){five++;}
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==6){six++;}
                }
            }
        }

        if(five>six){points=getVictoryPoints()*six;}
        if(five<six){points=getVictoryPoints()*five;}
        if(five==six){points=getVictoryPoints()*five;}

        return points;
    }
}
