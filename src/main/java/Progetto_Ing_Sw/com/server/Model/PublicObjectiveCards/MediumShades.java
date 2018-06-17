package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class MediumShades {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/MediumShades.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private int victoryPoints;
    public MediumShades() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        int three=0;
        int four=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for (int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed() && (localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==3 || localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==4))
                {
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==3){three++;}
                    if(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getValue()==4){four++;}
                }
            }
        }

        if(three>four){points=getVictoryPoints()*four;}
        if(three<four){points=getVictoryPoints()*three;}
        if(three==four){points=getVictoryPoints()*three;}

        return points;
    }
}
