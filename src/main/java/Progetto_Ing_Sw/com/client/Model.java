package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;

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
        }
        catch (FileNotFoundException e){
            hostname="localhost";
            port=1024;
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

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
