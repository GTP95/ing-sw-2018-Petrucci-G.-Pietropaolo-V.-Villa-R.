package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.Card;
import Progetto_Ing_Sw.com.server.Model.ToolCard;
import Progetto_Ing_Sw.com.server.Model.ToolCardDeck;
import org.junit.*;

import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToolCardDeckTest {
    private ArrayList<ToolCard> cardArrayList;
    private ToolCardDeck toolCardDeckTest;
    private ToolCard card1, card2, card3, card4;

    @Before
    public void before() {
        card1 = mock(ToolCard.class);
        card2 = mock(ToolCard.class);
        card3 = mock(ToolCard.class);
        card4 = mock(ToolCard.class);

        when(card1.getTitle()).thenReturn("GrozingPliers");
        when(card1.getID()).thenReturn(1);
        when(card1.getDescription()).thenReturn("After drafting, increase or decrease the value of teh drafted die by 1");
        when(card1.getInfo()).thenReturn("1 may not change to 6, or 6 to 1");


        when(card2.getTitle()).thenReturn("EglomiseBrush");
        when(card2.getID()).thenReturn(2);
        when(card2.getDescription()).thenReturn("Move any one die in your window ignoring color restrictions");
        when(card2.getInfo()).thenReturn("You must obey all other placement restrictions");

        when(card3.getTitle()).thenReturn("CopperFoilBurnisher");
        when(card3.getID()).thenReturn(3);
        when(card3.getDescription()).thenReturn("Move any one die in your window ignoring value restrictions");
        when(card3.getInfo()).thenReturn("You must obey all other placement restrictions");

        when(card4.getTitle()).thenReturn("Lathekin");
        when(card4.getID()).thenReturn(4);
        when(card4.getDescription()).thenReturn("Move exactly two dice, obeying all placement restrictions");

        cardArrayList = new ArrayList<>();
        cardArrayList.add(0, card1);
        cardArrayList.add(1, card2);
        cardArrayList.add(2, card3);
        cardArrayList.add(3, card4);

        toolCardDeckTest = new ToolCardDeck(cardArrayList);
    }


    @Test
    public void drawAllTest() {
        ToolCard card;
        System.out.println("drawAllTest:");
        for (int counter = 0; counter < 4; counter++) {
            card = toolCardDeckTest.draw();
            System.out.println(card.getTitle());
        }
        Assert.assertEquals(0, cardArrayList.size());
        System.out.println();
    }

    @Test
    public void draw3Test() {
        ToolCard card;
        System.out.println("draw3Test:");
        for (int counter = 0; counter < 3; counter++) {
            card = toolCardDeckTest.draw();
            System.out.println("Title:" + card.getTitle());
            System.out.println("ID:" + card.getID());
            System.out.println("Description:" + card.getDescription());
            if (card.getInfo() != null) {
                System.out.println("Additional Info:" + card.getInfo());
            }
            System.out.println("________________________________________________________");

        }
        Assert.assertEquals(1, cardArrayList.size());
        System.out.println();
    }

    @Test
    public void multipleDrawTest(){
        System.out.println("multipleDrawTest");
        ArrayList<Card> arrayList;
        arrayList=toolCardDeckTest.draw(3);
        Assert.assertEquals(3,arrayList.size());
    }


}
