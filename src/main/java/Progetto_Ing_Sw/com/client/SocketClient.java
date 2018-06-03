package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.InvalidUsernameException;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable{
    private Socket socket;  //TODO: rendere final se decido di non interrompere la connessione in caso di username non valido, lasciare così altrimenti
    private String username;
    PrintWriter out;
    BufferedReader in;
    Model model;
    Exception trownException;

    public SocketClient() throws UnknownHostException, ConnectException {
        username=null;
        model=Model.getInstance();
        String host=model.getHostname();
        int port=model.getSocketPort();
        try{
            socket=new Socket(host, port);
            System.out.println("Connected to "+host+":"+port);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e){
            throw e;    // dato che IOException è più generale della UnknownHostException, è necessario catturare quest'ultima separatamente e rilanciarla per farla ricevere al chiamante
        }

        catch (ConnectException e){
            throw e;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String serverResponse="";
        while (username==null || username.equals("")) {    //In questo caso essendo il metodo getUsername synchronized, non solo non è necessario utilizzare wait() all'interno del while, ma addirittura causerebbe una IllegalMonitorStateException
            username=model.getUsername();
        }
        out.println(username);
        System.out.println("Inviato "+username+" come username");
        while (true) {      //TODO:ricevere notifica chiusura GUI ed usarla come condizione
            try {
                receiveMessage();
            } catch (TooManyPlayersException | Progetto_Ing_Sw.com.client.InvalidUsernameException e) {
                trownException = e;
            }
        }
    }

    private void receiveMessage() throws TooManyPlayersException, Progetto_Ing_Sw.com.client.InvalidUsernameException {
        try {
            while (!in.ready()) ;    //aspetta il messaggio dal server
            String message = in.readLine();
            System.out.println("Message received: "+message);
            String messageFields[]=message.split("%");  //Salva nell'array i campi del messaggio separati da %
            String messageType=messageFields[0];    //il primo campo del messaggio contiene il tipo del messaggio
            switch (messageType){
                case "Control":
                    String messageContent=messageFields[1];    //Nel caso di messaggi di controllo il messaggio è contenuto nel secondo campo della stringa
                    handleControlMessage(messageContent);
                    break;
                case "JSON":
                    String json=messageFields[1];       //nel caso dell'invio di JSON il JSON è contenuto nel secondo campo della stringa
                    String nameOfClass=messageFields[2];    //mentre il terzo campo contiene il nome della classe
                    handleJSONmessage(json, nameOfClass);
                    break;
                case "Action":
                    String actionDescription=messageFields[1];  //La descrizione dell'azione dovrà poi essere ulteriormente "parsata" da un apposito metodo
                    break;
                default: System.err.println("Unable to understand the following message received from server: "+message);
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }

    private void handleControlMessage(String messageContent) throws TooManyPlayersException, Progetto_Ing_Sw.com.client.InvalidUsernameException {
        switch (messageContent) {
            case "Connected":
                System.out.println("Connected");
                break;
            case "Max number of players exceeded":
                throw new TooManyPlayersException();
                //Non solo non serve mettere un break ma viene addirittura segnalato come errore perchè viene lanciata un'eccezione edunque il break non verrebbe mai eseguito
        }
        if(messageContent.startsWith("Invalid username: ")) throw new Progetto_Ing_Sw.com.client.InvalidUsernameException(messageContent.substring(18));
    }

    private void handleJSONmessage(String json, String nameOfClass){
        System.out.println("handling JSON message");
        switch (nameOfClass){
            case "arrayListOfPlayers":
                model.setPlayerArrayList(JSONCreator.playerArrayListLoaderFromString(json));
                break;
            case "Player":
                model.addPlayerToPlayerArrayList(JSONCreator.playerLoaderFromString(json));
                break;
        }
    }

    public Boolean exceptioneTrown(){
        if(trownException!=null) return true;
        return false;
 }

    public Exception getTrownException() {
        return trownException;
    }

    public void resetException(){
        trownException=null;
    }
}
