package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.Table;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class FluxBrush {

    private Table table = Table.getOurInstance();

    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/FluxBrush.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    //-----------------

    private boolean firstUsage;
    public FluxBrush() {this.firstUsage = localFirstUsage;}

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice diceDrawn, int row, int column, int favorTokensUsed){

        Dice localdice = new Dice(diceDrawn.getValue(),diceDrawn.getColor());
        System.out.println("Valor del dado prima della nuova pescata :"+localdice.getValue());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                int newDiceValue =new SplittableRandom().nextInt(1,7); //lancio il nuovo dado
                localdice.setValue(newDiceValue); //assegno il nuovo valore al dado
                System.out.println("Nuovo Valore del dado : "+localdice.getValue());

                localBoard.insertDice(row,column,localdice);
                if(localBoard.getUsedMatrix().get(row-1).get(column-1).isUsed()==false)//ossia mancato inserimento
                {
                    table.addDiceFluxBrush(localdice);
                }
            }
        }
        else if (firstUsage==true)
        {
            if (favorTokensUsed == 2)
            {
                int newDiceValue =new SplittableRandom().nextInt(1,7); //lancio il nuovo dado
                localdice.setValue(newDiceValue); //assegno il nuovo valore al dado
                System.out.println("Nuovo Valore del dado : "+localdice.getValue());
                localBoard.insertDice(row,column,localdice);
                if(localBoard.getUsedMatrix().get(row-1).get(column-1).isUsed()==false)//ossia mancato inserimento
                {
                    table.addDiceFluxBrush(localdice);
                }
            }
        }

        return localBoard;
    }
}
