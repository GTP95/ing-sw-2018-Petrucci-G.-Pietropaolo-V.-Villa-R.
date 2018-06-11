package Progetto_Ing_Sw.com.client;

import java.util.ArrayList;


public  class LocalModel {

    private static LocalModel ourInstance=new LocalModel();

    private ArrayList <ClientPlayer> clientPlayerArrayList;
    private Object lockUsername, lockPlayerArrayList;
    private MultiplayerGUI observer;

    private LocalModel(){

        ourInstance=this;
        lockUsername=new Object();
        lockPlayerArrayList=new Object();

    }

    public static LocalModel getInstance(){
        return ourInstance;
    }





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


    public synchronized void setClientPlayerArrayList(ArrayList<ClientPlayer> clientPlayerArrayList) {

                this.clientPlayerArrayList = clientPlayerArrayList;
                System.out.println("clientPlayerArrayList set to"+ clientPlayerArrayList.toString());
                observer.update();
                notifyAll();
    }

    public synchronized void addPlayerToPlayerArrayList(ClientPlayer clientPlayer) {
        boolean addPlayer=false;
            if (clientPlayerArrayList == null) clientPlayerArrayList = new ArrayList<>();
            for(ClientPlayer clientPlayerInArray : clientPlayerArrayList)
                if(!clientPlayerInArray.getName().equals(clientPlayer)) addPlayer=true;
        if (addPlayer) {
            clientPlayerArrayList.add(clientPlayer);
        System.out.println("PlayerArrayList:");
            for(ClientPlayer player : clientPlayerArrayList){
                System.out.println(player.getName());
            }
            observer.update();
            notifyAll();
        }
    }

   /* private void notifyObserver(){
        observer.update();
    }*/

    public void registerAsObserver(MultiplayerGUI multiplayerGUI){
            this.observer=multiplayerGUI;
    }


}
