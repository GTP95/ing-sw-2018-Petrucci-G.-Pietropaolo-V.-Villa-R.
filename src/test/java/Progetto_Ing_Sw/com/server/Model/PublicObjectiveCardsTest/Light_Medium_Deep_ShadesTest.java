package Progetto_Ing_Sw.com.server.Model.PublicObjectiveCardsTest;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards.DeepShades;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards.LightShades;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCards.MediumShades;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Light_Medium_Deep_ShadesTest {

    private WindowBoard windowBoard;
    private LightShades lightShades;
    private MediumShades mediumShades;
    private DeepShades deepShades;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;
    private Dice dice6;
    private Dice diceBounus;
    private int rows=4;
    private int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        lightShades=mock(LightShades.class);
        mediumShades=mock(MediumShades.class);
        deepShades=mock(DeepShades.class);

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
    public void countPoints(){

        WindowBoard boardPlayerOne = new WindowBoard(rows,columns);
        LightShades lightShades = new LightShades();
        MediumShades mediumShades = new MediumShades();
        DeepShades deepShades = new DeepShades();
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,17);//Batllo
        System.out.println("BATLLO CHOOSEN");
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        boardPlayerOne.printMatrix(testMatrix,rows,columns);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("INSERIMENTO DADI");
        boardPlayerOne.insertDice(1,1,dice1);//rosso
        boardPlayerOne.insertDice(1,2,dice2);//blu
        boardPlayerOne.insertDice(1,3,dice6);//giallo
        boardPlayerOne.insertDice(1,4,dice1);//rosso
        boardPlayerOne.insertDice(1,5,dice2);//blu
        boardPlayerOne.insertDice(2,1,dice2);//blu
        boardPlayerOne.insertDice(2,2,dice5);//giallo
        boardPlayerOne.insertDice(2,3,dice2);//blu
        boardPlayerOne.insertDice(2,4,dice4);//verde
        boardPlayerOne.insertDice(2,5,dice6);//verde
        boardPlayerOne.insertDice(3,4,dice3);//viola
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("CALCOLO PUNTI");
        System.out.println("Punti ricavati da LightShades==>"+lightShades.calculatePoints(boardPlayerOne));
        System.out.println("Punti ricavati da MediumShades==>"+mediumShades.calculatePoints(boardPlayerOne));
        System.out.println("Punti ricavati da DeepShades==>"+deepShades.calculatePoints(boardPlayerOne));
        System.out.println();


    }
}
