package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientDice;
import Progetto_Ing_Sw.com.client.ClientToolCard;

import Progetto_Ing_Sw.com.client.ClientWindowBoard;
import Progetto_Ing_Sw.com.client.PlaceDiceException;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TESTATA direttamente su GamePlayTest
/*public class ClientGrindingStone extends ClientEffect{

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GrindingStone.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    private String toolCardTitle;
    public ClientGrindingStone() {this.firstUsage = localFirstUsage;}
    public ClientGrindingStone(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public ClientWindowBoard applyEffect(ClientWindowBoard localBoard, ClientDice dice, int row, int column, int favorTokensUsed)throws PlaceDiceException {

        ClientDice localDice = new ClientDice(dice.getValue(),dice.getColor());

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
    public ClientWindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }

    @Override
    public String getTitle() {
        return toolCardTitle;
    }
}*/
