package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public  class Model {
    private  String username;
    private static Model ourInstance=new Model();
    private String hostname;
    private int socketPort;
    private int rmiRegistryPort;


    private Model(){
        username="";
        ourInstance=this;
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

    public static Model getInstance(){
        return ourInstance;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
        System.out.println("Username set to "+username);
        notifyAll();
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

    public void setHostname(String hostname) {  //TODO: write to JSON
        this.hostname = hostname;
    }

    public void setSocketPort(int socketPort) {
        this.socketPort = socketPort;
    }

    public void writeSettingsToJSON(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{\"host\""+":\"" + hostname + "\""+ "," + "\"socketPort\"" + ":" + socketPort + "," + "\"username\"" + ":\"" + username+"\""+","+"\"rmiRegistryPort\""+":"+ rmiRegistryPort +"}");
            bufferedWriter.flush();
        }
        catch (IOException e){
            System.err.println("Failed to save current settings into JSON file \"ClientSettings.json\"");
        }
    }
}
