package Progetto_Ing_Sw.com.server.Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

public class Lobby {
    private static Lobby ourInstance = new Lobby();
    private ArrayList<Player> connectedPlayers;
    private Timer timer, countdown;
    private long timerValue;
    public volatile int countdownValue;    //accesso concorrente da più Task che inviano il countdown ai client e dal task interno a questa classe che ne aggiorna il valore
    public static boolean isRunning;


    public static Lobby getInstance() {
        return ourInstance;
    }

    private Lobby() {
        connectedPlayers = new ArrayList<>();
        isRunning=true;
        timer = new Timer();    //serve a far partire il gioco dopo un certo tempo se sono connessi almeno due giocatori
        countdown=new Timer();  //Serve a comunicare ai client il tempo rimanente prima dell'inizio automatico del gioco
        try {
            timerValue = JSONCreator.parseLongFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json", "timerValue");

        } catch (FileNotFoundException e) {
            System.out.println("File \"ServerSetting.json\" not found, falling back to default timerValue of 30 seconds");
            timerValue = 30000;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Table.getOurInstance().startGame();
                    isRunning=false;
                }
            }, timerValue);
        }
        finally {
            countdownValue=(int)timerValue/1000;
        }
    }

    public synchronized void addPlayer(String playerName, SocketClientHandler socketClientHandler) throws TooManyPlayersException, InvalidUsernameException {
        if(connectedPlayers.size()<4 && isRunning) {     //Non più di 4 giocatori per partita e vietate connessioni a gioco già iniziato
            if(playerName==null) throw new InvalidUsernameException("Invalid username: username cannot be null");
            if (playerName.isEmpty()) throw new InvalidUsernameException("Invalid username: empty username not allowed");
            for(Player alreadyConnected : connectedPlayers){
                if (alreadyConnected.getName().equals(playerName)) throw new InvalidUsernameException("Username already in use");
            }
            Player player=new Player(playerName, socketClientHandler);   //se vengono passati tutti i controlli, viene generato il nuovo utente ed inserito nell'arraylist
            connectedPlayers.add(player);
            System.out.println("Player " + player.getName() + " joined the game!");    //TODO: test per verificare aggiunta giocatori nell'arraylist
            for(Player player2:connectedPlayers){
                player2.getSocketClientHandler().ourThread.interrupt();
            }
            if(connectedPlayers.size()==2){ //fa partire il conto alla rovescia per l'inizio della partita. ==2 per non far partire più timer se si connettono più di due giocatori
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isRunning=false;
                        if(Table.gameRunning) return;
                        Table.getOurInstance().startGame();
                        return;     //Di vitale importanza per non far partire il gioco due volte!!
                    }
                }, timerValue);

                countdown.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        countdownValue--;
                        if (countdownValue>=0) System.out.println(countdownValue);
                        else countdown.cancel();
                    }
                },1000,1000);   //decrementa ogni secondo countdownValue;
            }
            if(getNumOfPlayers()==4) {
                Table.getOurInstance().startGame();    //La parita comincia automaticamente se ci sono connessi 4 giocatori
                isRunning=false;
            }
        }

        else{
            System.out.println("User " + playerName + " Tried to connect, but 4 players are already connected or game is already started.");
            throw new TooManyPlayersException();
        }
    }

    public synchronized ArrayList<Player> getConnctedPlayers(){  //Ritorna l'arraylist per copia e non per riferimento per evitare modifiche all'esterno della classe
        ArrayList<Player> arrayList=new ArrayList<>();
     //   while(connectedPlayers.size()==0);              //aspetta finchè viene aggiunto almeno un giocatore, evita NullPointerException
        for(Player player : connectedPlayers) arrayList.add(player);
        return arrayList;
    }

    public int getNumOfPlayers(){   //Non è necessario sincronizzarla: viene chiamata solo dalla classe Table e solo dopo che tutti i giocatori si sono connessi (qundi non può più cambiare)
        return connectedPlayers.size();
    }

    public void startTimer(){};
}
