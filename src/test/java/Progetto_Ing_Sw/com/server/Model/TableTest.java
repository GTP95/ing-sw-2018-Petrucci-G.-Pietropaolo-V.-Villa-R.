package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SplittableRandom;

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

    @Test
    public void returnDiceTest(){       //Controlla che togliendo e reinserendo lo stesso dado l'arrayList DrawnDice non cambi
        ArrayList<Dice> diceArray=gameTable.getDrawnDice();
        SplittableRandom splittableRandom=new SplittableRandom();

        try {
            Dice dice=gameTable.getChoosenDice(diceArray.get(splittableRandom.nextInt(0,diceArray.size())));    //Pesca un dado a caso nell'arraylist
            gameTable.returnDice(dice);     //reinserisce il dado pescato nell'arrayList
        } catch (IllegalDiceException e) {
            Assert.fail(e.getMessage());
        }
        ArrayList<Dice> diceArrayListAfterDraftingAndReinserting=gameTable.getDrawnDice();
        for(Dice dice : diceArray) {   //Controllo che i due ArrayList siano uguali eliminando i dadi di diceArrayListAfterDraftingAndReinserting che hanno una corrispondenza in diceArray, se alla fine la dimansione di diceArrayListAfterDraftingAndReinserting è 0 allora i due arrayList contenevano esattamente gli stessi dadi
            for(Dice dice2 : diceArrayListAfterDraftingAndReinserting){
                if(dice.equals(dice2)){
                    diceArrayListAfterDraftingAndReinserting.remove(dice2);
                    break;      //dopo aver rimosso il dado deve uscire dal ciclo più interno per passare al dado successivo, altrimenti se sono presenti più dadi uguali verrebbero subito rimossi tutti alla prima occorrenza e risolve anche la ConcurrentModificationException
                }
            }
        }
        if(!diceArrayListAfterDraftingAndReinserting.isEmpty()) Assert.fail("Size is "+diceArrayListAfterDraftingAndReinserting.size());
    }

    }
