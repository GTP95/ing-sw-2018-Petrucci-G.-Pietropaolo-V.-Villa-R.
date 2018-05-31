package Progetto_Ing_Sw.com.server.Model;

import java.io.*;
import java.net.Socket;

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
            timeout=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json","timeout");
            clientSocket.setSoTimeout(timeout); //timeout inattività giocatore impostato direttamante sulla socket del giocatore
        }
        catch (FileNotFoundException e){
            System.out.println("File ServerSettings not found, falling back to 30 seconds of timeout");
            timeout=30000;  //timeout in millisecopndi
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
                out.println(e.getMessage());
            }
            catch (IOException e){
                e.printStackTrace();    //non ha senso mettere qui il timeout, deve solo inviare il nome
            }
        }


    @Deprecated
    private void sendCard(Card card){
        String json=JSONCreator.generateJSON(card);
        out.println(json);
    }

    private void sendControlMessage(String message){    //Nei messaggi uso % come separatore dei campi per semplificare il parsing in ricezione ed evitare confilitti con il formato JSON
        String messageToSend="Control%"+message;
        out.println(messageToSend);
    }

    private void sendJSONmessage(String json, String nameOfClass){
        String messageToSend="JSON%"+json+"%"+nameOfClass;
        out.println(messageToSend);
    }

    private void sendActionMessage(String actionDescription){   //TODO: stabilire formato actionDescription
        String messageToSend="Action%"+actionDescription;
        out.println(messageToSend);
    }

}
