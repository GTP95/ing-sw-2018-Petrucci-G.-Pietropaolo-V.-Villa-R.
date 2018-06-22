package Progetto_Ing_Sw.com.client;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/*
* Questa classe non utilizza synchronyzed in quanto l'esecuzione in ordine dei metodi è implicita nel pattern observer e non utilizza le primitive wait() e
* notify a causa della IllegalMonitorStateException.
*/

public  class LocalModel {

    private static LocalModel ourInstance=new LocalModel();

    private ArrayList <ClientPlayer> clientPlayerArrayList;
    private MultiplayerGUI multiplayerGUIobserver;
    private ArrayList<ClientDice> drawnDice;
    private ArrayList<ClientToolCard> drawnToolCards;
    private ArrayList<ClientPublicObjectiveCard> drawnPublicObjectiveCards;
    private TableGUI tableGUIobserver;
    private ChooseAWindow chooseAWindowobserver;
    private ClientPrivateObjectiveCard privateObjectiveCard;
    private boolean gameRunning;
    private ArrayList<ClientGameBoardCard> drawnGameBoardCards;
    private ClientGameBoardCard choosenGameBoardCard;
    private ClientWindowBoard windowBoard;
    private int numOfDice, numOfToolCards, numOfPublicObjectiveCards, numOfGameBoardCards;
    private long countdownValue;
    public volatile boolean sendDataToServer, sendWindowBoard, immediatelyUpdateGUI;
    private ArrayBlockingQueue<Exception> exceptions;   //contiene le eccezioni lanciate dal server
    private Boolean usernameIsCorrect;
    private LoginStage loginStageObserver;
    /*sezione informazioni azioni*/
    private ClientDice diceToInsert;
    private int row,column;

    private LocalModel(){

        ourInstance=this;
        sendDataToServer=false;
        exceptions=new ArrayBlockingQueue<>(3); //La coda conterrà al massimo 3 elementi. Probabilmente sarebbe bastato 1, ma così si evitano errori se arriva un'altra eccezione prima che la GUI abbia consumato quella presente nella coda. Il numero 3 è basato sul tipico numero di azioni in un turno.
        usernameIsCorrect=null;
        windowBoard=null;
        immediatelyUpdateGUI=false;
    }

    public static LocalModel getInstance(){
        return ourInstance;
    }





    public synchronized ArrayList<ClientPlayer> getClientPlayerArrayList() {
        System.out.println("Getting clientPlayerArrayList");
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

    public ArrayList<ClientDice> getDrawnDice() {
        while(drawnDice==null);
        return drawnDice;
    }

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

    public int getCountdownValue() {
        return (int)countdownValue; //cast ad int per comodità della GUI
    }

    public ClientWindowBoard getWindowBoard() {
        return windowBoard;
    }

    public ArrayList<ClientPublicObjectiveCard> getDrawnPublicObjectiveCards() {    //provo a non sincronizzare perchè la sincronizzazione è implicita nell'observer
        return drawnPublicObjectiveCards;
    }

    public ClientDice getDiceToInsert() {
        return diceToInsert;
    }

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

    public ArrayList<ClientGameBoardCard> getDrawnGameBoardCards() {
        while(drawnGameBoardCards==null);   //aspetta che l'ArrayList venga creato
        while(drawnGameBoardCards.size()!=numOfGameBoardCards); //aspetta di ricevere tutte le GameBoardCards
        return drawnGameBoardCards;
    }

    public ClientPrivateObjectiveCard getPrivateObjectiveCard() {
        System.out.println("L'obbiettivo privato è: "+privateObjectiveCard.getColor());
        return privateObjectiveCard;
    }

    public ClientGameBoardCard getChoosenGameBoardCard() {
        return choosenGameBoardCard;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

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
    }

    public Boolean checkUsername(){ //ATTENZIONE: può ritornare null
        return usernameIsCorrect;
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

    public void setPrivateObjectiveCard(ClientPrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
        while (chooseAWindowobserver==null);
        chooseAWindowobserver.updateChooseAWindow();
    }

    public void requestAction(String description, Object... objects){   //Invocata dalla view per richiedere al server di eseguire azioni da parte del giocatore
        switch(description){
            case "ToolCard":

        }
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
        multiplayerGUIobserver.StartGame();
    }

    public void addDrawnDice(ClientDice dice){
        if(drawnDice==null) drawnDice=new ArrayList<ClientDice>();
        drawnDice.add(dice);
        if(immediatelyUpdateGUI) tableGUIobserver.updateDice();
    }

    public void addDrawnToolCard(ClientToolCard toolCard){
        if(drawnToolCards==null) drawnToolCards=new ArrayList<>();
        drawnToolCards.add(toolCard);
    }

    public void addDrawnGameBoardCard(ClientGameBoardCard gameBoardCard){
        if(drawnGameBoardCards==null) drawnGameBoardCards=new ArrayList<>();
        drawnGameBoardCards.add(gameBoardCard);
    }

    public void addDrawnPublicObjectiveCard(ClientPublicObjectiveCard publicObjectiveCard){
        if(drawnPublicObjectiveCards==null) drawnPublicObjectiveCards=new ArrayList<>();
        drawnPublicObjectiveCards.add(publicObjectiveCard);
        if(drawnPublicObjectiveCards.size()==numOfPublicObjectiveCards){
            System.err.print("ASPETTO CHE TABLEGUI SI REGISTRI COME OBSERVER");
            while(tableGUIobserver==null) {
                try {
                    Thread.sleep(100);  //In questo caso il while vuoto non funziona!
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tableGUIobserver.updateTable();
            System.err.println("NOTIFICA INVIATA A TABLEGUI");
        }
    }

    public void setNumOfDice(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    public void setNumOfToolCards(int numOfToolCards) {
        this.numOfToolCards = numOfToolCards;
    }

    public void setNumOfPublicObjectiveCards(int numOfPublicObjectiveCards) {
        this.numOfPublicObjectiveCards = numOfPublicObjectiveCards;
    }

    public void setNumOfGameBoardCards(int numOfGameBoardCards) {
        this.numOfGameBoardCards = numOfGameBoardCards;
    }

    public void setChoosenGameBoardCard(ClientGameBoardCard choosenGameBoardCard) {
        this.choosenGameBoardCard = choosenGameBoardCard;
        this.windowBoard=new ClientWindowBoard(choosenGameBoardCard);
        this.sendWindowBoard=true;
    }

    public void setCountdownValue(long countdownValue) {
        this.countdownValue = countdownValue;
        while(multiplayerGUIobserver==null);
        multiplayerGUIobserver.updateTimer();
    }

    public void insertDice(ClientDice dice, int row, int column){
      //  System.err.println("ATTENZIONE: AL MOMENTO IL METODO insertDice() NON È ANCORA COMPLETO!");
        this.diceToInsert=dice;
        this.row=row;
        this.column=column;
        sendDataToServer=true;

    }

    public Exception returnTrownException(){    //restituisce l'eccezione in testa alla coda
        return exceptions.poll();
    }

    public void addException(Exception e){  //aggiunge un'eccezione alla coda delle eccezioni lanciate dal server
        exceptions.add(e);
    }

    public boolean exceptionTrown(){    //ritorna true se è stata lanciata un'eccezione dal server, false altrimenti
        if(exceptions.size()==0) return false;
        return true;
    }

    public void setUsernameIsCorrect(Boolean usernameIsCorrect) {
        this.usernameIsCorrect = usernameIsCorrect;
        while(loginStageObserver==null);
        loginStageObserver.usernameCheck();
    }

    public void setWindowBoard(ClientWindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        while(tableGUIobserver==null);
        tableGUIobserver.insertion();   //notifica a tablegui dell'aggiornamento della windowboard
    }
    public void resetDiceArrayIfNecessary(){
        if(drawnDice==null) return;
        drawnDice.clear();
        immediatelyUpdateGUI=true;
    }
    public void notifyTurn(){
       // while (tableGUIobserver==null);
        tableGUIobserver.isYourTurn();
    }
}
