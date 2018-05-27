package Progetto_Ing_Sw.com.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private final String host;
    private final int port;
    private Socket clientSocket;

    public Client(String host, int port) {//importo i dati dal file di configurazione
        this.host = host;
        this.port = port;
        try{
            System.out.println("Connecting to" + host + ":" + port);
            startSocket();
        }
        catch (UnknownHostException e){
            System.out.println("Unknown host, are you sure to have typed it correctly?");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void startSocket() throws IOException{//creo la socket con i dati da configurazione
        clientSocket = new Socket(host, port);
    }
    public void closeSocket() throws IOException{
        clientSocket.close();
    }
}
