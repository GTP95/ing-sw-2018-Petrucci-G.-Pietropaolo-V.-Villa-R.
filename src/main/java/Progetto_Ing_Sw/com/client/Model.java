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
    private int port;


    private Model(){
        username=null;
        ourInstance=this;
        try {
            hostname = JSONCreator.parseStringFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json", "host");
            port=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json","port");
            username=JSONCreator.parseStringFieldFromFile("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json", "username");
        }
        catch (FileNotFoundException e){
            System.err.println("File ClientSettings.json not found, falling back to defaults");
            hostname="localhost";
            port=1024;
            username="";
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

    public int getPort() {
        return port;
    }

    public void setHostname(String hostname) {  //TODO: write to JSON
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void writeSettingsToJSON(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/Progetto_Ing_Sw/com/client/Settings/ClientSettings.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{\"host\""+":\"" + hostname + "\""+ "," + "\"port\"" + ":" + port + "," + "\"username\"" + ":\"" + username+"\""+"}");
            bufferedWriter.flush();
        }
        catch (IOException e){
            System.err.println("Failed to save current settings into JSON file \"ClientSettings.json\"");
        }
    }
}
