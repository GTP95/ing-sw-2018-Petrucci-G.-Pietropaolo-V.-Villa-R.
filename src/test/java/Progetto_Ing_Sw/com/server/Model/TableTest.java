package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableTest {    //Questa classe, data la sua natura, fa integration testing anzichè unit testing

    private ArrayList<PublicObjectiveCard> publicObjectiveCardArrayList;
    private ArrayList<ToolCard> toolCardArrayList;

    private PublicObjectiveCardDeck publicObjectiveCardDeckTest;
    private ToolCardDeck toolCardDeckTest;

    private Table gameTable;
    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;

    private PublicObjectiveCard cardP1, cardP2, cardP3, cardP4;
    private ToolCard cardT1, cardT2, cardT3, cardT4;
    private DiceBag diceBag;

    @Before
    public void before(){
        /*Mock del tavolo da gioco*/
        gameTable=Table.getOurInstance();

        /*Costruzione dei mazzi che vengono pescati*/

    }

    @Test
    public void verifyThatTheDrawPhaseHasTheCorrectTypeAndNumberOfCards(){

        ArrayList<PublicObjectiveCard> testPublicObjectiveArrayList;
        ArrayList<ToolCard> testToolCardArrayList;

        /*Creo la mia mano di gioco, pescando 6 carte, 3 per tipo*/
        testPublicObjectiveArrayList=gameTable.getDrawnPublicObjectiveCards();
        testToolCardArrayList=gameTable.getDrawnToolCards();

        /*Verifico di avere effettivamente pescato 3 carte per tipo*/
        Assert.assertEquals("Incorrect number of PublicObjectiveCards!",3, testPublicObjectiveArrayList.size());
        Assert.assertEquals("Incorrect number of ToolCards!",3, testToolCardArrayList.size());

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
