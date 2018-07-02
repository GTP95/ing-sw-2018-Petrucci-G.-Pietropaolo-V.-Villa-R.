package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SplittableRandom;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class TableTest {    //Questa classe, data la sua natura, fa integration testing anzichè unit testing

    private Table gameTable;


    @Before
    public void before(){
        gameTable=Table.getOurInstance();
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

    @Test
    public void getPlayerFromNameTest(){
        Player player1=mock(Player.class);
        when(player1.getName()).thenReturn("John");

        Player player2=mock(Player.class);
        when(player2.getName()).thenReturn("Smith");

        CopyOnWriteArrayList<Player> players=new CopyOnWriteArrayList<>();
        gameTable.setPlayers(players);

        try {
            Assert.assertEquals(player1, gameTable.getPlayerFromName("John"));
            Assert.assertEquals(player2, gameTable.getPlayerFromName("Smith"));
        }
        catch (InvalidUsernameException e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getActivePlayerTest(){

    }

    }
