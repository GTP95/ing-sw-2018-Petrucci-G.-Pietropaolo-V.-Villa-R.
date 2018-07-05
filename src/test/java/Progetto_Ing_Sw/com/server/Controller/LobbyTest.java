package Progetto_Ing_Sw.com.server.Controller;


import Progetto_Ing_Sw.com.server.Controller.Lobby;
import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;
import Progetto_Ing_Sw.com.server.Model.InvalidUsernameException;
import Progetto_Ing_Sw.com.server.Model.TooManyPlayersException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

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

  /* @Test
    public void getNumOfPlayersTest(){
       try {
           lobby.addPlayer("ReadyPlayerOne", new SocketClientHandler(new Socket()));    //socketClientHandler impostato a null perchè tanto non viene usato per il test
           lobby.addPlayer("ReadyPlayerTwo", new SocketClientHandler(new Socket()));    //socketClientHandler impostato a null perchè tanto non viene usato per il test
           lobby.addPlayer("ReadyPlayerThree", new SocketClientHandler(new Socket()));    //socketClientHandler impostato a null perchè tanto non viene usato per il test
           lobby.addPlayer("ReadyPlayerFour", new SocketClientHandler(new Socket()));    //socketClientHandler impostato a null perchè tanto non viene usato per il test
           Assert.assertEquals(4, lobby.getNumOfPlayers());
       }
       catch (TooManyPlayersException e){
           Assert.fail("TooManyPlayersException");
       }
       catch(InvalidUsernameException e){
           Assert.fail(e.getMessage());
       }
   }*/

}
