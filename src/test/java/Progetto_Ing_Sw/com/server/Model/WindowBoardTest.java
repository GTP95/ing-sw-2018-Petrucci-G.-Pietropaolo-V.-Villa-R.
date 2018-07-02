package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.ToolCards.CopperFoilBurnisher;
import Progetto_Ing_Sw.com.server.Model.ToolCards.CorkBackedStraightedge;
import Progetto_Ing_Sw.com.server.Model.ToolCards.EglomiseBrush;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private Dice dice3;
    private Dice dice2;
    private Dice dice1;
    private Dice dice4;
    private Dice dice5;
    private Dice dice5r;

    private Dice dice6Y;
    private Dice dice5Y;
    private Dice dice4Y;
    private Dice dice4r;

    private final int rows = 4;       //colonne e righe rimangono costanti
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice4=mock(Dice.class);
        dice5=mock(Dice.class);
        dice5r=mock(Dice.class);

        dice6Y=mock(Dice.class);
        dice5Y=mock(Dice.class);
        dice4Y=mock(Dice.class);
        dice4r=mock(Dice.class);



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

        when(dice5r.getColor()).thenReturn(Color.RED);
        when(dice5r.getValue()).thenReturn(5);

        when(dice6Y.getColor()).thenReturn(Color.YELLOW);
        when(dice6Y.getValue()).thenReturn(6);
        when(dice5Y.getColor()).thenReturn(Color.YELLOW);
        when(dice5Y.getValue()).thenReturn(5);
        when(dice4Y.getColor()).thenReturn(Color.YELLOW);
        when(dice4Y.getValue()).thenReturn(4);
        when(dice4r.getColor()).thenReturn(Color.RED);
        when(dice4r.getValue()).thenReturn(4);
    }

    @Test//Test utile solo al controllo delle variabili, da eliminare
    public void simplePrint() {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        windowBoard.printMatrix(testMatrix, rows, columns);
    }

    //non posso testare le eccezioni in quanto dovrei cambiare  il path delle carte e creerei problemi al gioco
    @Test
    public void testCorrectImportFromFile(){

        for(int i=1;i<=24;i++){
            WindowBoard windowBoard = new WindowBoard(rows, columns);
            int [][] localMatrix = windowBoard.importFromFile(4,5,i);
            ArrayList<ArrayList<MatrixCell>> localMatrixArray = windowBoard.fromIntToArrayList(localMatrix,4,5);
            windowBoard.setUsedMatrix(localMatrixArray);
            Assert.assertNotNull(windowBoard.getUsedMatrix());
        }
    }

    @Test
    public void correctNumberOfRowsAfterHJsonImport() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        System.out.println("INT-MATRIX ROWS: " + testMatrix.length);

        //Verifico che la matrice creata fa file abbia il numero di righe corrette
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setUsedMatrix(martrixArray);
        System.out.println("ARRAYLIST-MATRIX ROWS: " +windowBoard.getUsedMatrix().size());
        Assert.assertEquals(rows, martrixArray.size());
    }

    @Test
    public void correctNumberOfColumnsAfterHJsonImport() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        System.out.println("INT-MATRIX NUM OF COLUMN FOR EACH ROW: " + columns);

        //Verifico che la matrice creata fa file abbia il numero di righe corrette
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        for (int r = 0; r < rows; r++) {
            System.out.println("ARRAYLIST-MATRIX ROW (" + (r + 1) + ") NUM OF COLUMN : " + martrixArray.get(r).size());
            Assert.assertEquals(columns, martrixArray.get(r).size());
        }
    }

    @Test
    public void checkThatTheMatrixIsEmpty() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        Assert.assertFalse(windowBoard.matrixNotEmpty());
    }

    @Test
    public void setAndCheckBorders() {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 22);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        Assert.assertFalse(windowBoard.matrixNotEmpty());
        windowBoard.setBorders(); //set de bordi

        //controllo dei bordi
        for(int r=0;r<windowBoard.getUsedMatrix().size();r++){
            for (int c=0;c<windowBoard.getUsedMatrix().get(r).size();c++){

                if(c==0){
                    Assert.assertTrue(windowBoard.getUsedMatrix().get(r).get(c).isOnBorder());
                }
                if(c==windowBoard.getUsedMatrix().get(r).size()-1){
                    Assert.assertTrue(windowBoard.getUsedMatrix().get(r).get(c).isOnBorder());
                }
                if(r==0){
                    Assert.assertTrue(windowBoard.getUsedMatrix().get(r).get(c).isOnBorder());
                }
                if(r==windowBoard.getUsedMatrix().size()-1){
                    Assert.assertTrue(windowBoard.getUsedMatrix().get(r).get(c).isOnBorder());
                }
                if((r!=0)&&(c!=0)&&(c!=windowBoard.getUsedMatrix().get(r).size()-1)&&(r!=windowBoard.getUsedMatrix().size()-1)){
                    Assert.assertNotEquals(windowBoard.getUsedMatrix().get(r).get(c).isOnBorder(),true);
                }
            }
        }
        Assert.assertTrue(windowBoard.areOnBorders());
    }

    @Test
    public void printingValueColorDice(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,8);
        windowBoard.importNameFromFile(8);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrixArrayList();
        windowBoard.printDiceColorValueArrayList();

    }

    @Test
    public void importingNameFromJson(){

        for (int i=1;i<=24;i++){
            WindowBoard windowBoard = new WindowBoard(4,5);
            windowBoard.importNameFromFile(i);
            System.out.println("Nome carta #["+i+"] : "+windowBoard.getTitle());
        }

    }

    @Test
    public void checkShadeOfAMatrixCell(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(1).get(0),dice4));
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(1).get(2),dice3));
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(1).get(4),dice2));
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(2).get(1),dice1));
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(2).get(3),dice5));
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(3).get(2),dice6Y));
    }

    @Test
    public void checkBlankOfAMatrixCell(){
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,23);
        System.out.println("Matrice prima dell'inserimento");

        windowBoard.printMatrix(testMatrix,rows,columns);
        System.out.println("DICE COLOR ="+dice3.getColor());
        System.out.println("DICE VALUE ="+dice3.getValue());

        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        for (int row = 0; row < windowBoard.getUsedMatrix().size(); row++) {
            for (int column = 0; column < windowBoard.getUsedMatrix().get(row).size(); column++) {
                if(windowBoard.getUsedMatrix().get(row).get(column).getColor()==Color.BLANK){
                    System.out.println("CELL ["+(row+1)+"]["+(column+1)+"] COLOR IS "+windowBoard.getUsedMatrix().get(row).get(column).getColor());
                }
            }
        }
        windowBoard.printMatrixArrayList();
    }

    @Test
    public void insertDiceInArrayListMatrix(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        System.out.println("DICE COLOR ="+dice3.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice3.getValue()); //3

        //testo le caselle valide
        windowBoard.insertDiceARRLIST(1,1,dice3);
        windowBoard.insertDiceARRLIST(1,2,dice3);
        windowBoard.insertDiceARRLIST(1,4,dice3);
        windowBoard.insertDiceARRLIST(1,5,dice3);
        windowBoard.insertDiceARRLIST(2,1,dice3);
        windowBoard.insertDiceARRLIST(2,5,dice3);
        System.out.println();

        //testo le sfumature valide
        windowBoard.insertDiceARRLIST(3,1,dice3);
        windowBoard.insertDiceARRLIST(4,5,dice3);
        System.out.println();

        //testo i colori validi
        windowBoard.insertDiceARRLIST(4,3,dice3);
        System.out.println();

        //testo i colori NON validi
        windowBoard.insertDiceARRLIST(2,3,dice3);
        windowBoard.insertDiceARRLIST(3,2,dice3);
        windowBoard.insertDiceARRLIST(3,2,dice3);
        windowBoard.insertDiceARRLIST(3,4,dice3);
        System.out.println();

        //testo le sfumature non valide
        windowBoard.insertDiceARRLIST(1,3,dice3);
        windowBoard.insertDiceARRLIST(2,4,dice3);
        windowBoard.insertDiceARRLIST(3,5,dice3);
        windowBoard.insertDiceARRLIST(4,2,dice3);
        windowBoard.insertDiceARRLIST(4,4,dice3);
        windowBoard.insertDiceARRLIST(4,1,dice3);
        System.out.println();

        //testo le caselle occupate

        windowBoard.insertDiceARRLIST(1,1,dice3);
        windowBoard.insertDiceARRLIST(1,2,dice3);
        windowBoard.insertDiceARRLIST(1,4,dice3);
        windowBoard.insertDiceARRLIST(1,5,dice3);
        windowBoard.insertDiceARRLIST(2,1,dice3);
        windowBoard.insertDiceARRLIST(2,5,dice3);
        windowBoard.insertDiceARRLIST(3,1,dice3);
        windowBoard.insertDiceARRLIST(4,5,dice3);
        windowBoard.insertDiceARRLIST(4,3,dice3);
        System.out.println();

        windowBoard.printMatrixArrayList();
    }

    @Test
    public void AdditionalTestForCoverageForMethodInsertDiceInArrayListMatrix(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));

        windowBoard.insertDiceARRLIST(1,3,dice5); // copertura prima mossa illegale
        Assert.assertFalse(windowBoard.getUsedMatrix().get(0).get(2).isOnBorder());
        windowBoard.setBorders();

        windowBoard.insertDiceARRLIST(3,1,dice5); // copertura sfumaura non corretta
        Assert.assertTrue(windowBoard.checkShade(windowBoard.getUsedMatrix().get(2).get(0), dice3));

        windowBoard.insertDiceARRLIST(4,3,dice3); // copertura colore corretto
        Assert.assertNotEquals(windowBoard.getUsedMatrix().get(3).get(2).getColor(),Color.SHADE);

        windowBoard.getUsedMatrix().get(3).get(2).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(2).setUsed(false);

        windowBoard.insertDiceARRLIST(4,3,dice6Y); // copertura colore NON  corretto
        Assert.assertNotEquals(windowBoard.getUsedMatrix().get(3).get(2).getColor(),Color.SHADE);
        Assert.assertNotEquals(windowBoard.getUsedMatrix().get(3).get(2).getColor(),dice6Y.getColor());
    }

    @Test
    public void checkUsedCellAdjacent(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        //Set di inserimenti;

        windowBoard.insertDiceARRLIST(1,1,dice3);
        windowBoard.printMatrixArrayList();
        Assert.assertFalse(windowBoard.checkAdjacency(1,1));//nessuna cella

        windowBoard.insertDiceARRLIST(1,2,dice5);
        windowBoard.printMatrixArrayList();
        Assert.assertTrue(windowBoard.checkAdjacency(1,1));//adiacenza a destra

        windowBoard.getUsedMatrix().get(0).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(1).setUsed(false);

        windowBoard.insertDiceARRLIST(2,1,dice5);
        windowBoard.printMatrixArrayList();
        Assert.assertTrue(windowBoard.checkAdjacency(1,1));//adiacenza sotto

        windowBoard.getUsedMatrix().get(1).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(0).setUsed(false);

        windowBoard.insertDiceARRLIST(2,2,dice5);
        windowBoard.printMatrixArrayList();
        Assert.assertTrue(windowBoard.checkAdjacency(1,1));//adiacenza diag sotto/destra

        windowBoard.getUsedMatrix().get(1).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(1).setUsed(false);
        windowBoard.getUsedMatrix().get(0).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(0).setUsed(false);

        windowBoard.insertDiceARRLIST(1,5,dice3);
        windowBoard.insertDiceARRLIST(1,4,dice5);
        windowBoard.printMatrixArrayList();
        Assert.assertTrue(windowBoard.checkAdjacency(1,5));//adiacenza sinistra

        windowBoard.getUsedMatrix().get(0).get(3).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(3).setUsed(false);

        windowBoard.insertDiceARRLIST(2,4,dice4);
        windowBoard.printMatrixArrayList();
        Assert.assertTrue(windowBoard.checkAdjacency(1,5));//adiacenza diag sotto/sinistra



    }

    @Test
    public void checkSecondaryPositionRules(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDiceARRLIST(4,1,dice3);
        windowBoard.insertDiceARRLIST(3,1,dice4r);
        Assert.assertFalse(windowBoard.checkOrthogonalColor(4,1)); //colore sopra

        windowBoard.insertDiceARRLIST(3,5,dice4r);
        windowBoard.insertDiceARRLIST(4,5,dice3);
        Assert.assertFalse(windowBoard.checkOrthogonalColor(3,5)); //colore sotto

        windowBoard.getUsedMatrix().get(2).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(2).get(4).setUsed(false);
        windowBoard.getUsedMatrix().get(2).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(2).get(0).setUsed(false);

        windowBoard.insertDiceARRLIST(4,2,dice4r);
        Assert.assertFalse(windowBoard.checkOrthogonalColor(4,1)); //colore destra

        windowBoard.insertDiceARRLIST(4,4,dice4r);
        Assert.assertFalse(windowBoard.checkOrthogonalColor(4,5)); //colore sinistra

        //-----

        windowBoard.getUsedMatrix().get(3).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(0).setUsed(false);
        windowBoard.getUsedMatrix().get(3).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(4).setUsed(false);
        windowBoard.getUsedMatrix().get(3).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(1).setUsed(false);
        windowBoard.getUsedMatrix().get(3).get(3).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(3).setUsed(false);



        windowBoard.insertDiceARRLIST(4,1,dice4r);
        windowBoard.insertDiceARRLIST(3,1,dice4);
        Assert.assertFalse(windowBoard.checkOrthogonalValue(4,1)); //valore sopra

        windowBoard.insertDiceARRLIST(3,5,dice5);
        windowBoard.insertDiceARRLIST(4,5,dice5Y);
        Assert.assertFalse(windowBoard.checkOrthogonalValue(3,5)); //valore sotto

        windowBoard.getUsedMatrix().get(2).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(2).get(4).setUsed(false);
        windowBoard.getUsedMatrix().get(2).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(2).get(0).setUsed(false);

        windowBoard.insertDiceARRLIST(4,2,dice4);
        Assert.assertFalse(windowBoard.checkOrthogonalValue(4,1)); //valore destra

        windowBoard.insertDiceARRLIST(4,4,dice5);
        Assert.assertFalse(windowBoard.checkOrthogonalValue(4,5)); //valore sinistra

    }

    @Test
    public void insertDiceTestForGamePlay() throws PlaceDiceException {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();
        Assert.assertFalse(windowBoard.matrixNotEmpty());

        windowBoard.insertDice(4,1,dice5); //bordo, bianco
        windowBoard.getUsedMatrix().get(3).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(0).setUsed(false);

        windowBoard.insertDice(4,3,dice6Y); //bordo, sfumatura ok
        windowBoard.getUsedMatrix().get(3).get(2).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(2).setUsed(false);

        EglomiseBrush eglomiseBrush1 = new EglomiseBrush();//color breaker colore qualsiasi
        windowBoard.insertDice(1,2,dice4Y);
        eglomiseBrush1.applyEffect(windowBoard,dice4Y,1,2,1,1,1);
        windowBoard.getUsedMatrix().get(0).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(0).setUsed(false);

        EglomiseBrush eglomiseBrush2 = new EglomiseBrush();//color breaker sfumatura
        windowBoard.insertDice(3,1,dice4Y);
        eglomiseBrush2.applyEffect(windowBoard,dice4Y,3,1,2,1,1);;
        windowBoard.getUsedMatrix().get(1).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(0).setUsed(false);

        CopperFoilBurnisher copperFoilBurnisher1 = new CopperFoilBurnisher();//number breaker sfumature qualsiasi
        windowBoard.insertDice(1,2,dice4Y);
        copperFoilBurnisher1.applyEffect(windowBoard,dice4Y,1,2,2,5,1);
        windowBoard.getUsedMatrix().get(1).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(4).setUsed(false);

        CopperFoilBurnisher copperFoilBurnisher2 = new CopperFoilBurnisher();//number breaker colore
        windowBoard.insertDice(1,2,dice4Y);
        copperFoilBurnisher2.applyEffect(windowBoard,dice4Y,1,2,1,5,1);
        windowBoard.getUsedMatrix().get(0).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(4).setUsed(false);

        windowBoard.insertDice(1,2,dice4Y); //regole normali, sfumatura
        windowBoard.getUsedMatrix().get(0).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(1).setUsed(false);

        windowBoard.insertDice(1,5,dice4Y); //regole normali, colore
        windowBoard.getUsedMatrix().get(0).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(4).setUsed(false);

        //-------------------------------------TURNI SUCCESSIVI AL PRIMO--------------------------------------//

        windowBoard.insertDice(3,1,dice4Y);//ajacency breaker, adiacenza
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        corkBackedStraightedge.applyEffect(windowBoard,dice4Y,4,5,1);
        windowBoard.getUsedMatrix().get(3).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(4).setUsed(false);
        windowBoard.getUsedMatrix().get(2).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(2).get(0).setUsed(false);

        windowBoard.insertDice(2,1,dice4Y);//color breaker
        windowBoard.insertDice(1,2,dice6Y);
        EglomiseBrush eglomiseBrush3 = new EglomiseBrush();
        eglomiseBrush3.applyEffect(windowBoard,dice4Y,2,1,1,1,1);
        windowBoard.getUsedMatrix().get(0).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(0).setUsed(false);
        windowBoard.getUsedMatrix().get(0).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(1).setUsed(false);

        windowBoard.insertDice(4,2,dice5);//number breaker
        windowBoard.insertDice(3,3,dice5r);
        CopperFoilBurnisher copperFoilBurnisher3 = new CopperFoilBurnisher();
        copperFoilBurnisher3.applyEffect(windowBoard,dice5r,3,3,4,3,1);
        windowBoard.getUsedMatrix().get(3).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(1).setUsed(false);
        windowBoard.getUsedMatrix().get(3).get(2).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(2).setUsed(false);

        windowBoard.insertDice(4,1,dice5r);//regole normali cella bianca
        windowBoard.insertDice(4,2,dice4Y);
        windowBoard.getUsedMatrix().get(3).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(0).setUsed(false);
        windowBoard.getUsedMatrix().get(3).get(1).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(1).setUsed(false);

        windowBoard.insertDice(4,1,dice4Y);//adiacency breaker color & sfumatura
        CorkBackedStraightedge corkBackedStraightedge2 = new CorkBackedStraightedge();
        corkBackedStraightedge2.applyEffect(windowBoard,dice4Y,1,5,1);
        windowBoard.getUsedMatrix().get(0).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(4).setUsed(false);
        corkBackedStraightedge2.applyEffect(windowBoard,dice2,2,5,2);
        windowBoard.getUsedMatrix().get(3).get(0).setDiceContained(null);
        windowBoard.getUsedMatrix().get(3).get(0).setUsed(false);
        windowBoard.getUsedMatrix().get(1).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(4).setUsed(false);

        windowBoard.insertDice(2,5,dice2);//regole normali cella non bianca
        windowBoard.insertDice(1,5,dice4Y);
        windowBoard.getUsedMatrix().get(1).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(1).get(4).setUsed(false);
        windowBoard.getUsedMatrix().get(0).get(4).setDiceContained(null);
        windowBoard.getUsedMatrix().get(0).get(4).setUsed(false);
    }

    //Copertura dei test fatta fino a qui

    @Test(expected = PlaceDiceException.class)
    public void ExceptionColorBreakerShade() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        EglomiseBrush eglomiseBrush2 = new EglomiseBrush();//color breaker sfumatura
        windowBoard.insertDice(3,1,dice4Y);
        eglomiseBrush2.applyEffect(windowBoard,dice4Y,3,1,3,2,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionNumberBreakerColor() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        CopperFoilBurnisher copperFoilBurnisher4 = new CopperFoilBurnisher();//number breaker colore
        windowBoard.insertDice(1,2,dice4Y);
        copperFoilBurnisher4.applyEffect(windowBoard,dice4Y,1,2,2,4,1);
    }

    //-------------------------------------TURNI SUCCESSIVI AL PRIMO--------------------------------------//

    @Test(expected = PlaceDiceException.class)
    public void ExceptionAdjacencyBreakerAdjacencyWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice4Y);//ajacency breaker, adiacenza
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        corkBackedStraightedge.applyEffect(windowBoard,dice4Y,4,1,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionColorBreakerWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(2,1,dice4Y);//color breaker
        windowBoard.insertDice(1,2,dice4Y);
        EglomiseBrush eglomiseBrush3 = new EglomiseBrush();
        eglomiseBrush3.applyEffect(windowBoard,dice4Y,1,2,3,1,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionNumberBreakerWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(4,2,dice5r);//number breaker
        windowBoard.insertDice(3,3,dice5r);
        CopperFoilBurnisher copperFoilBurnisher3 = new CopperFoilBurnisher();
        copperFoilBurnisher3.applyEffect(windowBoard,dice5r,3,3,4,1,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionNormalRulesWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(4,1,dice5r);//regole normali cella bianca
        windowBoard.insertDice(4,2,dice4r);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionNumberBreakerNotWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(4,2,dice1);//number breaker
        windowBoard.insertDice(3,3,dice1);
        CopperFoilBurnisher copperFoilBurnisher3 = new CopperFoilBurnisher();
        copperFoilBurnisher3.applyEffect(windowBoard,dice1,3,3,3,2,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionColorBreakerNotWhiteCell() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(2,1,dice4Y);//color breaker
        windowBoard.insertDice(1,2,dice4Y);
        EglomiseBrush eglomiseBrush3 = new EglomiseBrush();
        eglomiseBrush3.applyEffect(windowBoard,dice4Y,1,2,2,2,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionAdjacencyBreakerAdjacencyNotWhiteCellColor() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice4Y);//ajacency breaker, adiacenza
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        corkBackedStraightedge.applyEffect(windowBoard,dice4Y,2,4,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionAdjacencyBreakerAdjacencyNotWhiteCellNumber() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice4Y);//ajacency breaker
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        corkBackedStraightedge.applyEffect(windowBoard,dice6Y,3,4,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void ExceptionAdjacencyBreakerAdjacencyNotWhiteCellNotNonAdjacent() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice4Y);//ajacency breaker
        CorkBackedStraightedge corkBackedStraightedge = new CorkBackedStraightedge();
        corkBackedStraightedge.applyEffect(windowBoard,dice6Y,3,2,1);
    }

    @Test(expected = PlaceDiceException.class)
    public void NormalMoveNotWhiteShade() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(4,1,dice5r);//regole normali cella bianca
        windowBoard.insertDice(3,2,dice4Y);
    }

    @Test(expected = PlaceDiceException.class)
    public void NormalMoveNotWhiteColor() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice5r);//regole normali cella bianca
        windowBoard.insertDice(2,2,dice4Y);
    }

    @Test(expected = PlaceDiceException.class)
    public void CellOccupied() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,1,dice5r);//regole normali cella bianca
        windowBoard.insertDice(3,1,dice4Y);
    }

    @Test(expected = PlaceDiceException.class)
    public void NotOnBorder() throws PlaceDiceException {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,12);
        windowBoard.importNameFromFile(12);
        System.out.println(windowBoard.getTitle());
        windowBoard.printMatrix(testMatrix,rows,columns);
        windowBoard.setUsedMatrix(windowBoard.fromIntToArrayList(testMatrix, rows, columns));
        windowBoard.setBorders();

        windowBoard.insertDice(3,2,dice5r);//regole normali cella bianca

    }
}
