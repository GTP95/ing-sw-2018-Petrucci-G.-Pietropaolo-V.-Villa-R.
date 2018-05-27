package Progetto_Ing_Sw.com.client;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LaunchSocketClient {
    public static void main(String args[]) {
        String host;
        final int port = 1024;

        if (args.length == 1) host = args[0];



        //se l'utente non passa alcun parametro da linea di comando si carica da file
        else if (args.length == 0) {
            try {
                host = JSONCreator.parseStringFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json", "host");
            } catch (FileNotFoundException e) { //se il file non esiste lo crea
                try {
                    e.printStackTrace();    //TODO: gestire tenendo conto della GUI
                    host = "localhost";   //prenderlo dalla GUI
                    FileWriter fileWriter = new FileWriter("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("{\"host\":" + "\"" + host + "\"");  //valore dell'host in formato JSON
                    bufferedWriter.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                    return;
                }
            }
        }

        else {      //caso args.lenght>=2, messo senza condizioni per evitare errore "variable host might not have been initialized"
            System.out.println("Usage:\njava LaunchSocketClient hostname/IP");
            return;
        }
        //parte che importa host/port da file
        //righe successive da eliminare dopo aver fatto la parte sopra
        //   String host="localhost";
        //   int port=1024;

        //creazione del client

            Client client = new Client(host, port);
            ClientController clientController = new ClientController(client.getClientSocket());

            try {
                client.startSocket();
                clientController.startGame();

                try {
                    Thread.sleep(60000);
                }   //solo per test multithread
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}
