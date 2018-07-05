package Progetto_Ing_Sw.com.server.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Progetto_Ing_Sw.com.tools.JSONCreator;

/**
 * @author Giacomo Tommaso Petrucci
 * This class is the entry point for the server
 */
public class LaunchServer {
    static int portNumber;
    public static void main(String[] args) {
        try {
            if(args.length>0) portNumber = Integer.parseInt(args[0]);
            else
                try{portNumber=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json","port");}
                catch (FileNotFoundException e){
                    portNumber=1024;
                    System.err.println("File \"ServerSetting.json\" not found, falling back to default port 1024");
                }
            ServerSocket serverSocket=new ServerSocket(portNumber);
            System.out.println("Server started on port " + portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();

              Thread thread=new Thread(new SocketClientHandler(clientSocket));
              thread.start();
              System.err.println("LaunchServer: creato "+thread.getName());

            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please provide a valid port number");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: ");
            System.out.println("java LaunchServer portNumber");
        }
        catch(IOException e){
            e.getCause();
        }



    }
}
