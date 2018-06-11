package Progetto_Ing_Sw.com.server.Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Progetto_Ing_Sw.com.client.ClientPlayer;
import Progetto_Ing_Sw.com.tools.JSONCreator;

public class Lobby {
    private static Lobby ourInstance = new Lobby();
    private ArrayList<Player> connectedPlayers;
    private Timer timer;
    private long timerValue;



    public static Lobby getInstance() {
        return ourInstance;
    }

    private Lobby() {
        connectedPlayers = new ArrayList<>();
        timer = new Timer();
        try {
            timerValue = JSONCreator.parseLongFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json", "timerValue");

        } catch (FileNotFoundException e) {
            System.out.println("File \"ServerSetting.json\" not found, falling back to default timerValue of 30 seconds");
            timerValue = 30000;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Table.getOurInstance().startGame();
                }
            }, timerValue);
        }
    }

    public void addPlayer(String playerName, SocketClientHandler socketClientHandler) throws TooManyPlayersException, InvalidUsernameException {
        if(connectedPlayers.size()<4) {     //Non più di 4 giocatori per partita
            if(playerName==null) throw new InvalidUsernameException("Invalid username: username cannot be null");
            if (playerName.isEmpty()) throw new InvalidUsernameException("Invalid username: empty username not allowed");
            for(Player alreadyConnected : connectedPlayers){
                if (alreadyConnected.getName().equals(playerName)) throw new InvalidUsernameException("Username already in use");
            }
            Player player=new Player(playerName, PrivateObjectiveCardDeck.getInstance().draw(), socketClientHandler);   //se vengono passati tutti i controlli, viene generato il nuovo utente ed inserito nell'arraylist
            connectedPlayers.add(player);
            System.out.println("ClientPlayer " + player.getName() + " joined the game!");    //TODO: test per verificare aggiunta giocatori nell'arraylist
            for(Player connectedPlayer : connectedPlayers){
                connectedPlayer.getSocketClientHandler().getNotificationNewPlayerConnected(playerName);
            }
            if(connectedPlayers.size()==2){ //fa partire il conto alla rovescia per l'inizio della partita. ==2 per non far partire più timer se si connettono più di due giocatori
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Table.getOurInstance().startGame();
                    }
                }, timerValue);
            }
            if(getNumOfPlayers()==4) Table.getOurInstance().startGame();    //La parita comincia automaticamente se ci sono connessi 4 giocatori
        }

        else{
            System.out.println("User " + playerName + " Tried to connect, but 4 players are already connected.");
            throw new TooManyPlayersException();
        }
    }
    public ArrayList<Player> getConnctedPlayers(){  //Ritorna l'arraylist per copia e non per riferimento per evitare modifiche all'esterno della classe
        ArrayList<Player> arrayList=new ArrayList<>();
     //   while(connectedPlayers.size()==0);              //aspetta finchè viene aggiunto almeno un giocatore, evita NullPointerException
        for(Player player : connectedPlayers) arrayList.add(player);
        return arrayList;
    }

    public int getNumOfPlayers(){
        return connectedPlayers.size();
    }

    public void startTimer(){};
}
