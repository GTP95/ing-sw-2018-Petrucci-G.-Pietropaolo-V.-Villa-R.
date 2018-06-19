package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCardsTest;


import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards.RowShadeVariety;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RowShadeVarietyTest {

    private WindowBoard windowBoard;
    private RowShadeVariety rowShadeVariety;
    private DiceBag diceBag;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;
    private Dice dice6;
    private int rows=4;
    private int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        rowShadeVariety=mock(RowShadeVariety.class);
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

        when(dice3.getColor()).thenReturn(Color.PURPLE);
        when(dice3.getValue()).thenReturn(3);

        when(dice4.getColor()).thenReturn(Color.GREEN);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.YELLOW);
        when(dice5.getValue()).thenReturn(5);

        when(dice6.getColor()).thenReturn(Color.YELLOW);
        when(dice6.getValue()).thenReturn(6);
    }

    @Test
    public void countPoints() throws ShadeNotEqualException, AdjacencyException, OccupiedCellException, ColorNotEqualException, AdjacencyBreakerException, OrthogonalColorException, OrthogonalValueException, NotOnBordersException {

        WindowBoard boardPlayerOne = new WindowBoard(rows,columns);
        RowShadeVariety rowShadeVariety = new RowShadeVariety();
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,17);//Batllo
        System.out.println("BATLLO CHOOSEN");
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        boardPlayerOne.printMatrix(testMatrix,rows,columns);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("INSERIMENTO DADI");
        boardPlayerOne.insertDice(1,1,dice1);
        boardPlayerOne.insertDice(1,2,dice2);
        boardPlayerOne.insertDice(1,3,dice6);
        boardPlayerOne.insertDice(1,4,dice3);
        boardPlayerOne.insertDice(1,5,dice4);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("CALCOLO PUNTI");
        int points=rowShadeVariety.calculatePoints(boardPlayerOne);
        System.out.println("Punti ricavati dalla carta ==>"+points);
        System.out.println();


    }
}
