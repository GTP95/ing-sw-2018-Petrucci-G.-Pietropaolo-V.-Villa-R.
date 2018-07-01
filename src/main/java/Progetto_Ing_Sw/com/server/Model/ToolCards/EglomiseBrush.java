package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/*
Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di COLORE
Devi rispettare tutte le altre restrizioni di piazzamento
*/

//TESTING fatto direttamente sulla classe GamePlayTest
public class EglomiseBrush extends Effect{

    private boolean localFirstUsage;
    {try {
        localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/EglomiseBrush.json","firstUsage" );
    } catch (FileNotFoundException e) {
        e.printStackTrace();}
    }

    private boolean firstUsage;
    private String toolcardTitle;

    public EglomiseBrush() {
        this.firstUsage = localFirstUsage;
    }
    public EglomiseBrush(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolcardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}

    public WindowBoard applyEffect(WindowBoard localBoard, Dice dice, int rowBefore, int columnBefore, int rowAfter, int columnAfter, int favorTokensUsed) throws PlaceDiceException {

        Dice localDice = new Dice(dice.getValue(),dice.getColor());
        //System.out.println(">>> First usage value = "+isFirstUsage());

        if(firstUsage==false){

            if (favorTokensUsed == 1) {
                //System.out.println("***FIRST USAGE***");

                localDice.setColorBreaker(true); //setting del fatto che il dado deve evitare certe regole di posizionamento
                //Rimozione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setDiceContained(null); //rimuove il dado dalla vecchia posizione
                localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setUsed(false); //sistema il flag di rimozione del dado, rimettendo la casella "libera"
                //Nuovo Posizionamento
                localBoard.insertDice(rowAfter,columnAfter,localDice);
                firstUsage=true;


            }else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - FIRST USAGE -");
                //TODO GUI EXCEPTION
            }
        }
        else if(firstUsage) {
                if (favorTokensUsed == 2) {
                    //System.out.println("***OTHER USAGE***");

                    localDice.setColorBreaker(true); //setting del fatto che il dado deve evitare certe regole di posizionamento
                    //Rimozione
                    localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setDiceContained(null); //rimuove il dado dalla vecchia posizione
                    localBoard.getUsedMatrix().get(rowBefore-1).get(columnBefore-1).setUsed(false); //sistema il flag di rimozione del dado, rimettendo la casella "libera"
                    //Nuovo Posizionamento
                    localBoard.insertDice(rowAfter,columnAfter,localDice);
                }else{
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
        return toolcardTitle;
    }
}
