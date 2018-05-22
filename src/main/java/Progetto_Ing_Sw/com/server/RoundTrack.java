package Progetto_Ing_Sw.com.server;

import java.util.ArrayList;

public class RoundTrack implements RoundTrackObserver {   //Implementata come singleton
    private static RoundTrack ourInstance = new RoundTrack();
    private int roundNumber;    //Il primo turno è il numero 0 per avere corrispondenza tra turni ed indici di diceRemained
    private ArrayList<ArrayList<Dice>> diceRemained;    //ciascun indice contiene un ArrayList con dentro i dadi avanzati in quel turno

    public static RoundTrack getInstance() {
        return ourInstance;
    }

    private RoundTrack() {
        roundNumber=0;  //Il primo turno dev'essere il numero 0 per avere equivalenza con gli indici, vedi sopra
        diceRemained=new ArrayList<ArrayList<Dice>>();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<Dice> getDiceRemained(int roundNumber){    //restituisce una copia dell'ArrayList contenete i dadi avanzati al turno numero roundNumber
        ArrayList<Dice> diceArrayList=new ArrayList<>();        //non viene restituito un riferimento all'oggetto originale per evitare modifiche all'esterno di questa classe

        for(Dice dice : diceRemained.get(roundNumber)){
            diceArrayList.add(dice);
        }

        return diceArrayList;
    }

    public void addRemainedDice(int roundNumber, Dice dice){
        diceRemained.get(roundNumber).add(dice);
    }

    public void incrementRound(){
        if(roundNumber<10) roundNumber++;   //Ci sono al massimo 10 round, mitiga l'effetto di eventuali bug
    }
}
