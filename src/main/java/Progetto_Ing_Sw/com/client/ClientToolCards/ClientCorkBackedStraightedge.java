package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientToolCard;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.ToolCard;
import Progetto_Ing_Sw.com.server.Model.ToolCards.Effect;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TESTATA direttamente in GamePlayTest
public class ClientCorkBackedStraightedge extends ClientEffect{

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
    public ClientCorkBackedStraightedge() {this.firstUsage = localFirstUsage;}
    public ClientCorkBackedStraightedge(ClientToolCard toolCard) {

        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

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
