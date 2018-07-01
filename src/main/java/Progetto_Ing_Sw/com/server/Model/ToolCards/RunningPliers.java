package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class RunningPliers extends Effect{

    private Table table = Table.getOurInstance();


    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/RunningPliers.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    public RunningPliers() {this.firstUsage = localFirstUsage;}
    public RunningPliers(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice1, Dice dice2, int row1, int column1, int row2, int column2, int favorTokensUsed) throws PlaceDiceException {

        Dice localDice1 = new Dice(dice1.getValue(),dice1.getColor());
        Dice localDice2 = new Dice(dice2.getValue(),dice2.getColor());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                localBoard.insertDice(row1,column1,localDice1);
                localBoard.insertDice(row2,column2,localDice2);
                if(table.getActivePlayer().getRoundNumber()==1)
                {
                    table.getActivePlayer().setRoundNumber(0);
                }
                else{
                    //TODO eccezione di turno,
                }
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
                localBoard.insertDice(row1,column1,localDice1);
                localBoard.insertDice(row2,column2,localDice2);
                if(table.getActivePlayer().getRoundNumber()==1)
                {
                    table.getActivePlayer().setRoundNumber(0);
                }
                else{
                    //TODO eccezione di turno,
                }
            }
            else
            {
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA,- OTHER USAGE -");
                //TODO GUI EXCEPTION
            }
        }

        return localBoard;
    }

    @Override
    public WindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }
}
