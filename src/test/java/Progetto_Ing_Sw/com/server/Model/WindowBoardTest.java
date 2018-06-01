package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.MatrixCell;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private DiceBag diceBag;
    private Dice dice;
    private final int rows = 4;       //colonne e righe rimangono costanti
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        diceBag=mock(DiceBag.class);
        dice=mock(Dice.class);

        when(dice.getColor()).thenReturn(Color.RED);
        when(dice.getValue()).thenReturn(3);
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
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
        System.out.println("ARRAYLIST-MATRIX ROWS: " + martrixArray.size());
        Assert.assertEquals(rows, martrixArray.size());
    }

    @Test
    public void correctNumberOfColumnsAfterHJsonImport() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        System.out.println("INT-MATRIX NUM OF COLUMN FOR EACH ROW: " + columns);

        //Verifico che la matrice creata fa file abbia il numero di righe corrette
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
        for (int r = 0; r < rows; r++) {
            System.out.println("ARRAYLIST-MATRIX ROW (" + (r + 1) + ") NUM OF COLUMN : " + martrixArray.get(r).size());
            Assert.assertEquals(columns, martrixArray.get(r).size());
        }
    }

    @Test
    public void checkThatTheMatrixIsEmpty() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 1);
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
        Assert.assertFalse(windowBoard.matrixNotEmpty(martrixArray));
    }

    @Test
    public void setAndCheckBorders() {
        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns, 22);
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);

        for (int row = 0; row < martrixArray.size(); row++) {
            for (int column = 0; column < martrixArray.get(row).size(); column++){
                windowBoard.setBorders(martrixArray, row, column);
            }
        }
        windowBoard.areOnBorders(martrixArray);
    }

    @Test
    public void checkShadeOfAMatrixCell() {

        WindowBoard windowBoard = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard.importFromFile(rows, columns,23);
        System.out.println("Matrice prima dell'inserimento");

        windowBoard.printMatrix(testMatrix,rows,columns);
        System.out.println("DICE COLOR ="+dice.getColor());
        System.out.println("DICE VALUE ="+dice.getValue());

        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
        for (int row = 0; row < martrixArray.size(); row++) {
            for (int column = 0; column < martrixArray.get(row).size(); column++) {
                if (windowBoard.checkShade(martrixArray.get(row).get(column), dice) == true) {
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
        System.out.println("DICE COLOR ="+dice.getColor());
        System.out.println("DICE VALUE ="+dice.getValue());

        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
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
        ArrayList<ArrayList<MatrixCell>> martrixArray = windowBoard.fromIntToDice(testMatrix, rows, columns);
        for (int row = 0; row < martrixArray.size(); row++) {
            for (int column = 0; column < martrixArray.get(row).size(); column++){
                windowBoard.setBorders(martrixArray, row, column);
            }
        }
        System.out.println("DICE COLOR ="+dice.getColor()); //rosso
        System.out.println("DICE VALUE ="+dice.getValue()); //3

        //Set di inserimenti;
        windowBoard.insertDiceARRLIST(martrixArray,4,3,dice);
        windowBoard.insertDiceARRLIST(martrixArray,2,2,dice);
        windowBoard.insertDiceARRLIST(martrixArray,1,4,dice);
        windowBoard.insertDiceARRLIST(martrixArray,1,5,dice);
        windowBoard.insertDiceARRLIST(martrixArray,3,5,dice);
        windowBoard.insertDiceARRLIST(martrixArray,3,1,dice);
        windowBoard.insertDiceARRLIST(martrixArray,4,3,dice);
        windowBoard.insertDiceARRLIST(martrixArray,3,3,dice);





    }

}
