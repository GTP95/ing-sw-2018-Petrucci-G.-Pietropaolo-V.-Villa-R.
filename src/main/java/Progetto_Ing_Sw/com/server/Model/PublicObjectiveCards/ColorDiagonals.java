package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

/**
 * <h1>ColorDiagonals</h1>
 *  The class ColorDiagonals implements the PublicObjectiveCard ColorDiagonal
 *  @author Roberto Villa
 */
public class ColorDiagonals {


    public ColorDiagonals() {}
    /**
     * This method counts how much consecutive colors there are in the input-object Diagonal (that is an integer array)
     * @param diagonal this parameter represents the diagonal that has to be analyzed
     * @return ths method returns the number of consecutive-same color-couple
     */
    public int countConsecutiveColors(int [] diagonal){

        int count=0;
        for(int i=0;i<diagonal.length;i++)
        {
            if(diagonal[i]!=23){
                if(i==0){}
                else{
                    if(diagonal[i-1]==diagonal[i]){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * This method calculates points counting the number of consecutive colors located main diagonals
     * @param localBoard this parameter represents the WindowBoard where i have to calculate points
     * @return this method returns the points calculated
     */
    public int calculatePoints(WindowBoard localBoard) {

        int points=0;
        Color color = new Color();
        int [] diagA1 = new int[2];
        int [] diagA2 = new int[3];
        int [] diagA3 = new int[4];
        int [] diagA4 = new int[4];
        int [] diagA5 = new int[3];
        int [] diagA6 = new int[2];

        int [] diagB1 = new int[2];
        int [] diagB2 = new int[3];
        int [] diagB3 = new int[4];
        int [] diagB4 = new int[4];
        int [] diagB5 = new int[3];
        int [] diagB6 = new int[2];

        for(int r=0;r<localBoard.getUsedMatrix().size();r++){
            for(int c=0;c<localBoard.getUsedMatrix().get(r).size();c++){
                if(localBoard.getUsedMatrix().get(r).get(c).isUsed()==false){
                    Dice helpDice = new Dice (1,23); //metto un valore che so che non stamperà
                    localBoard.getUsedMatrix().get(r).get(c).setDiceContained(helpDice);
                }
            }
        }

        diagA1[0]=localBoard.getUsedMatrix().get(1).get(0).getDiceContained().getColor();
        diagA1[1]=localBoard.getUsedMatrix().get(0).get(2).getDiceContained().getColor();

        diagA2[0]=localBoard.getUsedMatrix().get(2).get(0).getDiceContained().getColor();
        diagA2[1]=localBoard.getUsedMatrix().get(1).get(1).getDiceContained().getColor();
        diagA2[2]=localBoard.getUsedMatrix().get(0).get(2).getDiceContained().getColor();

        diagA3[0]=localBoard.getUsedMatrix().get(3).get(0).getDiceContained().getColor();
        diagA3[1]=localBoard.getUsedMatrix().get(2).get(1).getDiceContained().getColor();
        diagA3[2]=localBoard.getUsedMatrix().get(1).get(2).getDiceContained().getColor();
        diagA3[3]=localBoard.getUsedMatrix().get(0).get(3).getDiceContained().getColor();

        diagA4[0]=localBoard.getUsedMatrix().get(3).get(1).getDiceContained().getColor();
        diagA4[1]=localBoard.getUsedMatrix().get(2).get(2).getDiceContained().getColor();
        diagA4[2]=localBoard.getUsedMatrix().get(1).get(3).getDiceContained().getColor();
        diagA4[3]=localBoard.getUsedMatrix().get(0).get(4).getDiceContained().getColor();

        diagA5[0]=localBoard.getUsedMatrix().get(3).get(2).getDiceContained().getColor();
        diagA5[1]=localBoard.getUsedMatrix().get(2).get(3).getDiceContained().getColor();
        diagA5[2]=localBoard.getUsedMatrix().get(1).get(4).getDiceContained().getColor();

        diagA6[0]=localBoard.getUsedMatrix().get(3).get(3).getDiceContained().getColor();
        diagA6[1]=localBoard.getUsedMatrix().get(2).get(4).getDiceContained().getColor();

        //------ Creazione manuale delle diagonali 4X5

        diagB1[0]=localBoard.getUsedMatrix().get(1).get(4).getDiceContained().getColor();
        diagB1[1]=localBoard.getUsedMatrix().get(0).get(3).getDiceContained().getColor();

        diagB2[0]=localBoard.getUsedMatrix().get(2).get(4).getDiceContained().getColor();
        diagB2[1]=localBoard.getUsedMatrix().get(1).get(3).getDiceContained().getColor();
        diagB2[2]=localBoard.getUsedMatrix().get(0).get(2).getDiceContained().getColor();

        diagB3[0]=localBoard.getUsedMatrix().get(3).get(4).getDiceContained().getColor();
        diagB3[1]=localBoard.getUsedMatrix().get(2).get(3).getDiceContained().getColor();
        diagB3[2]=localBoard.getUsedMatrix().get(1).get(2).getDiceContained().getColor();
        diagB3[3]=localBoard.getUsedMatrix().get(0).get(1).getDiceContained().getColor();

        diagB4[0]=localBoard.getUsedMatrix().get(3).get(3).getDiceContained().getColor();
        diagB4[1]=localBoard.getUsedMatrix().get(2).get(2).getDiceContained().getColor();
        diagB4[2]=localBoard.getUsedMatrix().get(1).get(1).getDiceContained().getColor();
        diagB4[3]=localBoard.getUsedMatrix().get(0).get(0).getDiceContained().getColor();

        diagB5[0]=localBoard.getUsedMatrix().get(3).get(2).getDiceContained().getColor();
        diagB5[1]=localBoard.getUsedMatrix().get(2).get(1).getDiceContained().getColor();
        diagB5[2]=localBoard.getUsedMatrix().get(1).get(0).getDiceContained().getColor();

        diagB6[0]=localBoard.getUsedMatrix().get(3).get(1).getDiceContained().getColor();
        diagB6[1]=localBoard.getUsedMatrix().get(2).get(0).getDiceContained().getColor();



        for(int i=0;i<diagA1.length;i++){
            System.out.println("Color inside A1["+(i+1)+"] :"+color.IntToColor(diagA1[i]));
        }
        System.out.println();
        for(int i=0;i<diagA2.length;i++){
            System.out.println("Color inside A2["+(i+1)+"] :"+color.IntToColor(diagA2[i]));
        }
        System.out.println();
        for(int i=0;i<diagA3.length;i++){
            System.out.println("Color inside A3["+(i+1)+"] :"+color.IntToColor(diagA3[i]));
        }
        System.out.println();
        for(int i=0;i<diagA4.length;i++){
            System.out.println("Color inside A4["+(i+1)+"] :"+color.IntToColor(diagA4[i]));
        }
        System.out.println();
        for(int i=0;i<diagA5.length;i++){
            System.out.println("Color inside A5["+(i+1)+"] :"+color.IntToColor(diagA5[i]));
        }
        System.out.println();
        for(int i=0;i<diagA6.length;i++){
            System.out.println("Color inside A6["+(i+1)+"] :"+color.IntToColor(diagA6[i]));
        }
        System.out.println();

        //----- stampa dei colori interni


        for(int i=0;i<diagB1.length;i++){
            System.out.println("Color inside B1["+(i+1)+"] :"+color.IntToColor(diagB1[i]));
        }
        System.out.println();
        for(int i=0;i<diagB2.length;i++){
            System.out.println("Color inside B2["+(i+1)+"] :"+color.IntToColor(diagB2[i]));
        }
        System.out.println();
        for(int i=0;i<diagB3.length;i++){
            System.out.println("Color inside B3["+(i+1)+"] :"+color.IntToColor(diagB3[i]));
        }
        System.out.println();
        for(int i=0;i<diagB4.length;i++){
            System.out.println("Color inside B4["+(i+1)+"] :"+color.IntToColor(diagB4[i]));
        }
        System.out.println();
        for(int i=0;i<diagB5.length;i++){
            System.out.println("Color inside B5["+(i+1)+"] :"+color.IntToColor(diagB5[i]));
        }
        System.out.println();
        for(int i=0;i<diagB6.length;i++){
            System.out.println("Color inside B6["+(i+1)+"] :"+color.IntToColor(diagB6[i]));
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        points=countConsecutiveColors(diagA1)+
                countConsecutiveColors(diagA2)+
                countConsecutiveColors(diagA3)+
                countConsecutiveColors(diagA4)+
                countConsecutiveColors(diagA5)+
                countConsecutiveColors(diagA6)+
                countConsecutiveColors(diagB1)+
                countConsecutiveColors(diagB2)+
                countConsecutiveColors(diagB3)+
                countConsecutiveColors(diagB4)+
                countConsecutiveColors(diagB5)+
                countConsecutiveColors(diagB6);

        int collateralPoints=0;

        if(countConsecutiveColors(diagA1)>0)collateralPoints++;
        if(countConsecutiveColors(diagA2)>0)collateralPoints++;
        if(countConsecutiveColors(diagA3)>0)collateralPoints++;
        if(countConsecutiveColors(diagA4)>0)collateralPoints++;
        if(countConsecutiveColors(diagA5)>0)collateralPoints++;
        if(countConsecutiveColors(diagA6)>0)collateralPoints++;
        if(countConsecutiveColors(diagB1)>0)collateralPoints++;
        if(countConsecutiveColors(diagB2)>0)collateralPoints++;
        if(countConsecutiveColors(diagB3)>0)collateralPoints++;
        if(countConsecutiveColors(diagB4)>0)collateralPoints++;
        if(countConsecutiveColors(diagB5)>0)collateralPoints++;
        if(countConsecutiveColors(diagB6)>0)collateralPoints++;

        return points+collateralPoints;
    }
}
