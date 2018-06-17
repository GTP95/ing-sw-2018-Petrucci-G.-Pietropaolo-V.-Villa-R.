package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class LightShades {
    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/LightShades.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private int victoryPoints;
    public LightShades() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        int one=0;
        int two=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for (int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed() && (localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==1 || localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==2))
                {
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==1){one++;}
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==2){two++;}
                }
            }
        }

        if(one>two){points=getVictoryPoints()*two;}
        if(one<two){points=getVictoryPoints()*one;}
        if(one==two){points=getVictoryPoints()*one;}

    return points;
    }
}
