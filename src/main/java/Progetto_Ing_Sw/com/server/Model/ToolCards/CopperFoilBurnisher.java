package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

//TESTING fatto direttamente sulla classe GamePlayTest

/**
 * <h1>CopperFoilBurnisher</h1>
 *  The class CopperFoilBurnisher implements the ToolCard CopperFoilBurnisher
 *  @author Roberto Villa
 */
public class CopperFoilBurnisher extends Effect{
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


    public CopperFoilBurnisher() {
        this.firstUsage = localFirstUsage;
    }
    public CopperFoilBurnisher(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle= toolCard.getTitle();
    }


    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    /**
     * this method is not used in the game, only for testing
     * @param localBoard
     * @param dice
     * @param rowBefore
     * @param columnBefore
     * @param rowAfter
     * @param columnAfter
     * @param favorTokensUsed
     * @return it returns a windowboard edited by the effect
     * @throws PlaceDiceException
     */
    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int rowBefore, int columnBefore, int rowAfter, int columnAfter, int favorTokensUsed) throws PlaceDiceException {

        Dice localDice = new Dice(dice.getValue(),dice.getColor());
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
    public WindowBoard applyEffect() throws PlaceDiceException {
        return null;
    }

    public String getTitle() {
        return toolCardTitle;
    }
}
