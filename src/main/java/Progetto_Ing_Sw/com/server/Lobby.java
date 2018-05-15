package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;

public class Lobby {
    private static Lobby ourInstance = new Lobby();
    private ArrayList<Player> connectedPlayers;
    private int timer;


    public static Lobby getInstance() {
        return ourInstance;
    }

    private Lobby() {
        connectedPlayers=new ArrayList<>();
    }

    public void addPlayer(String playerName) throws TooManyPlayersException, InvalidUsernameException {
        if(connectedPlayers.size()<4) {     //Non più di 4 giocatori per partita
            for(Player alreadyConnected : connectedPlayers){
                if (alreadyConnected.getName().equals(playerName)) throw new InvalidUsernameException();
            }
            Player player=new Player(playerName, PrivateObjectiveCardDeck.getInstance().draw());
            connectedPlayers.add(player);
            System.out.println("Player " + player.getName() + " joined the game!");    //TODO: test per verificare aggiunta giocatori nell'arraylist
        }

        else{
            System.out.println("User " + playerName + " Tried to connect, but 4 players are already connected.");
            throw new TooManyPlayersException();
        }
    }
    public ArrayList<Player> getConnctedPlayers(){  //Ritorna l'arraylist per copia e non per riferimento per evitare modifiche all'esterno della classe
        ArrayList<Player> arrayList=new ArrayList<>();
        for(Player player : arrayList) arrayList.add(player);
        return arrayList;
    }

    public int getNumOfPlayers(){
        return connectedPlayers.size();
    }
}
