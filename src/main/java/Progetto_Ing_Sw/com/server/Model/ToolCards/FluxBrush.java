package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SplittableRandom;

/**
 * this class implements the FluxBrush toolCard
 */

public class FluxBrush extends Effect{

    private Table table = Table.getOurInstance();
    private String toolcardTitle;
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/FluxBrush.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    public FluxBrush() {this.firstUsage = localFirstUsage;}
    public FluxBrush(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolcardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * this method is not used in the game, but it's used for testing
     * @param localBoard
     * @param diceDrawn
     * @param row
     * @param column
     * @param favorTokensUsed
     * @return it returns the windowBoard edited after applying the effect
     * @throws PlaceDiceException
     */
    public WindowBoard applyEffect(WindowBoard localBoard, Dice diceDrawn, int row, int column, int favorTokensUsed) throws PlaceDiceException {

        Dice localdice = new Dice(diceDrawn.getValue(),diceDrawn.getColor());
        System.out.println("Valor del dado prima della nuova pescata :"+localdice.getValue());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                int newDiceValue =new SplittableRandom().nextInt(1,7); //lancio il nuovo dado
                localdice.setValue(newDiceValue); //assegno il nuovo valore al dado
                System.out.println("Nuovo Valore del dado : "+localdice.getValue());

                localBoard.insertDice(row,column,localdice);//TODO catch dell'eccezione altriementi crasha
                if(localBoard.getUsedMatrix().get(row-1).get(column-1).isUsed()==false)//ossia mancato inserimento
                {
                    table.addDiceFluxBrush(localdice);
                    firstUsage=true;
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

                localBoard.insertDice(row,column,localdice);//TODO catch dell'eccezione altriementi crasha
                if(localBoard.getUsedMatrix().get(row-1).get(column-1).isUsed()==false)//ossia mancato inserimento
                {
                    table.addDiceFluxBrush(localdice);
                }
            }
        }

        return localBoard;
    }

    @Override
    public WindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }

    @Override
    public String getTitle() {
        return toolcardTitle;
    }
}
