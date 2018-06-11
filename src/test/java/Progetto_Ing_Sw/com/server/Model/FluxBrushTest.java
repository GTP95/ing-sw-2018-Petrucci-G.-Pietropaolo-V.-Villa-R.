package Progetto_Ing_Sw.com.server.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FluxBrushTest {

    private WindowBoard windowBoard;
    private FluxBrush fluxBrush;
    private DiceBag diceBag;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;
    private Dice dice6;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        fluxBrush=mock(FluxBrush.class);
        diceBag=mock(DiceBag.class);

        dice1 = mock(Dice.class);
        dice2 = mock(Dice.class);
        dice3 = mock(Dice.class);
        dice4 = mock(Dice.class);
        dice5 = mock(Dice.class);
        dice6 = mock(Dice.class);

        when(dice1.getColor()).thenReturn(Color.RED);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.BLUE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.BLUE);
        when(dice3.getValue()).thenReturn(3);

        when(dice4.getColor()).thenReturn(Color.PURPLE);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.YELLOW);
        when(dice5.getValue()).thenReturn(5);

        when(dice6.getColor()).thenReturn(Color.YELLOW);
        when(dice6.getValue()).thenReturn(6);
    }

    @Test
    public void applyEffect(){

        //riempimento della diceBag
        diceBag = new DiceBag();
        ArrayList<Dice> diceDrawn = diceBag.diceDraw(10);
        int rows=4;
        int columns=5;

        //Inizializzazione gameboard + carte usate
        WindowBoard boardPlayerOne = new WindowBoard(rows,columns);
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);//Industria
        System.out.println("INDUSTRIA CHOOSEN");
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        boardPlayerOne.printMatrix(testMatrix,rows,columns);

            //TODO da completare in quanto integration testing





    }
}
