package Progetto_Ing_Sw.com.server;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class WindowBoardTest {
    private WindowBoard windowBoard;
    private final int raws=4;       //colonne e righe rimangono costanti
    private final int columns=5;
    private int dice;
    private int cell;

    @Before
    public void before(){
        windowBoard=mock(WindowBoard.class);
    }
    @Test
    public void simplePrint() {
        WindowBoard windowBoard1 = new WindowBoard(raws, columns); //costruisce la matrice con le dimensioni date da me
        WindowBoard windowBoard2 = new WindowBoard(raws, columns); //costruisce la matrice con le dimensioni date da me
        int[][] testMatrix1 = windowBoard1.buildEmptyMatrix(raws, columns);//crea una oggetto matrice di 0
        int[][] testMatrix2 = windowBoard2.buildEmptyMatrix(raws, columns);//crea una oggetto matrice di 0

        //NB l'inserimento avviene correttamente su due matrici separate
        // solo se le due matrici sono su due oggetti windowBoard differenti

        windowBoard1.insertDice(testMatrix1,raws,columns,23,5);
        windowBoard1.printMatrix(testMatrix1,raws,columns);
        windowBoard2.insertDice(testMatrix2,raws,columns,45,5);
        windowBoard2.printMatrix(testMatrix2,raws,columns);
    }

}
