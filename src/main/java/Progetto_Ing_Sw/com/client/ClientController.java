package Progetto_Ing_Sw.com.client;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientController {

    private final View view;
    private Client client;
    private Socket clientSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public ClientController(Client socketClient){
        this.client = socketClient;
        this.view=new View(this);
    }

    //qui ci vanno i metodi che andranno a comunicare con il server controller

    public void startGame() throws IOException{
        Scanner scanner=new Scanner(System.in);
        socketOut.println("Inserisci il tuo nome utente");
        socketOut.println(scanner.nextLine());
        socketOut.println(socketIn.readLine());
    }
    /*      Scanner scanner=new Scanner(System.in);
            out.println(scanner.nextLine());
            out.println(in.readLine());

            try{Thread.sleep(60000);}   //solo per test multithread
            catch(InterruptedException e){e.printStackTrace();}


        public void insertAction() throws IOException{
            socketOut.println(scanner.nextLine());
            socketOut.println(socketIn.readLine());

    }


    */
}
