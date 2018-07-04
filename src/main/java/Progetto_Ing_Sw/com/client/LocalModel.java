package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.client.ClientToolCards.*;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Giacomo Tommaso Petrucci
 * This class is used to keep a copy of the status of the game synchronized with the server so the view can display the
 * current status and be separated from the model following the MVC pattern prescriptions.
 * NOTE:
* This class doens't use the synchronyzed keywork because the ordered execution of methods is omplicit in the observer
 *pattern and doesn't utilize the primitives wait() and notify() because of IllegalMonitorStateException.
*/

public  class LocalModel {

    private static final LocalModel ourInstance=new LocalModel();

    private ArrayList <ClientPlayer> clientPlayerArrayList;
    private MultiplayerGUI multiplayerGUIobserver;
    private ArrayList<ClientDice> drawnDice;
    private ArrayList<ClientToolCard> drawnToolCards;
    private ArrayList<ClientEffect> drawnToolCardsWithEffect;
    private ArrayList<ClientPublicObjectiveCard> drawnPublicObjectiveCards;
    private TableGUI tableGUIobserver;
    private ChooseAWindow chooseAWindowobserver;
    private RoundTrackView roundTrackViewobserver;
    private ToolCardDisplayer toolCardDisplayerObserver;
    private ClientPrivateObjectiveCard privateObjectiveCard;
    private boolean gameRunning;
    private ArrayList<ClientGameBoardCard> drawnGameBoardCards;
    private ClientGameBoardCard choosenGameBoardCard;
    private ClientWindowBoard windowBoard;
    private int numOfDice, numOfToolCards, numOfPublicObjectiveCards, numOfGameBoardCards, numOfWindowBoards, countdownValue,turnCountDownValue;
    public volatile boolean sendDataToServer, sendWindowBoard, immediatelyUpdateGUI, skipTurn, sendDiceToServer,useGrozingPliers, useGrindingStone, useFluxBrush, useGlazingHammers, useFluxRemover, sendFluxRemoverDiceWithSetValue, useEglomiseBrush, useCopperFoilBurnisher,useCorkBackedStraightEdge;
    private ArrayBlockingQueue<Exception> exceptions;   //contiene le eccezioni lanciate dal server
    private Boolean  firstWindowBoardsReceived, dontNotifyUsedToolCard;
    private LoginStage loginStageObserver;
    /*sezione informazioni azioni*/
    private ClientDice diceToInsert;
    private int row,column, roundNumber;
    private ArrayList<ClientWindowBoard> updatedWindowBoards;
    private String currentPlayerName;   //Stringa che,se non è il turno di questo giocatore, contiene il nome del giocatore che stà giocando il turno
    private ClientRoundTrack roundTrack;
    private int toolCardWithEffectIndex, oldRow, oldColumn, newRow, newColumn;

    private ClientDice diceToUseWithEffect;
    private String command;

    private LocalModel(){

        sendDataToServer=false;
        exceptions=new ArrayBlockingQueue<>(10); //TODO La coda conterrà al massimo 3 elementi. Probabilmente sarebbe bastato 1, ma così si evitano errori se arriva un'altra eccezione prima che la GUI abbia consumato quella presente nella coda. Il numero 3 è basato sul tipico numero di azioni in un turno.
        windowBoard=null;
        immediatelyUpdateGUI=false;
        currentPlayerName="NotAValidPlayerName";    //valore di default per comodità della GUI
        firstWindowBoardsReceived=true;
        sendDiceToServer=false;
        drawnToolCardsWithEffect=new ArrayList<>();
        useFluxBrush=false;
        useGlazingHammers=false;
        useFluxRemover=false;
        sendFluxRemoverDiceWithSetValue=false;
        dontNotifyUsedToolCard=false;
        useEglomiseBrush=false;
        useCorkBackedStraightEdge=false;
    }


    /**
     * Used to implement the singleton pattern, call this instead of a constructor
     * @return a reference to it's unique instance
     */
    public static LocalModel getInstance(){
        return ourInstance;
    }


    /**
     *
     * @return the ArrayList of players in the current game
     */
    public synchronized ArrayList<ClientPlayer> getClientPlayerArrayList() {
        System.out.println("Getting clientPlayerArrayList");
        return clientPlayerArrayList;
    }

    /**
     * Returns a reference to a player given it's name (username)
     * @param name  the name of the player
     * @return a reference to the specified player
     */
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

    /**
     * @return a reference to the ArrayList containing the dice aviable in the draftpool for the current round
     */
    public ArrayList<ClientDice> getDrawnDice() {
        while(drawnDice==null);
        return drawnDice;
    }

    /**
     *
     * @return a refernce to the avaiable tool cards of the current game
     */
    public ArrayList<ClientToolCard> getDrawnToolCards() {
        while(drawnToolCards==null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return drawnToolCards;
    }

    /**
     * Returns an int representing how many seconds the lobby will still be visible before the players will be assigned
     * their own private objective and will have to choose their own gameBoardCard
     * @return int seconds left to closing lobby
     */
    public int getCountdownValue() {
        return countdownValue; //cast ad int per comodità della GUI
    }

    /**
     * After the player has choosen a ClientGameBoarCard, this method returns a reference to the corresponding ClientWindowBoard
     * @see ClientGameBoardCard ClientWindowBoard
     * @return a reference to the choosen ClientWindowBoard
     */
    public ClientWindowBoard getWindowBoard() {
        return windowBoard;
    }

    /**
     * @see ClientPublicObjectiveCard
     * @return a reference to the arrayList containing the avaiable public objective cards
     */
    public ArrayList<ClientPublicObjectiveCard> getDrawnPublicObjectiveCards() {    //provo a non sincronizzare perchè la sincronizzazione è implicita nell'observer
        return drawnPublicObjectiveCards;
    }

    /**
     * After the player has clicked on the "Move" button of the TableGUI, the die choosen from the player is saved in
     * the attribute diceToInsert. This method returns a reference to the dice the player wants to insert in it's WindowBoard
     * @see TableGUI
     * @return a refernce to the dice the player wants to insert
     */
    public ClientDice getDiceToInsert() {   //Restituisce il dado di cui si richiede l'inserimento nella WindowBoard
        return diceToInsert;
    }

    /**
     * Adds the given ClientPlayer object to the ArrayList of players of the current game
     * @param clientPlayer
     */
    public void addPlayerToPlayerArrayList(ClientPlayer clientPlayer) {
        boolean addPlayer=true;
            if (clientPlayerArrayList == null) clientPlayerArrayList = new ArrayList<>();
            for(ClientPlayer clientPlayerInArray : clientPlayerArrayList){
                if(clientPlayerInArray.getName().equals(clientPlayer.getName())) addPlayer=false;
            }
        if (addPlayer) {
            clientPlayerArrayList.add(clientPlayer);
             System.out.println("PlayerArrayList:");
            for(ClientPlayer player : clientPlayerArrayList){
                System.out.println(player.getName());
            }
            multiplayerGUIobserver.update();
            System.out.println("Observer was just notified");

        }
    }

    /**
     * Returns the Game Board cards that are aviable to our player. If they weren't received by the server yet, it waits
     * until all of them are received and then returns the complete ArrayList
     * @return an ArrayList containing the Game Board cards avaiable to our player
     * @see ClientGameBoardCard
     */
    public ArrayList<ClientGameBoardCard> getDrawnGameBoardCards() {
        while(drawnGameBoardCards==null);   //aspetta che l'ArrayList venga creato
        while(drawnGameBoardCards.size()!=numOfGameBoardCards); //aspetta di ricevere tutte le GameBoardCards
        return drawnGameBoardCards;
    }

    /**
     * Returns a reference to the private objective assigned by the server to our player
     * @return the player's private objective
     */
    public ClientPrivateObjectiveCard getPrivateObjectiveCard() {
        System.out.println("L'obbiettivo privato è: "+privateObjectiveCard.getColor());
        return privateObjectiveCard;
    }

    /**
     * Returns the game board card choosen by the player
     * @see ClientGameBoardCard
     * @return our player's game board card
     */
    public ClientGameBoardCard getChoosenGameBoardCard() {
        return choosenGameBoardCard;
    }

    /**
     * Returns the row in which the player wishes to place a dice
     * @return int representing the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column in which the player wishes to place a dice
     * @return int representing the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the time left for the player to make a move
     * @return int representing the seconds left to move
     */
    public int getTurnCountDownValue() {
        return turnCountDownValue;
    }

    /**
     * Returns the name of the player who is playing the current turn
     * @return name as String of the player who is playing the current turn
     */
    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    /**
     * Returns the window boards that are updated after a move
     * @return all the player's window boards
     * @see ClientWindowBoard
     */
    public ArrayList<ClientWindowBoard> getUpdatedWindowBoards() {
        ArrayList<ClientWindowBoard> clientWindowBoardsToReturn=new ArrayList<>();
        for(ClientWindowBoard updatedWindowBoard : updatedWindowBoards)
            if(!windowBoard.equals(this.windowBoard))
                clientWindowBoardsToReturn.add(updatedWindowBoard);
        return updatedWindowBoards;
    }

    /**
     * When a round ends, the round track gets updated. This method returns the current state of the round track
     * @return the current state of the round track
     * @see ClientRoundTrack
     */
    public ClientRoundTrack getRoundTrack() {
        return roundTrack;
    }

    /**
     * Used to register elements of the GUI as observers of this object (LocalModel).
     * You can register the following types of object as observer: MultiplayerGUI, TableGUI, ChooseAWindow, LoginStage,
     * RoundTrackView, ToolCardDisplayer. For each type only one observer is allowed, if more than one object of the
     * same type tries to register as observer, only the last one is kept as an observer.
     * @param currentObject
     */
    public void registerAsObserver(Object currentObject){   //Serve per registrare come observer classi della view, l'utyilizzo di instanceof permette di avere un unico metodo per registrare tutte le classi necessarie.
            if(currentObject instanceof MultiplayerGUI) {
                this.multiplayerGUIobserver = (MultiplayerGUI)currentObject;
                System.out.println("MultiplayerGUI registrata come observer");
                return;
            }
            if (currentObject instanceof TableGUI){
                this.tableGUIobserver=(TableGUI)currentObject;
                System.out.println("TableGUI registrata come observer");
                return;
            }
            if(currentObject instanceof ChooseAWindow){
                this.chooseAWindowobserver=(ChooseAWindow)currentObject;
                return;
            }
            if(currentObject instanceof LoginStage){
                this.loginStageObserver=(LoginStage)currentObject;
            }
            if (currentObject instanceof RoundTrackView){
                this.roundTrackViewobserver=(RoundTrackView)currentObject;
            }
            if(currentObject instanceof ToolCardDisplayer){
                this.toolCardDisplayerObserver=(ToolCardDisplayer) currentObject;
            }
    }




    public void setDrawnDice(ArrayList<ClientDice> drawnDice) {
        this.drawnDice=drawnDice;

    }

    public void setDrawnToolCards(ArrayList<ClientToolCard> drawnToolCards) {
        this.drawnToolCards = drawnToolCards;

    }

    public void setDrawnPublicObjectiveCards(ArrayList<ClientPublicObjectiveCard> drawnPublicObjectiveCards) {  //Provo a non sincronizzare dal momentto che la sincronizzazione è implicita nnell'observer
        this.drawnPublicObjectiveCards = drawnPublicObjectiveCards;

    }

    public void setDrawnGameBoardCards(ArrayList<ClientGameBoardCard> drawnGameBoardCards) {
        this.drawnGameBoardCards = drawnGameBoardCards;

    }

    /**
     * Sets the private objective of the player
     * @param privateObjectiveCard
     */
    public void setPrivateObjectiveCard(ClientPrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
        while (chooseAWindowobserver==null);
        chooseAWindowobserver.updateChooseAWindow();
    }


    /**
     * Changes the current state to reflect the status of the game (if the match is currently in progress or not) and
     * notifies the GUI
     * @param gameRunning boolean representing the status of the match
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
        multiplayerGUIobserver.StartGame();
    }

    /**
     * Adds the given dice to the ArrayList of dice avaiable
     * @param dice dice to insert
     * @see ClientDice
     */
    public void addDrawnDice(ClientDice dice){
        if(drawnDice==null) drawnDice=new ArrayList<ClientDice>();
        drawnDice.add(dice);
        if(immediatelyUpdateGUI) tableGUIobserver.updateDice();
    }

    /**
     * Adds the given tool card to the ArrayList of tool cards avaiable
     * @param toolCard tool card to insert
     * @see ClientToolCard
     */
    public void addDrawnToolCard(ClientToolCard toolCard){
        if(drawnToolCards==null) drawnToolCards=new ArrayList<>();
        drawnToolCards.add(toolCard);
    }

    /**
     * Adds the given game board card to the ArrayList of drawn game board cards which is used by the player to choose
     * his/her own game board card
     * @param gameBoardCard game board card to insert
     */
    public void addDrawnGameBoardCard(ClientGameBoardCard gameBoardCard){
        if(drawnGameBoardCards==null) drawnGameBoardCards=new ArrayList<>();
        drawnGameBoardCards.add(gameBoardCard);
    }

    /**
     * Adds the given public objective card to the ArrayList of drawn public objective cards
     * @param publicObjectiveCard public objective card to insert
     */
    public void addDrawnPublicObjectiveCard(ClientPublicObjectiveCard publicObjectiveCard){
        if(drawnPublicObjectiveCards==null) drawnPublicObjectiveCards=new ArrayList<>();
        drawnPublicObjectiveCards.add(publicObjectiveCard);
    }

    /**
     * Sets the number of dice that the client should expect to receive from the server in the following messages
     * @see SocketClient
     * @see ClientDice
     * @param numOfDice number of dice being sent by the server
     */
    public void setNumOfDice(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    /**
     * Sets the number of tool cards that the client should expect to receive from the server in the following messages
     * @see SocketClient
     * @see ClientToolCard
     * @param numOfToolCards number of tool cards being sent by the server
     */
    public void setNumOfToolCards(int numOfToolCards) {
        this.numOfToolCards = numOfToolCards;
    }

    /**
     * Sets the number of public objective cards that the client should expect to receive from the server in the following messages
     * @see SocketClient
     * @see ClientPublicObjectiveCard
     * @param numOfPublicObjectiveCards number of public objective cards being sent by the server
     */
    public void setNumOfPublicObjectiveCards(int numOfPublicObjectiveCards) {
        this.numOfPublicObjectiveCards = numOfPublicObjectiveCards;
    }

    /**
     * Sets the number of game board cards that the client should expect to receive from the server in the following messages
     * @see SocketClient
     * @see ClientGameBoardCard
     * @param numOfGameBoardCards number of game board cards being sent by the server
     */
    public void setNumOfGameBoardCards(int numOfGameBoardCards) {
        this.numOfGameBoardCards = numOfGameBoardCards;
    }

    /**
     * Sets the number of window boards that the client should expect to receive from the server in the following messages
     * @see SocketClient
     * @see ClientWindowBoard
     * @param numOfWindowBoards number of window boards being sent by the server
     */
    public void setNumOfWindowBoards(int numOfWindowBoards) {
        this.numOfWindowBoards = numOfWindowBoards;
        System.err.println("numofwindowboards settaato a "+numOfWindowBoards);
    }

    /**
     * Sets the game board card choosen by the player and notifies SocketClient that it has to send it to the server
     * @param choosenGameBoardCard the game board card choosen by the player
     */
    public void setChoosenGameBoardCard(ClientGameBoardCard choosenGameBoardCard) {
        this.choosenGameBoardCard = choosenGameBoardCard;
        this.windowBoard=new ClientWindowBoard(choosenGameBoardCard);
        this.sendWindowBoard=true;
        this.sendDataToServer=true;
    }

    /**
     * Sets the number of seconds left for the player to play it's turn
     * @param countdownValue time in seconds left for the player to play it's turn
     */
    public void setCountdownValue(int countdownValue) {
        this.countdownValue = countdownValue;
        while(multiplayerGUIobserver==null);
        multiplayerGUIobserver.updateTimer();
    }

    /**
     * Used to place a dice in the window board. Notifies SocketClient that it has to tell the server the player's move
     * @param dice dice the player wishes to place
     * @param row row in which the player wishes to place the dice
     * @param column column in which the player wishes to place the dice
     */
    public void insertDice(ClientDice dice, int row, int column){
        this.diceToInsert=dice;
        this.row=row;
        this.column=column;
        sendDiceToServer=true;
        sendDataToServer=true;
    }

    /**
     * Since SocketClient runs inside a thread and the run method can't throw checked exceptions, all checked exceptions
     * are put in a queue and the GUI is notified, which in turn has to call this method to get the head of the queue.
     * The exception that is returned is automatically removed from the queue.
     * @return the head of the exception's queue
     */
    public Exception returnTrownException(){    //restituisce l'eccezione in testa alla coda
        return exceptions.poll();
    }

    /**
     * Adds a InvalidUserNameException or a TooManyPlayersException to the queue and notifies the GUI
     * @param e the exception to add to the exception's queue
     */
    public void addUsernameException(Exception e){
        exceptions.add(e);
        loginStageObserver.usernameException();
    }

    /**
     * Adds a IllegalDiceException or a PlaceDiceException to the queue and notifies the GUI
     * @param e the exception to add to the exception's queue
     */
    public void addDiceException(Exception e){  //aggiunge un'eccezione alla coda delle eccezioni lanciate dal server

        exceptions.add(e);
        tableGUIobserver.DiceExceptionThrower();
    }

    /**
     * Adds a NotEnoughFavorTokensException to the queue and notifies the GUI
     * @param e the exception to add to the exception's queue
     */
    public void addFavorTokensException(Exception e){
        exceptions.add(e);
        toolCardDisplayerObserver.toolCardExceptionCatcher();
    }

    /**
     * Sets the player's choosen window board and notifies the GUI
     * @param windowBoard the player's choosen window board
     */
    public void setWindowBoard(ClientWindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        while(tableGUIobserver==null);
        tableGUIobserver.insertion();   //notifica a tablegui dell'aggiornamento della windowboard
    }

    /**
     * Used to update the window boards. Adds the given window board to the window board's ArrayList in place of the old one
     * and notifies the GUI when it has finished adding all the window boards.
     * @param windowBoard window board to add to the ArrayList
     */
    public void addUpdatedWindowBoard(ClientWindowBoard windowBoard){
        if(updatedWindowBoards==null) updatedWindowBoards=new ArrayList<>();
        updatedWindowBoards.add(windowBoard);
        if (updatedWindowBoards.size()==numOfWindowBoards){
            System.err.println("Ricevute tutte le gameboardcard");
            for(int index=0;index<clientPlayerArrayList.size();index++) clientPlayerArrayList.get(index).updateWindowBoard(updatedWindowBoards.get(index));
            if(firstWindowBoardsReceived){
//                generateToolcardsWithEffects();   //TODO: decommentare!
                getPlayerFromName(ClientSettings.getInstance().getUsername()).setFavorTokens(updatedWindowBoards.get(clientPlayerArrayList.indexOf(getPlayerFromName(ClientSettings.getInstance().getUsername()))).getDifficulty());
                System.err.println("Sto per chiamare starttable");
                chooseAWindowobserver.StartTable();
               // System.err.println("Aspetto tablegui (addUpdatedWindowBoard)");
               // while (tableGUIobserver==null); //aspetta che tableGui si registri come observer
               // System.err.println("tablegui registata (addUpadtedWindowBoard)");
               // tableGUIobserver.updateTable();
                firstWindowBoardsReceived=false;
            }

           else tableGUIobserver.updateOtherPlayersBoards();
        }
    }


    /**
     * Used to update the ArrayList containig the avaible dice. It clears the ArrayList if it already contains dice to
     *
     */
    public void resetDiceArrayIfNecessary(){
        if(drawnDice==null) return;
        drawnDice.clear();
        immediatelyUpdateGUI=true;
    }

    public void notifyTurn(){
        while (tableGUIobserver==null) {
            try {
                Thread.sleep(1000);
                System.err.println("time elapsed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      //  chooseAWindowobserver.StartTable();

        tableGUIobserver.isYourTurn();
    }

    public void setTurnCountDownValue(int turnCountDownValue) {
        if(turnCountDownValue==-1){         //-1 significa che il turno è finito
            this.turnCountDownValue=-1;
            tableGUIobserver.isNotYourTurn();
            return;
        }
        this.turnCountDownValue = turnCountDownValue;
        tableGUIobserver.updateTimer();
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
        tableGUIobserver.isNotYourTurn();   //notifica la GUI
    }

    public void skipTurn(){
        skipTurn=true;
        sendDataToServer=true;
    }

    public void updateRound(int roundNumber){
        this.roundNumber=roundNumber;
        tableGUIobserver.updateRound(); //qui table si è sicuramente già registrato come observer, nessun bisogno di aspettare
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundTrack(ClientRoundTrack roundTrack) {
        this.roundTrack = roundTrack;
        System.err.println("Aspetto che la roundtrack si registri come observer");
        while(roundTrackViewobserver==null);    //aspetto che si registri come observer
        roundTrackViewobserver.updateRoundTrack();
        System.err.println("roundtrackobserver notificato");
    }
    

    public void notifyUsernameIsCorrect(){
        loginStageObserver.startLobby();
    }

    public void setToolCardWithEffectIndex(int toolCardWithEffectIndex) {
        this.toolCardWithEffectIndex = toolCardWithEffectIndex;
    }

    public ArrayList<ClientEffect> getDrawnToolCardsWithEffect() {
        return drawnToolCardsWithEffect;
    }

    public void useGrozingPliers(ClientDice diceToModify, String command){
        diceToUseWithEffect=diceToModify;
        this.command=command;
        useGrozingPliers=true;
        sendDataToServer=true;
    }

    public ClientDice getDiceToUseWithEffect() {
        return diceToUseWithEffect;
    }

    public void setDiceToUseWithEffect(ClientDice diceToUseWithEffect) {
        this.diceToUseWithEffect = diceToUseWithEffect;
    }

    public String getCommand() {
        return command;
    }

    public void useGrindingStone(ClientDice dice){
        diceToUseWithEffect=dice;
        useGrindingStone=true;
        sendDataToServer=true;
    }
    public void useFluxBrush(ClientDice dice){
        diceToUseWithEffect=dice;
        useFluxBrush=true;
        sendDataToServer=true;
    }

    public void useGlazingHammers(){
        useGlazingHammers=true;
        sendDataToServer=true;
    }

    public void useFluxRemover(ClientDice diceToRemove){
        diceToUseWithEffect=diceToRemove;
        useFluxRemover=true;
        sendDataToServer=true;
        dontNotifyUsedToolCard=true;
    }

    public void notifyFluxRemoverDiceValueSet(){
        sendFluxRemoverDiceWithSetValue=true;
        sendDataToServer=true;
       // dontNotifyUsedToolCard=true;
    }

    public void setFluxRemoverNewlyDrawnDice(ClientDice newDrawnDice){
        diceToUseWithEffect=newDrawnDice;
        toolCardDisplayerObserver.fluxRemoverDie(); //notifica GUI
    }

    public void useEglomiseBrush(int oldRow, int oldColumn, int newRow, int newColumn){
        this.oldRow=oldRow;
        this.oldColumn=oldColumn;
        this.newRow=newRow;
        this.newColumn=newColumn;
        useEglomiseBrush=true;
        sendDataToServer=true;
    }

    public void useCopperFoilBurnisher(int oldRow, int oldColumn, int newRow, int newColumn){
        this.oldRow=oldRow;
        this.oldColumn=oldColumn;
        this.newRow=newRow;
        this.newColumn=newColumn;
        useCopperFoilBurnisher=true;
        sendDataToServer=true;
    }

    public void useCorkBackedStraightEdge(ClientDice dice, int row, int column){
        this.newRow=row;
        this.newColumn=column;
        diceToUseWithEffect=dice;
        useCorkBackedStraightEdge=true;
        sendDataToServer=true;
    }

    public String getCoordinatesAsString(){
        String message=oldRow+"&"+oldColumn+"&"+newRow+"&"+newColumn;
        return message;
    }

    public void notifyUsedToolCard(){   //notifica GUI
        if (!dontNotifyUsedToolCard){
            System.err.println("chiamo closeToolCardMenu() e disableToolCards()");
            toolCardDisplayerObserver.closeToolCardMenu();
            tableGUIobserver.disableToolCards();
        }
        dontNotifyUsedToolCard=false;
    }
    public void updateTokens(int numOfTokens){
        getPlayerFromName(ClientSettings.getInstance().getUsername()).setFavorTokens(numOfTokens);
        tableGUIobserver.updateTokens();
    }

    public void resetToolCardArrayIfNecessary(){
        if(drawnToolCards==null) return;
        drawnToolCards.clear();
        immediatelyUpdateGUI=true;
    }

    public int getNewRow() {
        return newRow;
    }

    public int getNewColumn() {
        return newColumn;
    }
}
