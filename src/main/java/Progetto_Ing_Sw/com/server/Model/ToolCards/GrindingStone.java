package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * this class implements the GrindingStone toolCard
 */
public class GrindingStone extends Effect{

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GrindingStone.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    private String toolCardTitle;
    public GrindingStone() {this.firstUsage = localFirstUsage;}
    public GrindingStone(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * this method is not used in the game, only for testing
     * @param localBoard
     * @param dice
     * @param row
     * @param column
     * @param favorTokensUsed
     * @return it returns the windowBoard edited
     * @throws PlaceDiceException
     */
    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int row, int column, int favorTokensUsed)throws PlaceDiceException {

        Dice localDice = new Dice(dice.getValue(),dice.getColor());

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                switch (localDice.getValue()) {
                    case (1): localDice.setValue(6);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
                    case (2): localDice.setValue(5);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
                    case (3): localDice.setValue(4);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
                    case (4): localDice.setValue(3);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
                    case (5): localDice.setValue(2);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
                    case (6): localDice.setValue(1);localBoard.insertDice(row,column,localDice);firstUsage=true;
                        break;
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
                switch (localDice.getValue()) {
                case (1): localDice.setValue(6);localBoard.insertDice(row,column,localDice);
                    break;
                case (2): localDice.setValue(5);localBoard.insertDice(row,column,localDice);
                    break;
                case (3): localDice.setValue(4);localBoard.insertDice(row,column,localDice);
                    break;
                case (4): localDice.setValue(3);localBoard.insertDice(row,column,localDice);
                    break;
                case (5): localDice.setValue(2);localBoard.insertDice(row,column,localDice);
                    break;
                case (6): localDice.setValue(1);localBoard.insertDice(row,column,localDice);
                    break;
                }
            }
            else
            {
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA- OTHER USAGE -");
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
