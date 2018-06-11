package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class CopperFoilBurnisher {



    //-------import del costo di primo uso
    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/CopperFoilBurnisher.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }
    //-----------------

    //Costruttore della classe EglomiseBrush
    private boolean firstUsage;
    public CopperFoilBurnisher() {
        this.firstUsage = localFirstUsage;
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int rowBefore, int columnBefore, int rowAfter, int columnAfter, int favorTokensUsed){

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
}
