package Progetto_Ing_Sw.com.server.Controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

public class SocketClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static int timeout;
    public Thread ourThread;
    private Table table;
    private ArrayList<Player> currentPlayerArrayList, previousPlayerArrayList;
    private ArrayList<Dice> currentDiceArrayList, previousDiceArrayList;
    public volatile boolean updateWindowBoards, updateDice,isMyTurn, changedTurn, timerStarted, changedRound, updateRoundTrack, notifyUsedToolCard, updateTokens, updateToolCards; //servono per gestire gli interrupt ricevuti da Table per aggiornare i dati, analogo al pattern observer ma fatto usando gli interrupt al posto di un metodo "notify()"
    private String myPlayerName;
    private Player myPlayer;
    private Timer countdown; //Countdown invia il conto alla rovescia della Lobby, timerTurn invece gestisce la durata del turno di gioco
    private boolean otherPlayersWindowBoardsSent;

    public SocketClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket; //socket su cui è in ascolto il client
        countdown=new Timer();
        updateWindowBoards=false;   //serve per gestire gli interrupt ricevuti da Table per aggiornare i dati, analogo al pattern observer ma fatto usando gli interrupt al posto di un metodo "notify()"
        updateDice=false;           //Idem come sopra
        isMyTurn=false;
        otherPlayersWindowBoardsSent=false;
        updateRoundTrack=false;
        notifyUsedToolCard=false;
        updateTokens=false;
        updateToolCards=false;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            timeout=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json","timeout");
            clientSocket.setSoTimeout(timeout); //timeout inattività giocatore impostato direttamante sulla socket del giocatore
        }
        catch (FileNotFoundException e){
            System.out.println("File ServerSettings not found, falling back to 30 seconds of timeout and 60 seconds of turn duration");
            timeout=30000;  //timeout in millisecopndi
        }
        catch (IOException e){
            e.printStackTrace();    //TODO: timeout?
        }
    }

    public void run(){

            try {
                    myPlayerName=in.readLine();          //nome del giocatore gestito da questo thread
                    ourThread=Thread.currentThread();   //riferimento al thread che sta eseguendo questo codice
                    System.err.println(ourThread.getName());
                    ourThread.setName(myPlayerName+"'s SocketClientHandler");
                    Lobby.getInstance().addPlayer(myPlayerName, this);
                    sendControlMessage("Connected");

                countdown.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        int countdownValue=Lobby.getInstance().countdownValue;
                        if(countdownValue>=0) sendControlMessage("CountdownValue&"+countdownValue);
                        else countdown.cancel();
                    }
                },1000,1000);   //invia ogni secondo countdownValue;

                        while(Lobby.isRunning) {
                            if(ourThread.interrupted()){
                                sendPlayerMessage();
                            }
                        }
                        //arrivati qui il gioco è cominciato
                sendPlayerMessage();
                sendControlMessage("Game started!");
                this.table=Table.getOurInstance();  //La lobby è terminata, è tempo di lavorare sul tavolo
                previousDiceArrayList=table.getDrawnDice();
                sendGameInitializationData();
                receiveChoosenGameBoardCard();
                System.out.println(ourThread.getName()+": waiting for windowboards");
                sendOtherPlayersWindowBoards();
              //  sendControlMessage("Your turn just ended"); //all'inizio non è il turno di nessuno, fatto per xomodità della GUI
                notifyIfIsYourTurn();   //Invia la notifica di inizio turno solo al primo giocatore
                System.err.println("STO PER ENTRARE NEL WHILE "+ourThread.getName());

                while(Table.gameRunning){
                    receiveMessage();
                    if(ourThread.isInterrupted()) updateTable();

                 }

                 System.err.println("SE LEGGI QUI SEI NEI GUAI "+ourThread.getName());
            }
            catch(TooManyPlayersException e){
                sendControlMessage("Max number of players exceeded");
            }
            catch(InvalidUsernameException e){
                sendControlMessage(e.getMessage());
                myPlayerName=null;  //resetto il nome perchè non valido
            }
            catch (IOException e){
                e.printStackTrace();    //non ha senso mettere qui il timeout, deve solo inviare il nome
           }
           catch (NotEnoughFavorTokensException e) {
                sendControlMessage("Not enough favor tokens");
            }

    }


    @Deprecated
    private void sendCard(Card card){
        String json=JSONCreator.generateJSON(card);
        out.println(json);
    }

    private void sendControlMessage(String message){    //Nei messaggi uso % come separatore dei campi per semplificare il parsing in ricezione ed evitare confilitti con il formato JSON
        String messageToSend="Control%"+message;
        out.println(messageToSend);
        System.out.println(ourThread.getName()+": sent control message "+messageToSend);
    }

    private void sendJSONmessage(String json, String nameOfClass){
        String messageToSend="JSON%"+json+"%"+nameOfClass;
        out.println(messageToSend);
        System.out.println(ourThread.getName()+": JSON message sent "+nameOfClass);
    }

    private void sendActionMessage(String json, String actionDescription){   //TODO: stabilire formato actionDescription
        String messageToSend="Action%"+json+"%"+actionDescription;
        out.println(messageToSend);
    }

    private void sendPlayerMessage() {
        System.err.println("Sending players");
        for (Player player : Lobby.getInstance().getConnctedPlayers()) {
            String messageToSend = "Player%" + player.getName();
            out.println(messageToSend);
        }
    }



    public void getNotificationNewPlayerConnected(String playerName){
            System.out.println("Sending newly connected player name to player");
            String messageToSend = "Player%" + playerName;
            out.println(messageToSend);

    }



    private void sendGameInitializationData(){
        try {
            ArrayList<GameBoardCard> drawnGameBoardCard=null;
            while(drawnGameBoardCard==null){
                myPlayer=table.getPlayerFromName(myPlayerName);
                drawnGameBoardCard=myPlayer.getDrawnGameBoardCard();
            }
            sendControlMessage("Sending GameBoardcards&"+myPlayer.getDrawnGameBoardCard().size());
            for(GameBoardCard gameBoardCard : myPlayer.getDrawnGameBoardCard()){
                sendJSONmessage(JSONCreator.generateJSON(gameBoardCard),"GameBoardCard");
            }
            sendJSONmessage(JSONCreator.generateJSON(myPlayer.getPrivateObjective()),"PrivateObjectiveCard");
        }
        catch(InvalidUsernameException e){System.err.println(e.getMessage());}

        receiveControlMessage();

        sendControlMessage("Sending Dice&"+table.getDrawnDice().size());    //Comunico al client quanti dadi sto per inviare
        for(Dice dice : table.getDrawnDice()){  //Purtroppo è necessario inviare le carte una per volta: se si invia il JSON dell'intero ArrayList il client riceve solo i primi due...
            sendJSONmessage(JSONCreator.generateJSON(dice), "Dice");
        }

        sendControlMessage("Sending ToolCards&"+table.getDrawnToolCards().size());
        for(ToolCard toolCard : table.getDrawnToolCards()){
            sendJSONmessage(JSONCreator.generateJSON(toolCard),"ToolCard");
        }

        sendControlMessage("Sending publicObjectiveCards&"+table.getDrawnPublicObjectiveCards().size());
        for (PublicObjectiveCard publicObjectiveCard : table.getDrawnPublicObjectiveCards()){
            sendJSONmessage(JSONCreator.generateJSON(publicObjectiveCard),"PublicObjectiveCard");
        }
    }

    private void receiveControlMessage(){   //È bloccante!
        try{
        while(!in.ready()); //aspetta che il buffer sia pronto ad essere letto

            String message=in.readLine();
            String messageFields[]=message.split("%");
            switch(messageFields[0]){
                case "Data received, go ahead":
                    //niente, va avanti
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void receiveChoosenGameBoardCard(){
        try {
            System.err.println("ASPETTO LA GAMEBOARDCARD "+ourThread.getName());
            while (!in.ready()) ; //aspetta che il buffer sia pronto per essere letto
            System.err.println("Sto per leggere il buffer "+ourThread.getName());
            String message = in.readLine();
            String messageFields[] = message.split("%");
            System.err.println("receiveGameBoardCard: ricevuta "+messageFields[2]+" "+ourThread.getName());
            table.setChoosenGameBoardCard(myPlayerName, messageFields[2]);
           // myPlayer.setChoosenGameBoard(myPlayer.getGameBoardCardFromTitle(messageFields[2]));
            System.err.println();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

 /*   private void handleInsertDice(Dice dice, int row, int column) {
        try {
            myPlayer.getChoosenWindowBoard().insertDice(row, column, dice);
        }
        catch (PlaceDiceException e){
            sendControlMessage(e.getMessage());
        }
    }

    private void handleUseToolCard(ToolCard toolCard){
        try {
            myPlayer.useToolCard(toolCard);
            sendControlMessage("OK");
        } catch (NotEnoughFavorTokensException e) {
            sendControlMessage("You don't have enough favour tokens!");
        }
    }*/

    private void handleActionMessage(String messageContent)throws NotEnoughFavorTokensException {
        String[] fields = messageContent.split("&");
        Dice dice;
        switch (fields[1]) {
            case "Place dice":
                try {
                    dice = JSONCreator.diceLoaderFromString(fields[0]);
                    System.err.println("Received the following dice color: "+dice.getColor()+" value: "+dice.getValue());
                    if (table.diceExists(dice)) {
                        myPlayer.getChoosenWindowBoard().insertDice(Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), dice);
                        if (table.removeDice(dice)) System.out.println("Dice removed");
                        sendControlMessage("Dice placed successfully");
                        sendJSONmessage(JSONCreator.generateJSON(myPlayer.getChoosenWindowBoard()), "WindowBoard");
                        table.notifyWindowBoardChange(ourThread);
                      /*  sendControlMessage("Sending Dice&" + table.getDrawnDice().size());    //Comunico al client quanti dadi sto per inviare
                        for (Dice diceToSend : table.getDrawnDice()) {  //Purtroppo è necessario inviare i dadi uno per volta: se si invia il JSON dell'intero ArrayList il client riceve solo i primi due...
                            sendJSONmessage(JSONCreator.generateJSON(diceToSend), "Dice");
                        }*/

                    } else throw new IllegalDiceException("Selected dice doesn't exists!");
                } catch (PlaceDiceException e) {
                    sendControlMessage(e.getMessage());
                    myPlayer.getChoosenWindowBoard().printMatrixArrayList();
                } catch (IllegalDiceException e) {
                    sendControlMessage(e.getMessage());
                }
                break;
            case "Use Grozing Pliers":
                //System.err.println("Credo di dover usare Grozing Pliers percè ho ricevuto il seguente messaggio azione: "+messageContent);
                dice=JSONCreator.diceLoaderFromString(fields[0]);
                String command=fields[2];
                table.useToolCard("Grozing Pliers",myPlayer);
                table.useGrozingPliers(dice, command,myPlayer);
                break;
            case "Use Grinding Stone":
                 dice=JSONCreator.diceLoaderFromString(fields[0]);
                 table.useToolCard("Grinding Stone",myPlayer);
                 table.useGrindingStone(dice,myPlayer);
                break;
            case "Use Flux Brush":
                dice=JSONCreator.diceLoaderFromString(fields[0]);
                table.useToolCard("Flux Brush",myPlayer);
                table.useFluxBrush(dice,myPlayer);
                break;
            default:
                System.err.println("Can't understand the following action message: "+messageContent);
        }
    }

    private void handleEndTurn(){}

    private void receiveMessage() throws NotEnoughFavorTokensException{
        try {
            if (in.ready()) {     //aspetta che il buffer sia prono ad essere letto
                String message = in.readLine();
                System.out.println("Message received: " + message);
                String messageFields[] = message.split("%");  //Salva nell'array i campi del messaggio separati da %
                String messageType = messageFields[0];    //il primo campo del messaggio contiene il tipo del messaggio
                switch (messageType) {
                    case "Action":
                        handleActionMessage(messageFields[1]);
                        break;
                    case "Control":
                        handleControlMessage(messageFields[1]);
                        break;
                    default:
                        System.err.println("Can't understand the following message's category: "+message);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void updateTable(){
        updateDrawnDiceIfNecessary();
        updatePlayersWindowBoardsIfNecessary();
        notifyRoundChange();
        sendRoundTrackUpdateIfNecessary();
        notifyIfIsYourTurn();
        notifyWhoIsTheCurrentPlayer();
        notifyUsedToolCard();
        updateTokens();
        updateToolCards();
    }

    private void notifyIfIsYourTurn(){
        if(table.getActivePlayer().getName().equals(myPlayerName) && !isMyTurn){
            isMyTurn=true;
            sendControlMessage("It's your turn now");
        }
    }

    private void updateDrawnDiceIfNecessary(){
        if(updateDice){
            System.err.println("INVIO AGGIORNAMENTO DADI");
            sendControlMessage("Sending Dice&"+table.getDrawnDice().size());    //Comunico al client quanti dadi sto per inviare
            for(Dice dice : table.getDrawnDice()){  //Purtroppo è necessario inviare i dadi uno per volta: se si invia il JSON dell'intero ArrayList il client riceve solo i primi due...
                sendJSONmessage(JSONCreator.generateJSON(dice), "Dice");
            }
         //   previousDiceArrayList=currentDiceArrayList;
            updateDice=false;
        }
    }
    private void updatePlayersWindowBoardsIfNecessary(){
        if(updateWindowBoards) {
            sendControlMessage("Sending WindowBoards update&" + table.getPlayers().size());
            for (Player player : table.getPlayers()) {
                    sendJSONmessage(JSONCreator.generateJSON(player.getChoosenWindowBoard()), "WindowBoardUpdate");
            }
            updateWindowBoards=false;
        }
    }
   private void notifyWhoIsTheCurrentPlayer(){
        if(!isMyTurn && changedTurn){
            sendControlMessage("Current player is&"+table.getActivePlayer().getName());
            changedTurn=false;
        }

   }

   private void handleControlMessage(String message){
        String[] messageFields=message.split("&");
       switch(messageFields[0]){
           case "End my turn":
               table.changeCurrentPlayer();
               sendControlMessage("Your turn just ended");
               isMyTurn=false;
               break;

           default:
               System.err.println("Can't understand the following control message: "+message);
       }

   }

   private void sendOtherPlayersWindowBoards(){
        while(!updateWindowBoards); //aspetta che il server riceva le WindowBoards di tutti i giocatori
       sendControlMessage("Sending WindowBoards update&" + table.getPlayers().size());
       for (Player player : table.getPlayers()) {
           sendJSONmessage(JSONCreator.generateJSON(player.getChoosenWindowBoard()), "WindowBoardUpdate");
       }
       updateWindowBoards=false;
   }

   private void  notifyRoundChange(){
        if (changedRound){
            sendControlMessage("Changed round&"+RoundTrack.getInstance().getRoundNumber());
            changedRound=false;
        }
   }

   private void sendRoundTrackUpdateIfNecessary(){
        if(updateRoundTrack){
            sendJSONmessage(JSONCreator.generateJSON(RoundTrack.getInstance()),"RoundTrack");
            updateRoundTrack=false;
        }
   }

   private void notifyUsedToolCard(){
        if(notifyUsedToolCard){
            sendControlMessage("Tool card used correctly");
            notifyUsedToolCard=false;
        }
   }

   private void updateTokens(){
        if(updateTokens) {
            try {
                sendControlMessage("Update your tokens&" + table.getPlayerFromName(myPlayerName).getFavorTokens());
                updateTokens=false;
            }
            catch (InvalidUsernameException e){
                System.err.println("This is not the player you're looking for!");
            }
        }
   }

   private void updateToolCards(){
        if(updateToolCards) {
            sendControlMessage("Sending ToolCards&" + table.getDrawnToolCards().size());
            for (ToolCard toolCard : table.getDrawnToolCards()) {
                sendJSONmessage(JSONCreator.generateJSON(toolCard), "ToolCard");
            }
            updateToolCards=false;
        }
   }

}
