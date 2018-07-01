package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TODO FARE TEST DI QUESTA CARTA

public class LensCutter extends Effect{

    private RoundTrack roundTrack = RoundTrack.getInstance();

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/LensCutter.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    public LensCutter() {this.firstUsage = localFirstUsage;}
    public LensCutter(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, Dice roundTrackDice, int row, int column, int favorTokensUsed) throws PlaceDiceException {

        Dice localDice = new Dice(dice.getValue(),dice.getColor());
        Dice localRoundTrackDice = new Dice(roundTrackDice.getValue(),roundTrackDice.getColor());


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
    public WindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }
}
