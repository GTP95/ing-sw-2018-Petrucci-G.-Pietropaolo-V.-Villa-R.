package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.ToolCards.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GAMEPLAYTEST {
    private WindowBoard windowBoard;
    private RoundTrack roundTrack;

    private GrozingPliers grozingPliers;
    private EglomiseBrush eglomiseBrush;
    private CopperFoilBurnisher copperFoilBurnisher;
    private Lathekin lathekin;
    private CorkBackedStraightedge corkBackedStraightedge;
    private GrindingStone grindingStone;

    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;
    private Dice dice6;
    private Dice dice1Copper;

    private final int rows = 4;
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);

        grozingPliers=mock(GrozingPliers.class);
        eglomiseBrush=mock(EglomiseBrush.class);
        copperFoilBurnisher=mock(CopperFoilBurnisher.class);
        lathekin =mock(Lathekin.class);
        corkBackedStraightedge=mock(CorkBackedStraightedge.class);
        grindingStone=mock(GrindingStone.class);

        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice4=mock(Dice.class);
        dice5=mock(Dice.class);
        dice6=mock(Dice.class);
        dice1Copper=mock(Dice.class);

        when(dice1.getColor()).thenReturn(Color.RED);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.BLUE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.BLUE);
        when(dice3.getValue()).thenReturn(3);

        when(dice1Copper.getColor()).thenReturn(Color.GREEN);
        when(dice1Copper.getValue()).thenReturn(1);

        when(dice4.getColor()).thenReturn(Color.PURPLE);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.YELLOW);
        when(dice5.getValue()).thenReturn(5);

        when(dice6.getColor()).thenReturn(Color.YELLOW);
        when(dice6.getValue()).thenReturn(6);
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
        Lathekin lathekin = new Lathekin();
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        GrindingStone grindingStone = new GrindingStone();


        //Importing da file
        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);//Industria
        System.out.println("INDUSTRIA CHOOSEN");
        boardPlayerOne.printMatrix(testMatrix,rows,columns);

        //Creazione dell'oggetto WindowBoard su cui andremo a lavorare
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        System.out.println();

        //Dadi da usare
        System.out.println("DICE DRAWN");
        System.out.println("Dice1 color (RED):"+dice1.getColor());
        System.out.println("Dice1 value :"+dice1.getValue());
        System.out.println("Dice2 color (BLUE):"+dice2.getColor());
        System.out.println("Dice2 value :"+dice2.getValue());
        System.out.println("Dice3 color (BLUE):"+dice3.getColor());
        System.out.println("Dice3 value :"+dice3.getValue());
        System.out.println("Dice4 color (PURPLE):"+dice4.getColor());
        System.out.println("Dice4 value :"+dice4.getValue());
        System.out.println("Dice5 color (YELLOW):"+dice5.getColor());
        System.out.println("Dice5 value :"+dice5.getValue());
        System.out.println("Dice6 color (YELLOW):"+dice6.getColor());
        System.out.println("Dice6 value :"+dice6.getValue());
        System.out.println();

        //Inizio partita vera e propria
        System.out.println("***START GAME***");
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        //Inizio inserimento/uso carte
        System.out.println("1° Inserimento");
        boardPlayerOne.insertDice(4,1,dice1);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("2° Inserimento");
        boardPlayerOne.insertDice(4,2,dice2);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();
        /*

        System.out.println("Grozing Pliers");
        grozingPliers.applyEffect(boardPlayerOne,"UP",dice2,3,1,1);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("Eglomise Brush");
        eglomiseBrush.applyEffect(boardPlayerOne,dice3,3,1,3,2,1);
        boardPlayerOne.printMatrixArrayList();
        //Test per il controllo della Eglomise Brush
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(1).getDiceContained().getValue(),3);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(1).getDiceContained().getColor(),2);
        System.out.println();

        System.out.println("Copper Foil Burnisher");
        copperFoilBurnisher.applyEffect(boardPlayerOne,dice1Copper,4,2,3,1,1);
        boardPlayerOne.printMatrixArrayList();
        //Test per il controllo della Copper Foil Burnisher
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(0).getDiceContained().getValue(),1);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(0).getDiceContained().getColor(),5);
        System.out.println();
        */

        System.out.println("Lathekin");
        lathekin.applyEffect(boardPlayerOne,4,1,4,2,3,1,3,2,dice1,dice2,1);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(0).isUsed(),true);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(2).get(1).isUsed(),true);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("Lathekin 2° uso ");
        lathekin.applyEffect(boardPlayerOne,3,1,3,2,4,1,4,2,dice1,dice2,2);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(3).get(0).isUsed(),true);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(3).get(1).isUsed(),true);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("CorkBackedStraightedge");
        corkBackedStraightedge.applyEffect(boardPlayerOne,dice5,2,5,1);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(1).get(4).isUsed(),true);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();


        System.out.println("CorkBackedStraightedge 2° uso");
        corkBackedStraightedge.applyEffect(boardPlayerOne,dice1,4,5,2);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(3).get(4).isUsed(),true);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("Grinding Stone");
        grindingStone.applyEffect(boardPlayerOne,dice6,3,5,1);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(3).get(4).isUsed(),true);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(3).get(4).getDiceContained().getValue(),1);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("Grinding Stone 2° uso");
        grindingStone.applyEffect(boardPlayerOne,dice1,1,5,2);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(0).get(4).isUsed(),true);
        Assert.assertEquals(boardPlayerOne.getUsedMatrix().get(0).get(4).getDiceContained().getValue(),6);
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

    }
}
