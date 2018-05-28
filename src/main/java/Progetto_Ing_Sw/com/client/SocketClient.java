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

    public SocketClient(String host, int port) throws UnknownHostException {
        username=null;
        model=Model.getInstance();
        try{
            socket=new Socket(host, port);
            System.out.println("Connected to "+host+":"+port);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e){
            throw e;    //TODO: IOException è più generale della UnknownHostException, chiedere se c'è un modo più elegante per non perdere la seconda.
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
            while (!serverResponse.equals("connected")) {
               // System.out.println(in.readLine());
               // System.out.println("letto");
                serverResponse=in.readLine();
            }
            System.out.println(serverResponse); //non viene stampato!!
            }
            catch(IOException e){
                e.printStackTrace();
            }

    }

}
