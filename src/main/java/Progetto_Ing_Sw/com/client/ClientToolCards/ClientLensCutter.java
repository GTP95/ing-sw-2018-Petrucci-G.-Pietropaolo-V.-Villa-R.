package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.*;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TODO FARE TEST DI QUESTA CARTA

/*public class ClientLensCutter extends ClientEffect{

    private ClientRoundTrack roundTrack = ClientRoundTrack.getInstance();

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/LensCutter.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    private String toolCardTitle;

    public ClientLensCutter() {this.firstUsage = localFirstUsage;}
    public ClientLensCutter(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public ClientWindowBoard applyEffect(ClientWindowBoard localBoard, ClientDice dice, ClientDice roundTrackDice, int row, int column, int favorTokensUsed) throws PlaceDiceException {

        ClientDice localDice = new ClientDice(dice.getValue(),dice.getColor());
        ClientDice localRoundTrackDice = new ClientDice(roundTrackDice.getValue(),roundTrackDice.getColor());


        if(firstUsage==false)
        {
            if (favorTokensUsed == 1)
            {
                try {
                    localBoard.insertDice(row,column,roundTrack.swapDice(localRoundTrackDice,localDice));
                    firstUsage=true;

                } catch (IllegalDiceException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA, stai pagando troppo! - FIRST USAGE -");
                //TODO GUI EXCEPTION
            }
        }else if (firstUsage==true)
        {
            if (favorTokensUsed == 2)
            {
                try {
                    localBoard.insertDice(row,column,roundTrack.swapDice(localRoundTrackDice,localDice));
                } catch (IllegalDiceException e) {
                        e.printStackTrace();
                    }
            }else
                {
                    System.out.println("ERRORE DI PAGAMENTO DELLA CARTA,- OTHER USAGE -");
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
}
*/