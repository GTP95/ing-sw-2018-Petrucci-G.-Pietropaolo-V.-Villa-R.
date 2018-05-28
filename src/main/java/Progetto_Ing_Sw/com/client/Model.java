package Progetto_Ing_Sw.com.client;

public  class Model {
    private  String username;
    private static Model ourInstance=new Model();


    private Model(){
        username=null;
        ourInstance=this;
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
}
