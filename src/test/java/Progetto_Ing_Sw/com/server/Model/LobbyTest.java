package Progetto_Ing_Sw.com.server.Model;


import Progetto_Ing_Sw.com.server.Model.Lobby;
import Progetto_Ing_Sw.com.server.Model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;


public class LobbyTest {    //TODO: chiedere come testare i singleton! È difficile testare separatamente alcuni metodi
    private Lobby lobby;

    @Before
    public void testInit(){
        lobby=Lobby.getInstance();
    }

  /* @Test
    public void maxFourPlayersTest(){
        ClientPlayer player1=mock(ClientPlayer.class);
        ClientPlayer player2=mock(ClientPlayer.class);
        ClientPlayer player3=mock(ClientPlayer.class);
        ClientPlayer player4=mock(ClientPlayer.class);
        ClientPlayer player5=mock(ClientPlayer.class);

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
