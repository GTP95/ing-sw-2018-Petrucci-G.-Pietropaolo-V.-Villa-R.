package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;
/**
 * <h1>CorkBackedStraightedge</h1>
 *  The class CorkBackedStraightedge implements the ToolCard CorkBackedStraightedge
 *  @author Roberto Villa
 */
public class CorkBackedStraightedge extends Effect{

    private WindowBoard helpBoard;

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/CorkBackedStraightedge.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    private String toolCardTitle;
    public CorkBackedStraightedge() {this.firstUsage = localFirstUsage;}
    public CorkBackedStraightedge(ToolCard toolCard) {

        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}



    @Override
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * this method is not used in the game, only for testing
     * @param localBoard
     * @param dice
     * @param row
     * @param column
     * @param favorTokensUsed
     * @return it returns a windowboard edited
     * @throws PlaceDiceException
     */
    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int row, int column, int favorTokensUsed) throws PlaceDiceException {

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

    @Override
    public WindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }

    @Override
    public String getTitle() {
        return toolCardTitle;
    }
}
