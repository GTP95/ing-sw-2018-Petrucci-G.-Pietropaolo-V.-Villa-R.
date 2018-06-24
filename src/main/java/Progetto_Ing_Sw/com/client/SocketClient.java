package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable{
    private Socket socket;  //TODO: rendere final se decido di non interrompere la connessione in caso di username non valido, lasciare così altrimenti
    private String username;
    PrintWriter out;
    BufferedReader in;
    LocalModel localModel;
    Exception trownException;

    public SocketClient() throws UnknownHostException, ConnectException {
        username=null;
        localModel =LocalModel.getInstance();
        String host= ClientSettings.getInstance().getHostname();
        int port= ClientSettings.getInstance().getSocketPort();
        try{
            socket=new Socket(host, port);
            System.out.println("Connected to "+host+":"+port);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e){
            throw e;    // dato che IOException è più generale della UnknownHostException, è necessario catturare quest'ultima separatamente e rilanciarla per farla ricevere al chiamante
        }

        catch (ConnectException e){
            throw e;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String serverResponse="";
        while (username==null || username.equals("")) {    //In questo caso essendo il metodo getUsername synchronized, non solo non è necessario utilizzare wait() all'interno del while, ma addirittura causerebbe una IllegalMonitorStateException
            username= ClientSettings.getInstance().getUsername();
        }
        out.println(username);
        System.out.println("Inviato "+username+" come username");
        while (true) {      //TODO:ricevere notifica chiusura GUI ed usarla come condizione
            try {
                receiveMessage();
                tryToSendMessage();
            } catch (TooManyPlayersException | Progetto_Ing_Sw.com.client.InvalidUsernameException e) {
                localModel.addException(e);
            }
            catch (IllegalDiceException e){
                localModel.addException(e);
            }
        }
    }

    private void receiveMessage() throws TooManyPlayersException, Progetto_Ing_Sw.com.client.InvalidUsernameException, IllegalDiceException {
        try {
            if(!in.ready()) return;
            String message = in.readLine();
            System.out.println("Message received: "+message);
            String messageFields[]=message.split("%");  //Salva nell'array i campi del messaggio separati da %
            String messageType=messageFields[0];    //il primo campo del messaggio contiene il tipo del messaggio
            switch (messageType){
                case "Control":
                    String messageContent=messageFields[1];    //Nel caso di messaggi di controllo il messaggio è contenuto nel secondo campo della stringa
                    handleControlMessage(messageContent);
                    break;
                case "JSON":
                    String json=messageFields[1];       //nel caso dell'invio di JSON il JSON è contenuto nel secondo campo della stringa
                    String nameOfClass=messageFields[2];    //mentre il terzo campo contiene il nome della classe
                    handleJSONmessage(json, nameOfClass);
                    break;
                case "Action":
                    String actionDescription=messageFields[1];  //La descrizione dell'azione dovrà poi essere ulteriormente "parsata" da un apposito metodo
                    break;
                case "Player":
                    String playerName=messageFields[1];
                    handlePlayerMessage(playerName);
                    break;
                default: System.err.println("Unable to understand the following message received from server: "+message);
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }

    private void handleControlMessage(String messageContent) throws TooManyPlayersException, Progetto_Ing_Sw.com.client.InvalidUsernameException, IllegalDiceException {
        String messageFields[]=messageContent.split("&");
        switch (messageFields[0]) {
            case "Connected":
                localModel.setUsernameIsCorrect(true);
                System.out.println("Connected");
                break;
            case "Username already in use":
                localModel.setUsernameIsCorrect(false);
                throw new InvalidUsernameException("Username already in use");  //TODO: GUI il metodo getMessage() restituisce il motivo dell'eccezione
            case "Max number of players exceeded":
                throw new TooManyPlayersException();    //TODO: GUI
                //Non solo non serve mettere un break ma viene addirittura segnalato come errore perchè viene lanciata un'eccezione dunque il break non verrebbe mai eseguito
            case "Invalid username: username cannot be null":
                localModel.setUsernameIsCorrect(false);
                throw new InvalidUsernameException("Invalid username: username cannot be null");    //TODO: GUI il metodo getMessage() restituisce il motivo dell'eccezione
            case "Invalid username: empty username not allowed":
                localModel.setUsernameIsCorrect(false);
                throw new InvalidUsernameException("Invalid username: empty username not allowed"); //TODO: GUI il metodo getMessage() restituisce il motivo dell'eccezione
            case "Game started!":
                localModel.setGameRunning(true);
                break;
            case "Sending Dice":
                localModel.setNumOfDice(Integer.parseInt(messageFields[1]));
                localModel.resetDiceArrayIfNecessary();
                break;
            case "Sending ToolCards":
                localModel.setNumOfToolCards(Integer.parseInt(messageFields[1]));
                break;
            case "Sending publicObjectiveCards":
                localModel.setNumOfPublicObjectiveCards(Integer.parseInt(messageFields[1]));
                break;
            case "Sending GameBoardcards":
                localModel.setNumOfGameBoardCards(Integer.parseInt(messageFields[1]));
                break;
            case "CountdownValue":
                localModel.setCountdownValue(Long.parseLong(messageFields[1]));
                break;
            case "Your turn will end in":
                localModel.setTurnCountDownValue(Long.parseLong(messageFields[1]));
            case "Dice placed successfully":
                System.out.println("Dice placed successfully");
                break;
            case "Selected dice doesn't exists!":
                throw new IllegalDiceException("Selected dice doesn't exists!");    //TODO: GUI il metodo getMessage() restituisce il motivo dell'eccezione
            case "It's your turn now":
                localModel.notifyTurn();
                break;
            case "Sending WindowBoards update":
                localModel.setNumOfWindowBoards(Integer.parseInt(messageFields[1]));
                break;
            default: System.err.println("can't understand the following control message: "+messageContent);
        }
        if(messageContent.startsWith("Invalid username: ")) throw new Progetto_Ing_Sw.com.client.InvalidUsernameException(messageContent.substring(18));
    }

    private void handleJSONmessage(String json, String nameOfClass){
        System.out.println("handling JSON message");
        switch (nameOfClass){
            case "arrayListOfDice":
                localModel.setDrawnDice(JSONCreator.diceArrayListLoaderFromString(json));
                break;
            case "arrayListOfPublicObjectiveCards":
                localModel.setDrawnPublicObjectiveCards(JSONCreator.publicObjectiveCardArrayListLoaderFromString(json));
                break;
            case "arrayListOfToolCards":
                localModel.setDrawnToolCards(JSONCreator.toolCardArrayListloaderFromString(json));
                break;
            case "arrayListOfGameBoardCards":
                localModel.setDrawnGameBoardCards(JSONCreator.gameBoardCardArrayListLoaderFromString(json));
                break;
            case "PrivateObjectiveCard":
                localModel.setPrivateObjectiveCard(JSONCreator.clientPrivateObjectiveCardLoaderFromString(json));
                System.out.println("OBBIETTIVO PRIVATO RICEVUTO");
                sendControlMessage("Data received, go ahead");
                break;
            case "Dice":
                localModel.addDrawnDice(JSONCreator.clientDiceLoaderFromString(json));
                break;
            case "ToolCard":
                localModel.addDrawnToolCard(JSONCreator.clientToolCardLoaderFromString(json));
                break;
            case "PublicObjectiveCard":
                localModel.addDrawnPublicObjectiveCard(JSONCreator.clientPublicObjectiveCardLoaderFromString(json));
            /*    while(localModel.getChoosenGameBoardCard()==null);  //aspeta che il giocatore abbia scelto la GameBoardCard
                sendJSONmessage(JSONCreator.generateJSON(localModel.getChoosenGameBoardCard()),"GameBoardCard");*/
                break;
            case "GameBoardCard":
                localModel.addDrawnGameBoardCard(JSONCreator.clientGameBoardCardLoaderFromString(json));
                break;
            case "WindowBoard":
                localModel.setWindowBoard(JSONCreator.clientWindowBoardLoaderFromString(json));
                break;
            case "WindowBoardUpdate":
                localModel.addUpdatedWindowBoard(JSONCreator.clientWindowBoardLoaderFromString(json));
                break;
            default:
                System.err.println("Can't understand class " + nameOfClass);
        }
    }

    private void handlePlayerMessage(String playerName){
            System.out.println("Handling player message");
            localModel.addPlayerToPlayerArrayList(new ClientPlayer(playerName));
    }

    private  void handleActionMessage(String actionDescription){
        String actionFields[]=actionDescription.split("&");
        String username=actionFields[1];
        switch (actionFields[0]){
            case "UseToolCard":
                String title=actionFields[2];   //Title è il nome della carta (titolo), non ricevo un JSON perchè è già stato inviato in una fase precedente
                break;
            case "PlaceDice":
                String json=actionFields[2];
                ClientWindowBoard windowBoard=JSONCreator.clientWindowBoardLoaderFromString(json);
                localModel.getPlayerFromName(username).updateWindowBoard(windowBoard);
                break;
            default: System.err.println("Can't understand the following action message sent from the server: "+actionDescription);
        }
    }


    public void sendMessage(){

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

    private void sendActionMessage(String json, String actionDescription){
        String messageToSend="Action%"+json+"&"+actionDescription;
        out.println(messageToSend);
    }

    private void tryToSendMessage(){    //Controlla se LocalModel ha bisogno di inviare dati al server e nel caso li invia, altrimenti prosegue senza far nulla
        if(localModel.sendDataToServer){
            if(localModel.sendWindowBoard=true){
                sendControlMessage("Choosen GameBoardCard's name:%"+localModel.getChoosenGameBoardCard().getTitle());
                System.err.println("Inviata GameBoardCard");
                localModel.sendWindowBoard=false;
            }
            ClientDice diceTosend=localModel.getDiceToInsert();
            System.err.println("Controllo se devo inviare un dado");
            if(diceTosend!=null){
                System.err.println("Invio il dado");
                sendPlaceDiceActionMessage(JSONCreator.generateJSON(localModel.getDiceToInsert()), localModel.getRow(),localModel.getColumn());
                System.err.println("Dado inviato");
            }

            localModel.sendDataToServer=false;
        }
    }

    private void sendPlaceDiceActionMessage(String json, int row, int column){
        String actionDescription="Place dice&"+row+"&"+column;
        sendActionMessage(json, actionDescription);
        localModel.sendDataToServer=false;
    }
}
