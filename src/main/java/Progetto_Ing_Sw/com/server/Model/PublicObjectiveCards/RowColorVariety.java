package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class RowColorVariety {

    //-------import del costo di primo uso
    private int localVictoryPoints;
    {try {
        localVictoryPoints = JSONCreator.parseIntFieldFromFile("Resources/Cards/PublicObjectiveCards/RowColorVariety.json","victoryPoints" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------


    private int victoryPoints;
    public RowColorVariety() {
        this.victoryPoints = localVictoryPoints;
    }

    public int getVictoryPoints() {return victoryPoints;}

    public int calculatePoints(WindowBoard localBoard){

        int points=0;
        int red=0;
        int blue=0;
        int purple=0;
        int yellow=0;
        int green=0;

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for(int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if (localBoard.getUsedMatrix().get(r).get(c).isUsed() == false) {
                    //se ho una casella vuota non fa nulla, non calcola il punteggio
                    break;
                }
                else{
                    switch(localBoard.getUsedMatrix().get(r).get(c).getDiceContained().getColor()){
                        case(Color.RED): red++;break;
                        case(Color.BLUE): blue++;break;
                        case(Color.PURPLE): purple++;break;
                        case(Color.YELLOW): yellow++;break;
                        case(Color.GREEN): green++;break;
                    }
                }
                System.out.println("ROW #"+(r+1)+" has:");
                System.out.println("********************");
                System.out.println("#RED :"+red);
                System.out.println("#BLUE :"+blue);
                System.out.println("#PURPLE :"+purple);
                System.out.println("#YELLOW :"+yellow);
                System.out.println("#GREEN :"+green);
            }
            //Alla fine della riga analizzo o risultati
            if(red==1 && blue==1 && purple ==1 && yellow==1 && green ==1){
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
