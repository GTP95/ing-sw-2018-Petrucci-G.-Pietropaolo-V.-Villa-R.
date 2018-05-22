package Progetto_Ing_Sw.com.client;

import java.io.*;
import java.net.Socket;

public class Client {

    private final String host;
    private final int port;
    private Socket clientSocket;

    public Client(String host, int port) {//importo i dati dal file di configurazione
        this.host = host;
        this.port = port;
    }
    public void startSocket() throws IOException{//creo la socket con i dati da configurazione e inserisco il nome utente
        clientSocket = new Socket(host, port);
    }
    public void closeSocket() throws IOException{
        clientSocket.close();
    }
}
