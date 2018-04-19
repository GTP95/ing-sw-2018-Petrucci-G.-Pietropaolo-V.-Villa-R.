package Progetto_Ing_Sw.com.server;

import org.junit.*;
import org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class DeckTest {
private Card card1, card2, card3, card4;
private ArrayList<Card> cardArrayList;
private Deck deckTest;
    @Before
    public void before(){
      /*  card1=mock(Card.class);
        card2=mock(Card.class);
        card3=mock(Card.class);
        card4=mock(Card.class);
        cardArrayList=new ArrayList<>();
        cardArrayList.add(card1);
        cardArrayList.add(card2);
        cardArrayList.add(card3);
        cardArrayList.add(card4);*/
        deckTest=mock(Deck.class);

    }

@Test
   public Card drawTest(){
        deckTest.draw();
        return card2;
     }
}
