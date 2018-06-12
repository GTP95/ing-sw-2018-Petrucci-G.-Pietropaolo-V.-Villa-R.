package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TESTATA direttamente in GamePlayTest
public class CorkBackedStraightedge {

    private WindowBoard helpBoard;

    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/CorkBackedStraightedge.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    //-----------------


    private boolean firstUsage;
    public CorkBackedStraightedge() {this.firstUsage = localFirstUsage;}

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int row, int column, int favorTokensUsed){

        Dice localDice = new Dice(dice.getValue(),dice.getColor());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                localDice.setAdjacencyBreaker(true);
                localBoard.insertDice(row,column,localDice);
                firstUsage=true;
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA, stai pagando troppo! - FIRST USAGE -");
                //TODO GUI EXCEPTION
            }
        }
        else if (firstUsage==true)
        {
            if (favorTokensUsed == 2)
            {
                localDice.setAdjacencyBreaker(true);
                localBoard.insertDice(row,column,localDice);
            }
            else
            {
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA,- OTHER USAGE -");
                //TODO GUI EXCEPTION
            }
        }

        return localBoard;
    }
}
