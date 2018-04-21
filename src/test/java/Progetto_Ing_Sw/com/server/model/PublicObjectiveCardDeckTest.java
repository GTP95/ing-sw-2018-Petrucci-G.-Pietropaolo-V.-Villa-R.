package Progetto_Ing_Sw.com.server.model;

import Progetto_Ing_Sw.com.server.model.PublicObjectiveCard;
import Progetto_Ing_Sw.com.server.model.PublicObjectiveCardDeck;
import org.junit.*;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicObjectiveCardDeckTest {
private ArrayList<PublicObjectiveCard> cardArrayList;
private PublicObjectiveCardDeck publicObjectiveCardDeckTest;
private PublicObjectiveCard card1, card2, card3, card4;
    @Before
    public void before(){
        card1=mock(PublicObjectiveCard.class);
        card2=mock(PublicObjectiveCard.class);
        card3=mock(PublicObjectiveCard.class);
        card4=mock(PublicObjectiveCard.class);

        when(card1.getTitle()).thenReturn("RowColorVariety");
        when(card2.getTitle()).thenReturn("ColumnColorVariety");
        when(card3.getTitle()).thenReturn("RowShadeVariety");
        when(card4.getTitle()).thenReturn("ColorVariety");

        cardArrayList=new ArrayList<>();
        cardArrayList.add(0,card1);
        cardArrayList.add(1,card2);
        cardArrayList.add(2,card3);
        cardArrayList.add(3,card4);

        publicObjectiveCardDeckTest=new PublicObjectiveCardDeck(cardArrayList);

    }

@Test
   public void drawAllTest(){
            PublicObjectiveCard card;
            System.out.println("drawAllTest:");
        for(int counter=0;counter<4;counter++){
            card=publicObjectiveCardDeckTest.draw();
            System.out.println(card.getTitle());
        }
        Assert.assertEquals(0,cardArrayList.size());
        System.out.println();


     }
@Test
    public void draw3Test(){
        PublicObjectiveCard card;
        System.out.println("draw3Test:");
        for(int counter=0;counter<3;counter++){
            card=publicObjectiveCardDeckTest.draw();
            System.out.println(card.getTitle());
        }
        Assert.assertEquals(1,cardArrayList.size());
        System.out.println();
    }
}
