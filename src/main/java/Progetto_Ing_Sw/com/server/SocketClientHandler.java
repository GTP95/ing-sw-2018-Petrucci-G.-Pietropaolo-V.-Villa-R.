package Progetto_Ing_Sw.com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Progetto_Ing_Sw.com.tools.JSONCreator;

public class SocketClientHandler implements Runnable{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static int timeout;

    public SocketClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            timeout=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/server/Settings/ServerSettings.json","timeout");
            clientSocket.setSoTimeout(timeout);
        }
        catch (IOException e){
            e.printStackTrace();    //TODO: timeout?
        }
    }

    public void run(){

            try {

                    Lobby.getInstance().addPlayer(in.readLine(), this);
                    out.println("Connected");
                //   if(Lobby.getInstance().getNumOfPlayers()==4) startGame();

            }
            catch(TooManyPlayersException e){
                out.println("Max number of players exceeded");
            }
            catch(InvalidUsernameException e){
                out.println("Username already in use");
            }
            catch (IOException e){
                e.printStackTrace();    //non ha senso mettere qui il timeout, deve solo inviare il nome
            }
        }



    public void sendCard(Card card){
        String json=JSONCreator.generateJSON(card);
        out.println(json);
    }
}
