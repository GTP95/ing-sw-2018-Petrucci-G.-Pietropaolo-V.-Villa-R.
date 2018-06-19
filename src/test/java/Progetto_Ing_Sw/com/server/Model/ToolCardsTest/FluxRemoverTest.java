package Progetto_Ing_Sw.com.server.Model.ToolCardsTest;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.server.Model.ToolCards.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FluxRemoverTest {

    private WindowBoard windowBoard;
    private int rows=4;
    private int columns=5;
    private DiceBag diceBag;
    private FluxRemover fluxRemover;
    private Dice dice1;
    private Dice dice2;
    private Dice dice3;
    private Dice dice4;
    private Dice dice5;


    @Before
    public void before() {

        windowBoard = mock(WindowBoard.class);
        fluxRemover=mock(FluxRemover.class);
        diceBag=mock(DiceBag.class);

        dice1=mock(Dice.class);
        dice2=mock(Dice.class);
        dice3=mock(Dice.class);
        dice4=mock(Dice.class);
        dice5=mock(Dice.class);


        when(dice1.getColor()).thenReturn(Color.RED);
        when(dice1.getValue()).thenReturn(1);

        when(dice2.getColor()).thenReturn(Color.BLUE);
        when(dice2.getValue()).thenReturn(2);

        when(dice3.getColor()).thenReturn(Color.PURPLE);
        when(dice3.getValue()).thenReturn(3);

        when(dice4.getColor()).thenReturn(Color.YELLOW);
        when(dice4.getValue()).thenReturn(4);

        when(dice5.getColor()).thenReturn(Color.GREEN);
        when(dice5.getValue()).thenReturn(5);
    }

    @Test
    public void applyEffect() throws PlaceDiceException {

        WindowBoard boardPlayerOne = new WindowBoard(rows, columns);
        FluxRemover fluxRemover = new FluxRemover();
        DiceBag diceBag = new DiceBag();

        int[][] testMatrix = boardPlayerOne.importFromFile(rows, columns,24);
        System.out.println("INDUSTRIA CHOOSEN");
        boardPlayerOne.printMatrix(testMatrix,rows,columns);
        boardPlayerOne.setUsedMatrix(boardPlayerOne.fromIntToArrayList(testMatrix,rows,columns));
        boardPlayerOne.setBorders();
        boardPlayerOne.printMatrixArrayList();
        System.out.println();

        System.out.println("<BEFORE>");
        System.out.println();
        ArrayList<Dice> diceDrawn =diceBag.diceDraw(6);
        for (int i=0; i<diceDrawn.size();i++){
            System.out.println("DICE DRAWN #"+(i+1)+"  ==> value :"+diceDrawn.get(i).getColor());
            System.out.println("DICE DRAWN #"+(i+1)+" ==> color :"+diceDrawn.get(i).getColor());
        }
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("RED DICE ["+diceBag.getNumDiceRed()+"]");
        System.out.println("BLUE DICE ["+diceBag.getNumDiceBlue()+"]");
        System.out.println("PURPLE DICE ["+diceBag.getNumDicePurple()+"]");
        System.out.println("YELLOW DICE ["+diceBag.getNumDiceYellow()+"]");
        System.out.println("GREEN DICE ["+diceBag.getNumDiceGreen()+"]");
        System.out.println("***************************************************");
        System.out.println();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        fluxRemover.applyEffect(boardPlayerOne,dice1,diceBag,3,5,1,1);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println("<AFTER>");
        System.out.println();
        for (int i=0; i<diceDrawn.size();i++){
            System.out.println("DICE DRAWN #"+(i+1)+"  ==> value :"+diceDrawn.get(i).getColor());
            System.out.println("DICE DRAWN #"+(i+1)+" ==> color :"+diceDrawn.get(i).getColor());
        }
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("RED DICE ["+diceBag.getNumDiceRed()+"]");
        System.out.println("BLUE DICE ["+diceBag.getNumDiceBlue()+"]");
        System.out.println("PURPLE DICE ["+diceBag.getNumDicePurple()+"]");
        System.out.println("YELLOW DICE ["+diceBag.getNumDiceYellow()+"]");
        System.out.println("GREEN DICE ["+diceBag.getNumDiceGreen()+"]");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

        boardPlayerOne.printMatrixArrayList();


    }
}
