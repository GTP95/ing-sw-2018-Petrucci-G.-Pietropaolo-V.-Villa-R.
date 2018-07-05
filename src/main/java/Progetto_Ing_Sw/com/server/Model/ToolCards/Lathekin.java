package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * this class implements the Lathekin toolCard
 */

//TESTING fatto direttamente sulla classe GamePlayTest
public class Lathekin extends Effect{

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/Lathekin.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private String toolcardTitle;
    private boolean firstUsage;
    public Lathekin() {this.firstUsage = localFirstUsage;}
    public Lathekin(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        toolcardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * this method is not used in game, only for testing
     * @param localBoard
     * @param rowBefore1
     * @param columnBefore1
     * @param rowBefore2
     * @param columnBefore2
     * @param rowAfter1
     * @param columnAfter1
     * @param rowAfter2
     * @param columnAfter2
     * @param dice1
     * @param dice2
     * @param favorTokensUsed
     * @return it returns the windowBoard deited by the effect
     * @throws PlaceDiceException
     */
    public WindowBoard applyEffect(WindowBoard localBoard, int rowBefore1, int columnBefore1, int rowBefore2, int columnBefore2, int rowAfter1, int columnAfter1, int rowAfter2, int columnAfter2, Dice dice1, Dice dice2, int favorTokensUsed) throws PlaceDiceException {

        Dice localdice1 = new Dice(dice1.getValue(),dice1.getColor());
        Dice localdice2 = new Dice(dice2.getValue(),dice2.getColor());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setDiceContained(null);
                localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setUsed(false);
                localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setDiceContained(null);
                localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setUsed(false);

                localBoard.insertDice(rowAfter1,columnAfter1,localdice1);
                localBoard.insertDice(rowAfter2,columnAfter2,localdice2);
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
                localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setDiceContained(null);
                localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setUsed(false);
                localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setDiceContained(null);
                localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setUsed(false);

                localBoard.insertDice(rowAfter1,columnAfter1,localdice1);
                localBoard.insertDice(rowAfter2,columnAfter2,localdice2);
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - SECOND USAGE -");
                //TODO GUI EXCEPTION
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
