package Progetto_Ing_Sw.com.server;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private final int rows=4;       //colonne e righe rimangono costanti
    private final int columns=5;
    private int dice;
    private int cell;
    private String field;
    private String path;
    int cardCode;

    @Before
    public void before(){
        windowBoard=mock(WindowBoard.class);
    }
    @Test
    public void simplePrint() {

        //TODO sistemare il concetto della doppia matrice sulla stessa WindowBoard

        WindowBoard windowBoard1 = new WindowBoard(rows, columns);
        int[][] testMatrix = windowBoard1.importFromFile(rows, columns,1);
        windowBoard1.printMatrix(testMatrix,rows,columns);

        WindowBoard windowBoard2 = new WindowBoard(rows, columns);
        int[][] testMatrix2 = windowBoard1.buildEmptyMatrix(rows, columns);
        windowBoard1.printMatrix(testMatrix,rows,columns);

        //NB l'inserimento avviene correttamente su due matrici separate
        // solo se le due matrici sono su due oggetti windowBoard2 differenti


    }

}
