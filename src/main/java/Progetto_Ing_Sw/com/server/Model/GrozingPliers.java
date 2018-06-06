package Progetto_Ing_Sw.com.server.Model;

public class GrozingPliers{ //prende un dado un entrata, lo incrementa/decrementa di 1 e lo posiziona

    public WindowBoard applyEffect(WindowBoard localBoard, String command, Dice dice, int row, int column){

        Dice localDice = new Dice(dice.getValue(),dice.getColor());

        if(command.equals("UP")){

           if(localDice.getValue()==6)
           {
               localBoard.insertDice(localBoard.getUsedMatrix(),row,column,localDice);
           }
           else
           {
               localDice.setValue(localDice.increaseValue(localDice.getValue()));
               localBoard.insertDice(localBoard.getUsedMatrix(),row,column,localDice);
           }

        }else if(command.equals("DOWN")){

            if(localDice.getValue()==1)
            {
                localBoard.insertDice(localBoard.getUsedMatrix(),row,column,localDice);
            }
            else
            {
                localDice.setValue(localDice.decreaseValue(localDice.getValue()));
                localBoard.insertDice(localBoard.getUsedMatrix(),row,column,localDice);
            }
        }
        return localBoard;
    }
}
