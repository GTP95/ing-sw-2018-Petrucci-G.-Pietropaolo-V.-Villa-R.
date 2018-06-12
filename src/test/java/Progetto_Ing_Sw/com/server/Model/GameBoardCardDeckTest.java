package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.GameBoardCard;
import Progetto_Ing_Sw.com.server.Model.GameBoardCardDeck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameBoardCardDeckTest {

    private ArrayList<GameBoardCard> cardArrayList;
    private GameBoardCardDeck gameBoardCardDeckTest;
    private GameBoardCard card1, card2, card3, card4, card5, card6;

    @Before
    public void before(){
        card1=mock(GameBoardCard.class);
        card2=mock(GameBoardCard.class);
        card3=mock(GameBoardCard.class);
        card4=mock(GameBoardCard.class);
        card5=mock(GameBoardCard.class);
        card6=mock(GameBoardCard.class);

        when(card1.getTitle()).thenReturn("KalidoscopicDream");
        when(card2.getTitle()).thenReturn("Firmitas");
        when(card3.getTitle()).thenReturn("FractalDrops");
        when(card4.getTitle()).thenReturn("RippleOfLight");
        when(card5.getTitle()).thenReturn("LuxMundi");
        when(card6.getTitle()).thenReturn("LuxAstram");

        when(card1.getGameBoardCode()).thenReturn(1);
        when(card2.getGameBoardCode()).thenReturn(2);
        when(card3.getGameBoardCode()).thenReturn(3);
        when(card4.getGameBoardCode()).thenReturn(4);
        when(card5.getGameBoardCode()).thenReturn(5);
        when(card6.getGameBoardCode()).thenReturn(6);

        /*when(card1.getDifficulty()).thenReturn(4);
        when(card2.getDifficulty()).thenReturn(5);
        when(card3.getDifficulty()).thenReturn(6);
        when(card4.getDifficulty()).thenReturn(5);*/

        when(card1.getOtherSide()).thenReturn("Firmitas");
        when(card2.getOtherSide()).thenReturn("KalidoscopicDream");
        when(card3.getOtherSide()).thenReturn("RippleOfLight");
        when(card4.getOtherSide()).thenReturn("FractalDrops");
        when(card5.getOtherSide()).thenReturn("LuxAstras");
        when(card6.getOtherSide()).thenReturn("LuxMundi");

        when(card1.getOtherSideCode()).thenReturn(2);
        when(card2.getOtherSideCode()).thenReturn(1);
        when(card3.getOtherSideCode()).thenReturn(4);
        when(card4.getOtherSideCode()).thenReturn(3);
        when(card5.getOtherSideCode()).thenReturn(6);
        when(card6.getOtherSideCode()).thenReturn(5);


        cardArrayList=new ArrayList<>();
        cardArrayList.add(0,card1);
        cardArrayList.add(1,card2);
        cardArrayList.add(2,card3);
        cardArrayList.add(3,card4);
        cardArrayList.add(4,card5);
        cardArrayList.add(5,card6);

        gameBoardCardDeckTest=new GameBoardCardDeck(cardArrayList);

    }

    @Test
    public void drawThreeCard() {
        GameBoardCard card;
        int originalSize;

        originalSize=cardArrayList.size();
        for(int counter=0;counter<3;counter++) {
            card = gameBoardCardDeckTest.draw();
            System.out.println((counter + 1)+" Carta Pescata ==> " + card.getTitle());
            System.out.println("Dimensione array dopo il ciclo "+(counter + 1)+" ==> "+cardArrayList.size());

        }
        Assert.assertFalse(cardArrayList.size()>originalSize);//la dimensione dell'array deve diminuire
    }

    @Test
    public void verifyThatIfIDrawTheFrontTheBackIsNotinTheDeck() {
        GameBoardCard card;
        card=gameBoardCardDeckTest.draw();
        for(int counter=0;counter<cardArrayList.size();counter++){
            Assert.assertTrue(cardArrayList.get(counter).getTitle()!=card.getOtherSide());//verifico che nel mazzo non ci sia il lato opposto della carta pescata
        }
    }

    @Test
    public void verifyThatTheNumbersOfCardsInTheDeckIsAlwaysEven(){
        GameBoardCard card;
        for(int counter=0;counter<cardArrayList.size();counter++){
            card=gameBoardCardDeckTest.draw();
            Assert.assertTrue((cardArrayList.size()%2==0));//verifico che il mazzo si sempre pari dopo ogni turno di pesca
        }
    }
    @Test
    public void drawTheCorrectNumberOfRoundCards(){
        ArrayList roundCards;
        GameBoardCard card;
        roundCards=gameBoardCardDeckTest.draw(3);
        Assert.assertTrue(roundCards.size()==3);//verifico di aver pescato effettivamente 3 carte
    }

    @Test
    public void loadDeckFromJSONTest(){
        GameBoardCardDeck deck=new GameBoardCardDeck(new File("Resources/Cards/GameBoardCards"));
        GameBoardCard card=deck.draw();
        Assert.assertNotNull("È stata caricata una carta \"null\"!",card);
        System.out.println("La carta pescata è:");
        System.out.println(card.getTitle());
        System.out.println(card.getGameBoardCode());
        System.out.println(card.getOtherSide());
        System.out.println(card.getOtherSideCode());

    }
}
