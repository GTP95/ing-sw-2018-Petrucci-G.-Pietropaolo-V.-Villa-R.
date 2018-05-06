package Progetto_Ing_Sw.com.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableTest {

    private ArrayList<PublicObjectiveCard> publicObjectiveCardArrayList;
    private ArrayList<ToolCard> toolCardArrayList;

    private PublicObjectiveCardDeck publicObjectiveCardDeckTest;
    private ToolCardDeck toolCardDeckTest;

    private Table gameTable;
    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;

    private PublicObjectiveCard cardP1, cardP2, cardP3, cardP4;
    private ToolCard cardT1, cardT2, cardT3, cardT4;

    @Before
    public void before(){

        /*Mock delle carte dei due mazzi*/
        cardP1=mock(PublicObjectiveCard.class);
        cardP2=mock(PublicObjectiveCard.class);
        cardP3=mock(PublicObjectiveCard.class);
        cardP4=mock(PublicObjectiveCard.class);
        cardT1 = mock(ToolCard.class);
        cardT2 = mock(ToolCard.class);
        cardT3 = mock(ToolCard.class);
        cardT4 = mock(ToolCard.class);

        when(cardP1.getTitle()).thenReturn("RowColorVariety");
        when(cardP2.getTitle()).thenReturn("ColumnColorVariety");
        when(cardP3.getTitle()).thenReturn("RowShadeVariety");
        when(cardP4.getTitle()).thenReturn("ColorVariety");

        when(cardT1.getTitle()).thenReturn("GrozingPliers");
        when(cardT1.getID()).thenReturn(1);
        when(cardT1.getDescription()).thenReturn("After drafting, increase or decrease the value of teh drafted die by 1");
        when(cardT1.getInfo()).thenReturn("1 may not change to 6, or 6 to 1");

        when(cardT2.getTitle()).thenReturn("EglomiseBrush");
        when(cardT2.getID()).thenReturn(2);
        when(cardT2.getDescription()).thenReturn("Move any one die in your window ignoring color restrictions");
        when(cardT2.getInfo()).thenReturn("You must obey all other placement restrictions");

        when(cardT3.getTitle()).thenReturn("CopperFoilBurnisher");
        when(cardT3.getID()).thenReturn(3);
        when(cardT3.getDescription()).thenReturn("Move any one die in your window ignoring value restrictions");
        when(cardT3.getInfo()).thenReturn("You must obey all other placement restrictions");

        when(cardT4.getTitle()).thenReturn("Lathekin");
        when(cardT4.getID()).thenReturn(4);
        when(cardT4.getDescription()).thenReturn("Move exactly two dice, obeying all placement restrictions");

        /*Costruzione dei due mazzi da usare*/
        publicObjectiveCardArrayList=new ArrayList<>();
        publicObjectiveCardArrayList.add(0,cardP1);
        publicObjectiveCardArrayList.add(1,cardP2);
        publicObjectiveCardArrayList.add(2,cardP3);
        publicObjectiveCardArrayList.add(3,cardP4);
        publicObjectiveCardDeckTest=new PublicObjectiveCardDeck(publicObjectiveCardArrayList);

        toolCardArrayList=new ArrayList<>();
        toolCardArrayList.add(0,cardT1);
        toolCardArrayList.add(1,cardT2);
        toolCardArrayList.add(2,cardT3);
        toolCardArrayList.add(3,cardT4);
        toolCardDeckTest=new ToolCardDeck(toolCardArrayList);

        /*Mock del tavolo da gioco*/
        gameTable=mock(Table.class);
        when(gameTable.getToolCardDeck()).thenReturn(toolCardDeckTest);
        when(gameTable.getPublicObjectiveCardDeck()).thenReturn(publicObjectiveCardDeckTest);

        /*Costruzione dei mazzi che vengono pescati*/
        drawnPublicObjectiveCards= new ArrayList<>();
        drawnPublicObjectiveCards.add(0,publicObjectiveCardDeckTest.draw());
        drawnPublicObjectiveCards.add(1,publicObjectiveCardDeckTest.draw());
        drawnPublicObjectiveCards.add(2,publicObjectiveCardDeckTest.draw());

        drawnToolCards= new ArrayList<>();
        drawnToolCards.add(0,toolCardDeckTest.draw());
        drawnToolCards.add(1,toolCardDeckTest.draw());
        drawnToolCards.add(2,toolCardDeckTest.draw());

        when(gameTable.getDrawnPublicObjectiveCards()).thenReturn(drawnPublicObjectiveCards);
        when(gameTable.getDrawnToolCards()).thenReturn(drawnToolCards);

    }

    @Test
    public void verifyThatTheDrawPhaseHasTheCorrectTypeAndNumberOfCards(){

        ArrayList<PublicObjectiveCard> testPublicObjectiveArrayList;
        ArrayList<ToolCard> testToolCardArrayList;

        /*Creo la mia mano di gioco, pescando 6 carte, 3 per tipo*/
        testPublicObjectiveArrayList=gameTable.getDrawnPublicObjectiveCards();
        testToolCardArrayList=gameTable.getDrawnToolCards();

        /*Verifico di avere effettivamente pescato 3 carte per tipo*/
        Assert.assertEquals(3, testPublicObjectiveArrayList.size());
        Assert.assertEquals(3, testToolCardArrayList.size());

        System.out.println("Le PublicObjectiveCards della partita sono: ");
        System.out.println("********************************************");
        for (int counter=0;counter<testPublicObjectiveArrayList.size();counter++) {
            System.out.println(counter + 1 + ") [" + testPublicObjectiveArrayList.get(counter).getTitle()+"]");
            Assert.assertTrue(testPublicObjectiveArrayList.get(counter) instanceof PublicObjectiveCard );
            /*Verifico che la carta che pesco sia effettivamente del tipo che mi serve*/
        }

        System.out.println();

        System.out.println("Le ToolCards della partita sono: ");
        System.out.println("********************************************");
        for (int counter=0;counter<testToolCardArrayList.size();counter++) {
            System.out.println(counter + 1 + ") [" + testToolCardArrayList.get(counter).getTitle()+"]");
            System.out.println(">Descrizione:    " + testToolCardArrayList.get(counter).getDescription());
            System.out.println(">Informazioni:   " + testToolCardArrayList.get(counter).getInfo());
            Assert.assertTrue(testToolCardArrayList.get(counter) instanceof ToolCard );
            /*Verifico che la carta che pesco sia effettivamente del tipo che mi serve*/
        }
        System.out.println();
    }
}
