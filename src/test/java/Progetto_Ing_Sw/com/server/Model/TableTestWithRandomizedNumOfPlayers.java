
/*
Lo scopo di questa calsse è di testare la funzione reintroduceDice con più dadi. Dato che il numero di dadi pescato dipende dal numero di giocatori è necessario
aggiungere manualmente dei giocatori nella lobby. Dato che questo è necessario farlo solo per questo test, ho creato una classe separata anzichè metterlo nella sezione
@Before dei test di TableTest
*/

package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.Lobby;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.SplittableRandom;
import java.util.concurrent.CopyOnWriteArrayList;

public class TableTestWithRandomizedNumOfPlayers {
    private Lobby lobby;
    private Table gameTable;
    private int numOfPlayersToTest;
    private SplittableRandom splittableRandom;
    private CopyOnWriteArrayList<Player> players;

    @Before
    public void testInit(){
        lobby=Mockito.mock(Lobby.class);
        splittableRandom=new SplittableRandom();
        numOfPlayersToTest=splittableRandom.nextInt(2,5);   //al solito, il 2 è incluso nell'intervallo mentre il 5 è escluso
        players=new CopyOnWriteArrayList<>();
           //aggiungo i giocatori alla lobby in base al numero pseudocasuale generato
           switch(numOfPlayersToTest){
               case 5:
                   Player player1=new Player("John",null);
                   players.add(player1);
               case 4:
                   Player player2=new Player("Smith",null);
                   players.add(player2);
               case 3:
                   Player player3=new Player("Doe", null);
                   players.add(player3);
               case 2:
                   Player player4=new Player("John",null);
                   Player player5=new Player("The Ripper", null);
                   players.add(player4);
                   players.add(player5);

           }

           Mockito.when(lobby.getNumOfPlayers()).thenReturn(players.size());
           Mockito.when(lobby.getConnctedPlayers()).thenReturn(players);
           gameTable=Table.getOurInstance();    //creo oggetto Table dopo aver inserito i giocatori nella lobby, così verrà pescato il numero corretto di dadi

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

    @Test
    public void testForCorrectNumOfDiceDrawnAndGetDrawnDice(){
        ArrayList<Dice> drawnDice;
        drawnDice=gameTable.getDrawnDice();
        Assert.assertEquals(2*gameTable.getPlayers().size()+1,drawnDice.size());
    }

    @Test
    public void changeCurrentPlayerAndGetActivePlayerTest(){
        switch (gameTable.getPlayers().size()){
            case 2:

        }
    }
}
