package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientDice;
import Progetto_Ing_Sw.com.client.ClientToolCard;

import Progetto_Ing_Sw.com.client.ClientWindowBoard;
import Progetto_Ing_Sw.com.client.PlaceDiceException;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/*public class ClientTapWheel extends ClientEffect {

    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/TapWheel.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    //-----------------


    private boolean firstUsage;
    private String toolCardTitle;
    public ClientTapWheel() {this.firstUsage = localFirstUsage;}
    public ClientTapWheel(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public ClientWindowBoard applyEffect(ClientWindowBoard localBoard, ClientDice roundTrackDice, int rowBefore1, int columnBefore1, int rowBefore2, int columnBefore2, int rowAfter1, int columnAfter1, int rowAfter2, int columnAfter2, ClientDice dice1, ClientDice dice2, int favorTokensUsed) throws PlaceDiceException{

        ClientDice localdice1 = new ClientDice(dice1.getValue(),dice1.getColor());
        ClientDice localdice2 = new ClientDice(dice2.getValue(),dice2.getColor());
        ClientDice roundTrackLocalDice = new ClientDice(roundTrackDice.getValue(),roundTrackDice.getColor());
        int colorChoosed = roundTrackLocalDice.getColor();

        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                if(colorChoosed==localdice1.getColor()&&colorChoosed==localdice2.getColor()){
                    localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setDiceContained(null);
                    localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setUsed(false);
                    localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setDiceContained(null);
                    localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setUsed(false);

                    localBoard.insertDice(rowAfter1,columnAfter1,localdice1);
                    localBoard.insertDice(rowAfter2,columnAfter2,localdice2);
                    firstUsage=true;
                }else{
                    System.out.println("IL COLORE NON CORRISPONDE A QUELLO SCELTO");
                    //TODO GUI EXCEPTION
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
                if(colorChoosed==localdice1.getColor()&&colorChoosed==localdice2.getColor()){
                    localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setDiceContained(null);
                    localBoard.getUsedMatrix().get(rowBefore1-1).get(columnBefore1-1).setUsed(false);
                    localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setDiceContained(null);
                    localBoard.getUsedMatrix().get(rowBefore2-1).get(columnBefore2-1).setUsed(false);

                    localBoard.insertDice(rowAfter1,columnAfter1,localdice1);
                    localBoard.insertDice(rowAfter2,columnAfter2,localdice2);
                }else{
                    System.out.println("IL COLORE NON CORRISPONDE A QUELLO SCELTO");
                    //TODO GUI EXCEPTION
                }
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - SECOND USAGE -");
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
