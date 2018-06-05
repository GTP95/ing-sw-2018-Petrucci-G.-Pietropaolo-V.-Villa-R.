package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Player;
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
    private ArrayList <ClientPlayer> clientPlayerArrayList;
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

    public synchronized ArrayList<ClientPlayer> getClientPlayerArrayList() {

        System.out.println("Getting clientPlayerArrayList");
        while (clientPlayerArrayList ==null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return clientPlayerArrayList;

    }

    public ClientPlayer getPlayerFromName(String name){
        ClientPlayer player=null;

        for(ClientPlayer playerCandidate : clientPlayerArrayList){
            if(playerCandidate.getName().equals(name)) {
                player = playerCandidate;
                break;
            }
        }

        return player;
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

    public synchronized void setClientPlayerArrayList(ArrayList<ClientPlayer> clientPlayerArrayList) {

                this.clientPlayerArrayList = clientPlayerArrayList;
                System.out.println("clientPlayerArrayList set to"+ clientPlayerArrayList.toString());
                notifyAll();
    }

    public synchronized void addPlayerToPlayerArrayList(ClientPlayer clientPlayer) {


            if (clientPlayerArrayList == null) clientPlayerArrayList = new ArrayList<>();
            clientPlayerArrayList.add(clientPlayer);
            notifyAll();

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
