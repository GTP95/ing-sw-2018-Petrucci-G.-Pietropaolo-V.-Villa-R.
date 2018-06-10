package Progetto_Ing_Sw.com.server.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class GamePlayTest {
    private WindowBoard windowBoard;
    private GrozingPliers grozingPliers;
    private EglomiseBrush eglomiseBrush;
    private CopperFoilBurnisher copperFoilBurnisher;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice1Copper;

    private final int rows = 4;
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        grozingPliers=mock(GrozingPliers.class);
        eglomiseBrush=mock(EglomiseBrush.class);
        copperFoilBurnisher=mock(CopperFoilBurnisher.class);
        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice1Copper=mock(Dice.class);

        when(dice1.getColor()).thenReturn(Color.RED);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.BLUE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.BLUE);
        when(dice3.getValue()).thenReturn(3);

        when(dice1Copper.getColor()).thenReturn(Color.GREEN);
        when(dice1Copper.getValue()).thenReturn(1);


    }

    @Test
    public void GAMEPLAY() {

        //apertura prima schermata
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(" ____  _                           _     _                 _         ____                            _         _____               _ _ _       _ \n" +
                "| __ )(_) ___ _ ____   _____ _ __ (_) __| | ___     __ _  | | __ _  / ___|  __ _  __ _ _ __ __ _  __| | __ _  |  ___|_ _ _ __ ___ (_) (_) __ _| |\n" +
                "|  _ \\| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\   / _` | | |/ _` | \\___ \\ / _` |/ _` | '__/ _` |/ _` |/ _` | | |_ / _` | '_ ` _ \\| | | |/ _` | |\n" +
                "| |_) | |  __/ | | \\ V /  __/ | | | | (_| | (_) | | (_| | | | (_| |  ___) | (_| | (_| | | | (_| | (_| | (_| | |  _| (_| | | | | | | | | | (_| |_|\n" +
                "|____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/   \\__,_| |_|\\__,_| |____/ \\__,_|\\__, |_|  \\__,_|\\__,_|\\__,_| |_|  \\__,_|_| |_| |_|_|_|_|\\__,_(_)\n" +
                "                                                                                 |___/     ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

        //Inizializzazione gameboard + carte usate
        WindowBoard boardPlayerOne = new WindowBoard(rows, columns);
        GrozingPliers grozingPliers = new GrozingPliers();
        EglomiseBrush eglomiseBrush = new EglomiseBrush();
        CopperFoilBurnisher copperFoilBurnisher = new CopperFoilBurnisher();

        //Importing da file
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);//Industria
        System.out.println("INDUSTRIA CHOOSEN");
        boardPlayerOne.printMatrix(testMatrix,rows,columns);

        //Creazione dell'oggetto WindowBoard su cui andremo a lavorare
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders(boardPlayerOne.getUsedMatrix());
        System.out.println();

        //Dadi da usare
        System.out.println("DICE DRAWN");
        System.out.println("Dice1 color (RED):"+dice1.getColor());
        System.out.println("Dice1 value :"+dice1.getValue());
        System.out.println("Dice2 color (BLUE):"+dice2.getColor());
        System.out.println("Dice2 value :"+dice2.getValue());
        System.out.println("Dice3 color (BLUE):"+dice3.getColor());
        System.out.println("Dice3 value :"+dice3.getValue());
        System.out.println();

        //Inizio partita vera e propria
        System.out.println("***START GAME***");
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        System.out.println();

        //Inizio inserimento/uso carte
        System.out.println("1° Inserimento");
        boardPlayerOne.insertDice(boardPlayerOne.getUsedMatrix(),4,1,dice1);
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        System.out.println();

        System.out.println("2° Inserimento");
        boardPlayerOne.insertDice(boardPlayerOne.getUsedMatrix(),4,2,dice2);
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        System.out.println();

        System.out.println("3° Inserimento - Grozing Pliers");
        grozingPliers.applyEffect(boardPlayerOne,"UP",dice2,3,1,1);
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        System.out.println();

        System.out.println("3° Inserimento - Eglomise Brush");
        eglomiseBrush.applyEffect(boardPlayerOne,dice3,3,1,3,2,1);
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        //Test per il controllo della Eglomise Brush
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(1).getDiceContained().getValue(),3);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(1).getDiceContained().getColor(),2);
        System.out.println();

        System.out.println("3° Inserimento - Copper Foil Burnisher");
        copperFoilBurnisher.applyEffect(boardPlayerOne,dice1Copper,4,2,3,1,1);
        boardPlayerOne.printMatrixArrayList(boardPlayerOne.getUsedMatrix());
        //Test per il controllo della Copper Foil Burnisher
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(0).getDiceContained().getValue(),1);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(0).getDiceContained().getColor(),5);
        System.out.println();



    }
}
