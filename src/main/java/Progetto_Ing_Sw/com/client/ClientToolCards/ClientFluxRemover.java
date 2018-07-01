package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientToolCard;
import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.server.Model.ToolCards.Effect;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class ClientFluxRemover extends ClientEffect{

        private boolean localFirstUsage;
        {try {
            localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/FluxRemover.json","firstUsage" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }

        private boolean firstUsage;
        private String toolCardTitle;

        public ClientFluxRemover() {this.firstUsage = localFirstUsage;}
        public ClientFluxRemover(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        toolCardTitle=toolCard.getTitle();
    }

        public boolean isFirstUsage() {return firstUsage;}
        public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

        public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, DiceBag diceBag, int row, int column, int valueRequested, int favorTokensUsed) throws PlaceDiceException {

            Dice localDice = new Dice(dice.getValue(),dice.getColor());

            if(firstUsage==false)
            {
                if (favorTokensUsed == 1)
                {
                    try {
                        diceBag.reintroduceDice(localDice);
                    } catch (IllegalDiceException e) {
                        e.printStackTrace();
                    }
                    localDice=diceBag.diceDraw();
                    System.out.println("DICE DRAWN value :"+localDice.getValue());
                    System.out.println("DICE DRAWN color :"+localDice.getColor());
                    System.out.println("Valore da imporre "+valueRequested);
                    localDice.setValue(valueRequested);
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
                    try {
                        diceBag.reintroduceDice(localDice);
                    } catch (IllegalDiceException e) {
                        e.printStackTrace();
                    }
                    localDice=diceBag.diceDraw();
                    System.out.println("DICE DRAWN value :"+localDice.getValue());
                    System.out.println("DICE DRAWN color :"+localDice.getColor());
                    System.out.println("Valore da imporre "+valueRequested);
                    localDice.setValue(valueRequested);
                    localBoard.insertDice(row,column,localDice);
                }
                else
                {
                    System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - OTHER USAGE -");
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
