package Progetto_Ing_Sw.com.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameBoardCardDeckTest {

    private ArrayList<GameBoardCard> cardArrayList;
    private GameBoardCardDeck gameBoardCardDeckTest;
    private GameBoardCard card1, card2, card3, card4;

    @Before
    public void before(){
        card1=mock(GameBoardCard.class);
        card2=mock(GameBoardCard.class);
        card3=mock(GameBoardCard.class);
        card4=mock(GameBoardCard.class);

        when(card1.getTitle()).thenReturn("KalidoscopicDream");
        when(card2.getTitle()).thenReturn("Firmitas");
        when(card3.getTitle()).thenReturn("LuxMundi");
        when(card4.getTitle()).thenReturn("LuxAstram");

        when(card1.getGameBoardCode()).thenReturn(1);
        when(card2.getGameBoardCode()).thenReturn(2);
        when(card3.getGameBoardCode()).thenReturn(3);
        when(card4.getGameBoardCode()).thenReturn(4);

        when(card1.getDifficulty()).thenReturn(4);
        when(card2.getDifficulty()).thenReturn(5);
        when(card3.getDifficulty()).thenReturn(6);
        when(card4.getDifficulty()).thenReturn(5);

        when(card1.getOtherSide()).thenReturn("Firmitas");
        when(card2.getOtherSide()).thenReturn("KalidoscopicDream");
        when(card3.getOtherSide()).thenReturn("LuxAstras");
        when(card4.getOtherSide()).thenReturn("LuxMundi");

        when(card1.getOtherSideCode()).thenReturn(2);
        when(card2.getOtherSideCode()).thenReturn(1);
        when(card3.getOtherSideCode()).thenReturn(4);
        when(card4.getOtherSideCode()).thenReturn(3);

        cardArrayList=new ArrayList<>();
        cardArrayList.add(0,card1);
        cardArrayList.add(1,card2);
        cardArrayList.add(2,card3);
        cardArrayList.add(3,card4);

        gameBoardCardDeckTest=new GameBoardCardDeck(cardArrayList);

    }

    @Test
    public void drawAllTest(){
        GameBoardCard card;
        System.out.println("drawAllTest:");
        System.out.println("----------------");
        for(int counter=0;counter<4;counter++){
            card=gameBoardCardDeckTest.draw();
            System.out.println(card.getTitle());
        }
        Assert.assertEquals(0,cardArrayList.size());
        System.out.println();


    }
    @Test
    public void draw3Test(){
        GameBoardCard card;
        System.out.println("draw3Test:");
        System.out.println("-----------------");
        for(int counter=0;counter<3;counter++){
            card=gameBoardCardDeckTest.draw();
            System.out.println(card.getTitle());
        }
        Assert.assertEquals(1,cardArrayList.size());
        System.out.println();
    }

    @Test
    public void otherSideTest(){
        GameBoardCard card;
        System.out.println("CorrectOtherSide:");
        System.out.println("----------------");
        for(int counter=0;counter<1;counter++){
            card=gameBoardCardDeckTest.draw();
            System.out.println("Front: "+card.getTitle());
            System.out.println(">Code: "+card.getGameBoardCode());
            System.out.println(">Difficulty: "+card.getDifficulty());
            System.out.println("Back: "+card.getOtherSide());
            System.out.println(">>Code: "+card.getOtherSideCode());
            System.out.println(">>Difficulty: "+card.getDifficulty());
            System.out.println("--------Card "+(counter+1)+" drawn");
        }

        Assert.assertTrue((card1.getGameBoardCode()==1)&&(card1.getOtherSideCode()==2));
        Assert.assertTrue((card2.getGameBoardCode()==2)&&(card2.getOtherSideCode()==1));
        Assert.assertTrue((card3.getGameBoardCode()==3)&&(card3.getOtherSideCode()==4));
        Assert.assertTrue((card4.getGameBoardCode()==4)&&(card4.getOtherSideCode()==3));

        System.out.println("END TEST");
        System.out.println("*******************");
        System.out.println();
    }
}
