
/*
Lo scopo di questa calsse è di testare la funzione reintroduceDice con più dadi. Dato che il numero di dadi pescato dipende dal numero di giocatori è necessario
aggiungere manualmente dei giocatori nella lobby. Dato che questo è necessario farlo solo per questo test, ho creato una classe separata anzichè metterlo nella sezione
@Before dei test di TableTest
*/

package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.Lobby;
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

           switch(numOfPlayersToTest){      //Imposta il numero di giocatori e li genera in base al numero pseudocasuale generato. SocketClientHandler posto a null perchè non usato nei test
               case 2:
                   Mockito.when(lobby.getNumOfPlayers()).thenReturn(2);
                   players.add(new Player("John",null));
                   players.add(new Player("Doe",null));
                   Mockito.when(lobby.getConnctedPlayers()).thenReturn(players);
                   break;
               case 3:
                   Mockito.when(lobby.getNumOfPlayers()).thenReturn(3);
                   players.add(new Player("John",null));
                   players.add(new Player("Doe",null));
                   players.add(new Player("Smith",null));
                   Mockito.when(lobby.getConnctedPlayers()).thenReturn(players);
                   break;
               case 4:
                   Mockito.when(lobby.getNumOfPlayers()).thenReturn(4);
                   players.add(new Player("John",null));
                   players.add(new Player("Doe",null));
                   players.add(new Player("Smith",null));
                   players.add(new Player("Sam",null));
                   Mockito.when(lobby.getConnctedPlayers()).thenReturn(players);
                   break;

           }

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
    public void changeCurrentPlayerTest(){ //include test di buildMirrorArray() che è privato e non testabile direttamente

    }
}
