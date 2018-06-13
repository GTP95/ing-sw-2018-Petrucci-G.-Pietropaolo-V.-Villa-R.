package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.ToolCards.FluxRemover;
import Progetto_Ing_Sw.com.server.Model.ToolCards.TapWheel;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TapWheelTest {

    private WindowBoard windowBoard;
    private int rows=4;
    private int columns=5;
    private TapWheel tapWheel;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;

    @Before
    public void before() {

        windowBoard = mock(WindowBoard.class);
        tapWheel=mock(TapWheel.class);

        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice4=mock(Dice.class);
        dice5=mock(Dice.class);


        when(dice1.getColor()).thenReturn(Color.RED);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.BLUE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.PURPLE);
        when(dice3.getValue()).thenReturn(3);

        when(dice4.getColor()).thenReturn(Color.YELLOW);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.GREEN);
        when(dice5.getValue()).thenReturn(5);
    }

    @Test
    public void applyEffect(){

        WindowBoard boardPlayerOne = new WindowBoard(rows, columns);
        TapWheel tapWheel = new TapWheel();

        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);
        System.out.println("INDUSTRIA CHOOSEN");
        boardPlayerOne.printMatrix(testMatrix,rows,columns);
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        boardPlayerOne.printMatrixArrayList();
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println();

        //Inserimento di due dadi
        boardPlayerOne.insertDice(4,1,dice1);
        boardPlayerOne.insertDice(3,2,dice1);
        boardPlayerOne.printMatrixArrayList();

        //Applicazione dell'effetto
        tapWheel.applyEffect(boardPlayerOne,dice1,4,1,3,2,4,5,3,4,dice1,dice1,1);
        boardPlayerOne.printMatrixArrayList();

    }
}
