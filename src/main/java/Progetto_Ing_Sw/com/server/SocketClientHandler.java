package Progetto_Ing_Sw.com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientHandler implements Runnable{ //TODO: controllare che non ci siano nomi utente duplicati
    private Socket clientSocket;


    public SocketClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
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
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
}
