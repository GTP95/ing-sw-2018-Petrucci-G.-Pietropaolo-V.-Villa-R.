package Progetto_Ing_Sw.com.server.Model.ToolCardsTest;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.server.Model.ToolCards.GrozingPliers;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GrozingPliersTest {

    private WindowBoard windowBoard;
    private GrozingPliers grozingPliers;
    private Dice dice3;
    private Dice dice2;
    private Dice dice1;
    private Dice dice4;
    private Dice dice5;
    private int rows=4,columns=5;

    @Before
    public void before(){
        windowBoard=mock(WindowBoard.class);
        grozingPliers=mock(GrozingPliers.class);
        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice4=mock(Dice.class);
        dice5=mock(Dice.class);


        when(dice1.getColor()).thenReturn(Color.BLUE);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.PURPLE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.RED);
        when(dice3.getValue()).thenReturn(3);

        when(dice4.getColor()).thenReturn(Color.YELLOW);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.GREEN);
        when(dice5.getValue()).thenReturn(5);
    }

  /*  @Test
    public void testCard() throws PlaceDiceException {

        // dice3 = rosso,3
        // dice2 = viola,2
        // dice1 = blu(2), 1
        // dice4 = giallo, 4
        // dice5 = verde, 5

        WindowBoard boardPlayerOne = new WindowBoard(rows, columns);
        GrozingPliers grozingPliers = new GrozingPliers();
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);//Industria

        System.out.println("Matrice prima dell'inserimento");
        boardPlayerOne.printMatrix(testMatrix,rows,columns);
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        System.out.println("***MATRICE di ArrayList creata+settata correttamente***");
        System.out.println();

        boardPlayerOne.insertDice(4,3,dice1);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        grozingPliers.applyEffect(boardPlayerOne,"UP",dice1,3,2, 1); //da uno deve diventare 2
        boardPlayerOne.printMatrixArrayList();
        System.out.println("***GROZING PLIERS usata correttamente***");
        System.out.println();

        grozingPliers.applyEffect(boardPlayerOne,"UP",dice2,4,2, 2);
        boardPlayerOne.printMatrixArrayList();
        System.out.println("***GROZING PLIERS usata correttamente***");
        System.out.println();

        grozingPliers.applyEffect(boardPlayerOne,"DOWN",dice3,4,1, 2);
        boardPlayerOne.printMatrixArrayList();
        System.out.println("***GROZING PLIERS usata correttamente***");
        System.out.println();
    }*/
}
