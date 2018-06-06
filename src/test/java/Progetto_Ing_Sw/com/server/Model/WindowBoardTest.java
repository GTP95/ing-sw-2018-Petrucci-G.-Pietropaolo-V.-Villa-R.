package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.client.TableGUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private DiceBag diceBag;
    private Dice dice3;
    private Dice dice2;
    private Dice dice1;
    private Dice dice4;
    private Dice dice5;

    private final int rows = 4;       //colonne e righe rimangono costanti
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        diceBag=mock(DiceBag.class);

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

    @Test//Test utile solo al controllo delle variabili, da eliminare
    public void simplePrint() {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        windowBoard.printMatrix(testMatrix, rows, columns);
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
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        Assert.assertFalse(windowBoard.matrixNotEmpty(martrixArray));
    }

    @Test
    public void setAndCheckBorders() {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 22);
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);


        windowBoard.setBorders(martrixArray); //set de bordi

        //controllo dei bordi
        for(int r=0;r<martrixArray.size();r++){
            for (int c=0;c<martrixArray.get(r).size();c++){

                if(c==0){
                    Assert.assertEquals(martrixArray.get(r).get(c).isOnBorder(),true);
                }
                if(c==martrixArray.get(r).size()-1){
                    Assert.assertEquals(martrixArray.get(r).get(c).isOnBorder(),true);
                }
                if(r==0){
                    Assert.assertEquals(martrixArray.get(r).get(c).isOnBorder(),true);
                }
                if(r==martrixArray.size()-1){
                    Assert.assertEquals(martrixArray.get(r).get(c).isOnBorder(),true);
                }
                if((r!=0)&&(c!=0)&&(c!=martrixArray.get(r).size()-1)&&(r!=martrixArray.size()-1)){
                    Assert.assertNotEquals(martrixArray.get(r).get(c).isOnBorder(),true);
                }
            }
        }
        windowBoard.areOnBorders(martrixArray); //visual test method
    }

    //TEST COMPLETI FINO A QUI
    @Test
    public void checkShadeOfAMatrixCell() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,24);
        System.out.println("Matrice prima dell'inserimento");

        windowBoard.printMatrix(testMatrix,rows,columns);
        System.out.println("DICE COLOR ="+dice3.getColor());
        System.out.println("DICE VALUE ="+dice3.getValue());

        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        for (int row = 0; row < martrixArray.size(); row++) {
            for (int column = 0; column < martrixArray.get(row).size(); column++) {
                if (windowBoard.checkShade(martrixArray.get(row).get(column), dice3)) {
                    System.out.println("CELL ["+(row+1)+"]["+(column+1)+"] SHADE IS "+martrixArray.get(row).get(column).getShade());
                }
            }
        }
    }

    @Test
    public void checkBlankOfAMatrixCell(){
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,23);
        System.out.println("Matrice prima dell'inserimento");

        windowBoard.printMatrix(testMatrix,rows,columns);
        System.out.println("DICE COLOR ="+dice3.getColor());
        System.out.println("DICE VALUE ="+dice3.getValue());

        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        for (int row = 0; row < martrixArray.size(); row++) {
            for (int column = 0; column < martrixArray.get(row).size(); column++) {
                if(martrixArray.get(row).get(column).getColor()==Color.BLANK){
                    System.out.println("CELL ["+(row+1)+"]["+(column+1)+"] COLOR IS "+martrixArray.get(row).get(column).getColor());
                }
            }
        }
    }

    @Test
    public void insertDiceInArrayListMatrix(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setBorders(martrixArray);

        System.out.println("DICE COLOR ="+dice3.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice3.getValue()); //3

        //testo le caselle valide
        windowBoard.insertDiceARRLIST(martrixArray,1,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,5,dice3);
        System.out.println();

        //testo le sfumature valide
        windowBoard.insertDiceARRLIST(martrixArray,3,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,5,dice3);
        System.out.println();

        //testo i colori validi
        windowBoard.insertDiceARRLIST(martrixArray,4,3,dice3);
        System.out.println();

        //testo i colori NON validi
        windowBoard.insertDiceARRLIST(martrixArray,2,3,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,3,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,3,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,3,4,dice3);
        System.out.println();

        //testo le sfumature non valide
        windowBoard.insertDiceARRLIST(martrixArray,1,3,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,3,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,1,dice3);
        System.out.println();

        //testo le caselle occupate

        windowBoard.insertDiceARRLIST(martrixArray,1,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,3,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,3,dice3);
        System.out.println();
    }

    @Test
    public void checkUsedCellAdjacent(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi - SEMPRE DA METTERE -
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setBorders(martrixArray);
        System.out.println("DICE COLOR ="+dice3.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice3.getValue()); //3
        System.out.println("DICE COLOR ="+dice2.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice2.getValue()); //5

        //Set di inserimenti;
        windowBoard.insertDiceARRLIST(martrixArray,1,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,2,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,1,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,2,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,5,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,4,4,dice2);


        //Parte di controllo adiacenza
        if(windowBoard.checkAdjacency(martrixArray,1,5)){
            System.out.println("Ho trovato un dado adiacente!!!");
        }
    }

    @Test
    public void checkAdjacencyAndOrtogonalColor(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi - SEMPRE DA METTERE -
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setBorders(martrixArray);

        System.out.println("DICE COLOR ="+dice3.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice3.getValue()); //3

        //Set di inserimenti;
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice3);

        windowBoard.checkOrtogonalColor(martrixArray,1,4);

    }

    @Test
    public void checkAdjacencyAndOrtogonalNumber(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,17);
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi - SEMPRE DA METTERE -
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setBorders(martrixArray);

        System.out.println("DICE COLOR ="+dice3.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice3.getValue()); //3
        System.out.println("DICE COLOR ="+dice2.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice2.getValue()); //5

        //Set di inserimenti;
        //DA COMPLETARE
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice3);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice1);

        windowBoard.checkOrtogonalValue(martrixArray,1,4);
    }

    @Test
    public void TEST_INSERT_DICE_GAMEPLAY(){

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,24);//Industria
        System.out.println("Matrice prima dell'inserimento");
        windowBoard.printMatrix(testMatrix,rows,columns);

        //parte dell'algoritmo per settare i bordi
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToArrayList(testMatrix, rows, columns);
        windowBoard.setBorders(martrixArray);

        // dice3 = rosso,3
        // dice2 = viola,2
        // dice1 = blu, 1
        // dice4 = giallo, 4
        // dice5 = verde, 5

        //Test da effettuare - windowBoard.insertDice(martrixArray,#,#,dice#);

        windowBoard.insertDice(martrixArray,1,4,dice1);
        System.out.println("COLOR "+martrixArray.get(0).get(3).getColor());
        System.out.println("DADO COLORE "+dice2.getColor());
        System.out.println("DADO VALORE "+dice2.getValue());
        windowBoard.insertDice(martrixArray,2,4,dice2);
        windowBoard.insertDice(martrixArray,2,5,dice3);
        windowBoard.insertDice(martrixArray,2,1,dice5);
        System.out.println(martrixArray.get(1).get(0).getDiceContained());//null


    }
}
