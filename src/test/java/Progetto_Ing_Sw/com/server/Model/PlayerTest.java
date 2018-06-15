package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SplittableRandom;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;
    private final SplittableRandom splittableRandom=new SplittableRandom();
    private PrivateObjectiveCard privateObjective;
    private SocketClientHandler socketClientHandler;
    private ArrayList<GameBoardCard> drawnGameBoardCards;

    @Before
    public void testInit(){ //costruisce un oggetto player con alcuni parametri casuali
        privateObjective=mock(PrivateObjectiveCard.class);
        when(privateObjective.getColor()).thenReturn(splittableRandom.nextInt(0,5));

        drawnGameBoardCards=new ArrayList<>();
        drawnGameBoardCards.add(mock(GameBoardCard.class));
        drawnGameBoardCards.add(mock(GameBoardCard.class));
        player=new Player("John",  socketClientHandler );
    }

    @Test
    public void useToolCardTest(){
        ToolCard neverUsedToolCard=mock(ToolCard.class);
        when(neverUsedToolCard.isFirstUsage()).thenReturn(true);

        ToolCard alreadyUsedToolCard=mock(ToolCard.class);
        when(alreadyUsedToolCard.isFirstUsage()).thenReturn(false);

        int favorTokens=player.getFavorTokens();
        try {
            player.useToolCard(neverUsedToolCard);
            Assert.assertEquals(favorTokens - 1, player.getFavorTokens());
        }
        catch (NotEnoughFavorTokensException e){
            Assert.assertEquals(0,player.getFavorTokens()); //In questo caso l'eccezione viene lanciata se il giocatore non ha più segnalini favore
            Assert.assertEquals(favorTokens, player.getFavorTokens());  //se viene lanciata l'eccezione il numero di segnalini favore non viene modificato
        }

        favorTokens=player.getFavorTokens();
        try{
            player.useToolCard(alreadyUsedToolCard);
            Assert.assertEquals(favorTokens-2, player.getFavorTokens());
        }
        catch (NotEnoughFavorTokensException e){
            Assert.assertTrue(player.getFavorTokens()<2);   //In questo caso l'eccezione viene lanciata se il giocatore non ha almeno 2 segnalini favore
            Assert.assertEquals(favorTokens, player.getFavorTokens());  //Se viene lanciata l'eccezione il numero di segnalini favore non viene cambiato
        }

    }

    @Test
    public void notEnoughFavorTokensTest(){
        ToolCard neverUsedToolCard=mock(ToolCard.class);
        when(neverUsedToolCard.isFirstUsage()).thenReturn(true);

        ToolCard alreadyUsedToolCard=mock(ToolCard.class);
        when(alreadyUsedToolCard.isFirstUsage()).thenReturn(false);

        Player player=new Player("John",  socketClientHandler);

        try {
            System.out.println("Testo l'utilizzo di una ToolCard mai usata prima");
            player.useToolCard(neverUsedToolCard);
            Assert.fail("Il metodo useToolCard non ha lanciato l'eccezione notEnoughFavorTokens nonostante il giocatore abbia 0 segnalini favore");
        }
        catch(NotEnoughFavorTokensException e){
            Assert.assertTrue(true);    //Non esiste Assert.pass...
        }

        try{
            System.out.println("Testo l'utilizzo di una ToolCard già usata in precedenza");
            player.useToolCard(alreadyUsedToolCard);
            Assert.fail("Il metodo useToolCard non ha lanciato l'eccezione notEnoughFavorTokens nonostante il giocatore abbia 0 segnalini favore");
            }
            catch(NotEnoughFavorTokensException e){
                Assert.assertTrue(true);    //Non esiste Assert.pass...
            }


        player=  new Player("John",  socketClientHandler);
        try{
            System.out.println("Testo l'utilizzo di una ToolCard già usata in precedenza");
            player.useToolCard(alreadyUsedToolCard);
            Assert.fail("Il metodo useToolCard non ha lanciato l'eccezione notEnoughFavorTokens nonostante il giocatore abbia solo 1 segnalino favore");
        }
        catch(NotEnoughFavorTokensException e){
            Assert.assertTrue(true);    //Non esiste Assert.pass...
        }
    }
}

