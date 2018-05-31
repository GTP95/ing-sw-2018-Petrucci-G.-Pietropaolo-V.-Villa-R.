package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.MatrixCell;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private final int rows = 4;       //colonne e righe rimangono costanti
    private final int columns = 5;

    @Before
    public void before() {
        windowBoard = mock(WindowBoard.class);
        MatrixCell matrixCell = mock(MatrixCell.class);
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

}
