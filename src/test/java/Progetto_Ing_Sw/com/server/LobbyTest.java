package Progetto_Ing_Sw.com.server;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LobbyTest {    //TODO: chiedere come testare i singleton! È difficile testare separatamente alcuni metodi
    private Lobby lobby;

    @Before
    public void testInit(){
        lobby=Lobby.getInstance();
    }

  /* @Test
    public void maxFourPlayersTest(){
        Player player1=mock(Player.class);
        Player player2=mock(Player.class);
        Player player3=mock(Player.class);
        Player player4=mock(Player.class);
        Player player5=mock(Player.class);

        lobby.addPlayer("");
   } */

   @Test
    public void getNumOfPlayersTest(){
       Player player1=mock(Player.class);
       Player player2=mock(Player.class);
       Player player3=mock(Player.class);
       Player player4=mock(Player.class);

       Assert.assertEquals(4, lobby.getNumOfPlayers());
   }

}
