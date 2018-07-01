package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class GrozingPliers extends Card{ //prende un dado un entrata, lo incrementa/decrementa di 1 e lo posiziona

    private boolean localFirstUsage;
    {try {
            localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GrozingPliers.json","firstUsage" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean firstUsage;
    public GrozingPliers() {
        this.firstUsage = localFirstUsage;
    }

    public GrozingPliers(ToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}


    public WindowBoard applyEffect(WindowBoard localBoard, String command, Dice dice, int row, int column, int favorTokensUsed) throws PlaceDiceException {

        Dice localDice = new Dice(dice.getValue(),dice.getColor());
        //System.out.println(">>> First usage value = "+isFirstUsage());

        if(firstUsage==false) {

            if (favorTokensUsed == 1) {
                //System.out.println("***FIRST USAGE***");

                if (command.equals("UP")) {
                    if (localDice.getValue() == 6) {
                        localBoard.insertDice(row, column, localDice);
                        firstUsage=true;
                        //System.out.println(">>> FIRST USAGE "+isFirstUsage());
                    } else {
                        localDice.setValue(localDice.increaseValue(localDice.getValue()));
                        localBoard.insertDice(row, column, localDice);
                        firstUsage=true;
                        //System.out.println(">>> FIRST USAGE "+isFirstUsage());
                    }

                } else if (command.equals("DOWN")) {
                    if (localDice.getValue() == 1) {
                        localBoard.insertDice(row, column, localDice);
                        firstUsage=true;
                        //System.out.println(">>> FIRST USAGE "+isFirstUsage());
                    } else {
                        localDice.setValue(localDice.decreaseValue(localDice.getValue()));
                        localBoard.insertDice(row, column, localDice);
                        firstUsage=true;
                        //System.out.println(">>> FIRST USAGE "+isFirstUsage());
                    }
                }

            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA, stai pagando troppo! - FIRST USAGE -");
                //TODO GUI EXCEPTION
            }
        }
        else if(firstUsage) {

            if (favorTokensUsed == 2) {
                //System.out.println("***OTHER USAGE***");
                if(command.equals("UP")){
                if(localDice.getValue()==6)
                {
                    localBoard.insertDice(row,column,localDice);
                    //System.out.println(">>> OTHER USAGE "+isFirstUsage());
                }
                else
                {
                    localDice.setValue(localDice.increaseValue(localDice.getValue()));
                    localBoard.insertDice(row,column,localDice);
                    //System.out.println(">>> OTHER USAGE "+isFirstUsage());
                }

            }else if(command.equals("DOWN")){
                if(localDice.getValue()==1)
                {
                    localBoard.insertDice(row,column,localDice);
                    //System.out.println(">>> OTHER USAGE "+isFirstUsage());
                }
                else
                {
                    localDice.setValue(localDice.decreaseValue(localDice.getValue()));
                    localBoard.insertDice(row,column,localDice);
                    //System.out.println(">>> OTHER USAGE "+isFirstUsage());
                }
            }
            }
            else{
                System.out.println("ERRORE DI PAGAMENTO DELLA CARTA - OTHER USAGE -");
                //TODO GUI EXCEPTION
            }
        }

        return localBoard;
    }
}
