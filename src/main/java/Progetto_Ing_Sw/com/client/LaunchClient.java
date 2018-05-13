package Progetto_Ing_Sw.com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LaunchClient {

    public static void main(String args[]) {
        try {
            Socket clientSocket = new Socket("localhost", 1024);
            PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner=new Scanner(System.in);
            out.println(scanner.nextLine());
            System.out.println(in.readLine());

            try{Thread.sleep(60000);}   //solo per test multithread
            catch(InterruptedException e){e.printStackTrace();}
        }
        catch(IOException e){
            e.getStackTrace();
        }
    }
}
