package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.Table;
import Progetto_Ing_Sw.com.tools.JSONCreator;
import java.io.FileNotFoundException;
import java.util.SplittableRandom;

public class GlazingHammer {

    private Table table = Table.getOurInstance();

    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GlazingHammer.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    //-----------------

    private boolean firstUsage;
    public GlazingHammer() {this.firstUsage = localFirstUsage;}

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public void applyEffect(int favorTokensUsed){

        if(firstUsage==false){

            if (favorTokensUsed == 1)
            {

                if(table.getAcivePlayer().getRoundNumber()==1)//controlla che effettivamente il giocatore sia nel secondo turno
                {
                    for(int d=0;d<table.getDrawnDice().size();d++){
                        Dice localDice = new Dice(table.getDrawnDice().get(d).getValue(),table.getDrawnDice().get(d).getColor());
                        int newDiceValue =new SplittableRandom().nextInt(1,7);
                        localDice.setValue(newDiceValue);
                        table.getDrawnDice().get(d).setValue(localDice.getValue());
                        firstUsage=true;
                    }

                }else if(table.getAcivePlayer().getRoundNumber()==2){
                    System.out.println("NON PUOI USARE LA CARTA NEL PRIMO ROUND");
                    //TODO GUI EXCEPTION
                }
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - FIRST USAGE -");
                //TODO GUI EXCEPTION
            }
        }else if(firstUsage) {

        }
        if (favorTokensUsed == 2)
        {
            if(table.getAcivePlayer().getRoundNumber()==1)//controlla che effettivamente il giocatore sia nel secondo turno
            {
                for(int d=0;d<table.getDrawnDice().size();d++){
                    Dice localDice = new Dice(table.getDrawnDice().get(d).getValue(),table.getDrawnDice().get(d).getColor());
                    int newDiceValue =new SplittableRandom().nextInt(1,7);
                    localDice.setValue(newDiceValue);
                    table.getDrawnDice().get(d).setValue(localDice.getValue());
                }

            }else if(table.getAcivePlayer().getRoundNumber()==2)
            {
                System.out.println("NON PUOI USARE LA CARTA NEL PRIMO ROUND");
                //TODO GUI EXCEPTION
            }
        }else{
            System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - OTHER USAGE -");
            //TODO GUI EXCEPTION
        }
    }
}
