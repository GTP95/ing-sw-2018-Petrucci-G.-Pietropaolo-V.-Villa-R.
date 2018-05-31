package Progetto_Ing_Sw.com.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable{
    private Socket socket;  //TODO: rendere final se decido di non interrompere la connessione in caso di username non valido, lasciare così altrimenti
    private String username;
    PrintWriter out;
    BufferedReader in;
    Model model;

    public SocketClient() throws UnknownHostException {
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

        System.out.println("Pronto all'invio dello username");
        out.println(username);
        System.out.println("Inviato "+username+" come username");

        try {
            System.out.println("Provo a leggere");
            while (!in.ready());    //aspetta il messaggio dal server
            serverResponse=in.readLine();
            System.out.println(serverResponse);

            }
            catch(IOException e){
                e.printStackTrace();
            }

    }

    private void receiveMessage() {
        try {
            while (!in.ready()) ;    //aspetta il messaggio dal server
            String message = in.readLine();
            String messageFields[]=message.split("%");  //Salva nell'array i campi del messaggio separati da %
            String messageType=messageFields[0];    //il primo campo del messaggio contiene il tipo del messaggio
            switch (messageType){
                case "Control":
                    String messageContnent=messageFields[1];    //Nel caso di messaggi di controllo il messaggio è contenuto nel secondo campo della stringa
                    break;
                case "JSON":
                    String json=messageFields[1];       //nel caso dell'invio di JSON il JSON è contenuto nel secondo campo della stringa
                    String nameOfClass=messageFields[2];    //mentre il terzo campo contiene il nome della classe
                    break;
                case "Action":
                    String actionDescription=messageFields[1];  //La descrizione dell'azione dovrà poi essere ulteriormente "parsata" da un apposito metodo
                    break;
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }

 /*   private void receiveUserArrayList(){
        while (!in.ready());    //aspetta il messaggio dal server

    }*/

}
