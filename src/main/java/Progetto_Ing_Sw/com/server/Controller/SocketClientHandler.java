package Progetto_Ing_Sw.com.server.Controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import Progetto_Ing_Sw.com.server.Controller.Lobby;
import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

public class SocketClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static int timeout;
    public final Thread ourThread;
    private Table table;
    private ArrayList<Player> currentPlayerArrayList, previousPlayerArrayList;
    private String myPlayerName;

    public SocketClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        ourThread=Thread.currentThread();

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            timeout=JSONCreator.parseIntFieldFromFile("src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json","timeout");
            clientSocket.setSoTimeout(timeout); //timeout inattività giocatore impostato direttamante sulla socket del giocatore
        }
        catch (FileNotFoundException e){
            System.out.println("File ServerSettings not found, falling back to 30 seconds of timeout");
            timeout=30000;  //timeout in millisecopndi
        }
        catch (IOException e){
            e.printStackTrace();    //TODO: timeout?
        }
    }

    public void run(){

            try {
                    myPlayerName=in.readLine();
                    Lobby.getInstance().addPlayer(myPlayerName, this);
                    sendControlMessage("Connected");
                        while(Lobby.isRunning) {
                            currentPlayerArrayList=Lobby.getInstance().getConnctedPlayers();
                            if(!currentPlayerArrayList.equals(previousPlayerArrayList)){    //Se i due array non sono uguali si è connesso un nuovo giocatore ed è necessario aggiornare il client. Purtroppo non c'è stato verso di far funzionare l'observer e gli interrupt "spammano" l'elenco dei giocatori...
                                sendPlayerMessage();    //Invia l'elenco aggiornato dei giocatori
                                previousPlayerArrayList=currentPlayerArrayList; //aggiornamento dello stato
                            }
                        }
                        //arrivati qui il gioco è cominciato
                sendPlayerMessage();
                sendControlMessage("Game started!");
                this.table=Table.getOurInstance();  //La lobby è terminata, è tempo di lavorare sul tavolo
                sendGameInitializationData();

                while(Table.gameRunning){

                 }

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
           /*catch (InterruptedException e) {
                sendPlayerMessage();
            }*/

    }


    @Deprecated
    private void sendCard(Card card){
        String json=JSONCreator.generateJSON(card);
        out.println(json);
    }

    private void sendControlMessage(String message){    //Nei messaggi uso % come separatore dei campi per semplificare il parsing in ricezione ed evitare confilitti con il formato JSON
        String messageToSend="Control%"+message;
        out.println(messageToSend);
    }

    private void sendJSONmessage(String json, String nameOfClass){
        String messageToSend="JSON%"+json+"%"+nameOfClass;
        out.println(messageToSend);
        System.out.println("JSON message sent");
    }

    private void sendActionMessage(String actionDescription){   //TODO: stabilire formato actionDescription
        String messageToSend="Action%"+actionDescription;
        out.println(messageToSend);
    }

    private void sendPlayerMessage() {
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
     /*   sendJSONmessage(JSONCreator.generateJSON(table.getDrawnDice()),"arrayListOfDice"); //invia ArrayList dei dadi pescati
        sendJSONmessage(JSONCreator.generateJSON(table.getDrawnToolCards()),"arrayListOfToolCards");
        sendJSONmessage(JSONCreator.generateJSON(table.getDrawnPublicObjectiveCards()),"arrayListOfPublicObjectiveCards"); */



        try {
        /*    sendJSONmessage(JSONCreator.generateJSON(table.getPlayerFromName(myPlayerName).getDrawnGameBoardCard()), "arrayListOfGameBoardCards");
            sendJSONmessage(JSONCreator.generateJSON(table.getPlayerFromName(myPlayerName).getPrivateObjective()),"privateObjectiveCard");*/
            ArrayList<GameBoardCard> drawnGameBoardCard=null;
            while(drawnGameBoardCard==null){
                drawnGameBoardCard=table.getPlayerFromName(myPlayerName).getDrawnGameBoardCard();
            }
            sendControlMessage("Sending GameBoardcards&"+table.getPlayerFromName(myPlayerName).getDrawnGameBoardCard().size());
            for(GameBoardCard gameBoardCard : table.getPlayerFromName(myPlayerName).getDrawnGameBoardCard()){
                sendJSONmessage(JSONCreator.generateJSON(gameBoardCard),"GameBoardCard");
            }
            sendJSONmessage(JSONCreator.generateJSON(table.getPlayerFromName(myPlayerName).getPrivateObjective()),"PrivateObjectiveCard");
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

    private void receiveControlMessage(){
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
}
