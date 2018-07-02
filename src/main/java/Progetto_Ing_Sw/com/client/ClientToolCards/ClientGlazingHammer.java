package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientDice;
import Progetto_Ing_Sw.com.client.ClientToolCard;
import Progetto_Ing_Sw.com.client.ClientWindowBoard;
import Progetto_Ing_Sw.com.client.PlaceDiceException;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;
import java.util.SplittableRandom;

/*public class ClientGlazingHammer extends ClientEffect{

    //TODO: rimuovere table
    private Table table = Table.getOurInstance();

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GlazingHammer.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

    private boolean firstUsage;
    private String toolCardTitle;

    public ClientGlazingHammer() {this.firstUsage = localFirstUsage;}
    public ClientGlazingHammer(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public void applyEffect(int favorTokensUsed){

        if(firstUsage==false){

            if (favorTokensUsed == 1)
            {

                if(table.getActivePlayer().getRoundNumber()==1)//controlla che effettivamente il giocatore sia nel secondo turno
                {
                    for(int d=0;d<table.getDrawnDice().size();d++){
                        ClientDice localDice = new ClientDice(table.getDrawnDice().get(d).getValue(),table.getDrawnDice().get(d).getColor());
                        int newDiceValue =new SplittableRandom().nextInt(1,7);
                        localDice.setValue(newDiceValue);
                        table.getDrawnDice().get(d).setValue(localDice.getValue());
                        firstUsage=true;
                    }

                }else if(table.getActivePlayer().getRoundNumber()==2){
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
            if(table.getActivePlayer().getRoundNumber()==1)//controlla che effettivamente il giocatore sia nel secondo turno
            {
                for(int d=0;d<table.getDrawnDice().size();d++){
                    ClientDice localDice = new ClientDice(table.getDrawnDice().get(d).getValue(),table.getDrawnDice().get(d).getColor());
                    int newDiceValue =new SplittableRandom().nextInt(1,7);
                    localDice.setValue(newDiceValue);
                    table.getDrawnDice().get(d).setValue(localDice.getValue());
                }

            }else if(table.getActivePlayer().getRoundNumber()==2)
            {
                System.out.println("NON PUOI USARE LA CARTA NEL PRIMO ROUND");
                //TODO GUI EXCEPTION
            }
        }else{
            System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - OTHER USAGE -");
            //TODO GUI EXCEPTION
        }
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