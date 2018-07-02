package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientDice;
import Progetto_Ing_Sw.com.client.ClientToolCard;


import Progetto_Ing_Sw.com.client.ClientWindowBoard;
import Progetto_Ing_Sw.com.client.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TESTING fatto direttamente sulla classe GamePlayTest

/**
 * <h1>CopperFoilBurnisher</h1>
 *  The class CopperFoilBurnisher implements the ToolCard CopperFoilBurnisher
 *  @author Roberto Villa
 */

/*public class ClientCopperFoilBurnisher extends ClientEffect{
    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/CopperFoilBurnisher.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    private boolean firstUsage;
    private String toolCardTitle;


    public ClientCopperFoilBurnisher() {
        this.firstUsage = localFirstUsage;
    }
    public ClientCopperFoilBurnisher(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle= toolCard.getTitle();
    }


    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * This method apply CopperFoilBurnisher's effect at the input-WindowBoard
     * @param localBoard this parameter represents the input-WindowBoard where i have to apply the effect
     * @param dice this parameter represents
     * @param rowBefore this parameter represents
     * @param columnBefore this parameter represents
     * @param rowAfter this parameter represents
     * @param columnAfter this parameter represents
     * @param favorTokensUsed this parameter represents
     * @return
     * @throws PlaceDiceException
     */
 /*   public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int rowBefore, int columnBefore, int rowAfter, int columnAfter, int favorTokensUsed) throws PlaceDiceException {

        ClientDice localDice = new ClientDice(dice.getValue(),dice.getColor());
        //System.out.println(">>> First usage value = "+isFirstUsage());

        if(firstUsage==false){

            if (favorTokensUsed == 1) {
                //System.out.println("***FIRST USAGE***");

                localDice.setNumberBreaker(true); //setting del fatto che il dado deve evitare certe regole di posizionamento
                //Rimozione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setDiceContained(null); //rimuove il dado dalla vecchia posizione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setUsed(false); //sistema il flag di rimozione del dado, rimettendo la casella "libera"
                //Nuovo Posizionamento
                localBoard.insertDice(rowAfter,columnAfter,localDice);

            }else{
                //System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - FIRST USAGE -");
            }
        }
        else if(firstUsage) {
            if (favorTokensUsed == 2) {
                //System.out.println("***OTHER USAGE***");

                localDice.setNumberBreaker(true); //setting del fatto che il dado deve evitare certe regole di posizionamento
                //Rimozione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setDiceContained(null); //rimuove il dado dalla vecchia posizione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setUsed(false); //sistema il flag di rimozione del dado, rimettendo la casella "libera"
                //Nuovo Posizionamento
                localBoard.insertDice(rowAfter,columnAfter,localDice);
            }else{
                //System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - OTHER USAGE -");
            }
        }
        return localBoard;
    }

    @Override
    public ClientWindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }

    public String getTitle() {
        return toolCardTitle;
    }
}
*/