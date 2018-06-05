package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ClientSettings {
    private static ClientSettings ourInstance = new ClientSettings();
    private  String username;
    private String hostname;
    private int socketPort;
    private int rmiRegistryPort;

    public static ClientSettings getInstance() {
        return ourInstance;
    }

    private ClientSettings() {
        username="";
        try {
            hostname = JSONCreator.parseStringFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json", "host");
            socketPort =JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json","socketPort");
            username=JSONCreator.parseStringFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json", "username");
            rmiRegistryPort =JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json","rmiRegistryPort");
        }
        catch (FileNotFoundException e){
            System.err.println("File ClientSettings.json not found, falling back to defaults");
            hostname="localhost";
            socketPort =1024;
            username="";
            rmiRegistryPort =1099;
        }
    }

    public synchronized void setUsername(String username) {

        this.username = username;
        System.out.println("Username set to " + username);


    }

    public synchronized String getUsername() {

        return username;

    }

    public String getHostname() {
        return hostname;
    }

    public int getSocketPort() {
        return socketPort;
    }

    public int getRmiRegistryPort() { return rmiRegistryPort; }

    public void setHostname(String hostname) {
        this.hostname = hostname;
        System.out.println("Hostmane set to "+hostname);
    }

    public void setSocketPort(int socketPort) {
        this.socketPort = socketPort;
        System.out.println("SocketPort set to "+socketPort);
    }

    public void setRmiRegistryPort(int rmiRegistryPort) {
        this.rmiRegistryPort = rmiRegistryPort;
        System.out.println("rmiRegistryPort set to "+rmiRegistryPort);
    }

    public void writeSettingsToJSON(){          //Salva i valori correnti nel file ServerSettings.json
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{\"host\""+":\"" + hostname + "\""+ "," + "\"socketPort\"" + ":" + socketPort + "," + "\"username\"" + ":\"" + username+"\""+","+"\"rmiRegistryPort\""+":"+ rmiRegistryPort +"}");
            bufferedWriter.flush();
            System.out.println("Current settings saved to ClientSettings.json");
        }
        catch (IOException e){
            System.err.println("Failed to save current settings into JSON file \"ClientSettings.json\"");
        }
    }
}
