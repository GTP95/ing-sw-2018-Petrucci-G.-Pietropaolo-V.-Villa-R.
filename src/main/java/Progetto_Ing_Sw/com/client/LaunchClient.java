package Progetto_Ing_Sw.com.client;
import java.io.IOException;

public class LaunchClient {
    public static void main(String args[]) throws IOException{

    //parte che importa host/port da file
    //righe successive da eleiminare dopo aver fatto la parte sopra
    String host="localhost";
    int port=1024;

    //creazione del client
    Client client=new Client(host,port);
    ClientController clientController= new ClientController(client);

    try {
        client.startSocket();
        clientController.startGame();

        try{Thread.sleep(60000);}   //solo per test multithread
        catch(InterruptedException e){e.printStackTrace();}

    }catch (IOException e) {e.printStackTrace();}

    }
}
