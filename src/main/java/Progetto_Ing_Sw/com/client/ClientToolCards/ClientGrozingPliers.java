package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.client.ClientToolCard;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.ToolCard;
import Progetto_Ing_Sw.com.server.Model.ToolCards.Effect;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

public class ClientGrozingPliers extends ClientEffect{ //prende un dado un entrata, lo incrementa/decrementa di 1 e lo posiziona

    private WindowBoard localWindowBoard = new WindowBoard(4,5);
    private String localCommand = "";
    private Dice localDice = new Dice(0,0);
    private int localRow = 0;
    private int localColumn = 0;
    private boolean firstUsage;
    private boolean localFirstUsage;
    private String toolCardTitle;

    public void setLocalBoard(WindowBoard localBoard) {this.localWindowBoard = localBoard;}
    public void setLocalCommand(String localCommand) {this.localCommand = localCommand;}
    public void setLocalDice(Dice localDice) {this.localDice = localDice;}
    public void setLocalRow(int localRow) {this.localRow = localRow;}
    public void setLocalColumn(int localColumn) {this.localColumn = localColumn;}

    {try {localFirstUsage = JSONCreator.parseBooleanFieldFromFile("Resources/Cards/ToolCards/GrozingPliers.json","firstUsage" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ClientGrozingPliers() {
        this.firstUsage = localFirstUsage;
    }
    public ClientGrozingPliers(ClientToolCard toolCard) {
        this.firstUsage = toolCard.isFirstUsage();
        this.toolCardTitle=toolCard.getTitle();
    }

    public boolean isFirstUsage() {return firstUsage;}
    public void setFirstUsage(boolean firstUsage) {this.firstUsage = firstUsage;}
    public boolean isLocalFirstUsage() {return localFirstUsage;}

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

    @Override
    public WindowBoard applyEffect() throws PlaceDiceException {

        WindowBoard methodBoard = localWindowBoard;
        Dice methodDice = localDice;
        String methodCommand = localCommand;
        int methodRow = localRow;
        int methodColumn = localColumn;

        if(firstUsage==false) {

            if (methodCommand.equals("UP")) {
                    if (methodDice.getValue() == 6) {
                        methodBoard.insertDice(methodRow, methodColumn, methodDice);
                        firstUsage=true;
                    } else {
                        methodDice.setValue(methodDice.increaseValue(methodDice.getValue()));
                        methodBoard.insertDice(methodRow, methodColumn, methodDice);
                        firstUsage=true;
                    }

                } else if (methodCommand.equals("DOWN")) {
                    if (methodDice.getValue() == 1) {
                        methodBoard.insertDice(methodRow, methodColumn, methodDice);
                        firstUsage=true;
                    } else {
                        methodDice.setValue(methodDice.decreaseValue(methodDice.getValue()));
                        methodBoard.insertDice(methodRow, methodColumn, methodDice);
                        firstUsage=true;
                    }
                }
            }else
                {
                    if(methodCommand.equals("UP")){
                        if(methodDice.getValue()==6)
                        {
                            methodBoard.insertDice(methodRow,methodColumn,methodDice);
                        }
                        else
                        {
                            methodDice.setValue(methodDice.increaseValue(methodDice.getValue()));
                            methodBoard.insertDice(methodRow,methodColumn,methodDice);
                        }

                    }else if(methodCommand.equals("DOWN")){
                        if(methodDice.getValue()==1)
                        {
                            methodBoard.insertDice(methodRow,methodColumn,methodDice);
                        }
                        else
                        {
                            methodDice.setValue(methodDice.decreaseValue(methodDice.getValue()));
                            methodBoard.insertDice(methodRow,methodColumn,methodDice);
                        }
                    }
                }
            return methodBoard;
    }

    @Override
    public String getTitle() {
        return toolCardTitle;
    }
}
