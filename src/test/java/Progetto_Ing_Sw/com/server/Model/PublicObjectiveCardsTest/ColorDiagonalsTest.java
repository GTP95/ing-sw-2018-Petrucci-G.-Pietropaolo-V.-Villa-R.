package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCardsTest;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards.ColorDiagonals;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ColorDiagonalsTest {

        private WindowBoard windowBoard;
        private ColorDiagonals colorDiagonals;
        private Dice dice1;
    private Dice dice2r;
    private Dice dice3r;
    private Dice dice4r;
    private Dice dice5r;
    private Dice dice6r;
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
            colorDiagonals = mock(ColorDiagonals.class);

            dice1 = mock(Dice.class);
            dice2 = mock(Dice.class);
            dice3 = mock(Dice.class);
            dice4 = mock(Dice.class);
            dice5 = mock(Dice.class);
            dice6 = mock(Dice.class);

            dice2r = mock(Dice.class);
            dice3r = mock(Dice.class);
            dice4r = mock(Dice.class);
            dice5r = mock(Dice.class);
            dice6r = mock(Dice.class);


            when(dice1.getColor()).thenReturn(Color.RED);
            when(dice1.getValue()).thenReturn(1);

            when(dice2r.getColor()).thenReturn(Color.RED);
            when(dice2r.getValue()).thenReturn(2);
            when(dice3r.getColor()).thenReturn(Color.RED);
            when(dice3r.getValue()).thenReturn(3);
            when(dice4r.getColor()).thenReturn(Color.RED);
            when(dice4r.getValue()).thenReturn(4);
            when(dice5r.getColor()).thenReturn(Color.RED);
            when(dice5r.getValue()).thenReturn(5);
            when(dice6r.getColor()).thenReturn(Color.RED);
            when(dice6r.getValue()).thenReturn(6);

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
        public void countPoints() throws PlaceDiceException {

            WindowBoard boardPlayerOne = new WindowBoard(rows,columns);
            ColorDiagonals colorDiagonals = new ColorDiagonals();;

            int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);//Batllo
            boardPlayerOne.importNameFromFile(24);
            System.out.println(boardPlayerOne.getTitle());
            boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
            boardPlayerOne.setBorders();
            boardPlayerOne.printMatrix(testMatrix,rows,columns);
            boardPlayerOne.printMatrixArrayList();
            System.out.println();

            System.out.println("INSERIMENTO DADI");
            boardPlayerOne.insertDice(1,2,dice1);
            boardPlayerOne.insertDice(2,3,dice2r);
            boardPlayerOne.insertDice(3,4,dice3r);
            boardPlayerOne.insertDice(4,5,dice4r);
            boardPlayerOne.insertDice(4,3,dice1);
            boardPlayerOne.insertDice(3,2,dice2r);
            boardPlayerOne.insertDice(4,1,dice3r);
            boardPlayerOne.insertDice(3,1,dice5);
            boardPlayerOne.insertDice(4,2,dice6);
            boardPlayerOne.insertDice(3,3,dice5);
            boardPlayerOne.printMatrixArrayList();
            System.out.println();

            System.out.println("CALCOLO PUNTI");
            System.out.println("Punti guadagnati :"+colorDiagonals.calculatePoints(boardPlayerOne));
            System.out.println();


        }
}
