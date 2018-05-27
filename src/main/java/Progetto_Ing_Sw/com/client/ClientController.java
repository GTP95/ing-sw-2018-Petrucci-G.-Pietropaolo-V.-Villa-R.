package Progetto_Ing_Sw.com.client;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientController implements Runnable{

    private final View view;
   // private Client client;
    private Socket clientSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public ClientController(Socket clientSocket){
        //this.client = socketClient;
        this.view=new View(this);
        this.clientSocket=clientSocket;
        try {
            socketOut = new PrintWriter(clientSocket.getOutputStream());
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //qui ci vanno i metodi che andranno a comunicare con il server controller

    public void startGame() throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Inserisci il tuo nome utente");
        socketOut.write(scanner.nextLine());
        System.out.println(socketIn.readLine());
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
