package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public  class Model {
    private  String username;
    private static Model ourInstance=new Model();
    private String hostname;
    private int socketPort;
    private int rmiRegistryPort;
    private ArrayList <Player> playerArrayList;
    private Object lockUsername, lockPlayerArrayList;

    private Model(){
        username="";
        ourInstance=this;
        lockUsername=new Object();
        lockPlayerArrayList=new Object();
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

    public void setUsername(String username) {
        synchronized (lockUsername) {
            this.username = username;
            System.out.println("Username set to " + username);
            notifyAll();
        }
    }

    public String getUsername() {
        synchronized (lockUsername){
            return username;
        }
    }

    public String getHostname() {
        return hostname;
    }

    public int getSocketPort() {
        return socketPort;
    }

    public int getRmiRegistryPort() { return rmiRegistryPort; }

    public ArrayList<Player> getPlayerArrayList() {

        System.out.println("Getting playerArrayList");
        while (playerArrayList == null); //non posso usare wait() perchè non ho il lock su playerArrayList (IllegalMonitorStateException)
        return playerArrayList;

    }

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

    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
                 synchronized (lockPlayerArrayList) {
                this.playerArrayList = playerArrayList;
                System.out.println("playerArrayList set to"+playerArrayList.toString());
                notifyAll();    //IllegalMonitorStateException
            }


    }

    public void addPlayerToPlayerArrayList(Player player) {

        synchronized (lockPlayerArrayList) {
            if (playerArrayList == null) playerArrayList = new ArrayList<>();
            playerArrayList.add(player);
        }
    }

    public void writeSettingsToJSON(){
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
