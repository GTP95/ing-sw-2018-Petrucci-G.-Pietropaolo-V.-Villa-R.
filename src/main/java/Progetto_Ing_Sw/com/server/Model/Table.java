package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Controller.Lobby;
import Progetto_Ing_Sw.com.server.Controller.SocketClientHandler;
import Progetto_Ing_Sw.com.server.Model.ToolCards.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SplittableRandom;
import java.util.concurrent.CopyOnWriteArrayList;

public class Table {

    private ArrayList<PublicObjectiveCard> drawnPublicObjectiveCards;
    private ArrayList<ToolCard> drawnToolCards;
    private static ArrayList<Effect> toolCardsWithEffect=new ArrayList<>();
    private static ToolCardDeck toolCardDeck=new ToolCardDeck(new File("Resources/Cards/ToolCards"));
    private static PublicObjectiveCardDeck publicObjectiveCardDeck=new PublicObjectiveCardDeck(new File("Resources/Cards/PublicObjectiveCards"));
    private static PrivateObjectiveCardDeck privateObjectiveCardDeck=PrivateObjectiveCardDeck.getInstance();    //Il caricamento da file viene effettuato all'interno della classe stessa
    private static GameBoardCardDeck gameBoardCardDeck=new GameBoardCardDeck(new File("Resources/Cards/GameBoardCards"));
    private ArrayList<Dice> drawnDice;
    private static final DiceBag diceBag=new DiceBag();
    private static final Table ourInstance=new Table();
    private static CopyOnWriteArrayList<Player> players;
    private int currentPlayer;//indice del giocatore che sta giocando
    public static volatile boolean gameRunning=false;   //è volatile per via dell'accesso concorrente da parte di più thread che potrebberio leggerne il valore proprio mentre sta cambiando
    private int numOfSetWindowBoards;
    private CopyOnWriteArrayList<Player> mirrorArray;


    private Table() {
        int numPlayers = Lobby.getInstance().getNumOfPlayers();
        drawnDice = diceBag.diceDraw(2 * numPlayers + 1);
        drawnPublicObjectiveCards = publicObjectiveCardDeck.drawPublicObjectiveCards(3);
        drawnToolCards = toolCardDeck.drawToolCards(3);

        for (ToolCard toolCard : drawnToolCards){
            switch(toolCard.getTitle()){
                case "Grozing Pliers":
                    toolCardsWithEffect.add(new GrozingPliers(toolCard));
                    System.out.println("Creata Grozing Pliers");
                    break;
                case "Copper Foil Burnisher":
                    toolCardsWithEffect.add(new CopperFoilBurnisher(toolCard));
                    break;
                case "Cork-backed Straightedge":
                    toolCardsWithEffect.add(new CorkBackedStraightedge(toolCard));
                    break;
                case "Eglomise Brush":
                    toolCardsWithEffect.add(new EglomiseBrush(toolCard));
                    break;
                case "Flux Brusher":
                    toolCardsWithEffect.add(new FluxBrush(toolCard));
                    break;
                case "Flux Remover":
                    toolCardsWithEffect.add(new FluxRemover(toolCard));
                    break;
                case "Glazing Hammer":
                    toolCardsWithEffect.add(new GlazingHammer(toolCard));
                    break;
                case "Grinding Stone":
                    toolCardsWithEffect.add(new GrindingStone(toolCard));
                    break;
                case "Lathekin":
                    toolCardsWithEffect.add(new Lathekin(toolCard));
                    break;
                case "Lens Cutter":
                    toolCardsWithEffect.add(new LensCutter(toolCard));
                    break;
                case "Running Pliers":
                    toolCardsWithEffect.add(new RunningPliers(toolCard));
                    break;
                case "Tap Wheel":
                    toolCardsWithEffect.add(new TapWheel(toolCard));
                    break;
                default:
                    System.err.println("Can't decorate the following toolcard: "+toolCard.getTitle());
            }
        }

        players = Lobby.getInstance().getConnctedPlayers();
        numOfSetWindowBoards = 0;
    }

    public static CopyOnWriteArrayList<Player> getPlayers() {
        /*ArrayList<Player> playersToReturn;
        playersToReturn=(ArrayList<Player>) players.clone();*/
        return players;
    }

    public Player getPlayerFromName(String name) throws InvalidUsernameException {
        for(Player player : players){
            if(player.getName().equals(name)){
                return player;
            }
        }
        throw new InvalidUsernameException("The player you're looking for doesn't exists!");
    }

    public Player getActivePlayer(){
       /* ArrayList<Player> clonePlayers=getPlayers();
        Player selectedPlayer = clonePlayers.get(currentPlayer);
        return selectedPlayer;*/
        return mirrorArray.get(currentPlayer);
    }

    public static Table getOurInstance(){
        return ourInstance;
    }

    public ArrayList<PublicObjectiveCard> getDrawnPublicObjectiveCards() {  //restituisce una copia dell'ArrayList per evitare modifiche all'esterno della classe
        ArrayList<PublicObjectiveCard> cloneArrayList=new ArrayList<>();

        for(PublicObjectiveCard card : drawnPublicObjectiveCards){
            cloneArrayList.add(card);
        }

        return cloneArrayList;
    }

    public ArrayList<ToolCard> getDrawnToolCards() {    //restituisce una copia dell'ArrayList per evitare modifiche all'esterno della classe
        ArrayList<ToolCard> cloneArrayList=new ArrayList<>();

        for(ToolCard card : drawnToolCards){
            cloneArrayList.add(card);
        }

        return cloneArrayList;
    }
    
    public ArrayList<Dice> getDrawnDice(){
    	ArrayList<Dice> clone=new ArrayList();
    	for(Dice dice : drawnDice) clone.add(dice);
    	return clone;
    }

    public Boolean removeDice(Dice dice){
        if(drawnDice.contains(dice)){
            int index=drawnDice.indexOf(dice);
            drawnDice.get(index).setValue(0);
            SocketClientHandler socketClientHandler;
            for(Player player:players){
                socketClientHandler=player.getSocketClientHandler();
                socketClientHandler.updateDice=true;
                socketClientHandler.ourThread.interrupt();   //invia notifica ai thread dei SocketClientHandler
            }
            return true;
        }
        return false;
    }

    public Dice getChoosenDice(Dice dice) throws IllegalDiceException{      //Estrae il dado specificato prendendolo tra quelli disponibili. Se il dado indicato non è disponibile lancia eccezione
        for(Dice die : drawnDice){
            if(die.equals(dice)){
                drawnDice.remove(die);
                return die;
            }
        }
        throw new IllegalDiceException();
    }

    public void returnDice(Dice dice){  //"Restituisce" il dado, nel senso che viene riposto nuovamente sul tavolo di gioco a disposizione dei giocatori
        Dice cloneDice=new Dice(dice.getValue(),dice.getColor());   //fa una copia del dado per evitare modifiche del dado al di fuori di questa classe
        drawnDice.add(cloneDice);
    }

    public boolean diceExists(Dice dice){
        return drawnDice.contains(dice);
    }



    public void startGame(){
        gameRunning=true;
       // players=Lobby.getInstance().getConnctedPlayers();
   //  Collections.shuffle(players);  //Ordine casuale dei giocatori per il primo turno.
        currentPlayer=0;
        buildMirrorArray();
        for(Player player : players){   //inizializza i giocatori assegnadoli il loro obbiettivo privato e le GmaeBoardCard tra cui scegliere
            player.setPrivateObjective(privateObjectiveCardDeck.draw());
            player.setDrawnGameBoardCard(gameBoardCardDeck.drawMultipleFrontRear(3));
        }
        System.out.println("Game started!");
        System.out.println("Connected players:");
        for(Player player : players){
            System.out.println(player.getName());
        }
        System.out.println();
        System.out.println("Cards:");
        for (PublicObjectiveCard card : drawnPublicObjectiveCards){
            System.out.println(card.getTitle());
        }
        System.out.println();
        System.out.println("ToolCards:");
        for (ToolCard card : drawnToolCards){
            System.out.println(card.getTitle());
        }
        System.out.println();
        System.out.println("Dice:");
        for(Dice dice : drawnDice){
            System.out.println("Color: "+dice.getColor()+" value: "+dice.getValue());
        }
    }

    public void addDiceFluxBrush(Dice diceRejectedByInsert){
        Dice cloneDice=new Dice(diceRejectedByInsert.getValue(),diceRejectedByInsert.getColor());
        drawnDice.add(cloneDice);
    }

    private CopyOnWriteArrayList<Player> shufflePlayerArray(){ //inverte l'array dei giocatori
        CopyOnWriteArrayList<Player>arrayToReturn=new CopyOnWriteArrayList<>();
        for(int index=players.size()-1;index>=0;index--){
            arrayToReturn.add(players.get(index));
        }
        return arrayToReturn;
    }

    public void changeCurrentPlayer() {//Imposta il valore currentplayer all'indice dell'arraylist che contiene il giocatore del turno che sta per cominciare
        if(currentPlayer<2*players.size()-1) currentPlayer++;
        else prepareForNextRound();
        System.out.println("indexOfCurrentPlayer: "+currentPlayer);
        for(Player player:players){
            player.getSocketClientHandler().changedTurn=true;
        }
        notifyAllSocketClientHandlers();
    }


    public void notifyWindowBoardChange(Thread notifierThread){
        for(Player player : players){
            if(player.getSocketClientHandler().ourThread!=notifierThread){          //in questo caso posso limitarmi a confrontare i riferimenti anzichè usare la equals()
                player.getSocketClientHandler().updateWindowBoards=true;
                player.getSocketClientHandler().ourThread.interrupt();
            }
        }
    }

    public void setChoosenGameBoardCard (String playerName, String gameBoardCardTitle) {
        try {
            getPlayerFromName(playerName).setChoosenGameBoard(getPlayerFromName(playerName).getGameBoardCardFromTitle(gameBoardCardTitle));
            numOfSetWindowBoards++;
            if (numOfSetWindowBoards == players.size()){
                for(Player player:players) player.getSocketClientHandler().updateWindowBoards=true;
                notifyAllSocketClientHandlers();
            }
        }
        catch(InvalidUsernameException e){
            System.err.println(e.getMessage());
        }
    }

    private void notifyAllSocketClientHandlers(){
        for (Player player : players){
            player.getSocketClientHandler().ourThread.interrupt();
        }
    }

private void prepareForNextRound() {     //Cambia l'ordine di gioco dei giocatori al cambio di round
    CopyOnWriteArrayList<Player> newPlayerArray = new CopyOnWriteArrayList<>();
    for (int index = 1; index < players.size(); index++) {
        newPlayerArray.add(index - 1, players.get(index));
    }
    newPlayerArray.add(players.get(0));
    players = newPlayerArray;
    buildMirrorArray();
    currentPlayer = 0;
    for (Dice dice : drawnDice)
        RoundTrack.getInstance().addRemainedDice(RoundTrack.getInstance().getRoundNumber(), dice);   //aggiunge i dadi avanzati alla roundtrack
    RoundTrack.getInstance().incrementRound();
    drawnDice=diceBag.diceDraw(2*players.size()+1);
    for (Player player : players) {
        player.getSocketClientHandler().changedRound = true;  //non c'è bisogno di chiamare la notifyAllSocketClientHandlers perchè viene chiamata dopo alla fine della changeCurrentPlayer()
        player.getSocketClientHandler().updateRoundTrack=true;
        player.getSocketClientHandler().updateDice=true;
    }
}

private void buildMirrorArray(){
        CopyOnWriteArrayList<Player> mirrorArray=new CopyOnWriteArrayList<>();
        int index1,index2;
        for(index1=0;index1<players.size();index1++) mirrorArray.add(players.get(index1));

        for(index2=players.size()-1;index2>=0;index2--) mirrorArray.add(players.get(index2));
        this.mirrorArray=mirrorArray;

}

    public CopyOnWriteArrayList<Player> getMirrorArray() {
        return mirrorArray;
    }

    public static void setPlayers(CopyOnWriteArrayList<Player> players) {   //Usato solo per i test
        Table.players = players;
    }



    public Effect getToolCardWithEffectFromName(String title){
        Effect toolCardwithEffect=null;
        for(Effect card : toolCardsWithEffect){
            if(card.getTitle().equals(title)){
                toolCardwithEffect=card;
                break;
            }
        }
        return toolCardwithEffect;
    }


    public void useToolCard(String title, Player player)throws NotEnoughFavorTokensException {
        ToolCard toolCard=getToolCardFromTitle(title);
        player.useToolCard(toolCard);   //decrementa il numero di favor tokens o lancia eccezione se non sono abbastanza
    }

private ToolCard getToolCardFromTitle(String title){
        ToolCard toolCard=null;
        for(ToolCard card:drawnToolCards){
            if(card.getTitle().equals(title)){
                toolCard=card;
                break;
            }
        }
        return toolCard;
}

    private void notifyOfToolCardUsage(Player playerRequestingAction){
        playerRequestingAction.getSocketClientHandler().notifyUsedToolCard=true;
        playerRequestingAction.getSocketClientHandler().updateTokens=true;
        for(Player player : players){       //notifico i client della modifica alla draftpool
            player.getSocketClientHandler().updateDice=true;
            player.getSocketClientHandler().updateToolCards=true;
            player.getSocketClientHandler().updateWindowBoards=true;
            player.getSocketClientHandler().updateRoundTrack=true;
        }
        notifyAllSocketClientHandlers();
    }

    public void useGrozingPliers(Dice dice, String command, Player playerRequestingAction){
        ToolCard grozingPliers=getToolCardFromTitle("Grozing Pliers");
        Dice localDice=new Dice(dice.getValue(),dice.getColor());
        int index;
        for(index=0;index<drawnDice.size();index++){
            if(drawnDice.get(index).equals(dice)) break;
        }
        System.err.println("usegrozingpliers debug output");
        System.out.println("Dado ricevuto dal client: color: "+dice.getColor()+" value: "+dice.getValue());
        System.out.println("Dado trovato nella draftpool: color: "+drawnDice.get(index).getColor()+" value: "+drawnDice.get(index).getValue());
    if (command.equals("UP") && dice.getValue()<6) {
            System.out.println("Incremento di 1");
            drawnDice.get(index).setValue(drawnDice.get(index).getValue() + 1);
            localDice.setValue(localDice.getValue() + 1);
    }
    else if (command.equals("DOWN") && dice.getValue()>1) {
            System.out.println("deceremento di 1");
            drawnDice.get(index).setValue(drawnDice.get(index).getValue()-1);
            localDice.setValue(localDice.getValue()-1);
    }
        grozingPliers.setFirstUsage(true);
        System.out.println("Dado aggiornato nella draftpool: color:"+drawnDice.get(index).getColor()+" value: "+drawnDice.get(index).getValue());
        System.out.println("Dado locale aggiornato: color: "+localDice.getColor()+" value: "+localDice.getValue());
    for(Player player : players){       //notifico i client della modifica alla draftpool
        player.getSocketClientHandler().updateDice=true;
        player.getSocketClientHandler().updateToolCards=true;
    }
        System.out.println("Command received: "+command);
        playerRequestingAction.getSocketClientHandler().notifyUsedToolCard=true;
        playerRequestingAction.getSocketClientHandler().updateTokens=true;
        notifyAllSocketClientHandlers();
}

    public void useGrindingStone(Dice dice, Player playerRequestingAction){
        int index=drawnDice.indexOf(dice);
       /* for(index=0;index<drawnDice.size();index++){        //assegna ad index la posizione del dado nell'arrayList
            if(drawnDice.get(index).equals(dice)) break;
        }*/
        drawnDice.get(index).setValue(7-dice.getValue());
        getToolCardFromTitle("Grinding Stone").setFirstUsage(true);
        playerRequestingAction.getSocketClientHandler().notifyUsedToolCard=true;
        playerRequestingAction.getSocketClientHandler().updateTokens=true;
        for(Player player : players){       //notifico i client della modifica alla draftpool
            player.getSocketClientHandler().updateDice=true;
            player.getSocketClientHandler().updateToolCards=true;
        }
        notifyAllSocketClientHandlers();
    }



    public void useFluxBrush(Dice dice, Player playerRequestingAction){
        int index=drawnDice.indexOf(dice);
        SplittableRandom splittableRandom=new SplittableRandom();
        drawnDice.get(index).setValue(splittableRandom.nextInt(1,7));   //al solito il 7 è escluso dall'intervallo
        getToolCardFromTitle("Flux Brush").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
    }

    public void useGlazingHammer(Player playerRequestingAction){
        SplittableRandom splittableRandom=new SplittableRandom();
        for (Dice dice : drawnDice){
            dice.setValue(splittableRandom.nextInt(1,7));   //il 7 è escluso
        }
        getToolCardFromTitle("Glazing Hammer").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
    }

    public Dice useFluxRemover(Dice dice, Player playerRequestingAction){
        try {
            diceBag.reintroduceDice(dice);
            int index=drawnDice.indexOf(dice);
            drawnDice.get(index).setValue(0);
            drawnDice.get(index).setColor(0);
        } catch (IllegalDiceException e) {
            System.err.println("Flux Brush is trying to insert in dice bag an illegal dice! dice value: "+dice.getValue()+" dice color: "+dice.getColor());
        }
        getToolCardFromTitle("Flux Remover").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
        return diceBag.diceDraw();
    }

    public void substituteDice(Dice dice, Player playerRequestingAction){
        for(Dice diceInDraftPool : drawnDice){
            if(diceInDraftPool.getColor()==0 && diceInDraftPool.getValue()==0){
                diceInDraftPool.setColor(dice.getColor());
                diceInDraftPool.setValue(dice.getValue());
                break;
            }
        }
        for (Player player : players){
            player.getSocketClientHandler().updateDice=true;
        }
        playerRequestingAction.getSocketClientHandler().notifyUsedToolCard=true;
        notifyAllSocketClientHandlers();
    }

    public void useEglomiseBrush(int oldRow, int oldColumn, int newRow, int newColumn, Player playerRequestingAction) throws PlaceDiceException {
        WindowBoard playerWindowBoard=playerRequestingAction.getChoosenWindowBoard();
        Dice diceToMove=playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).getDiceContained();
        playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
        playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).setUsed(false);    //indico che la cella ora è libera
        diceToMove.setColorBreaker(true);   //indico che il dado può violare le limitazioni sul colore
        playerWindowBoard.insertDice(newRow,newColumn,diceToMove);
        getToolCardFromTitle("Eglomise Brush").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
        System.err.println("useEglomiseBrush eseguita con i segenti valori oldrow:"+oldRow+" oldColumn: "+oldColumn+" newRow: "+newRow+" newColumn: "+newColumn);
    }

    public void useCopperFoilBurnisher(int oldRow, int oldColumn, int newRow, int newColumn, Player playerRequestingAction) throws PlaceDiceException{
        WindowBoard playerWindowBoard=playerRequestingAction.getChoosenWindowBoard();
        Dice diceToMove=playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).getDiceContained();
        playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
        playerWindowBoard.getUsedMatrix().get(oldRow-1).get(oldColumn-1).setUsed(false);    //indico che la cella ora è libera
        diceToMove.setNumberBreaker(true);   //indico che il dado può violare le limitazioni sul valore
        playerWindowBoard.insertDice(newRow,newColumn,diceToMove);
        getToolCardFromTitle("Copper Foil Burnisher").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
    }

    public void useCorkBackedStraightEdge(Dice dice, int row, int column, Player playerRequestingAction) throws IllegalDiceException, PlaceDiceException {
        if (removeDice(dice)) {
            WindowBoard playerWindowBoard = playerRequestingAction.getChoosenWindowBoard();
            dice.setAdjacencyBreaker(true);   //indico che il dado può violare le limitazioni sulla diagonale
            playerWindowBoard.insertDice(row, column, dice);
            getToolCardFromTitle("Cork-backed Straightedge").setFirstUsage(true);
            notifyOfToolCardUsage(playerRequestingAction);
        }
        else throw new IllegalDiceException();
    }

    public void useLathekin(int oldRow1, int oldColumn1, int newRow1, int newColumn1, int oldRow2, int oldColumn2, int newRow2, int newColumn2, Player playerRequestingAction) throws PlaceDiceException {
        WindowBoard playerWindowBoard = playerRequestingAction.getChoosenWindowBoard();

        Dice diceToMove1=playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).getDiceContained();
        Dice diceToMove2=playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).getDiceContained();

        playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
        playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).setUsed(false);    //indico che la cella ora è libera
        playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
        playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).setUsed(false);    //indico che la cella ora è libera

        playerWindowBoard.insertDice(newRow1, newColumn1, diceToMove1); //Inserisco i due dadi
        playerWindowBoard.insertDice(newRow2, newColumn2, diceToMove2);

        getToolCardFromTitle("Lathekin").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
    }

    public void useLensCutter(Dice roundTrackDice, Dice draftpoolDice, Player playerRequestingAction) throws IllegalDiceException {
      Dice dice=RoundTrack.getInstance().swapDice(draftpoolDice,roundTrackDice);
        drawnDice.remove(draftpoolDice);
        drawnDice.add(dice);
        RoundTrack.getInstance().addRemainedDice(RoundTrack.getInstance().getRoundNumber(),draftpoolDice);
        getToolCardFromTitle("Lens Cutter").setFirstUsage(true);
        notifyOfToolCardUsage(playerRequestingAction);
    }

    public void useTapWheel(int color, int oldRow1, int oldColumn1, int newRow1, int newColumn1, int oldRow2, int oldColumn2, int newRow2, int newColumn2, Player playerRequestingAction) throws PlaceDiceException, IllegalDiceException {
        WindowBoard playerWindowBoard = playerRequestingAction.getChoosenWindowBoard();

        Dice diceToMove1=playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).getDiceContained();
        Dice diceToMove2=playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).getDiceContained();

        if(color==diceToMove1.getColor() && diceToMove1.getColor()==diceToMove2.getColor()){
            playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
            playerWindowBoard.getUsedMatrix().get(oldRow1-1).get(oldColumn1-1).setUsed(false);    //indico che la cella ora è libera
            playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).setDiceContained(null);    //rimuovo il dado dalla vecchia posizione
            playerWindowBoard.getUsedMatrix().get(oldRow2-1).get(oldColumn2-1).setUsed(false);    //indico che la cella ora è libera

            playerWindowBoard.insertDice(newRow1, newColumn1, diceToMove1); //Inserisco i due dadi
            playerWindowBoard.insertDice(newRow2, newColumn2, diceToMove2);

            getToolCardFromTitle("Tap Wheel").setFirstUsage(true);
            notifyOfToolCardUsage(playerRequestingAction);
        }
        else throw new IllegalDiceException("The two dice must have the same color choosen from the round track!");

    }

    private void removePlayerFromMirrorArrayList(Player player){    //fa saltare il turno successivo al giocatore
        mirrorArray.removeAll(Collections.singleton(player));
    }
}

